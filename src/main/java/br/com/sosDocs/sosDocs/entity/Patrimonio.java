package br.com.sosDocs.sosDocs.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "patrimonios")
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String numeroTombo;


}
