
package app.model;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.Objects;
=======
>>>>>>> 8d90c8ed82647cb1efffb005f2d856564fd968ff
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
<<<<<<< HEAD

@Entity
@Table(name = "ciudad")
public class Ciudad implements Serializable{
    @Id
    @Column(name = "ciu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ciu_nombre")
    private String ciudad;

    public Ciudad() {
    }

    public Ciudad(Long id, String ciudad) {
        this.id = id;
        this.ciudad = ciudad;
    }
    

    public Long getId() {
        return id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.ciudad);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ciudad other = (Ciudad) obj;
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "id=" + id + ", ciudad=" + ciudad + '}';
    }
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author Christian Mendieta**/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Ciudades")
public class Ciudad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ciu_id")
    private Long id;
    
    @Column(name = "ciu_nombre")
    private String nombre;
>>>>>>> 8d90c8ed82647cb1efffb005f2d856564fd968ff
}
