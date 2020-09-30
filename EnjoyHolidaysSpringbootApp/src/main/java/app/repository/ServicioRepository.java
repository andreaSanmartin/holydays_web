
package app.repository;

import app.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** @author Christian Mendieta*/

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{
    
    
}
