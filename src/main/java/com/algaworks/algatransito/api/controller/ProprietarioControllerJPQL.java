package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProprietarioControllerJPQL {

   @PersistenceContext // indicando que nesta variavel sera fornecido instancia (injetar)
    private EntityManager manager; //interface hibernate implementa

    @GetMapping("/proprietario")
    public List<Proprietario> listar(){
                                                            //createquery linguagem consultar objetos
       // TypedQuery<Proprietario> query = manager.createQuery("from Proprietario", Proprietario.class);
       //return query.getResultList();
        return manager.createQuery("from Proprietario", Proprietario.class).getResultList();

    }
    @GetMapping("/teste")
    public String test(){
        return "oi";
    }


}
