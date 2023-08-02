package br.com.sosDocs.sosDocs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatrimonioResponseDTO {

    @NotBlank(message = "nome é obrigatorio")
    private String nome;

    private String descricao;

    private String numeroTombo;

}
