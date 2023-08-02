package br.com.sosDocs.sosDocs.mapper;

import br.com.sosDocs.sosDocs.dto.PatrimonioRequestDTO;
import br.com.sosDocs.sosDocs.dto.PatrimonioResponseDTO;
import br.com.sosDocs.sosDocs.entity.Patrimonio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class PatrimonioMapper {
    public static final PatrimonioMapper INSTANCE = Mappers.getMapper(PatrimonioMapper.class);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    public abstract Patrimonio mapFrom(PatrimonioResponseDTO patrimonioDTO);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "numeroTombo", source = "numeroTombo")
    public abstract PatrimonioResponseDTO mapFrom(Patrimonio patrimonio);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    public abstract Patrimonio mapFrom(PatrimonioRequestDTO patrimonioDTO);

    public List<Patrimonio> mapFromDTO(List<PatrimonioResponseDTO> patrimoniosDTO) {
        List<Patrimonio> patrimonios = new ArrayList<>();
        patrimoniosDTO.forEach(patrimonioDTO -> patrimonios.add(mapFrom(patrimonioDTO)));

        return patrimonios;
    }

    public List<PatrimonioResponseDTO> mapFrom(List<Patrimonio> patrimonios) {
        List<PatrimonioResponseDTO> patrimoniosDTO = new ArrayList<>();
        patrimonios.forEach(patrimonio -> patrimoniosDTO.add(mapFrom(patrimonio)));

        return patrimoniosDTO;
    }

}
