
package app.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author Christian Mendieta*/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HttpObjectResponse <T>{
    
    private int codigo;
    private String descripcion;
    private T respuesta;
}
