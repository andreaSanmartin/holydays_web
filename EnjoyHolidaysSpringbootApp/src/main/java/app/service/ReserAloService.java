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
import app.model.ReservarAlojamiento;
import app.model.Usuario;
import app.repository.AlojamientoRepository;
import app.repository.ReservarAloRepository;
import app.repository.UsuarioRepository;

@Service
public class ReserAloService {

    @Autowired
    private ReservarAloRepository reservarAloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    public HttpListResponse<ReservarAlojamiento> getReserAlojamientos() {
        return new HttpListResponse<>(HttpCode.OK, HttpDescription.OK,
                reservarAloRepository.findAll());
    }

    public HttpObjectResponse<ReservarAlojamiento> getlReservarAloById(final Long id) {
        try {
            final ReservarAlojamiento reservarAlojamiento = reservarAloRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCode.OK, HttpDescription.OK, reservarAlojamiento);

        } catch (final NoSuchElementException e) {
            return new HttpObjectResponse<>(HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND, null);
        }
    }


    public HttpObjectResponse<ReservarAlojamiento> createaReservarAlo(final String correo_usu, final Long id_alo, final ReservarAlojamiento reservarAlojamiento) {
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
            reservarAlojamiento.setUsuario(usuario);
            reservarAlojamiento.setAlojamiento(alojamiento);
            reservarAloRepository.save(reservarAlojamiento);
            return new HttpObjectResponse<>(
                    HttpCode.CREATED,
                    HttpDescription.CREATED,
                    reservarAlojamiento);
            
        }
        return null;
    }

    public HttpSimpleResponse deleteReservarAlo(final Long id) {
        if(reservarAloRepository.findById(id).isPresent()){
            reservarAloRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCode.OK, HttpDescription.OK);
        }else
            return new HttpSimpleResponse(HttpCode.RESOURCE_NOT_FOUND, 
                    HttpDescription.RESOURCE_NOT_FOUND);
    }

    
    public HttpObjectResponse<ReservarAlojamiento> updateReservarAlo(final ReservarAlojamiento reservarAlojamiento) {
        final Long id = reservarAlojamiento.getId();
        if(reservarAloRepository.findById(id).isPresent()){
            final Usuario usuario = alojamientoRepository.findById(id).get().getUsuario();
            final Alojamiento alojamiento = alojamientoRepository.findById(id).get();
            reservarAlojamiento.setUsuario(usuario);
            reservarAlojamiento.setAlojamiento(alojamiento);
            reservarAloRepository.saveAndFlush(reservarAlojamiento);
            return  new HttpObjectResponse<>(
                    HttpCode.OK, HttpDescription.OK, reservarAlojamiento);
        }else
            return  new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND, 
                    HttpDescription.RESOURCE_NOT_FOUND, null);
    }
    
}
