package app.service;

import app.repository.CiudadRepository;
import app.http.HttpCodeResponse;
import app.http.HttpDescriptionResponse;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.model.Ciudad;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired

    public HttpListResponse<Ciudad> getCiudades() {
        return new HttpListResponse<>(
            HttpCodeResponse.OK,
            HttpDescriptionResponse.OK,
            ciudadRepository.findAll()
        );
    }

    public HttpObjectResponse<Ciudad> getCiudadById(Long id) {
        try {
            Ciudad ciudad = ciudadRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCodeResponse.OK, HttpDescriptionResponse.OK, ciudad);
            
        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("Ciudad"),
                    null
            );
        }
    } 
}
