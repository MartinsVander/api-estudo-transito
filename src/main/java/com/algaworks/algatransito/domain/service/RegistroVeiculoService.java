package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class RegistroVeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    //metodo para buscar veiculo para service autuacao
    public Veiculo findVeiculoById(Long veiculoID){
        return veiculoRepository.findById(veiculoID).orElseThrow(() -> new NegocioException("Veículo não encontrado!"));
    }

    @Transactional
    public Veiculo saveVeiculo(Veiculo veiculoNovo){

        //verifica se o id ja foi utilizado
        if(veiculoNovo.getId() != null){
            throw new NegocioException("Veículo a ser cadastrado não pode possuir um código id cadastrado");
        }

        // verifica se a placa esta em uso
        boolean placaEmUso = veiculoRepository.findByPlaca(veiculoNovo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(veiculoNovo))
                        .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com essa placa");
        }

       // busca proprietario e verifica se ele existe
       Proprietario proprietario = proprietarioRepository.findById(veiculoNovo.getProprietario().getId())
                       .orElseThrow(() -> new NegocioException("Proprietário não encontrado"));

        veiculoNovo.setProprietario(proprietario);
        veiculoNovo.setStatus(StatusVeiculo.REGULAR);
        veiculoNovo.setDataCadastro(OffsetDateTime.now()); //  data hora atual

        return veiculoRepository.save(veiculoNovo);
    }
}
