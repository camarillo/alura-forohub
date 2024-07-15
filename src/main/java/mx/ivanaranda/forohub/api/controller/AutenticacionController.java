package mx.ivanaranda.forohub.api.controller;

import jakarta.validation.Valid;
import mx.ivanaranda.forohub.api.domain.usuarios.Usuario;
import mx.ivanaranda.forohub.api.domain.usuarios.UsuarioAutenticacionDTO;
import mx.ivanaranda.forohub.api.infra.security.JWTTokenDTO;
import mx.ivanaranda.forohub.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid UsuarioAutenticacionDTO usuarioAutenticacionDTO){
        Authentication authtoken = new UsernamePasswordAuthenticationToken(usuarioAutenticacionDTO.username(), usuarioAutenticacionDTO.password());
        var usuarioAutenticado = authenticationManager.authenticate(authtoken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTtoken));
    }
}
