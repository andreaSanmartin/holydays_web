
package app.http;

/**@author Christian Mendieta*/
public class HttpDescriptionResponse {
    
    public static final String CREATED = "¡Recurso creado exitosamente!";
    public static final String OK = "¡Solicitud porcesada con exito!";
    public static final String INTERNAL_SERVER_ERROR = "¡Ha ocurrido un error en el servidor!";
    public static final String CLIENT_ERROR_SYNTAX = "¡Error!, el servidor no puede procesar su solicitud: solicitud malformada, sintaxis errónea, etc.";
    
    public static String entidadNoEncontrada(String entidad){
        return "¡Entidad: "+entidad+" no registrada en la BD!";
    }
}
