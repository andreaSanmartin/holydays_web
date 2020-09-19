package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.ReservarAlojamiento;

@Repository
public interface ReservarAloRepository extends JpaRepository<ReservarAlojamiento, Long> {
    
}
