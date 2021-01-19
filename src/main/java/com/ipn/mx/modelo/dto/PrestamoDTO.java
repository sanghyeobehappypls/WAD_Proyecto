package com.ipn.mx.modelo.dto;

import lombok.Data;
import java.io.Serializable;
import com.ipn.mx.modelo.entidades.Prestamo;

@Data

public class PrestamoDTO implements Serializable{
     
    private Prestamo entidad;
    
    public PrestamoDTO(){
        entidad = new Prestamo();
    }
}


