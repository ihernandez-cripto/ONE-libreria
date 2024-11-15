package com.library.desafio.library.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {
    @Id
    @Column(name = "id_autor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private Integer fechaNacimiento;
    @Column(name = "fecha_muerte")
    private Integer fechaMuerte;
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaMuerte = datosAutor.fechaMuerte();
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                        ", fechaNacimiento=" + fechaNacimiento +
                        ", fechaMuerte=" + fechaMuerte ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }
}
