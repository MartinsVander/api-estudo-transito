package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository;
    private ProprietarioRepository proprietarioRepository;

    //metodo para buscar veiculo para service autuacao

    public Veiculo buscarV(Long veiculoID){

        return veiculoRepository.findById(veiculoID).orElseThrow(() -> new NegocioException("Veiculo bão encontrado"));
    }

    public RegistroVeiculoService(VeiculoRepository veiculoRepository, ProprietarioRepository proprietarioRepository) {
        this.veiculoRepository = veiculoRepository;
        this.proprietarioRepository = proprietarioRepository;
    }

    @Transactional
    public Veiculo cadastrar(Veiculo veiculoNovo){
        //verifica se o id ja foi utilizado
        if(veiculoNovo.getId() != null){
            throw new NegocioException("Veiculo a ser cadastrado não pode possuir um codigo id cadastrado  ");
        }
        // verifica se a placa esta em uso
        boolean placaEmUso = veiculoRepository.findByPlaca(veiculoNovo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(veiculoNovo))
                        .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Ja existe um veiculo cadastrado com essa placa");
        }
      // busca proprietario e verifica se ele existe
       Proprietario proprietario = proprietarioRepository.findById(veiculoNovo.getProprietario().getId())
                       .orElseThrow(() -> new NegocioException("Proprietario não encontrado"));
        veiculoNovo.setProprietario(proprietario);
        veiculoNovo.setStatus(StatusVeiculo.REGULAR);
                                   //  data hora atual
        veiculoNovo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(veiculoNovo);

    }
}
