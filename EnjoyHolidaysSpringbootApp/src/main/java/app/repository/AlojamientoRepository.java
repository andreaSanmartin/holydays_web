
package app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.model.Alojamiento;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**@author Christian Mendieta */

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento, Long>{
    
    @Query("select a from Alojamiento a where a.ciudad.nombre = :ciudad and a.numeroHuespedes = :numHuespedes")
    List<Alojamiento>getAlojamientoByCiudadAndHuepedes(String ciudad, int numHuespedes);

    @Query("select a from Alojamiento a where a.usuario.correo = :correo")
    List<Alojamiento> getAlojamientoByUsu(String correo);
}
