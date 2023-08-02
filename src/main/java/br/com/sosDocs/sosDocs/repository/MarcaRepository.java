package br.com.sosDocs.sosDocs.repository;

import br.com.sosDocs.sosDocs.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Optional<Marca> findByMarcaId(Long marcaId);

    Boolean existsByNome(String nome);

}
