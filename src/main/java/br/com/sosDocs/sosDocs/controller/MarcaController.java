package br.com.sosDocs.sosDocs.controller;

import br.com.sosDocs.sosDocs.dto.MarcaDTO;
import br.com.sosDocs.sosDocs.entity.Marca;
import br.com.sosDocs.sosDocs.entity.Patrimonio;
import br.com.sosDocs.sosDocs.exception.NomeExistenteException;
import br.com.sosDocs.sosDocs.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping("/marcas")
    public ResponseEntity criarMarca(@RequestBody Marca marca) {
        try {
            Marca marcaResponse = marcaService.criarMarca(marca);
            return ResponseEntity.ok(marcaResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/marcas")
    public ResponseEntity<List<Marca>> buscarTodasMarcas() {
        return ResponseEntity.ok(marcaService.buscarMarcas());
    }

    @GetMapping("/marcas/{id}")
    public ResponseEntity<MarcaDTO> buscarMarcaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(marcaService.converterParaDTO(marcaService.buscarMarcaPorId(id)));
    }

    @GetMapping("/marcas/{id}/patrimonios")
    public ResponseEntity buscarPatrimonioPorMarcaId(@PathVariable Long id) {
        try {
            List<Patrimonio> patrimonios = marcaService.buscarPatrimoniosPorMarcaId(id);
            return ResponseEntity.ok(patrimonios);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado ao listar patrimonios de uma marca");
        }
    }

    @PutMapping("/marca/{id}")
    public ResponseEntity atualizarMarca(@RequestBody Marca marcaAtualizada) {
        try {
            marcaService.atualizarMarca(marcaAtualizada);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (NomeExistenteException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado ao atualizar uma marca");
        }
    }

    @DeleteMapping("/marca/{id}")
    public ResponseEntity deletarMarca(@PathVariable Long id) {
        try {
            marcaService.deletarMarca(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado ao deletar uma marca");
        }
    }
}
