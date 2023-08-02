package br.com.sosDocs.sosDocs.service;

import br.com.sosDocs.sosDocs.dto.PatrimonioRequestDTO;
import br.com.sosDocs.sosDocs.dto.PatrimonioResponseDTO;
import br.com.sosDocs.sosDocs.entity.Marca;
import br.com.sosDocs.sosDocs.entity.Patrimonio;
import br.com.sosDocs.sosDocs.mapper.PatrimonioMapper;
import br.com.sosDocs.sosDocs.repository.MarcaRepository;
import br.com.sosDocs.sosDocs.repository.PatrimonioRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatrimonioService {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public List<PatrimonioResponseDTO> buscarPatrimonios() {
        List<Patrimonio> all = patrimonioRepository.findAll();
        List<PatrimonioResponseDTO> patrimonioResponseDTOS = new ArrayList<>();
        all.forEach(patrimonio -> patrimonioResponseDTOS.add(PatrimonioMapper.INSTANCE.mapFrom(patrimonio)));
        return patrimonioResponseDTOS;
    }

    public PatrimonioResponseDTO buscarPatrimonioPorId(Long id) {
        Optional<Patrimonio> patrimonio = Optional.ofNullable(patrimonioRepository.findByPatrimonioId(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Id do patrimônio informado: %d não localizado", id))));

        return PatrimonioMapper.INSTANCE.mapFrom(patrimonio.get());
    }

    public PatrimonioResponseDTO criarPatrimonio(PatrimonioRequestDTO patrimonio) {
        validarMarcaId(patrimonio);
        Patrimonio patrimonio1 = PatrimonioMapper.INSTANCE.mapFrom(patrimonio);
        criarNumeroTombo(patrimonio1);

        Patrimonio save = patrimonioRepository.save(patrimonio1);
        vincularMarcaPatrimonio(patrimonio, save);
        return PatrimonioMapper.INSTANCE.mapFrom(save);
    }

    public void atualizarPatrimonio(PatrimonioRequestDTO patrimonioUpdate, Long id) {
        try {
            validarMarcaId(patrimonioUpdate);
            Patrimonio patrimonio1 = PatrimonioMapper.INSTANCE.mapFrom(patrimonioUpdate);
            patrimonio1.setPatrimonioId(id);
            patrimonioRepository.save(patrimonio1);
            vincularMarcaPatrimonio(patrimonioUpdate, patrimonio1);
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

    public void criarNumeroTombo(Patrimonio patrimonio) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Long numeroTombo = zonedDateTime.toInstant().toEpochMilli();
        patrimonio.setNumeroTombo(numeroTombo.toString());
    }

    private void validarMarcaId(PatrimonioRequestDTO patrimonio) {
        if (!marcaRepository.existsById(patrimonio.getMarcaId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Id da marca informado: %d não localizado",
                            patrimonio.getMarcaId()));
        }
    }

    private void validarPatrimonio(Patrimonio patrimonio, String numeroTombo) {
        if (!numeroTombo.equals(patrimonio.getNumeroTombo())) {
            patrimonio.setNumeroTombo(numeroTombo);
        }

    }

    private void vincularMarcaPatrimonio(PatrimonioRequestDTO patrimonio, Patrimonio save) {
        if (marcaRepository.existsById(patrimonio.getMarcaId())) {
            Optional<Marca> marca = marcaRepository.findByMarcaId(patrimonio.getMarcaId());

            List<Patrimonio> patrimonios = marca.get().getPatrimonios();
            if (!patrimonios.contains(save)) {
                patrimonios.add(save);
                marca.get().setPatrimonios(patrimonios);
                marcaRepository.save(marca.get());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Id da marca informado: %d não localizado", patrimonio.getMarcaId()));
        }
    }

}
