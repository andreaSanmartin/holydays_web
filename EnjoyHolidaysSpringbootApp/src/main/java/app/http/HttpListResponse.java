package app.http;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author Christian Mendieta*/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HttpListResponse <T>{
    
    private int codigo;
    private String descripcion;
    private List<T> respuesta;
}
