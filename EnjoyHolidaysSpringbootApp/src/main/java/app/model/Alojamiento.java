
package app.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author Christian Mendieta**/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Alojamientos")
public class Alojamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alj_id")
    private Long id;
    
    @Column(name = "alj_descripcion")
    private String descripcion;
    
    @Column(name = "alj_ubicacion")
    private String ubicacion;
    
    @Column(name = "alj_condiciones_uso")
    private String condicionesUso;
    
    @Column(name = "alj_servicios")
    private String servicios;
    
    @Column(name = "alj_num_huespedes")
    private int numeroHuespedes;
    
    @Column(name = "alj_num_camas")
    private int numeroCamas;
    
    @Column(name = "alj_num_baños")
    private int numeroBaños;
    
    @Column(name = "alj_num_habitaciones")
    private int numeroHabitaciones;
    
    @Column(name = "alj_precio_noche")
    private double precioPorNoche;
    
    @Column(name = "alj_disponibilidad")
    private boolean disponible;
    
    
    /**RELACIONES ENTRE ENTIDADES --------------------------------------------*/
    @ManyToOne
    @JoinColumn(name = "usu_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ciu_id")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "tip_id")
    private TipoVivienda tVivienda;
}
