package mx.ivanaranda.forohub.api.domain.topico;

import java.time.LocalDateTime;

public record TopicoRespuestaDTO(
        Long id
        , String titulo
        , String mensaje
        , LocalDateTime fecha
) {
    public TopicoRespuestaDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha());
    }
}
