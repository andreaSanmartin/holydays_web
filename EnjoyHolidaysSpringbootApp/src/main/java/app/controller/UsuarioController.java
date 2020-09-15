package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import app.model.Response;
import app.model.Usuario;
import app.service.UsuarioService;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/listar", produces = "application/json")
    public Response listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping(value = "/crear")
    public Response crearUsuario(@Validated @RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{cedula}")
    public Response findByCedula(@PathVariable String cedula) {
        return this.usuarioService.findByCedula(cedula);
    }

    @DeleteMapping("/{cedula}")
    public Response deleteByCedula(@PathVariable String cedula) {
        return this.usuarioService.deleteByCedula(cedula);
    }


    @PutMapping("/{cedula}")
    public Response updateByCedula(@PathVariable String cedula, @RequestBody Usuario usuario) {
        return usuarioService.updateByCedula(cedula, usuario);
    }

}
