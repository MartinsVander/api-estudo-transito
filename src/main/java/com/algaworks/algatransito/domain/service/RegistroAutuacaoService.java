package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.AutuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroAutuacaoService {

    @Autowired
    private  RegistroVeiculoService registroVeiculoService;

    @Autowired
    private AutuacaoRepository autuacaoRepository;

    @Transactional // spring framework
    public Autuacao registrar(Long veiculoID, Autuacao novaAutuacao) {
        Veiculo veiculo = registroVeiculoService.findVeiculoById(veiculoID);
        novaAutuacao.setDataOcorrencia(LocalDateTime.now());
        novaAutuacao.setVeiculo(veiculo);
        return autuacaoRepository.save(novaAutuacao);
    }

    public List<Autuacao> buscarPorVeiculo(Long veiculoID) {
        Veiculo veiculo = registroVeiculoService.findVeiculoById(veiculoID);
        return autuacaoRepository.findByVeiculo(veiculo);
    }
}
