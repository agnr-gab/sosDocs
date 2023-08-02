package br.com.sosDocs.sosDocs.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaRequestDTO {

    @NotNull(message = "o nome é obrigatório")
    private String nome;


}
