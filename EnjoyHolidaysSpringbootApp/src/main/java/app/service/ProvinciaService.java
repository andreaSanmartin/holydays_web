package app.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.http.HttpCode;
import app.http.HttpDescription;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.model.Provincia;
import app.repository.ProvinciaRepository;

@Service
public class ProvinciaService {
   @Autowired
    private ProvinciaRepository provinciaRepository;

    public HttpListResponse<Provincia> getProvincias() {
        return new HttpListResponse<>(
            HttpCode.OK,
            HttpDescription.OK,
            provinciaRepository.findAll()
        );
    }

    public HttpObjectResponse<Provincia> getProvinciaById(Long id) {
        try {
            Provincia provincia = provinciaRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCode.OK, HttpDescription.OK, provincia);
            
        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND,
                    null
            );
        }
    }
}
