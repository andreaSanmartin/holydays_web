package app.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author Christian Mendieta*/

@AllArgsConstructor
@NoArgsConstructor
@Data
    public class HttpSimpleResponse {
    
    private int codigo;
    private String decripcion;
}
