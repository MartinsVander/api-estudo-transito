package com.algaworks.algatransito.api.controller;


import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;

import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/proprietario") //mapeia as requisições
public class ProprietarioController {

    private RegistroProprietarioService registroProprietarioService;

    private ProprietarioRepository proprietarioRepository;

    public ProprietarioController(RegistroProprietarioService registroProprietarioService, ProprietarioRepository proprietarioRepository) {
        this.registroProprietarioService = registroProprietarioService;
        this.proprietarioRepository = proprietarioRepository;
    }

    @GetMapping("/findall")
    public List<Proprietario> findAll(){

    return proprietarioRepository.findAll();

    }

    @GetMapping("/{proprietarioID}")
    public ResponseEntity<Proprietario>  findById(@PathVariable Long proprietarioID ) {

         Optional<Proprietario> proprietarioBusca = proprietarioRepository.findById(proprietarioID);

         if (proprietarioBusca.isPresent()){

             return ResponseEntity.ok(proprietarioBusca.get());
         }

         return ResponseEntity.notFound().build();
    }

    //melhorando codigo busca por ID
    @GetMapping("/busca/{proprietarioID}")
    public  ResponseEntity<Proprietario> buscaMelhorada(@PathVariable Long proprietarioID){

        //findById retorna Optional
        // metodo map (Optional) espessap lambida ( ResponseEntity.ok(proprietario)) se iver algo dentro ele transforma em  ResponseEntity
      return proprietarioRepository.findById(proprietarioID).map(proprietario -> ResponseEntity.ok(proprietario)).orElse(ResponseEntity.notFound().build());



    }
    //adicinando novo proprietario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // status 201 de criado @validar proprietario j k validation
    public Proprietario buscaP( @Valid @RequestBody Proprietario proprietario) {
        return registroProprietarioService.salvar(proprietario);
       // return proprietarioRepository.save(proprietario);

    }
    //atualizando proprietario

    @PutMapping("/{proprietarioID}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioID, @Valid @RequestBody Proprietario proprietario) {

        if (!proprietarioRepository.existsById(proprietarioID)){

            return ResponseEntity.notFound().build();

        }


       proprietario.setId(proprietarioID);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);


        return ResponseEntity.ok(proprietarioAtualizado);

    }

    //delete
    @DeleteMapping("{proprietarioID}")
    //void pois nao retorna
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioID){

        if (!proprietarioRepository.existsById(proprietarioID)){
            return ResponseEntity.notFound().build();
        }
       registroProprietarioService.excluir(proprietarioID);
        //proprietarioRepository.deleteById(proprietarioID);
        return ResponseEntity.noContent().build();

    }
    //captura exceções do controlador que não foram capturadas





}





