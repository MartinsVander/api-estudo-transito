package com.algaworks.algatransito.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "veiculo")
public class Veiculo  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    @NotBlank
    @Column(name = "marca", length = 30, nullable = false)
    private String marca;

    @NotBlank
    @Column(name = "modelo", length = 30, nullable = false)
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")
    @Column(name = "placa", length = 7, nullable = false)
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private StatusVeiculo status;

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_apreensao")
    private LocalDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY)
    private List<Autuacao> autuacoes;
}
