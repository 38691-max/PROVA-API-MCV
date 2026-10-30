package escola.service;

import escola.exception.ConflitoException;
import escola.exception.SalaNaoEncontradaException;
import escola.model.Sala;
import escola.model.SalaRequest;
import escola.repository.SalaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaService {

    private final SalaRepository repository;

    public SalaService(SalaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Sala cadastrar(SalaRequest dados) {
        if (repository.existsByCodigo(dados.codigo())) {
            throw new ConflitoException("Já existe uma sala com o código " + dados.codigo());
        }
        return repository.save(new Sala(dados.codigo(), dados.nome(), dados.capacidade()));
    }

    @Transactional(readOnly = true)
    public List<Sala> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Sala buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new SalaNaoEncontradaException(id));
    }

    @Transactional
    public Sala reservar(Long id) {
        Sala sala = buscar(id);
        if (sala.isReservada()) {
            throw new ConflitoException("A sala já está reservada");
        }
        sala.setReservada(true);
        return repository.save(sala);
    }

    @Transactional
    public Sala liberar(Long id) {
        Sala sala = buscar(id);
        if (!sala.isReservada()) {
            throw new ConflitoException("A sala não está reservada");
        }
        sala.setReservada(false);
        return repository.save(sala);
    }

    @Transactional
    public void excluir(Long id) {
        Sala sala = buscar(id);
        if (sala.isReservada()) {
            throw new ConflitoException("Uma sala reservada não pode ser excluída");
        }
        repository.delete(sala);
    }
}