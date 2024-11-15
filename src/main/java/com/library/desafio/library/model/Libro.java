package com.library.desafio.library.model;

import jakarta.persistence.*;

import java.util.OptionalDouble;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne()
    @JoinColumn(name = "id_autor")
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Catalogo idiomas;
    private Double numeroDescargas;

    public Libro(){}

    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.idiomas = Catalogo.fromString(datosLibros.idiomas());
        this.numeroDescargas = OptionalDouble.of(Double.valueOf(datosLibros.numeroDescargas())).orElse(0);
    }

    @Override
    public String toString() {
        return
                "Id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
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
