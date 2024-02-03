package com.algaworks.algatransito.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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
@Table(name = "proprietario")
public class Veiculo {

    //validando em cascata id proprietario vai ser validado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  //muitos para um
    @JoinColumn(name = "proprietario_id") //nome da coluna que faz esse relacionamento
    @NotNull // not null porque é uma entidade
    private Proprietario proprietario;

    @NotBlank
    @Column(name = "marca", length = 30, nullable = false)
    private String marca;

    @NotBlank
    @Column(name = "modelo", length = 30, nullable = false)
    private String modelo;

    /*
    @Pattern
    Validar o formato da placa como xxx0000 ou xxx0x00 @Pattern
    Expressao Regular para validar texto regexp
    @Pattern(regexp = "[A-z]{3}[0-9]{4}")
    */
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")
    @Column(name = "placa", length = 7, nullable = false)
    private String placa;

    // anotação jakson
    //(access = JsonProperty.Access.READ_ONLY) propiedadde apenas leitura na hora do registro
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING) // especifica tipo de dado que vai armazenar mysql
    @Column(name = "status", length = 10)
    private StatusVeiculo status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataApreensao;

    //OffsetDateTime demonstra horario local em relação ao tcu
    // (mappedBy = "veiculo' ): propiedade da classe Autuaçao que  liga
    // (cascade =CascadeType.ALL) toda auteração aqui sera realizada tambem no banco de dados
    // cascade = CascadeType.ALL
    //@JsonIgnore
    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY)
    private List<Autuacao> autuacoes;

    //metodo adicionar autuaçao para clase Service Autuaçao
    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(LocalDateTime.now());
        autuacao.setVeiculo(this); //proprio veiculo da instancia corrente
        getAutuacoes().add(autuacao);
        return autuacao;
    }

}
