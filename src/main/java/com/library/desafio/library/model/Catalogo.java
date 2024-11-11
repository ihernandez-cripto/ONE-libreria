package com.library.desafio.library.model;

import java.util.List;
import java.util.stream.Stream;

public enum Catalogo {
// ISO 639 Languages
    CHINO("zh"),
    ESPAÑOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt"),
    ITALIANO("it"),
    RUSO("ru"),
    ALEMAN("de"),
    DANES("da"),
    GRIEGO("el"),
    TAGALOG("tl");

    private String catalogoOmdb;

    Catalogo(String catalogoOmdb) {
        this.catalogoOmdb = catalogoOmdb;
    }

    public static Catalogo fromString(List<String> text) {
        for (Catalogo catalogo : Catalogo.values()) {
            if (catalogo.catalogoOmdb.equalsIgnoreCase(text.get(0))) {
                return catalogo;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}
