
package app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.model.Alojamiento;

/**@author Christian Mendieta */

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento, Long>{
    
}
