package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/veiculo/{veiculoId}/autuacoes")

public class AutuacaoController {

    private RegistroAutuacaoService  registroAutuacaoService;

    public AutuacaoController(RegistroAutuacaoService registroAutuacaoService) {
        this.registroAutuacaoService = registroAutuacaoService;
    }
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
    public Autuacao registrar(@PathVariable Long veiculoId, @Valid @RequestBody Autuacao autuacaoImput) {

      Autuacao autuacaoRegistrada =  registroAutuacaoService.registrar(veiculoId, autuacaoImput);

      return autuacaoRegistrada;

    }


}
