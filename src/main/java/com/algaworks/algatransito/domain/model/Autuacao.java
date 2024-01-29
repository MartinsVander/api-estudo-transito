package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Autuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne //muitas autuações associa a um veiculo
    private Veiculo veiculo;
    private String descricao;
    private BigDecimal valorMulta;
    private LocalDateTime dataOcorrencia;

    public Autuacao(Long id, String descricao, BigDecimal valorMulta, LocalDateTime dataOcorrencia) {
    }

    public Autuacao(Long id, Veiculo veiculo, String descricao, BigDecimal valorMulta, LocalDateTime dataOcorrencia) {
        this.id = id;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.valorMulta = valorMulta;
        this.dataOcorrencia = dataOcorrencia;
    }

    public Autuacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public LocalDateTime getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autuacao autuacao = (Autuacao) o;
        return Objects.equals(id, autuacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
