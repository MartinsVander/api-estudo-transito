package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroAutuacaoService {


    private  RegistroVeiculoService registroVeiculoService;

    public RegistroAutuacaoService(RegistroVeiculoService registroVeiculoService) {
        this.registroVeiculoService = registroVeiculoService;
    }

    @Transactional // spring framework
    public Autuacao registrar(Long veiculoID, Autuacao novaAutuacao) {
        //buscarV foi metodo criado na classe VeiculoService
        Veiculo veiculo = registroVeiculoService.buscarV(veiculoID);
        return veiculo.adicionarAutuacao(novaAutuacao);
        //metodo na classe Veiculo para add autuacao

    }


}
