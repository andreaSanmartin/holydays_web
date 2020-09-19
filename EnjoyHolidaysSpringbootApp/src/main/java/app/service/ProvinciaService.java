package app.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.http.HttpCodeResponse;
import app.http.HttpDescriptionResponse;
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
            HttpCodeResponse.OK,
            HttpDescriptionResponse.OK,
            provinciaRepository.findAll()
        );
    }

    public HttpObjectResponse<Provincia> getProvinciaById(Long id) {
        try {
            Provincia provincia = provinciaRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCodeResponse.OK, HttpDescriptionResponse.OK, provincia);
            
        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("Provincia"),
                    null
            );
        }
    }
}
