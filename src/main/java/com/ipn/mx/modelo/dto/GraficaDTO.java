package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Grafica;
import java.io.Serializable;
import lombok.*;

@Data

public class GraficaDTO implements Serializable{
    private Grafica entidad;
    
    public GraficaDTO(){
        entidad = new Grafica();
    }
}


