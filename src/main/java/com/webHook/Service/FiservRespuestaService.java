package com.webHook.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webHook.Entity.FiservRespuesta;
import com.webHook.Repository.FiservRespuestaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FiservRespuestaService {

    @Autowired
    FiservRespuestaRepository datosUsuarioRepository;


    public Optional<FiservRespuesta> getOne(UUID id){
        return datosUsuarioRepository.findById(id);
    }

    public FiservRespuesta  save(FiservRespuesta actividad){
    	return datosUsuarioRepository.save(actividad);
    }
    
}
