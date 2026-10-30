package escola.exception;

public class SalaNaoEncontradaException extends RuntimeException {

    public SalaNaoEncontradaException(Long id) {
        super("Sala não encontrada: " + id);
    }
}