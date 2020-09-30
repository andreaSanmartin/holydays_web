package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author Christian Mendieta*/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Servicios_Alojamiento")
public class ServicioAlojamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serv_aloj_id")
    private Long Id;
    
    /**RELACIONES ENTRE ENTIDADES --------------------------------------------*/
    @ManyToOne
    @JoinColumn(name = "alj_id")
    private Alojamiento alojamiento;
    
    @ManyToOne
    @JoinColumn(name = "serv_id")
    private Servicio servicio;
}
