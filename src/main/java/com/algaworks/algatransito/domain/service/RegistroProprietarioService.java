package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    public Proprietario buscarProprietarioPorId(Long proprietarioID) {
        return proprietarioRepository.findById(proprietarioID).orElseThrow(() -> new NegocioException(String.format("Proprietário não encontrado com o ID: %s", proprietarioID)));
    }

    @Transactional // se algo der errado vai ser descartado e voltar ao estado anterior
    public Proprietario saveProprietario(Proprietario proprietario) {

        //tratamento para verificar emails duplicado
        //filter para filtrar
        boolean emailUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();

        if (emailUso) {
            throw new NegocioException("Email ja é cadastrado");
        }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long id) {
        proprietarioRepository.deleteById(id);
    }

}
