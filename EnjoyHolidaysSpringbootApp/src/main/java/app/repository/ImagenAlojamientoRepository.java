package app.repository;

import app.model.ImagenAlojamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**@author Christian Mendieta*/

@Repository
public interface ImagenAlojamientoRepository extends JpaRepository<ImagenAlojamiento, Long>{
    
    @Query("select img from ImagenAlojamiento img where img.alojamiento.id = :id AND img.alojamiento.disponible = true")
    List<ImagenAlojamiento> getImgsAlojByAlojId(Long id);
}
