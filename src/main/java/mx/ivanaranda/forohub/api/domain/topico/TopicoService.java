package mx.ivanaranda.forohub.api.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<TopicoListadoDTO> listar() {
        return topicoRepository.findAllByOrderByFechaDesc().stream().map(TopicoListadoDTO::new).toList();
    }
}
