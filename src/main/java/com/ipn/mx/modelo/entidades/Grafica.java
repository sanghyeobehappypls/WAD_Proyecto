package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Grafica implements Serializable{
    private String nombre;
    private long cantidad;   
}




