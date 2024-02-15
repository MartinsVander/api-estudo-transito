package com.algaworks.algatransito.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.service.ProprietarioService;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;

    @GetMapping
    public ResponseEntity<List<Proprietario>> buscarTodosProprietarios() {
        return new ResponseEntity<>(proprietarioService.buscarTodosProprietarios(), HttpStatus.OK);
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscarProprietarioPorId(@PathVariable Long proprietarioId) {
        return new ResponseEntity<>(proprietarioService.buscarProprietarioPorId(proprietarioId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Proprietario> salvarProprietario(@Valid @RequestBody Proprietario proprietario) {
        return new ResponseEntity<>(proprietarioService.savarProprietario(proprietario), HttpStatus.CREATED);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizarProprietario(@PathVariable Long proprietarioId,
                                                              @Valid @RequestBody Proprietario proprietario) {
        return new ResponseEntity<>(proprietarioService.atualizarProprietario(
                proprietario, proprietarioId), HttpStatus.CREATED);
    }

    @DeleteMapping("{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
        proprietarioService.excluirProprietario(proprietarioId);
        return ResponseEntity.noContent().build();
    }
}





