package com.algaworks.algatransito.controller;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.service.AutuacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autuacao")

public class AutuacaoController {

    @Autowired
    private AutuacaoService autuacaoService;

    @GetMapping
    public ResponseEntity<List<Autuacao>> buscarTodasAutuacoes() {
        return new ResponseEntity<>(autuacaoService.buscarTodasAutuacoes(), HttpStatus.OK);
    }

    @GetMapping(path = "/{autuacaoId}")
    public ResponseEntity<Autuacao> buscarAutuacaoPorId(@PathVariable Long autuacaoId) {
        return new ResponseEntity<>(autuacaoService.buscarAutuacaoPorId(autuacaoId), HttpStatus.OK);
    }

    @GetMapping(path = "/veiculo/{veiculoId}")
    public ResponseEntity<List<Autuacao>> buscarAutuacoesPorVeiculo(@PathVariable Long veiculoId) {
        return new ResponseEntity<>(autuacaoService.buscarAutuacoesPorVeiculo(veiculoId), HttpStatus.OK);
    }

    @PostMapping(path = "veiculo/{veiculoId}")
    public ResponseEntity<Autuacao> registrarAutuacao(@PathVariable Long veiculoId, @Valid @RequestBody Autuacao autuacao) {
        return new ResponseEntity<>(autuacaoService.registrarAutuacao(veiculoId, autuacao), HttpStatus.CREATED);
    }

}
