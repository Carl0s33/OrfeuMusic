package br.orfeu.music.model;

public enum GeneroEnum {
    ROCK("Rock"),
    POP("Pop"),
    MPB("MPB"),
    JAZZ("Jazz"),
    SAMBA("Samba"),
    HIP_HOP("Hip Hop"),
    ELETRONICA("Eletrônica"),
    CLASSICA("Clássica"),
    REGGAE("Reggae"),
    BLUES("Blues"),
    METAL("Metal"),
    FUNK("Funk"),
    SERTANEJO("Sertanejo"),
    PAGODE("Pagode"),
    INDIE("Indie"),
    FOLK("Folk"),
    SOUL("Soul"),
    R_AND_B("R&B"),
    RAP("Rap"),
    OUTRO("Outro");

    private final String descricao;

    GeneroEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

