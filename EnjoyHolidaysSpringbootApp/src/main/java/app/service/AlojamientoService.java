package app.service;

import app.http.HttpCode;
import app.http.HttpDescription;
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
                HttpCode.OK,
                HttpDescription.OK,
                alojamientoRepository.findAll()
        );
    }
    
    
    public HttpListResponse<Alojamiento> getAlojamientosDisponibles
        (Long ciudadId, int numHuespedes, String fecha_inicio, String fecha_fin){
        
        
        return null;
    }
    

    public HttpObjectResponse<Alojamiento> getAlojamientoById(Long id) {
        try {
            Alojamiento alojamiento = alojamientoRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCode.OK, HttpDescription.OK, alojamiento);
            
        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND,
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
                    HttpCode.CREATED,
                    HttpDescription.CREATED,
                    alojamiento);
        } else {
            return new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND,
                    null);
        }
    }

    public HttpSimpleResponse deleteAlojamiento(Long id) {
        if(alojamientoRepository.findById(id).isPresent()){
            alojamientoRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCode.OK, HttpDescription.OK);
        }else
            return new HttpSimpleResponse(HttpCode.RESOURCE_NOT_FOUND, 
                    HttpDescription.RESOURCE_NOT_FOUND);
    }

    
    public HttpObjectResponse<Alojamiento> updateAlojamiento(Alojamiento alojamiento) {
        Long id = alojamiento.getId();
        if(alojamientoRepository.findById(id).isPresent()){
            Usuario usuario = alojamientoRepository.findById(id).get().getUsuario();
            alojamiento.setUsuario(usuario);
            alojamientoRepository.saveAndFlush(alojamiento);
            return  new HttpObjectResponse<>(
                    HttpCode.OK, HttpDescription.OK, alojamiento);
        }else
            return  new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND, 
                    HttpDescription.RESOURCE_NOT_FOUND, null);
    }

}
