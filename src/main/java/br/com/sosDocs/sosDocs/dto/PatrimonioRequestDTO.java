package br.com.sosDocs.sosDocs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrimonioRequestDTO {

    @NotBlank(message = "nome é obrigatorio")
    private String nome;

    private String descricao;

    @NotNull(message = "marca id é obrigatorio")
    private Long marcaId;

}
