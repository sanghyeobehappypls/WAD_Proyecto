package com.ipn.mx.modelo.dto;

import lombok.*;
import java.io.Serializable;
import com.ipn.mx.modelo.entidades.Genero;

@Data

public class GeneroDTO implements Serializable{
    private Genero entidad;
    
    public GeneroDTO(){
        entidad = new Genero();
    }
}
