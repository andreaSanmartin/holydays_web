
package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import app.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**@Query("select usuario from Usuario usuario where usuario.cedula = :cedula AND usuario.estado = true")
    Usuario findByCedula(String cedula);**/
    
    /**@author Christian Mendieta*/
    @Query("select usuario from Usuario usuario where usuario.correo = :correo AND usuario.estado = true")
    Usuario findByUsername(String correo);

}
