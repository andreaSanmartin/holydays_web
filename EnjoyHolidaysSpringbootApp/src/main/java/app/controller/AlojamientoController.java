package app.controller;

import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.http.HttpSimpleResponse;
import app.model.Alojamiento;
import app.service.AlojamientoService;
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

/**@author Christian Mendieta*/

@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class AlojamientoController {
    
    @Autowired
    private AlojamientoService alojamientoService;
    
    
    @PostMapping("/alojamientos/{username}")
    public HttpObjectResponse<Alojamiento> createAlojamientos(
            @PathVariable String username, @RequestBody Alojamiento alojamiento){
        return alojamientoService.createAlojamiento(username, alojamiento);
    }
    
    
    @GetMapping("/alojamientos")
    public HttpListResponse<Alojamiento> getAlojamientos(){
        return alojamientoService.getAlojamientos();
    }
    
    
    @GetMapping("/alojamientos/{id}")
    public HttpObjectResponse<Alojamiento> getAlojamientoById(@PathVariable Long id){
       return alojamientoService.getAlojamientoById(id);
    }
    
    @DeleteMapping("/alojamientos/{id}")
    public HttpSimpleResponse deleteAlojamiento(@PathVariable Long id){
        return alojamientoService.deleteAlojamiento(id);
    }
    
    
    @PutMapping("/alojamientos")
    public HttpObjectResponse<Alojamiento> updateAlojamiento(@RequestBody Alojamiento alojamiento){
        return alojamientoService.updateAlojamiento(alojamiento);
    }
}
