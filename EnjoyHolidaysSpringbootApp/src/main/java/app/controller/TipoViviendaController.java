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
import app.model.TipoVivienda;
import app.service.TipoViviendaService;


@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class TipoViviendaController {
    @Autowired
    private TipoViviendaService tipovservice;
    
    
    @PostMapping("/tipoVivienda")
    public HttpObjectResponse<TipoVivienda> createTipoVivienda(
            @Validated @RequestBody TipoVivienda tipoV){
        return tipovservice.crearTipoVivienda(tipoV);
    }
    
    @GetMapping("/tipoViviendas")
    public HttpListResponse<TipoVivienda> getTipoViviendas(){
        return tipovservice.getTipoViviendas();
    }
    
    
    @GetMapping("/tipoViviendas/{id}")
    public HttpObjectResponse<TipoVivienda> getTipoViviendaById(@PathVariable Long id){
       return tipovservice.getTipoViviendaById(id);
    }
    
    @DeleteMapping("/tipoViviendas/{id}")
    public HttpSimpleResponse deleteTipoVivienda(@PathVariable Long id){
        return tipovservice.deleteTipoVivienda(id);
    }
    
    
    @PutMapping("/tipoViviendas")
    public HttpObjectResponse<TipoVivienda> updateTipoVivienda(@RequestBody TipoVivienda tipoV){
        return tipovservice.updateTipoVivienda(tipoV);
    }
}
