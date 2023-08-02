package br.com.sosDocs.sosDocs.service;

import br.com.sosDocs.sosDocs.dto.MarcaRequestDTO;
import br.com.sosDocs.sosDocs.dto.MarcaResponseDTO;
import br.com.sosDocs.sosDocs.dto.PatrimonioResponseDTO;
import br.com.sosDocs.sosDocs.entity.Marca;
import br.com.sosDocs.sosDocs.exception.NomeExistenteException;
import br.com.sosDocs.sosDocs.mapper.MarcaMapper;
import br.com.sosDocs.sosDocs.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;


    public List<MarcaResponseDTO> buscarMarcas() {
        List<Marca> all = marcaRepository.findAll();
        List<MarcaResponseDTO> marcaResponseDTOS = new ArrayList<>();
        all.forEach(marca -> marcaResponseDTOS.add(MarcaMapper.INSTANCE.mapFrom(marca)));
        return marcaResponseDTOS;
    }

    public MarcaResponseDTO buscarMarcaPorId(Long id) {
        Optional<Marca> marca = Optional.ofNullable(marcaRepository.findByMarcaId(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Id da marca informado: %d não localizado", id))));
        return MarcaMapper.INSTANCE.mapFrom(marca.get());
    }

    public List<PatrimonioResponseDTO> buscarPatrimoniosPorMarcaId(Long marcaId) {
        try {
            MarcaResponseDTO marca = buscarMarcaPorId(marcaId);
            return marca.getPatrimonios();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    (String.format("Erro inesperado ao resgatar os patrimonios da marca: %d",
                            marcaId)));
        }
    }

    public MarcaResponseDTO criarMarca(MarcaRequestDTO marca) {
        if (marcaRepository.existsByNome(marca.getNome())) {
            throw new NomeExistenteException(String.format("Nome da marca informada: %s já existe",
                    marca.getNome()));
        }
        Marca save = marcaRepository.save(MarcaMapper.INSTANCE.mapFromRequestDTO(marca));
        return MarcaMapper.INSTANCE.mapFrom(save);
    }

    public void atualizarMarca(MarcaRequestDTO marcaUpdate, Long marcaId) {
        try {
            MarcaResponseDTO marca = buscarMarcaPorId(marcaId);
            if (!marcaUpdate.getNome().equals(marca.getNome()) &&
                    marcaRepository.existsByNome(marcaUpdate.getNome())) {
                throw new NomeExistenteException
                        (String.format("Nome da marca informada: %s já existe",
                                marcaUpdate.getNome()));
            }
            Marca marca1 = MarcaMapper.INSTANCE.mapFromRequestDTO(marcaUpdate);
            marca1.setMarcaId(marcaId);
            marcaRepository.save(marca1);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (NomeExistenteException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro inesperado ao atualizar o patrimonio");
        }
    }

    public void deletarMarca(Long id) {
        if (!marcaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Id do patrimônio informado: %d não localizado", id));
        }
        marcaRepository.deleteById(id);
    }

    public Marca converterDoDTO(MarcaResponseDTO marcaResponseDTO) {
        Marca marca = MarcaMapper.INSTANCE.mapFromDTO(marcaResponseDTO);
        return marca;
    }

    public MarcaResponseDTO converterParaDTO(Marca marca) {
        MarcaResponseDTO marcaResponseDTO = MarcaMapper.INSTANCE.mapFrom(marca);
        return marcaResponseDTO;
    }
}
