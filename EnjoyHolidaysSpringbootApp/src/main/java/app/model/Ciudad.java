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


    ///RELACION DE ENTIDADES
    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Provincia provincia;
}
