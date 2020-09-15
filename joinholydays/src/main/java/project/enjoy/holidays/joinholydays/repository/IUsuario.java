
package project.enjoy.holidays.joinholydays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.enjoy.holidays.joinholydays.model.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Long> {

    @Query("select usuario from Usuario usuario where usuario.cedula = :cedula AND usuario.estado = true")
    Usuario findByCedula(String cedula);

}
