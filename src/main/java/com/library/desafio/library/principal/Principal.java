package com.library.desafio.library.principal;



import com.library.desafio.library.model.Datos;
import com.library.desafio.library.model.DatosAutor;
import com.library.desafio.library.model.DatosLibros;
import com.library.desafio.library.model.Libro;
import com.library.desafio.library.service.ConsumoAPI;
import com.library.desafio.library.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private  static  final  String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<DatosLibros>();

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
//                    listarAutores();
                    break;
                case 4:
//                    autoresAno();
                    break;
                case 5:
//                    librosIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");;
                    break;
                default:
                    System.out.println("Opción no valida");
            }

        }
    }

    // Busqueda de libros por titulo
    private DatosLibros buscarLibroWeb() {
        var teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){

            List<String> escritor = libroBuscado.get().autor().stream()
                    .map(DatosAutor::nombre)
                    .toList();
            System.out.println("------- LIBRO ------- ");
            System.out.println("Titulo: " + libroBuscado.get().titulo());
            System.out.println("Autor: " + escritor);
            System.out.println("Idioma: " + libroBuscado.get().idiomas());
            System.out.println("Número de descargas: " + libroBuscado.get().numeroDescargas());
            System.out.println("---------------------- ");
        }else {
            System.out.println("libro no encontrado");
            muestraMenu();
        }
        DatosLibros libroEncontrado = datosBusqueda.resultados().get(0);
        return libroEncontrado ;
    }

    private void registroLibro() {
        DatosLibros optionalDatosLibros = buscarLibroWeb();
        datosLibros.add(optionalDatosLibros);
        System.out.println(optionalDatosLibros);
    }

    private void libroRegistrado() {
   //    datosLibros.forEach(System.out::println);
        List<Libro> libros = new ArrayList<>();
        libros = datosLibros.stream()
                .map(d -> new Libro(d))
                .collect(Collectors.toList());
        libros.stream()
                .forEach(System.out::println);
    }


//        var json = consumoAPI.obtenerDatos(URL_BASE);
//        System.out.println(json);
//        var datos = conversor.obtenerDatos(json, Datos.class);
//        System.out.println(datos);

        // Top 10 libros mas descargados
//        System.out.println("Top 10 libros mas descargados");
//        datos.resultados().stream()
//                .sorted(Comparator.comparing(DatosLibros::numeroDescargas).reversed())
 //               .limit(10)
//                .map(l -> l.titulo().toUpperCase())
 //               .forEach(System.out::println);




        // trabajando con estadisticas
//        DoubleSummaryStatistics est = datos.resultados().stream()
//                .filter(d -> d.numeroDescargas() > 0)
//                .collect(Collectors.summarizingDouble(DatosLibros::numeroDescargas));
//        System.out.println("La media de descargas es: " + est.getAverage());
//        System.out.println("Cantidad maxima de descargas: " + est.getMax());
//        System.out.println("Cantidad minima de descargas: " + est.getMin());
//        System.out.println("Total de registros evaluados en el calculo de estadisticas: " + est.getCount());


}
