package br.com.sosDocs.sosDocs.dto;

import br.com.sosDocs.sosDocs.entity.Patrimonio;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaDTO {

    private String nome;

    private List<PatrimonioDTO> patrimonios;
}
