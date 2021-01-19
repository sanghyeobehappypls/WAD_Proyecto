package com.ipn.mx.modelo.entidades;

import lombok.*;
import java.sql.Date;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Prestamo", schema = "public")

public class Prestamo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibro;
    
    private Date fechaInicio;
    private Date fechaTermino;
}

