package app.service;

import app.http.HttpCodeResponse;
import app.http.HttpDescriptionResponse;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.http.HttpSimpleResponse;
import app.repository.AlojamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Alojamiento;
import app.model.Usuario;
import app.repository.UsuarioRepository;
import java.util.NoSuchElementException;

/**@author Christian Mendieta*/

@Service
public class AlojamientoService {

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public HttpListResponse<Alojamiento> getAlojamientos() {
        return new HttpListResponse<>(
                HttpCodeResponse.OK,
                HttpDescriptionResponse.OK,
                alojamientoRepository.findAll()
        );
    }

    public HttpObjectResponse<Alojamiento> getAlojamientoById(Long id) {
        try {
            Alojamiento alojamiento = alojamientoRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCodeResponse.OK, HttpDescriptionResponse.OK, alojamiento);
            
        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("Alojamiento"),
                    null
            );
        }
    }

    public HttpObjectResponse<Alojamiento> createAlojamiento(String username, Alojamiento alojamiento) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            alojamiento.setUsuario(usuario);
            alojamientoRepository.save(alojamiento);
            return new HttpObjectResponse<>(
                    HttpCodeResponse.CREATED,
                    HttpDescriptionResponse.CREATED,
                    alojamiento);
        } else {
            return new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("usuario"),
                    null);
        }
    }

    public HttpSimpleResponse deleteAlojamiento(Long id) {
        if(alojamientoRepository.findById(id).isPresent()){
            alojamientoRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCodeResponse.OK, HttpDescriptionResponse.OK);
        }else
            return new HttpSimpleResponse(HttpCodeResponse.ENTIDAD_NO_ENCONTRADA, 
                    HttpDescriptionResponse.entidadNoEncontrada("Alojamiento"));
    }

    
    public HttpObjectResponse<Alojamiento> updateAlojamiento(Alojamiento alojamiento) {
        Long id = alojamiento.getId();
        if(alojamientoRepository.findById(id).isPresent()){
            Usuario usuario = alojamientoRepository.findById(id).get().getUsuario();
            alojamiento.setUsuario(usuario);
            alojamientoRepository.saveAndFlush(alojamiento);
            return  new HttpObjectResponse<>(
                    HttpCodeResponse.OK, HttpDescriptionResponse.OK, alojamiento);
        }else
            return  new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA, 
                    HttpDescriptionResponse.entidadNoEncontrada("Alojamiento"), null);
    }

}
