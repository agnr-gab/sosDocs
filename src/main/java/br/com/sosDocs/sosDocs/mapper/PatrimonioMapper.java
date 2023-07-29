package br.com.sosDocs.sosDocs.mapper;

import br.com.sosDocs.sosDocs.dto.PatrimonioDTO;
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
    public abstract Patrimonio mapFrom(PatrimonioDTO patrimonioDTO);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    public abstract PatrimonioDTO mapFrom(Patrimonio patrimonio);

    public List<Patrimonio> mapFromDTO(List<PatrimonioDTO> patrimoniosDTO) {
        List<Patrimonio> patrimonios = new ArrayList<>();
        patrimoniosDTO.forEach(patrimonioDTO -> patrimonios.add(mapFrom(patrimonioDTO)));

        return patrimonios;
    }

    public List<PatrimonioDTO> mapFrom(List<Patrimonio> patrimonios) {
        List<PatrimonioDTO> patrimoniosDTO = new ArrayList<>();
        patrimonios.forEach(patrimonio -> patrimoniosDTO.add(mapFrom(patrimonio)));

        return patrimoniosDTO;
    }

}
