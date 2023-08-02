package br.com.sosDocs.sosDocs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "patrimonios",
        uniqueConstraints = {@UniqueConstraint(columnNames = "numeroTombo")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patrimonio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patrimonioId;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "nome é obrigatorio")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "numeroTombo")
    private String numeroTombo;


}
