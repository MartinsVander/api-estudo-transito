package com.algaworks.algatransito.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class Veiculo {
     //validando em cascata id proprietario vai ser validado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne  //muitos para um
    //@JoinColumn(name = "proprietario_id") //nome da coluna que faz esse relacionamento
    @NotNull // not null porque é uma entidade

    private Proprietario proprietario;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotBlank
    // validar formato de placa xxx0000 ou xxx0x00 @Pattern
    // expressao regular para validar texto regexp
  //  @Pattern(regexp = "[A-z]{3}[0-9]{4}")
    @Pattern(regexp ="[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")

    private String placa;
    // anotação jakson
    //(access = JsonProperty.Access.READ_ONLY propiedadde apenas leitura na hora do registro
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING) // especifica tipo de dado que vai armazenar mysql
    private StatusVeiculo status;
    @JsonProperty (access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastro;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataApreensao;
    //OffsetDateTime demonstra horario local em relação ao tcu
    // (mappedBy = "veiculo' ): propiedade da classe Autuaçao que  liga
    // (cascade =CascadeType.ALL) toda auteração aqui sera realizada tambem no banco de dados
    // cascade = CascadeType.ALL
    @OneToMany(mappedBy = "veiculo")
    @JsonIgnore
    private List<Autuacao> autuacoes = new ArrayList<>();

    //metodo adicionar autuaçao para clase Service Autuaçao

    public Autuacao adicionarAutuacao(Autuacao autuacao) {

        autuacao.setDataOcorrencia(LocalDateTime.now());
        autuacao.setVeiculo(this); //proprio veiculo da instancia corrente
        getAutuacoes().add(autuacao);
        return autuacao;
    }



    public List<Autuacao> getAutuacoes() {
        return autuacoes;
    }

    public void setAutuacoes(List<Autuacao> autuacoes) {
        this.autuacoes = autuacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public OffsetDateTime getDataApreensao() {
        return dataApreensao;
    }

    public void setDataApreensao(OffsetDateTime dataApreensao) {
        this.dataApreensao = dataApreensao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return id.equals(veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
