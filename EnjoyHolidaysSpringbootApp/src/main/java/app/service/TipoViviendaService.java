package app.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.http.HttpCodeResponse;
import app.http.HttpDescriptionResponse;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.model.TipoVivienda;
import app.repository.TipoViviendaRepository;

@Service
public class TipoViviendaService {
    @Autowired
    private TipoViviendaRepository tipovRepository;

    public HttpListResponse<TipoVivienda> getTipoViviendas() {
        return new HttpListResponse<>(HttpCodeResponse.OK, HttpDescriptionResponse.OK, tipovRepository.findAll());
    }

    public HttpObjectResponse<TipoVivienda> getTipoViviendaById(Long id) {
        try {
            TipoVivienda tipov = tipovRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCodeResponse.OK, HttpDescriptionResponse.OK, tipov);

        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(HttpCodeResponse.RESOURCE_NOT_FOUND,
                    HttpDescriptionResponse.RESOURCE_NOT_FOUND, null);
        }
    }

}
