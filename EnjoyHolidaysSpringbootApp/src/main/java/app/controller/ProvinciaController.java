package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.model.Provincia;
import app.service.ProvinciaService;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class ProvinciaController {
    
    @Autowired
    private ProvinciaService provinciaService;
    
    @GetMapping("/provincias")
    public HttpListResponse<Provincia> getProvincias(){
        return provinciaService.getProvincias();
    }
    
    
    @GetMapping("/provincias/{id}")
    public HttpObjectResponse<Provincia> getProvinciaById(@PathVariable Long id){
       return provinciaService.getProvinciaById(id);
    }
    
}
