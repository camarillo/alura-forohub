package mx.ivanaranda.forohub.api.domain.topico;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public TopicoRespuestaDTO registrar(TopicoRegistroDTO topicoRegistroDTO){
        if (topicoRepository.existsByTituloAndMensaje(topicoRegistroDTO.titulo(), topicoRegistroDTO.mensaje())){
            throw new ValidationException("Ya existe un topico con el mensaje y titulo enviado");
        }

        Topico topico = new Topico(topicoRegistroDTO);
        topicoRepository.save(topico);
        return new TopicoRespuestaDTO(topico);
    }

    public Page<TopicoListadoDTO> listar(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(TopicoListadoDTO::new);
    }

    public List<TopicoListadoDTO> listarTop10() {
        return topicoRepository.findTop10ByOrderByFechaDesc().stream().map(TopicoListadoDTO::new).toList();
    }

    public TopicoDetalleDTO detalle(Long idTopico) {
        return new TopicoDetalleDTO(topicoRepository.getReferenceById(idTopico));
    }

    public TopicoRespuestaDTO actualizar(Long idTopico, TopicoRegistroDTO topicoRegistroDTO) {
        Optional<Topico> topicoBuscado = topicoRepository.findById(idTopico);
        if (topicoBuscado.isPresent()) {
            Topico topico = topicoBuscado.get();
            topico.actualizarDatos(topicoRegistroDTO);
            return new TopicoRespuestaDTO(topico);
        } else {
            System.out.println("El topico " + idTopico + " no existe");
            return null;
        }
    }

    public void eliminar(Long idTopico) {
        Optional<Topico> topicoBuscado = topicoRepository.findById(idTopico);
        if (topicoBuscado.isPresent()) {
            topicoRepository.deleteById(idTopico);
        } else {
            System.out.println("El topico " + idTopico + " no existe");
        }
    }
}
