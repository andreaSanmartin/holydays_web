package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.ReservarAlojamiento;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ReservarAloRepository extends JpaRepository<ReservarAlojamiento, Long> {
    
    /*
     *@Author Christian Mendieta
     */
    @Query("select r from ReservarAlojamiento r where r.alojamiento.id = :idAloj")
    List<ReservarAlojamiento> getReservasByAlojamiento(Long idAloj);
}
