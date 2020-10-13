package app.service;

import app.repository.CiudadRepository;
import app.http.HttpCode;
import app.http.HttpDescription;
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
    //@Autowired

    public HttpListResponse<Ciudad> getCiudades() {
        return new HttpListResponse<>(
            HttpCode.OK,
            HttpDescription.OK,
            ciudadRepository.findAll()
        );
    }
    

    public HttpObjectResponse<Ciudad> getCiudadById(Long id) {
        try {
            Ciudad ciudad = ciudadRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCode.OK, HttpDescription.OK, ciudad);
            
        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND,
                    null
            );
        }
    } 
}
