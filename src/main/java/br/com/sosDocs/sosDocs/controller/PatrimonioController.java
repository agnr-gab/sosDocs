package br.com.sosDocs.sosDocs.controller;

import br.com.sosDocs.sosDocs.dto.PatrimonioRequestDTO;
import br.com.sosDocs.sosDocs.dto.PatrimonioResponseDTO;
import br.com.sosDocs.sosDocs.service.PatrimonioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PatrimonioController {

    @Autowired
    private PatrimonioService patrimonioService;

    @PostMapping("/patrimonios")
    public ResponseEntity criarPatrimonio(@RequestBody @Valid PatrimonioRequestDTO patrimonio) {
        try {
            return ResponseEntity.ok(patrimonioService.criarPatrimonio(patrimonio));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado ao criar um patrimonio");
        }
    }

    @GetMapping("/patrimonios")
    public ResponseEntity<List<PatrimonioResponseDTO>> buscarTodosPatrimonios() {
        return ResponseEntity.ok(patrimonioService.buscarPatrimonios());
    }

    @GetMapping("/patrimonios/{id}")
    public ResponseEntity buscarPatrimonioPorId(@PathVariable Long id) {
        try {
            PatrimonioResponseDTO patrimonio = patrimonioService.buscarPatrimonioPorId(id);
            return ResponseEntity.ok(patrimonio);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado ao listar um patrimonio");
        }
    }

    @PutMapping("/patrimonios/{id}")
    public ResponseEntity atualizarPatrimonio(@RequestBody PatrimonioRequestDTO patrimonioAtualizado,
                                              @PathVariable Long id) {
        try {
            patrimonioService.atualizarPatrimonio(patrimonioAtualizado, id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado ao atualizar um patrimonio");
        }
    }

    @DeleteMapping("/patrimonios/{id}")
    public ResponseEntity deletarPatrimonio(@PathVariable Long id) {
        try {
            patrimonioService.deletarPatrimonio(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado ao deletar um patrimonio");
        }
    }

}
