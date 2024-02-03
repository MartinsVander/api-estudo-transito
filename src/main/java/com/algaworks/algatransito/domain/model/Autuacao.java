package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "autuacao")
public class Autuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    Muitas autuações associada a um veículo
    */
    @ManyToOne
    @JoinColumn(name = "autuacao_id")
    private Veiculo veiculo;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "valor_multa", precision = 8, scale = 2)
    private BigDecimal valorMulta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ocorrencia")
    private LocalDateTime dataOcorrencia;

    public Autuacao(Long id, String descricao, BigDecimal valorMulta, LocalDateTime dataOcorrencia) {
    }
}
