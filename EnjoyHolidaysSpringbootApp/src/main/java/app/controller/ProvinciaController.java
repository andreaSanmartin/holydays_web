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
import app.model.Provincia;
import app.service.ProvinciaService;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class ProvinciaController {
    
    @Autowired
    private ProvinciaService provinciaService;
    
    
    @PostMapping("/provincia")
    public HttpObjectResponse<Provincia> createProvincia(
            @Validated @RequestBody Provincia provincia){
        return provinciaService.crearProvincia(provincia);
    }
    
    
    @GetMapping("/provincias")
    public HttpListResponse<Provincia> getProvincias(){
        return provinciaService.getProvincias();
    }
    
    
    @GetMapping("/provincias/{id}")
    public HttpObjectResponse<Provincia> getProvinciaById(@PathVariable Long id){
       return provinciaService.getProvinciaById(id);
    }
    
    @DeleteMapping("/provincias/{id}")
    public HttpSimpleResponse deleteProvincia(@PathVariable Long id){
        return provinciaService.deleteProvincia(id);
    }
    
    
    @PutMapping("/provincias")
    public HttpObjectResponse<Provincia> updateProvincia(@RequestBody Provincia provincia){
        return provinciaService.updateProvincia(provincia);
    }
}
