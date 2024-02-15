package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.AutuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AutuacaoService {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private AutuacaoRepository autuacaoRepository;

    public List<Autuacao> buscarTodasAutuacoes() {
        return autuacaoRepository.findAll();
    }

    public Autuacao buscarAutuacaoPorId(Long autuacaoID) {
        return autuacaoRepository.findById(autuacaoID).orElseThrow(
                () -> new NegocioException(String.format("Não foi encontrada uma autuação com o ID: %s", autuacaoID)));
    }

    public List<Autuacao> buscarAutuacoesPorVeiculo(Long veiculoID) {
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(veiculoID);
        return autuacaoRepository.findByVeiculo(veiculo);
    }

    public Autuacao registrarAutuacao(Long veiculoID, Autuacao novaAutuacao) {
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(veiculoID);
        novaAutuacao.setDataOcorrencia(LocalDateTime.now());
        novaAutuacao.setVeiculo(veiculo);
        return autuacaoRepository.save(novaAutuacao);
    }
}
