package mx.ivanaranda.forohub.api.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean status;
    private Long idUsuario;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    private Integer respuestas;

    public Topico(TopicoRegistroDTO dto) {
        this.titulo = dto.titulo();
        this.mensaje = dto.mensaje();
        this.fecha = LocalDateTime.now();
        this.status = true;
        this.idUsuario = dto.idUsuario();
        this.curso = dto.curso();
        this.respuestas = 0;
    }
}
