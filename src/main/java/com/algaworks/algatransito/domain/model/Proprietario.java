package com.algaworks.algatransito.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Builder
@Table(name = "proprietario")
public class Proprietario  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 60)
    @Email
    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @JsonManagedReference
    @OneToMany(mappedBy = "proprietario", fetch = FetchType.LAZY)
    private List<Veiculo> veiculos;
}
