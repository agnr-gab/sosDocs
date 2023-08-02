package br.com.sosDocs.sosDocs.mapper;

import br.com.sosDocs.sosDocs.dto.MarcaRequestDTO;
import br.com.sosDocs.sosDocs.dto.MarcaResponseDTO;
import br.com.sosDocs.sosDocs.entity.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        PatrimonioMapper.class
})
public abstract class MarcaMapper {

    public static final MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "patrimonios", source = "patrimonios")
    public abstract Marca mapFromDTO(MarcaResponseDTO marcaResponseDTO);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "patrimonios", source = "patrimonios")
    public abstract MarcaResponseDTO mapFrom(Marca marca);

    @Mapping(target = "nome", source = "nome")
    public abstract Marca mapFromRequestDTO(MarcaRequestDTO marcaRequestDTO);

}
