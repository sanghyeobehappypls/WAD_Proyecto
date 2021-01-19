package com.ipn.mx.modelo.dto;

import lombok.*;
import java.io.Serializable;
import com.ipn.mx.modelo.entidades.Autor;

@Data

public class AutorDTO implements Serializable{
    private Autor entidad;
    
    public AutorDTO(){
        entidad = new Autor();
    }
}
