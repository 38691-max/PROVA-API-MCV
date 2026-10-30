package escola.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/** Dados de entrada do cadastro. Não tem "reservada": toda sala nasce livre. */
public record SalaRequest(
        @NotBlank(message = "O código é obrigatório") String codigo,
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotNull(message = "A capacidade é obrigatória")
        @Positive(message = "A capacidade deve ser maior que zero") Integer capacidade) {
}