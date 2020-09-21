package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.http.HttpListResponse;
import app.http.HttpObjectResponse;
import app.model.TipoVivienda;
import app.service.TipoViviendaService;


@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class TipoViviendaController {
    @Autowired
    private TipoViviendaService tipovservice;
    
    
    @GetMapping("/tipoViviendas")
    public HttpListResponse<TipoVivienda> getTipoViviendas(){
        return tipovservice.getTipoViviendas();
    }
    
    
    @GetMapping("/tipoViviendas/{id}")
    public HttpObjectResponse<TipoVivienda> getTipoViviendaById(@PathVariable Long id){
       return tipovservice.getTipoViviendaById(id);
    }
    
}
