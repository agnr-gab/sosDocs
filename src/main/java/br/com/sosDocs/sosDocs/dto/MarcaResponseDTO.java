package br.com.sosDocs.sosDocs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaResponseDTO {

    private String nome;

    private List<PatrimonioResponseDTO> patrimonios;
}
