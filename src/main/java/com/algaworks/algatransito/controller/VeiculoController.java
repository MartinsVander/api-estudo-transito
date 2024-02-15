package com.algaworks.algatransito.controller;

import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.service.VeiculoService;
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
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> buscarTodosVeiculos() {
        return new ResponseEntity<>(veiculoService.buscarTodosVeiculos(), HttpStatus.OK);
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@PathVariable Long veiculoId) {
        return new ResponseEntity<>(veiculoService.buscarVeiculoPorId(veiculoId), HttpStatus.OK);
    }

    @PostMapping("/{priprietarioId}")
    public ResponseEntity<Veiculo> cadastrarVeiculo(@Valid @RequestBody Veiculo veiculo, @PathVariable Long priprietarioId) {
        return new ResponseEntity<>(veiculoService.cadastrarVeiculo(veiculo, priprietarioId), HttpStatus.CREATED);
    }

    @PutMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> alterarVeiculo(@Valid @RequestBody Veiculo veiculo, @PathVariable Long veiculoId) {
        return new ResponseEntity<>(veiculoService.alterarVeiculo(veiculo, veiculoId), HttpStatus.OK);
    }

    @PutMapping("/{veiculoId}/proprietario/{proprietarioId}")
    public ResponseEntity<Veiculo> alterarProprietario(@PathVariable Long veiculoId, @PathVariable Long proprietarioId) {
        return new ResponseEntity<>(veiculoService.alterarProprietario(veiculoId, proprietarioId), HttpStatus.OK);
    }
}
