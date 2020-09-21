
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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "imagenes_alojamientos")
public class ImagenAlojamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "img_id")
    private Long id;
    
    @Column(name = "img_nombre")
    private String nombre;
    
    @Column(name = "img_url")
    private String imagenUrl;
    
    @Column(name = "img_cloudinary_id")
    private String cloudinaryImgId;
    
    /**RELACIONES ENTRE ENTIDADES --------------------------------------------*/
    @ManyToOne
    @JoinColumn(name = "alj_id")
    private Alojamiento alojamiento;
}
