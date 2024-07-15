package mx.ivanaranda.forohub.api.controller;

import jakarta.validation.Valid;
import mx.ivanaranda.forohub.api.domain.topico.TopicoRegistroDTO;
import mx.ivanaranda.forohub.api.domain.topico.TopicoRespuestaDTO;
import mx.ivanaranda.forohub.api.domain.topico.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoRespuestaDTO> registrar(@RequestBody @Valid TopicoRegistroDTO topicoRegistroDTO){
        TopicoRespuestaDTO topicoRespuestaDTO = topicoService.registrar(topicoRegistroDTO);
        return ResponseEntity.ok(topicoRespuestaDTO);
    }
}
