package com.algaworks.algatransito.Dto;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutuacaoDto {


    private Long id;

    private String descricao;
    private BigDecimal valorMulta;
    private LocalDateTime dataOcorrencia;

    public AutuacaoDto() {
    }

    public AutuacaoDto(Long id, String descricao, BigDecimal valorMulta, LocalDateTime dataOcorrencia) {
        this.id = id;
        this.descricao = descricao;
        this.valorMulta = valorMulta;
        this.dataOcorrencia = dataOcorrencia;
    }

    public Autuacao transformaParaObjeto(){
        return new Autuacao(id,descricao,valorMulta,dataOcorrencia);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
