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
import app.model.CalificarAlojamiento;
import app.service.CalificarAloService;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class CalificarAloController {

    @Autowired
    private CalificarAloService calificarAloService;
    
    
    @PostMapping("/calificar-alojamientos/{username}/{id}")
    public HttpObjectResponse<CalificarAlojamiento> createCalificarAlo(
            @PathVariable String username, @PathVariable Long id_aloja, @RequestBody CalificarAlojamiento calificarAlojamiento){
        return calificarAloService.createCalifaicarAlo(username, id_aloja, calificarAlojamiento);
    }
    
    @GetMapping("/calificar-alojamientos")
    public HttpListResponse<CalificarAlojamiento> listarCalificarAlojamientos(){
        return calificarAloService.getCalAlojamientos();
    }
    
    
    @GetMapping("/calificar-alojamientos/{id}")
    public HttpObjectResponse<CalificarAlojamiento> getCalificarAloById(@PathVariable Long id){
       return calificarAloService.getlCalificarAloById(id);
    }
    
    @DeleteMapping("/calificar-alojamientos/{id}")
    public HttpSimpleResponse deleteCalificarAlo(@PathVariable Long id){
        return calificarAloService.deleteCalificarAlo(id);
    }
    
    
    @PutMapping("/calificar-alojamientos")
    public HttpObjectResponse<CalificarAlojamiento> updateCalificarAlo(@RequestBody CalificarAlojamiento calificarAlojamiento){
        return calificarAloService.updateCalificarAlo(calificarAlojamiento);
    }
    
}
