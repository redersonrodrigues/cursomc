package com.rederson.cursomc.domain.enums;

public enum TipoCliente {
    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String descricao;

    // construtor
    private TipoCliente(int cod, String descricao) {
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

    public static TipoCliente toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (TipoCliente x : TipoCliente.values()) {
            if (id.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido " + id);
    }
}
