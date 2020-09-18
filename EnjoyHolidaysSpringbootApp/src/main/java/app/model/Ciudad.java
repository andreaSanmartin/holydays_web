
package app.model;

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

}
