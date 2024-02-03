package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "proprietario")
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank //validação validation
    @Size(max = 60)
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 60)
    @Email
    @Column(name = "nome", length = 60, nullable = false)
    private String email;

    @Column(name = "telefone") // caso o nome da coluna esteja diferente no banco
    private String telefone;

    @OneToMany(mappedBy = "proprietario", fetch = FetchType.LAZY)
    private List<Veiculo> veiculos;

}
