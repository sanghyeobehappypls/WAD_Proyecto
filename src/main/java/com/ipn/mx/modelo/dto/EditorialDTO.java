package com.ipn.mx.modelo.dto;

import lombok.*;
import java.io.Serializable;
import com.ipn.mx.modelo.entidades.Editorial;

@Data

public class EditorialDTO implements Serializable{
    private Editorial entidad;
    
    public EditorialDTO(){
        entidad = new Editorial();
    }
}
