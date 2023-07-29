package br.com.sosDocs.sosDocs.repository;

import br.com.sosDocs.sosDocs.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    //@Query("SELECT m FROM marcas m LEFT JOIN m.patrimonios p WHERE m.marcaId = p.marca")
    Marca findByMarcaId(Long marcaId);

    Boolean existsByNome(String nome);

}
