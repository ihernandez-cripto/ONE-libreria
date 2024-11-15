package com.library.desafio.library.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private Integer fechaNacimiento;
    @Column(name = "fecha_muerte")
    private Integer fechaMuerte;

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
       this.nombre = datosAutor.nombre();
       try{
           this.fechaNacimiento = Integer.valueOf(datosAutor.fechaNacimiento());
       } catch (NumberFormatException e){
           this.fechaNacimiento = 0;
       }
       try{
           this.fechaMuerte = Integer.valueOf(datosAutor.fechaMuerte());
       } catch (NumberFormatException e){
           this.fechaMuerte = 0;
       }
    }

    @Override
    public String toString() {
        return
                "nombre=" + nombre + '\'' +
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
