package com.ipn.mx.modelo.dto;

import lombok.*;
import java.io.Serializable;
import com.ipn.mx.modelo.entidades.Usuario;

@Data

public class UsuarioDTO implements Serializable{
    private Usuario entidad;
    
    public UsuarioDTO(){
        entidad = new Usuario();
    }
}
