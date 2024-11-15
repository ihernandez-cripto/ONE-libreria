-- Database: librarydb

-- DROP DATABASE IF EXISTS librarydb;

CREATE DATABASE librarydb
    WITH
    OWNER = usrlib
    ENCODING = 'UTF8'
    LC_COLLATE = 'es_CO.UTF-8'
    LC_CTYPE = 'es_CO.UTF-8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
	@Entity
public class Autor {
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;
}

@Entity
public class Libro {
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}

@ManyToOne
@JoinColumn(name = "autor_id")
private Autor autor;

@Enumerated(EnumType.STRING)
private Status status;

@Entity
@NamedQuery(name = "Autor consultado", query = "SELECT c FROM Autor c")
public class Autor { ... }

@OneToMany(mappedBy = "datosAutor")
@Cascade(CascadeType.SAVE_UPDATE)
private List<Autor> autores;

@Embeddable
public class Autor{ ... }

@Entity
public class Autor {
    @Embedded
    private Autor;
}