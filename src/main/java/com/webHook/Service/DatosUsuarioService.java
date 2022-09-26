package com.webHook.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webHook.Entity.DatosUsuario;
import com.webHook.Repository.DatosUsuarioRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DatosUsuarioService {

    @Autowired
    DatosUsuarioRepository datosUsuarioRepository;


    public Optional<DatosUsuario> getOne(UUID id){
        return datosUsuarioRepository.findById(id);
    }

    public DatosUsuario  save(DatosUsuario actividad){
    	return datosUsuarioRepository.save(actividad);
    }
    
}
