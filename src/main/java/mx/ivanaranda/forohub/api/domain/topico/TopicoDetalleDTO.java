package mx.ivanaranda.forohub.api.domain.topico;

import java.time.LocalDateTime;

public record TopicoDetalleDTO(
        String titulo
        , String mensaje
        , LocalDateTime fecha
        , Boolean status
        , Long idUsuario
        , Curso curso
) {

    public TopicoDetalleDTO(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFecha(), topico.getStatus(), topico.getIdUsuario(), topico.getCurso());
    }
}
