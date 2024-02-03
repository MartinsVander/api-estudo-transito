package com.algaworks.algatransito.domain.repository;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Não tem necessidade de adicionar o @Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    //Nome = nome na entidade proprietario - Containing = busca por palavras imcompletas

    List<Proprietario> findByNomeContaining(String nome);

    Optional<Proprietario> findByEmail(String email);

}
