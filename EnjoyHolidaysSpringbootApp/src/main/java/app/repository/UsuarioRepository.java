
package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import app.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**@author Christian Mendieta*/
    @Query("select usuario from Usuario usuario where usuario.correo = :correo AND usuario.estado = true")
    Usuario findByUsername(String correo);

    @Query("select usuario from Usuario usuario where usuario.correo = :correo AND usuario.password = :password AND usuario.estado = true")
    Usuario usulogin(String correo, String password);

}
