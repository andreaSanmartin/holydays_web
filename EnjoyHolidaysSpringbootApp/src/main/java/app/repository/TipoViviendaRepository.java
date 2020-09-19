package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.TipoVivienda;

@Repository
public interface TipoViviendaRepository extends JpaRepository<TipoVivienda, Long> {

}
