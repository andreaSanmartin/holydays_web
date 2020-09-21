package app.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.http.HttpCodeResponse;
import app.http.HttpDescriptionResponse;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.http.HttpSimpleResponse;
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

    public HttpObjectResponse<Provincia> crearProvincia(Provincia provincia) {
        Optional<Provincia> provinciaBd = provinciaRepository.findById(provincia.getId());

        if (provinciaBd != null) {
            provinciaRepository.save(provincia);
            return new HttpObjectResponse<>(
                    HttpCodeResponse.CREATED,
                    HttpDescriptionResponse.CREATED,
                    provincia);
        }else{
            return new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("provincia"),
                    null);
        }
    }

    public HttpSimpleResponse deleteProvincia(Long id) {
        if(provinciaRepository.findById(id).isPresent()){
            provinciaRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCodeResponse.OK, HttpDescriptionResponse.OK);
        }else
            return new HttpSimpleResponse(HttpCodeResponse.ENTIDAD_NO_ENCONTRADA, 
                    HttpDescriptionResponse.entidadNoEncontrada("Provincia"));
    }


    public HttpObjectResponse<Provincia> updateProvincia(Provincia provincia) {
        Long id = provincia.getId();
        if(provinciaRepository.findById(id).isPresent()){
            provinciaRepository.saveAndFlush(provincia);
            return  new HttpObjectResponse<>(
                    HttpCodeResponse.OK, HttpDescriptionResponse.OK, provincia);
        }else
            return  new HttpObjectResponse<>(
                    HttpCodeResponse.ENTIDAD_NO_ENCONTRADA, 
                    HttpDescriptionResponse.entidadNoEncontrada("Provincia"), null);
    }
}
