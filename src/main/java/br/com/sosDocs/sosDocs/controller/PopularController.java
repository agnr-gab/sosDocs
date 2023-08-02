package br.com.sosDocs.sosDocs.controller;

import br.com.sosDocs.sosDocs.service.PopularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PopularController {

    @Autowired
    private PopularService popularService;

    @GetMapping("/popular")
    public ResponseEntity popularBanco() {
        popularService.popularBanco();
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
