package br.com.sosDocs.sosDocs.service;

import br.com.sosDocs.sosDocs.dto.MarcaDTO;
import br.com.sosDocs.sosDocs.entity.Marca;
import br.com.sosDocs.sosDocs.entity.Patrimonio;
import br.com.sosDocs.sosDocs.exception.NomeExistenteException;
//import br.com.sosDocs.sosDocs.mapper.MarcaMapper;
import br.com.sosDocs.sosDocs.mapper.MarcaMapper;
import br.com.sosDocs.sosDocs.repository.MarcaRepository;
import br.com.sosDocs.sosDocs.repository.PatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

//    @Autowired
//    private PatrimonioRepository patrimonioRepository;

    public List<Marca> buscarMarcas() {
        return marcaRepository.findAll();
    }

    public Marca buscarMarcaPorId(Long id) {
        Marca marca = marcaRepository.findByMarcaId(id);
                //.orElseThrow(() ->
              //  new ResponseStatusException(HttpStatus.NOT_FOUND,
                 //       String.format("Id da marca informado: %d não localizado", id)));
        return marca;
    }

    public List<Patrimonio> buscarPatrimoniosPorMarcaId(Long marcaId) {
        try {
            Marca marca = buscarMarcaPorId(marcaId);
            return marca.getPatrimonios();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    (String.format("Erro inesperado ao resgatar os patrimonios da marca: %d",
                            marcaId)));
        }
    }

    public Marca criarMarca(Marca marca) {
        marca.setMarcaId(null);
        if (marcaRepository.existsByNome(marca.getNome())) {
            throw new NomeExistenteException(String.format("Nome da marca informada: %s já existe",
                    marca.getNome()));
        }
        return marcaRepository.save(marca);
    }

    public void atualizarMarca(Marca marcaUpdate) {
        try {
            Marca marca = buscarMarcaPorId(marcaUpdate.getMarcaId());
            if (!marcaUpdate.getNome().equals(marca.getNome()) &&
                    marcaRepository.existsByNome(marcaUpdate.getNome())) {
                throw new NomeExistenteException
                        (String.format("Nome da marca informada: %s já existe",
                                marcaUpdate.getNome()));
            }
            marcaRepository.save(marcaUpdate);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (NomeExistenteException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro inesperado ao atualizar o patrimonio");
        }
    }

    public void deletarMarca(Long id) {//TODO não apagar uma marca se tiver um patrimonio vinculado
        if (!marcaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Id do patrimônio informado: %d não localizado", id));
        }
        marcaRepository.deleteById(id);
    }

    public Marca converterDoDTO(MarcaDTO marcaDTO) {
        Marca marca = MarcaMapper.INSTANCE.mapFromDTO(marcaDTO);
        return marca;
    }

    public MarcaDTO converterParaDTO(Marca marca) {
        MarcaDTO marcaDTO = MarcaMapper.INSTANCE.mapFrom(marca);
        return marcaDTO;
    }
}
