/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.repository.CiudadRepository;
import app.http.HttpCodeResponse;
import app.http.HttpDescriptionResponse;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.http.HttpSimpleResponse;
import app.model.Ciudad;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

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

    public HttpObjectResponse<Ciudad> crearCiudad(Ciudad ciudad) {
        Optional<Ciudad> ciudadBd = ciudadRepository.findById(ciudad.getId());

        if (ciudadBd != null) {
            ciudadRepository.save(ciudad);
            return new HttpObjectResponse<>(
                    HttpCodeResponse.CREATED,
                    HttpDescriptionResponse.CREATED,
                    ciudad);
        }else{
            return new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("ciudad"),
                    null);
        }
    }

    public HttpSimpleResponse deleteCiudad(Long id) {
        if(ciudadRepository.findById(id).isPresent()){
            ciudadRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCodeResponse.OK, HttpDescriptionResponse.OK);
        }else
            return new HttpSimpleResponse(HttpCodeResponse.ENTIDAD_NO_ENCONTRADA, 
                    HttpDescriptionResponse.entidadNoEncontrada("Ciudad"));
    }


    public HttpObjectResponse<Ciudad> updateCiudad(Ciudad ciudad) {
        Long id = ciudad.getId();
        if(ciudadRepository.findById(id).isPresent()){
            ciudadRepository.saveAndFlush(ciudad);
            return  new HttpObjectResponse<>(
                    HttpCodeResponse.OK, HttpDescriptionResponse.OK, ciudad);
        }else
            return  new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA, 
                    HttpDescriptionResponse.entidadNoEncontrada("Ciudad"), null);
    }
}
