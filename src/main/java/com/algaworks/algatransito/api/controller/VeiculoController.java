package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/veiculo")
@RestController
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private  RegistroVeiculoService registroVeiculoService;

    @Autowired
    private RegistroAutuacaoService registroAutuacaoService;

    @GetMapping
    public List<Veiculo> listar(){
        return  veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscaId(@PathVariable Long veiculoId){
        return veiculoRepository.findById(veiculoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo){
        return registroVeiculoService.saveVeiculo(veiculo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autuacao registrar(@PathVariable Long veiculoId, @Valid @RequestBody Autuacao autuacaoImput) {
        return registroAutuacaoService.registrar(veiculoId, autuacaoImput);
    }
}
