package app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Provincia;
import app.model.Response;
import app.repository.IProvincia;

@Service
public class ProvinciaService {
    @Autowired
    private IProvincia provinciaServicio;

    public Response listarprovinciaes() {
        return new Response().setTransaccion(true)
                .setPayload(provinciaServicio.findAll())
                .build();
    }

    public Response crearprovincia(Provincia provincia) {
        Response response = new Response();

        Optional<Provincia> provinciaBd = provinciaServicio.findById(provincia.getId());

        if (provinciaBd != null) {
            return response
                    .setTransaccion(false)
                    .setMessage("YA HAY UNA PROVINCIA CON ESE ID REGISTRADA").build();
        }

        return response.setTransaccion(true)
                .setMessage("SE HA CREADO LA PROVINCIA CORRECTAMENTE.")
                .setPayload(provinciaServicio.save(provincia));
    }

    public Response findById(int id) {
        Response response = new Response();
        Provincia provincia = provinciaServicio.findById(id);

        if (provincia != null) {
            return response.setTransaccion(true).setPayload(provincia).setMessage("SE HA ENCONTRADO UNA PROVINCIA");
        }
        return response
                .setTransaccion(false)
                .setPayload(null)
                .setMessage("NO SE HA ENCONTRADO UNA PROVINCIA");

    }

    public Response deleteById(int id) {
        try {
            Provincia provincia = provinciaServicio.findById(id);
            provinciaServicio.save(provincia);
            return new Response()
                    .setMessage("SE HA ELIMINADO CORRECTAMENTE")
                    .setTransaccion(true)
                    .build();
        } catch (Exception exception) {

        }

        return new Response()
                .setMessage("HA OCURRIDO UN PROBLEMA AL ELIMINAR LA provincia CON ID: " + id)
                .setTransaccion(false)
                .build();
    }


    public Response updateById(int id, Provincia provincia) {
        Provincia provinciaBd = provinciaServicio.findById(id);
        Response response = new Response();
        if (provinciaBd == null) {
            return response
                    .setTransaccion(false)
                    .setMessage("NO SE HA ENCONTRADO UNA PROVINCIA CON ID: " + id)
                    .build();
        }

        provinciaServicio.delete(provinciaBd);
        Provincia newprovincia = provinciaServicio.save(provincia);

        return response.setTransaccion(true)
                .setPayload(newprovincia)
                .build();

    }
}
