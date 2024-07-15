package mx.ivanaranda.forohub.api.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public TopicoRespuestaDTO registrar(TopicoRegistroDTO topicoRegistroDTO){
        Topico topico = new Topico(topicoRegistroDTO);
        topicoRepository.save(topico);
        return new TopicoRespuestaDTO(topico);
    }

    public Page<TopicoListadoDTO> listar(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(TopicoListadoDTO::new);
    }
}
