package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import app.model.Ciudad;
import app.service.CiudadService;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;
    
    
    @PostMapping("/ciudad")
    public HttpObjectResponse<Ciudad> createCiudad(
            @Validated @RequestBody Ciudad ciudad){
        return ciudadService.crearCiudad(ciudad);
    }
    
    
    @GetMapping("/ciudades")
    public HttpListResponse<Ciudad> getCiudads(){
        return ciudadService.getCiudades();
    }
    
    
    @GetMapping("/ciudades/{id}")
    public HttpObjectResponse<Ciudad> getCiudadById(@PathVariable Long id){
       return ciudadService.getCiudadById(id);
    }
    
    @DeleteMapping("/ciudades/{id}")
    public HttpSimpleResponse deleteCiudad(@PathVariable Long id){
        return ciudadService.deleteCiudad(id);
    }
    
    
    @PutMapping("/ciudades")
    public HttpObjectResponse<Ciudad> updateCiudad(@RequestBody Ciudad ciudad){
        return ciudadService.updateCiudad(ciudad);
    }
}
