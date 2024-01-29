package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroProprietarioService {

    private ProprietarioRepository proprietarioRepository;

    public RegistroProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }
    @Transactional // se algo der errado vai ser descartado e voltar ao estado anterior
    public Proprietario salvar(Proprietario proprietario){
        //tratamento para verificar emails duplicado
        //filter para filtrar

        boolean emailUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p1 -> !p1.equals(proprietario))
                .isPresent();

        if (emailUso) {
            throw new NegocioException("Email ja é cadastrado");
        }

        return proprietarioRepository.save(proprietario);

    }
    @Transactional
    public void excluir(Long id){

     proprietarioRepository.deleteById(id);

    }

}
