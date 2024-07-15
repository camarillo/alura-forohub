package mx.ivanaranda.forohub.api.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    public List<Topico> findTop10ByOrderByFechaDesc();
}
