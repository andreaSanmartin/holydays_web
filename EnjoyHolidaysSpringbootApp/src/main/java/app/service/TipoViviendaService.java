package app.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.http.HttpCode;
import app.http.HttpDescription;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.model.TipoVivienda;
import app.repository.TipoViviendaRepository;

@Service
public class TipoViviendaService {
    @Autowired
    private TipoViviendaRepository tipovRepository;

    public HttpListResponse<TipoVivienda> getTipoViviendas() {
        return new HttpListResponse<>(HttpCode.OK, HttpDescription.OK, tipovRepository.findAll());
    }

    public HttpObjectResponse<TipoVivienda> getTipoViviendaById(Long id) {
        try {
            TipoVivienda tipov = tipovRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCode.OK, HttpDescription.OK, tipov);

        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND, null);
        }
    }

}
