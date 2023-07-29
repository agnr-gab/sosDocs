package br.com.sosDocs.sosDocs.service;

import br.com.sosDocs.sosDocs.entity.Patrimonio;
import br.com.sosDocs.sosDocs.repository.MarcaRepository;
import br.com.sosDocs.sosDocs.repository.PatrimonioRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatrimonioService {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public List<Patrimonio> buscarPatrimonios() {
        return patrimonioRepository.findAll();
    }

    public Patrimonio buscarPatrimonioPorId(Long id) {
        return patrimonioRepository.findByPatrimonioId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Id do patrimônio informado: %d não localizado", id)));
    }

    public Patrimonio criarPatrimonio(Patrimonio patrimonio) {
        validarMarcaId(patrimonio);
        patrimonio.setNumeroTombo(null);
        return patrimonioRepository.save(patrimonio);
    }

    public void atualizarPatrimonio(Patrimonio patrimonioUpdate) {
        try {
            Patrimonio patrimonio = buscarPatrimonioPorId(patrimonioUpdate.getPatrimonioId());
            patrimonioUpdate.setNumeroTombo(patrimonio.getNumeroTombo());
//            if (!patrimonio.getMarcaId().equals(patrimonioUpdate.getMarcaId())) {
//                validarMarcaId(patrimonioUpdate);
   //         }
            patrimonioRepository.save(patrimonioUpdate);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro inesperado ao atualizar o patrimonio");
        }
    }

    public void deletarPatrimonio(Long id) {
        if (!patrimonioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Id do patrimônio informado: %d não localizado", id));
        }
        patrimonioRepository.deleteById(id);
    }

    private void validarMarcaId(Patrimonio patrimonio) {
//        if (!marcaRepository.existsById(patrimonio.getMarcaId())) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    String.format("Id da marca informado: %d não localizado",
//                            patrimonio.getMarcaId()));
//        }
    }

    private void validarPatrimonio(Patrimonio patrimonio, String numeroTombo) {
        if (!numeroTombo.equals(patrimonio.getNumeroTombo())) {
            patrimonio.setNumeroTombo(numeroTombo);
        }

    }


}
