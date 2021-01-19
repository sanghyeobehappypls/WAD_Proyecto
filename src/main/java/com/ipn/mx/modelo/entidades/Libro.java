package com.ipn.mx.modelo.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Libro", schema = "public")

public class Libro implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibro;
    private String titulo;
    private String edicion;
    private int idAutor;
    private int idEditorial;
    private int idGenero;
    private int npaginas;
    private int anio;
}



