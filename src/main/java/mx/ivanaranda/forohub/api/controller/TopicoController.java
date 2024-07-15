package mx.ivanaranda.forohub.api.controller;

import jakarta.validation.Valid;
import mx.ivanaranda.forohub.api.domain.topico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<Page<TopicoListadoDTO>> listar(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicoService.listar(paginacion));
    }

    @GetMapping
    @RequestMapping("/top10")
    public ResponseEntity<List<TopicoListadoDTO>> listarTop10(){
        return ResponseEntity.ok(topicoService.listarTop10());

    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalleDTO> detalle(@PathVariable("id") Long idTopico){
        return ResponseEntity.ok(topicoService.detalle(idTopico));

    }
}
