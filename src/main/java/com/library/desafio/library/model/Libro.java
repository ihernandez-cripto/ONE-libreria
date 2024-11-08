package com.library.desafio.library.model;

import java.util.List;
import java.util.Optional;

public class Libro {
    private String titulo;
    private List<DatosAutor> autor;
    private Catalogo idiomas;
    private Double numeroDescargas;

    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.autor = datosLibros.autor();
        this.idiomas = Catalogo.fromString(datosLibros.idiomas());
        this.numeroDescargas = datosLibros.numeroDescargas();
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas
                ;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DatosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autor = autor;
    }

    public Catalogo getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Catalogo idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
