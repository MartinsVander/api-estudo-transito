package com.algaworks.algatransito.domain.model.Dto;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class VeiculoDTO implements Serializable {

    private String marca;

    private String modelo;

    private String placa;

    private StatusVeiculo status;

    private LocalDate dataCadastro;

    private LocalDateTime dataApreensao;

    private Proprietario proprietario;

    private List<Autuacao> autuacoes;
}
