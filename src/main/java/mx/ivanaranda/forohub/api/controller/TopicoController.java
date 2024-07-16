package mx.ivanaranda.forohub.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mx.ivanaranda.forohub.api.domain.topico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoRespuestaDTO> registrar(
            @RequestBody @Valid TopicoRegistroDTO topicoRegistroDTO
            , UriComponentsBuilder uriComponentsBuilder

    ){
        TopicoRespuestaDTO topicoRespuestaDTO = topicoService.registrar(topicoRegistroDTO);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRespuestaDTO.id()).toUri();
        return ResponseEntity.created(url).body(topicoRespuestaDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoRespuestaDTO> actualizar(
            @PathVariable("id") Long idTopico
            , @RequestBody @Valid TopicoRegistroDTO topicoRegistroDTO){
        TopicoRespuestaDTO topicoRespuestaDTO = topicoService.actualizar(idTopico, topicoRegistroDTO);
        return ResponseEntity.ok(topicoRespuestaDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoRespuestaDTO> eliminar(@PathVariable("id") Long idTopico){
        topicoService.eliminar(idTopico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TopicoListadoDTO>> listar(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicoService.listar(paginacion));
    }

    @GetMapping("/top10")
    public ResponseEntity<List<TopicoListadoDTO>> listarTop10(){
        return ResponseEntity.ok(topicoService.listarTop10());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalleDTO> detalle(@PathVariable("id") Long idTopico){
        return ResponseEntity.ok(topicoService.detalle(idTopico));
    }


}
