package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.repository.AutuacaoRepository;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/autuacao")

public class AutuacaoController {

    @Autowired
    private RegistroAutuacaoService registroAutuacaoService;

    @Autowired
    private AutuacaoRepository autuacaoRepository;

    @PostMapping(name = "veiculo/{veiculoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Autuacao registrar(@PathVariable Long veiculoId, @Valid @RequestBody Autuacao autuacao) {
        return registroAutuacaoService.registrar(veiculoId, autuacao);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Autuacao> listarTodas() {
        return autuacaoRepository.findAll();
    }

    @GetMapping(name = "/veiculo/{veiculoId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Autuacao> listarPorVeiculo(@PathVariable Long veiculoId) {
        return registroAutuacaoService.buscarPorVeiculo(veiculoId);
    }
}
