package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ProprietarioService proprietarioService;

    public List<Veiculo> buscarTodosVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarVeiculoPorId(Long veiculoID) {
        return veiculoRepository.findById(veiculoID).orElseThrow(() -> new NegocioException("Veículo não encontrado!"));
    }

    public Veiculo cadastrarVeiculo(Veiculo veiculo, Long proprietarioId) {

        if (proprietarioId == null)
            throw new NegocioException("É obrigatório informar um proprietário para cadastrar o veículo");

        if (isPlacaEmUso(veiculo))
            throw new NegocioException("Já existe um veículo cadastrado com a placa informada.");

        Proprietario proprietario = proprietarioService.buscarProprietarioPorId(proprietarioId);

        veiculo.setProprietario(proprietario);
        veiculo.setStatus(StatusVeiculo.REGULAR);
        veiculo.setDataCadastro(LocalDate.now());
        return veiculoRepository.save(veiculo);
    }

    public Veiculo alterarVeiculo(Veiculo veiculo, Long veiculoId) {

        if (veiculoId == null)
            throw new NegocioException("É obrigatório informar o ID do veículo para alterar");

        if (isPlacaEmUso(veiculo))
            throw new NegocioException("Já existe um veículo cadastrado com a placa informada.");

        Veiculo entity = buscarVeiculoPorId(veiculoId);

        entity.setStatus(veiculo.getStatus());
        entity.setMarca(veiculo.getMarca());
        entity.setModelo(veiculo.getModelo());
        entity.setPlaca(veiculo.getPlaca());
        entity.setAutuacoes(veiculo.getAutuacoes());
        entity.setProprietario(veiculo.getProprietario());

        return veiculoRepository.save(veiculo);
    }

    public Veiculo alterarProprietario(Long veiculoId, Long proprietarioId) {

        if (veiculoId == null)
            throw new NegocioException("É obrigatório informar o ID do veículo para alterar");

        if (proprietarioId == null)
            throw new NegocioException("É obrigatório informar o ID do proproetário para alterar");

        Veiculo veiculo = buscarVeiculoPorId(veiculoId);
        Proprietario proprietario = proprietarioService.buscarProprietarioPorId(proprietarioId);

        veiculo.setProprietario(proprietario);
        return veiculoRepository.save(veiculo);
    }

    private boolean isPlacaEmUso(Veiculo veiculo) {
        return veiculoRepository.findByPlaca(veiculo.getPlaca())
                .filter(v -> !Objects.equals(v, veiculo))
                .isPresent();
    }
}
