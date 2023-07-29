package br.com.sosDocs.sosDocs.mapper;

import br.com.sosDocs.sosDocs.dto.MarcaDTO;
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
    public abstract Marca mapFromDTO(MarcaDTO marcaDTO);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "patrimonios", source = "patrimonios")
    public abstract MarcaDTO mapFrom(Marca marca);

}
