package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.http.HttpSimpleResponse;
import app.model.ReservarAlojamiento;
import app.service.ReserAloService;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class ReservarAloController {

    @Autowired
    private ReserAloService reserAloService;
    
    
    @PostMapping("/reservar-alojamientos/{username}/{id}")
    public HttpObjectResponse<ReservarAlojamiento> createReservarAlo(
            @PathVariable String username,@PathVariable Long id_aloja, @RequestBody ReservarAlojamiento reservarAlojamiento){
        return reserAloService.createaReservarAlo(username, id_aloja, reservarAlojamiento);
    }
    
    @GetMapping("/reservar-alojamientos")
    public HttpListResponse<ReservarAlojamiento> listarRevisarAlojamientos(){
        return reserAloService.getReserAlojamientos();
    }
    
    
    @GetMapping("/reservar-alojamientos/{id}")
    public HttpObjectResponse<ReservarAlojamiento> getReservarAloById(@PathVariable Long id){
       return reserAloService.getlReservarAloById(id);
    }
    
    @DeleteMapping("/reservar-alojamientos/{id}")
    public HttpSimpleResponse deleteReservarAlo(@PathVariable Long id){
        return reserAloService.deleteReservarAlo(id);
    }
    
    
    @PutMapping("/reservar-alojamientos")
    public HttpObjectResponse<ReservarAlojamiento> updateReservarAlo(@RequestBody ReservarAlojamiento reservarAlojamiento){
        return reserAloService.updateReservarAlo(reservarAlojamiento);
    }
    
    
}
