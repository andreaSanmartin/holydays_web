package app.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.http.HttpCode;
import app.http.HttpDescription;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.http.HttpSimpleResponse;
import app.model.Alojamiento;
import app.model.CalificarAlojamiento;
import app.model.Usuario;
import app.repository.AlojamientoRepository;
import app.repository.CalificarAloRepository;
import app.repository.UsuarioRepository;

@Service
public class CalificarAloService {
    @Autowired
    private CalificarAloRepository calificarAloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    public HttpListResponse<CalificarAlojamiento> getCalAlojamientos() {
        return new HttpListResponse<>(HttpCode.OK, HttpDescription.OK,
                calificarAloRepository.findAll());
    }

    public HttpObjectResponse<CalificarAlojamiento> getlCalificarAloById(final Long id) {
        try {
            final CalificarAlojamiento calificarAlojamiento = calificarAloRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCode.OK, HttpDescription.OK, calificarAlojamiento);

        } catch (final NoSuchElementException e) {
            return new HttpObjectResponse<>(HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND, null);
        }
    }


    public HttpObjectResponse<CalificarAlojamiento> createCalifaicarAlo(String correo_usu, Long id_alo, CalificarAlojamiento calificarAlojamiento) {
        final Usuario usuario = usuarioRepository.findByUsername(correo_usu);
        final Alojamiento alojamiento= alojamientoRepository.findById(id_alo).orElse(null);
        if (usuario == null ) {
            if(alojamiento == null){
                return new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND,
                    null);
           }
        } else {
            calificarAlojamiento.setUsuario(usuario);
            calificarAlojamiento.setAlojamiento(alojamiento);
            calificarAloRepository.save(calificarAlojamiento);
            return new HttpObjectResponse<>(
                    HttpCode.CREATED,
                    HttpDescription.CREATED,
                    calificarAlojamiento);
            
        }
        return null;
    }

    public HttpSimpleResponse deleteCalificarAlo(final Long id) {
        if(calificarAloRepository.findById(id).isPresent()){
            calificarAloRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCode.OK, HttpDescription.OK);
        }else
            return new HttpSimpleResponse(HttpCode.RESOURCE_NOT_FOUND, 
                    HttpDescription.RESOURCE_NOT_FOUND);
    }

    
    public HttpObjectResponse<CalificarAlojamiento> updateCalificarAlo(final CalificarAlojamiento calificarAlojamiento) {
        final Long id = calificarAlojamiento.getId();
        if(calificarAloRepository.findById(id).isPresent()){
            final Usuario usuario = alojamientoRepository.findById(id).get().getUsuario();
            final Alojamiento alojamiento = alojamientoRepository.findById(id).get();
            calificarAlojamiento.setUsuario(usuario);
            calificarAlojamiento.setAlojamiento(alojamiento);
            calificarAloRepository.saveAndFlush(calificarAlojamiento);
            return  new HttpObjectResponse<>(
                    HttpCode.OK, HttpDescription.OK, calificarAlojamiento);
        }else
            return  new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND, 
                    HttpDescription.RESOURCE_NOT_FOUND, null);
    }
}
