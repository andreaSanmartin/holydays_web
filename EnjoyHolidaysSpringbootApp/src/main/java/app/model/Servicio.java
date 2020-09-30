package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Christian Mendieta*/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Servicios")
public class Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serv_id")
    private Long id;
    
    @Column(name = "serv_decripcion")
    private String descripcion;
}
