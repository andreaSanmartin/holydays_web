/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.Response;
import app.repository.ICiudad;
import app.model.Ciudad;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadService {
    @Autowired
    private ICiudad ciudadServicio;

    public Response listarCiudades() {
        return new Response().setTransaccion(true)
                .setPayload(ciudadServicio.findAll())
                .build();
    }

    public Response crearCiudad(Ciudad ciudad) {
        Response response = new Response();

        Optional<Ciudad> ciudadBd = ciudadServicio.findById(ciudad.getId());

        if (ciudadBd != null) {
            return response
                    .setTransaccion(false)
                    .setMessage("YA HAY UNA CIUDAD CON ESE ID REGISTRADA").build();
        }

        return response.setTransaccion(true)
                .setMessage("SE HA CREADO LA CIUDAD CORRECTAMENTE.")
                .setPayload(ciudadServicio.save(ciudad));
    }

    public Response findById(int id) {
        Response response = new Response();
        Ciudad ciudad = ciudadServicio.findById(id);

        if (ciudad != null) {
            return response.setTransaccion(true).setPayload(ciudad).setMessage("SE HA ENCONTRADO UNA CIUDAD");
        }
        return response
                .setTransaccion(false)
                .setPayload(null)
                .setMessage("NO SE HA ENCONTRADO UNA CIUDAD");

    }

    public Response deleteById(int id) {
        try {
            Ciudad ciudad = ciudadServicio.findById(id);
            ciudadServicio.save(ciudad);
            return new Response()
                    .setMessage("SE HA ELIMINADO CORRECTAMENTE")
                    .setTransaccion(true)
                    .build();
        } catch (Exception exception) {

        }

        return new Response()
                .setMessage("HA OCURRIDO UN PROBLEMA AL ELIMINAR LA CIUDAD CON ID: " + id)
                .setTransaccion(false)
                .build();
    }


    public Response updateById(int id, Ciudad ciudad) {
        Ciudad ciudadBd = ciudadServicio.findById(id);
        Response response = new Response();
        if (ciudadBd == null) {
            return response
                    .setTransaccion(false)
                    .setMessage("NO SE HA ENCONTRADO UNA CIUDAD CON ID: " + id)
                    .build();
        }

        ciudadServicio.delete(ciudadBd);
        Ciudad newCiudad = ciudadServicio.save(ciudad);

        return response.setTransaccion(true)
                .setPayload(newCiudad)
                .build();

    }
}
