package app.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.http.HttpCode;
import app.http.HttpDescription;
import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.http.HttpSimpleResponse;
import app.model.Alojamiento;
import app.model.ReservarAlojamiento;
import app.model.Usuario;
import app.repository.AlojamientoRepository;
import app.repository.ReservarAloRepository;
import app.repository.UsuarioRepository;
import app.util.SimpleDate;
import java.util.Date;
import java.util.List;

@Service
public class ReserAloService {

    /*
     * -------------------------- DECLARACIONES --------------------------------
     */
    @Autowired
    private ReservarAloRepository reservarAloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlojamientoRepository alojamientoRepository;
    
    /*
     * ----------------------------- MÉTODOS -----------------------------------
     */
    public HttpListResponse<ReservarAlojamiento> getReserAlojamientos() {
        return new HttpListResponse<>(HttpCode.OK, HttpDescription.OK,
                reservarAloRepository.findAll());
    }

    public HttpObjectResponse<ReservarAlojamiento> getlReservarAloById(final Long id) {
        try {
            final ReservarAlojamiento reservarAlojamiento = reservarAloRepository.findById(id).get();
            return new HttpObjectResponse<>(HttpCode.OK, HttpDescription.OK, reservarAlojamiento);

        } catch (final NoSuchElementException e) {
            return new HttpObjectResponse<>(HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND, null);
        }
    }
    
    /*
     * @Author Christian Mendieta:
     * Este método ordena todas las reservas de un alojamiento por fecha y verifica si
     * la ultima reservacion por fecha es menor a la fecha actual, si se da este caso, 
     * actualizara la disponibilidad del alojamiento.
     */
    public List<ReservarAlojamiento> ordenarAndActualizarDisponibilidad(Long alojId){
         List<ReservarAlojamiento> listaReservas = reservarAloRepository.getReservasByAlojamiento(alojId);
         ReservarAlojamiento reserva1 = null;
         ReservarAlojamiento reserva2 = null;
         Date fecha1 = null;
         Date fecha2 = null;
         int tamano = listaReservas.size();
         for(int i=0; i<tamano; i++){  
             if(i<(tamano-1)){   
                 reserva1 = listaReservas.get(i);
                 reserva2 = listaReservas.get(i+1);
                 fecha1 = reserva1.getFechaFinal();
                 fecha2 = reserva2.getFechaFinal();
                 if(SimpleDate.isAfter(fecha1, fecha2)){  
                     listaReservas.set(i, reserva2);
                     listaReservas.set((i+1), reserva1);
                 }
             }else if(i == tamano-1 && SimpleDate.isBefore(listaReservas.get(i).getFechaFinal(), new Date())){
                 Alojamiento alojamiento = alojamientoRepository.findById(alojId).get();
                 alojamiento.setDisponible(true);
                 alojamientoRepository.saveAndFlush(alojamiento);
             }
         }
         return listaReservas;
    }
    
    /*
     * @Author Christian Mendieta:
     * Este método busca si existe la posiblidad de reservar un alojamiento que ya esta previamente reservado
     * pero en una fecha diferente, cada que se aga el llamado a este metodo, buscara si es factible, 
     * actualizar la disponibilidad de un alojamiento.
     */
    public boolean isAlojamientoDisponible(Long alojId, Date fechaInicio, Date fechaFin){
        List<ReservarAlojamiento> listaReservas = ordenarAndActualizarDisponibilidad(alojId);
        int tamano = listaReservas.size();
        Date fechaResInicio = null;
        Date fechaResFin = null;
        if(tamano > 0){  
            if(tamano > 1){ 
                for(int i=0; i<tamano; i++){
                        if(i<(tamano-1)){
                            fechaResFin = listaReservas.get(i).getFechaFinal();
                            fechaResInicio = listaReservas.get(i+1).getFechaInicio();
                            if(SimpleDate.isAfter(fechaInicio, fechaResFin) && SimpleDate.isBefore(fechaFin, fechaResInicio)){
                                return true;
                            }else 
                                return false;
                        }else 
                            return false;
                    }
            }else{
                fechaResInicio = listaReservas.get(0).getFechaInicio();
                fechaResFin = listaReservas.get(0).getFechaFinal();
                return SimpleDate.isBefore(fechaFin, fechaResInicio) || SimpleDate.isAfter(fechaInicio, fechaResFin);
            }      
        } 
        return true;
    }


    public HttpObjectResponse<ReservarAlojamiento> createaReservarAlo(final String correo_usu, final Long id_alo, final ReservarAlojamiento reservarAlojamiento) {
        final Usuario usuario = usuarioRepository.findByUsername(correo_usu);
        final Alojamiento alojamiento= alojamientoRepository.findById(id_alo).orElse(null);
        if (usuario == null ) {
            if(alojamiento == null){
                return new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND,
                    HttpDescription.RESOURCE_NOT_FOUND,
                    null);
           }
        } else {
            reservarAlojamiento.setUsuario(usuario);
            reservarAlojamiento.setAlojamiento(alojamiento);
            reservarAloRepository.save(reservarAlojamiento);
            return new HttpObjectResponse<>(
                    HttpCode.CREATED,
                    HttpDescription.CREATED,
                    reservarAlojamiento);
            
        }
        return null;
    }

    public HttpSimpleResponse deleteReservarAlo(final Long id) {
        if(reservarAloRepository.findById(id).isPresent()){
            reservarAloRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCode.OK, HttpDescription.OK);
        }else
            return new HttpSimpleResponse(HttpCode.RESOURCE_NOT_FOUND, 
                    HttpDescription.RESOURCE_NOT_FOUND);
    }

    
    public HttpObjectResponse<ReservarAlojamiento> updateReservarAlo(final ReservarAlojamiento reservarAlojamiento) {
        final Long id = reservarAlojamiento.getId();
        if(reservarAloRepository.findById(id).isPresent()){
            final Usuario usuario = alojamientoRepository.findById(id).get().getUsuario();
            final Alojamiento alojamiento = alojamientoRepository.findById(id).get();
            reservarAlojamiento.setUsuario(usuario);
            reservarAlojamiento.setAlojamiento(alojamiento);
            reservarAloRepository.saveAndFlush(reservarAlojamiento);
            return  new HttpObjectResponse<>(
                    HttpCode.OK, HttpDescription.OK, reservarAlojamiento);
        }else
            return  new HttpObjectResponse<>(
                    HttpCode.RESOURCE_NOT_FOUND, 
                    HttpDescription.RESOURCE_NOT_FOUND, null);
    }
    
}
