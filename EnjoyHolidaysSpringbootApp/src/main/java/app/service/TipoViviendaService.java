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
            return new HttpObjectResponse<>(HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("TipoVivienda"), null);
        }
    }

    public HttpObjectResponse<TipoVivienda> crearTipoVivienda(TipoVivienda tipov) {
        Optional<TipoVivienda> tipovBd = tipovRepository.findById(tipov.getId());

        if (tipovBd != null) {
            tipovRepository.save(tipov);
            return new HttpObjectResponse<>(HttpCodeResponse.CREATED, HttpDescriptionResponse.CREATED, tipov);
        } else {
            return new HttpObjectResponse<>(HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("tipovivienda"), null);
        }
    }

    public HttpSimpleResponse deleteTipoVivienda(Long id) {
        if (tipovRepository.findById(id).isPresent()) {
            tipovRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCodeResponse.OK, HttpDescriptionResponse.OK);
        } else
            return new HttpSimpleResponse(HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("TipoVivienda"));
    }

    public HttpObjectResponse<TipoVivienda> updateTipoVivienda(TipoVivienda tipov) {
        Long id = tipov.getId();
        if (tipovRepository.findById(id).isPresent()) {
            tipovRepository.saveAndFlush(tipov);
            return new HttpObjectResponse<>(HttpCodeResponse.OK, HttpDescriptionResponse.OK, tipov);
        } else
            return new HttpObjectResponse<>(HttpCodeResponse.ENTIDAD_NO_ENCONTRADA,
                    HttpDescriptionResponse.entidadNoEncontrada("TipoVivienda"), null);
    }
}
