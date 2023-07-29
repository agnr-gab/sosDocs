package br.com.sosDocs.sosDocs.repository;

import br.com.sosDocs.sosDocs.entity.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {

    Optional<Patrimonio> findByPatrimonioId(Long patrimonioId);

    //Optional<List<Patrimonio>> findByMarcaId(Long marcaId);
}
