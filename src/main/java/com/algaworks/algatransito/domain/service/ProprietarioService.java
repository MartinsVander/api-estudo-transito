package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    public List<Proprietario> buscarTodosProprietarios() {
        return proprietarioRepository.findAll();
    }

    public Proprietario buscarProprietarioPorId(Long proprietarioID) {
        return proprietarioRepository.findById(proprietarioID).orElseThrow(
                () -> new NegocioException(String.format("Proprietário não encontrado com o ID: %s.", proprietarioID)));
    }

    public Proprietario savarProprietario(Proprietario proprietario) {

        if (isEmailEmUso(proprietario))
            throw new NegocioException("Email já em uso em outro proprietário");

        return proprietarioRepository.save(proprietario);
    }

    public Proprietario atualizarProprietario(Proprietario proprietario, Long proprietarioID) {

        if (proprietarioID == null)
            throw new NegocioException("Para atualizar o prorpietário, é obrigatório informar o ID do mesmo.");

        if (isEmailEmUso(proprietario))
            throw new NegocioException("Email em uso em outro proprietário.");

        Proprietario entity = buscarProprietarioPorId(proprietarioID);

        entity.setNome(proprietario.getNome());
        entity.setTelefone(proprietario.getTelefone());
        entity.setEmail(proprietario.getEmail());
        entity.setVeiculos(proprietario.getVeiculos());
        return proprietarioRepository.save(entity);
    }

    public void excluirProprietario(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }

    private boolean isEmailEmUso(Proprietario proprietario) {
        return proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();
    }
}
