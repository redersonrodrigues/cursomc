package com.rederson.cursomc.domain.enums;

public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3,"Cancelado");

    private int cod;
    private String descricao;

    // construtor
    private EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    // Getters
    public int getCode() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (EstadoPagamento x : EstadoPagamento.values()) {
            if (id.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido " + id);
    }
}
