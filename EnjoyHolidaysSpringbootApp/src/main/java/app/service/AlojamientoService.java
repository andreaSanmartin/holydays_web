package app.service;

import app.http.HttpCode;
import app.http.HttpDescription;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.http.HttpSimpleResponse;
import app.repository.AlojamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Alojamiento;
import app.model.Ciudad;
import app.model.TipoVivienda;
import app.model.Usuario;
import app.repository.CiudadRepository;
import app.repository.TipoViviendaRepository;
import app.repository.UsuarioRepository;
import app.util.SimpleDate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Christian Mendieta
 */
@Service
public class AlojamientoService {

    /*
     * -------------------------- DECLARACIONES --------------------------------
     */
    @Autowired
    private AlojamientoRepository alojamientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReserAloService reservaService;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private TipoViviendaRepository tipoRepository;

    /*
     * ----------------------------- MÉTODOS -----------------------------------
     */
    public HttpListResponse<Alojamiento> getAlojamientos() {
        return new HttpListResponse<>(
                HttpCode.OK,
                HttpDescription.OK,
                alojamientoRepository.findAll()
        );
    }

    /*
     * Este método busca si un alojamiento se encuantra disponible en un periodo
     * determinado de fechas.
     */
    public HttpListResponse<Alojamiento> getAlojamientosDisponibles(String ciudad, int numHuespedes, String fecha_inicio, String fecha_fin) {
        try {
            Date fechaInicio = SimpleDate.getDateOf(fecha_inicio);
            Date fechaFin = SimpleDate.getDateOf(fecha_fin);
            if (SimpleDate.isValidPeriodToReserve(fechaInicio, fechaFin)) {
                List<Alojamiento> listaAlojDisponibles = new ArrayList<>();
                listaAlojDisponibles.clear();
                List<Alojamiento> listaAloj = alojamientoRepository.
                        getAlojamientoByCiudadAndHuepedes(ciudad, numHuespedes);
                for (Alojamiento aloj : listaAloj) {
                    if (aloj.isDisponible()) {
                        listaAlojDisponibles.add(aloj);
                    } else if (reservaService.isAlojamientoDisponible(aloj.getId(), fechaInicio, fechaFin)) {
                        listaAlojDisponibles.add(aloj);
                    }
                }
                return new HttpListResponse<>(HttpCode.OK, HttpDescription.OK, listaAlojDisponibles);
            } else {
                return new HttpListResponse<>(HttpCode.INVALID_RESERVATION_DATES, HttpDescription.INVALID_RESERVATION_DATES,
                         null);
            }
        } catch (ParseException e) {
            return new HttpListResponse<>(HttpCode.INVALID_DATE_FORMAT, HttpDescription.INVALID_DATE_FORMAT,
                     null);
        }
    }

    public HttpObjectResponse<Alojamiento> getAlojamientoById(Long id) {
        try {
            Alojamiento alojamiento = alojamientoRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCode.OK, HttpDescription.OK, alojamiento);

        } catch (NoSuchElementException e) {
            return new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND,
                    null
            );
        }
    }

    public HttpListResponse<Alojamiento> getAlojamientoByUsu(String usuario) {
        try {
            List<Alojamiento> lista = alojamientoRepository.getAlojamientoByUsu(usuario);
            return new HttpListResponse<>(HttpCode.OK, HttpDescription.OK, lista);

        } catch (NoSuchElementException e) {
            return new HttpListResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND,
                    null
            );
        }
    }

    public HttpObjectResponse<Alojamiento> createAlojamiento(Long userId, Long ciudadId, Long tipoId, Alojamiento alojamiento) {
        if (usuarioRepository.findById(userId).isPresent() && ciudadRepository.findById(ciudadId).isPresent() && tipoRepository.findById(tipoId).isPresent()) {
            Usuario usuario = usuarioRepository.findById(userId).get();
            Ciudad ciudad = ciudadRepository.findById(ciudadId).get();
            TipoVivienda tipo = tipoRepository.findById(tipoId).get();
            alojamiento.setCiudad(ciudad);
            alojamiento.setUsuario(usuario);
            alojamiento.setTVivienda(tipo);
            alojamientoRepository.save(alojamiento);
            List<Alojamiento> listaAlojamientos = alojamientoRepository.getAlojamientoByUsu(usuario.getCorreo());
            int cantidadAloj = listaAlojamientos.size();
            for (int i = 0; i < cantidadAloj; i++) {
                alojamiento = listaAlojamientos.get(i);
            }
            return new HttpObjectResponse<>(HttpCode.CREATED, HttpDescription.CREATED, alojamiento);
        } 
        return new HttpObjectResponse<>(HttpCode.RESOURCE_NOT_FOUND, HttpDescription.RESOURCE_NOT_FOUND, null);
    }

    public HttpSimpleResponse deleteAlojamiento(Long id) {
        if (alojamientoRepository.findById(id).isPresent()) {
            alojamientoRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCode.OK, HttpDescription.OK);
        } else {
            return new HttpSimpleResponse(HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND);
        }
    }

    public HttpObjectResponse<Alojamiento> updateAlojamiento(Alojamiento alojamiento) {
        Long id = alojamiento.getId();
        if (alojamientoRepository.findById(id).isPresent()) {
            Usuario usuario = alojamientoRepository.findById(id).get().getUsuario();
            alojamiento.setUsuario(usuario);
            alojamientoRepository.saveAndFlush(alojamiento);
            return new HttpObjectResponse<>(
                    HttpCode.OK, HttpDescription.OK, alojamiento);
        } else {
            return new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND, null);
        }
    }

}
