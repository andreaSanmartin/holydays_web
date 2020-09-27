package app.repository;

import app.model.ServicioAlojamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**@author Christian Mendieta*/

@Repository
public interface ServicioAlojamientoRepository extends JpaRepository<ServicioAlojamiento, Long>{
    
}
