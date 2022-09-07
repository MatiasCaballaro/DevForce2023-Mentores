package com.devForce.learning.service.impl;

import com.devForce.learning.model.entity.Solicitud;
import com.devForce.learning.repository.LicenciaRepository;
import com.devForce.learning.repository.MentorRepository;
import com.devForce.learning.repository.SolicitudRepository;
import com.devForce.learning.repository.UsuarioRepository;
import com.devForce.learning.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorServiceImpl implements MentorService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SolicitudRepository solicitudRepository;

    @Autowired
    LicenciaRepository licenciaRepository;

    @Autowired
    MentorRepository mentorRepository;

    @Override
    public void placeHolder(Solicitud solicitud) {
        System.out.println("hola");
    }
}
