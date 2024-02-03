package com.algaworks.algatransito.domain.repository;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutuacaoRepository extends JpaRepository<Autuacao, Long> {

    List<Autuacao> findByVeiculo(Veiculo veiculo);

}
