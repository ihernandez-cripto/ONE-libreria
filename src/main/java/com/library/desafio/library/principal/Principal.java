package com.library.desafio.library.principal;

import com.library.desafio.library.model.*;
import com.library.desafio.library.repository.AutorRepository;
import com.library.desafio.library.repository.LibroRepository;
import com.library.desafio.library.service.ConsumoAPI;
import com.library.desafio.library.service.ConvierteDatos;
import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private  static  final  String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private List<DatosAutor> datosAutor = new ArrayList<>();
    private List<Libro> libros = new ArrayList<>();
    private List<Autor> escritores = new ArrayList<>();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepositorio = libroRepository;
        this.autorRepositorio = autorRepository;
    }

    public void  muestraMenu(){
        var opcion = -1;
        var teclado = new Scanner(System.in);
        while (opcion != 0) {
            var menu = """
                    1. Buscar libro por titulo.
                    2. Listar libros registrados.
                    3. Listar autores registrados.
                    4. Listar autores vivos en un año determinado.
                    5. Listar libros por idioma.
                    0. Salir.
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    registroLibro();
                    break;
                case 2:
                    libroRegistrado();
                    break;
                case 3:
                    autorRegistrado();
                    break;
                case 4:
                    consultarAutoresVivos();
                    break;
                case 5:
                    mostrarlibrosIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no valida");
            }

        }
    }

    // Busqueda de libros por titulo
    private List<DatosLibros> buscarLibroWeb() {
        var teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ", "+"));
        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        datosLibros = datosBusqueda.resultados();
//        return libroBuscado ;

        return datosLibros;
    }

    // Agregar el libro a la lista de libros consultados y mostrar informacion en pantalla
    @Transactional
    private void registroLibro() {
        List<DatosLibros> datos = buscarLibroWeb();
        List<Libro> libro = convertirADatosLibros(datos);
        List<Autor> autor = convertirADatosAutores(datos.get(0).autor());
        libro.get(0).setAutor(autor.get(0));
        autorRepositorio.save(autor.get(0));
        libroRepositorio.save(libro.get(0));
        imprimirInformacionLibro(datos.get(0));
    }

    private List<Libro> convertirADatosLibros(List<DatosLibros> datosLibros) {
        return datosLibros.stream()
                .map(Libro::new)
                .collect(Collectors.toList());
    }

    private List<Autor> convertirADatosAutores(List<DatosAutor> datosAutor) {
        return datosAutor.stream()
                .map(Autor::new)
                .collect(Collectors.toList());
    }

    private void imprimirLibros(List<Libro> libros) {
        libros.forEach(System.out::println);
    }

    private void imprimirAutores(List<Autor> autores) {
        autores.forEach(System.out::println);
    }

    private void libroRegistrado() {
        libros = libroRepositorio.findAll();
        imprimirLibros(libros);
    }

    private void autorRegistrado() {
        escritores = autorRepositorio.findAll();
        imprimirAutores(escritores);
    }

    private void consultarAutoresVivos() {
        var teclado = new Scanner(System.in);
        System.out.println("Ingrese el año de nacimiento del autor que desea buscar buscar");
        var fechabuscar = teclado.nextInt();
        escritores.stream()
                .filter(e -> {
                    Integer anoNacimiento = e.getFechaNacimiento();
                    return anoNacimiento != null && anoNacimiento == fechabuscar;
                })
                .forEach(System.out::println);
    }

    private void mostrarlibrosIdioma() {
        libros = libroRepositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getIdiomas).reversed())
                .forEach(System.out::println);
    }

    public void imprimirInformacionLibro(DatosLibros libro) {
            System.out.println("------- LIBRO ------- ");
            System.out.println("Título: " + libro.titulo());
            System.out.println("Autor: " + libro.autor().get(0).nombre());
            System.out.println("Idioma: " + libro.idiomas());
            System.out.println("Número de descargas: " + libro.numeroDescargas());
            System.out.println("---------------------- ");
    }
}


