package com.ipn.mx.modelo.dto;

import lombok.*;
import java.io.Serializable;
import com.ipn.mx.modelo.entidades.Libro;

@Data

public class LibroDTO implements Serializable{
    private Libro entidad;
    
    public LibroDTO(){
        entidad = new Libro();
    }
}
