package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;

import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proprietario") //mapeia as requisições
public class ProprietarioController {

    @Autowired
    private RegistroProprietarioService registroProprietarioService;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> findAll() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioID}")
    public ResponseEntity<Proprietario> buscarProprietarioPorId(@PathVariable Long proprietarioID) {
        return proprietarioRepository.findById(proprietarioID).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proprietario> salvarProprietario(@Valid @RequestBody Proprietario proprietario) {
        return new ResponseEntity<>(registroProprietarioService.saveProprietario(proprietario), HttpStatus.CREATED);
    }

    @PutMapping("/{proprietarioID}")
    public ResponseEntity<Proprietario> atualizarProprietario(@PathVariable Long proprietarioID, @Valid @RequestBody Proprietario proprietario) {

        if (!proprietarioRepository.existsById(proprietarioID)) {

            return ResponseEntity.notFound().build();

        }


        proprietario.setId(proprietarioID);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);


        return ResponseEntity.ok(proprietarioAtualizado);

    }

    //delete
    @DeleteMapping("{proprietarioID}")
    //void pois nao retorna
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioID) {

        if (!proprietarioRepository.existsById(proprietarioID)) {
            return ResponseEntity.notFound().build();
        }
        registroProprietarioService.excluir(proprietarioID);
        //proprietarioRepository.deleteById(proprietarioID);
        return ResponseEntity.noContent().build();

    }
    //captura exceções do controlador que não foram capturadas


}





