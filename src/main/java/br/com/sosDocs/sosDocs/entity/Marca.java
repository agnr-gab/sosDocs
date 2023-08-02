package br.com.sosDocs.sosDocs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marcas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "marca_id", nullable = false)
    private Long marcaId;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "nome é obrigatorio")
    private String nome;

    @OneToMany
    @JoinColumn(name = "marca_id")
    private List<Patrimonio> patrimonios = new ArrayList<>();
}
