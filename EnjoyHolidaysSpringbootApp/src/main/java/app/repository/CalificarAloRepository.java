package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.CalificarAlojamiento;

@Repository
public interface CalificarAloRepository extends JpaRepository<CalificarAlojamiento, Long>{
    
}
