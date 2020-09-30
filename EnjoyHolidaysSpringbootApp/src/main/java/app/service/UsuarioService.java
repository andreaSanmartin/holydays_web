package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.model.Response;
import app.model.Usuario;
import app.repository.UsuarioRepository;
import app.util.SimpleDate;

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

        Usuario usuarioBd = usuarioServicio.findByUsername(usuario.getCorreo());

        if (usuarioBd != null) {
            return response
                    .setTransaccion(false)
                    .setMessage("YA HAY UN USUARIO CON ESE CORREO REGISTRADO").build();
        }else if(SimpleDate.isLegalAge(usuario.getFechaNacimiento()))
            return response.setTransaccion(true)
                .setMessage("SE HA CREADO EL USUARIO CORRECTAMENTE.")
                .setPayload(usuarioServicio.save(usuario));
        else
            return response
                    .setTransaccion(false)
                    .setMessage("!NO SE PUEDE REGISTRAR MENORES DE EDAD!").build();
    }

    public Response findByCorreo(String correo) {
        Response response = new Response();
        Usuario usuario = usuarioServicio.findByUsername(correo);

        if (usuario != null) {
            return response.setTransaccion(true).setPayload(usuario).setMessage("SE HA ENCONTRADO UN USUARIO");
        }
        return response
                .setTransaccion(false)
                .setPayload(null)
                .setMessage("NO SE HA ENCONTRADO UN USUARIO");

    }

    public Response deleteByCorreo(String correo) {
        try {
            Usuario usuario = usuarioServicio.findByUsername(correo);
            usuario.setEstado(false);
            usuarioServicio.save(usuario);
            return new Response()
                    .setMessage("SE HA ELIMINADO CORRECTAMENTE")
                    .setTransaccion(true)
                    .build();
        } catch (Exception exception) {

        }

        return new Response()
                .setMessage("HA OCURRIDO UN PROBLEMA AL ELIMINAR AL USUARIO CON EL CORREO: " + correo)
                .setTransaccion(false)
                .build();
    }


    public Response updateByCorreo(String correo, Usuario usuario) {
        Usuario usuarioBd = usuarioServicio.findByUsername(correo);
        Response response = new Response();
        if (usuarioBd == null) {
            return response
                    .setTransaccion(false)
                    .setMessage("NO SE HA ENCONTRADO UN USUARIO CON EL CORREO " + correo)
                    .build();
        }

        usuarioServicio.delete(usuarioBd);
        Usuario newUsuario = usuarioServicio.save(usuario);

        return response.setTransaccion(true)
                .setPayload(newUsuario)
                .build();

    }


}
