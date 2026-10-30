package escola.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErroResposta(int status, String erro, String mensagem, Map<String, String> campos) {

    public ErroResposta(int status, String erro, String mensagem) {
        this(status, erro, mensagem, null);
    }
}