package escola.model;

import jakarta.persistence.*;

@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer capacidade;

    @Column(nullable = false)
    private boolean reservada = false;

    public Sala() {
    }

    public Sala(String codigo, String nome, Integer capacidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.capacidade = capacidade;
        this.reservada = false;
    }

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public Integer getCapacidade() { return capacidade; }
    public boolean isReservada() { return reservada; }
    public void setReservada(boolean reservada) { this.reservada = reservada; }
}