package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import app.model.Response;
import app.model.Usuario;
import app.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/usuarios", produces = "application/json")
    public Response listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping(value = "/usuarios")
    public Response crearUsuario(@Validated @RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/usuarios/{correo}")
    public Response findByCorreo(@PathVariable String correo) {
        return this.usuarioService.findByCorreo(correo);
    }

    @DeleteMapping("usuarios/{correo}")
    public Response deleteByCorreo(@PathVariable String correo) {
        return this.usuarioService.deleteByCorreo(correo);
    }


    @PutMapping("usuarios/{correo}")
    public Response updateByCorreo(@PathVariable String correo, @RequestBody Usuario usuario) {
        return usuarioService.updateByCorreo(correo, usuario);
    }

    @PostMapping("usuarios/login/{correo}/{password}")
    public Response loginUsuario(@PathVariable String correo, @PathVariable String password) {
        return usuarioService.loginUsuario(correo, password);
    }
    
}
