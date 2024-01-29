package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/veiculo")
@RestController
public class VeiculoController {

    private VeiculoRepository veiculoRepository;

    private  RegistroVeiculoService registroVeiculoService;

    public VeiculoController(VeiculoRepository veiculoRepository, RegistroVeiculoService registroVeiculoService) {
        this.veiculoRepository = veiculoRepository;
        this.registroVeiculoService = registroVeiculoService;
    }

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
    public Veiculo cadastrar (@Valid @RequestBody Veiculo veiculo){

        return registroVeiculoService.cadastrar(veiculo);

    }



}
