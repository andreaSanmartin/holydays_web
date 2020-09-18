package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.model.Response;
import app.model.Usuario;
import app.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioServicio;

    public Response listarUsuarios() {
        return new Response().setTransaccion(true)
                .setPayload(usuarioServicio.findAll())
                .build();
    }

    public Response crearUsuario(Usuario usuario) {
        Response response = new Response();

        Usuario usuarioBd = usuarioServicio.findByCedula(usuario.getCedula());

        if (usuarioBd != null) {
            return response
                    .setTransaccion(false)
                    .setMessage("YA HAY UN USUARIO CON ESA CEDULA REGISTRADO").build();
        }

        return response.setTransaccion(true)
                .setMessage("SE HA CREADO EL USUARIO CORRECTAMENTE.")
                .setPayload(usuarioServicio.save(usuario));
    }

    public Response findByCedula(String cedula) {
        Response response = new Response();
        Usuario usuario = usuarioServicio.findByCedula(cedula);

        if (usuario != null) {
            return response.setTransaccion(true).setPayload(usuario).setMessage("SE HA ENCONTRADO UN USUARIO");
        }
        return response
                .setTransaccion(false)
                .setPayload(null)
                .setMessage("NO SE HA ENCONTRADO UN USUARIO");

    }

    public Response deleteByCedula(String cedula) {
        try {
            Usuario usuario = usuarioServicio.findByCedula(cedula);
            usuario.setEstado(false);
            usuarioServicio.save(usuario);
            return new Response()
                    .setMessage("SE HA ELIMINADO CORRECTAMENTE")
                    .setTransaccion(true)
                    .build();
        } catch (Exception exception) {

        }

        return new Response()
                .setMessage("HA OCURRIDO UN PROBLEMA AL ELIMINAR AL USUARIO CON LA CEDULA: " + cedula)
                .setTransaccion(false)
                .build();
    }


    public Response updateByCedula(String cedula, Usuario usuario) {
        Usuario usuarioBd = usuarioServicio.findByCedula(cedula);
        Response response = new Response();
        if (usuarioBd == null) {
            return response
                    .setTransaccion(false)
                    .setMessage("NO SE HA ENCONTRADO UN USUARIO CON LA CEDULA: " + cedula)
                    .build();
        }

        usuarioServicio.delete(usuarioBd);
        Usuario newUsuario = usuarioServicio.save(usuario);

        return response.setTransaccion(true)
                .setPayload(newUsuario)
                .build();

    }


}
