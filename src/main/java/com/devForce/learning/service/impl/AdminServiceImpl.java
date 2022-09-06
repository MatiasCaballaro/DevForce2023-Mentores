package com.devForce.learning.service.impl;

import com.devForce.learning.model.entity.Licencia;
import com.devForce.learning.model.entity.Solicitud;
import com.devForce.learning.model.entity.Usuario;
import com.devForce.learning.repository.LicenciaRepository;
import com.devForce.learning.repository.SolicitudRepository;
import com.devForce.learning.repository.UsuarioRepository;
import com.devForce.learning.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SolicitudRepository solicitudRepository;

    @Autowired
    LicenciaRepository licenciaRepository;


    @Override
    public ResponseEntity<String> crearUsuario(Usuario usuario) {
        //TODO: Chequear que soy un usuario Admin
        Usuario usuarioYaExiste=usuarioRepository.findByNombreAndApellido(usuario.getNombre(), usuario.getApellido());
        if(usuarioYaExiste==null) {
            addUsuario(usuario);
            return new ResponseEntity<>("Usuario Creado", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Usuario ya existe", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void addUsuario(Usuario usuario) {
        Usuario newUsuario = new Usuario(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getUsername(),
                usuario.getMail(),
                usuario.getPassword(),
                usuario.getPhone(),
                usuario.getRol(),
                usuario.getHasTeams()
        );
        usuarioRepository.save(newUsuario);
    }

    //TODO: Realizar el mètodo de asignar licencia
    @Override
    public ResponseEntity<String> asignarLicencia(Usuario usuario, Licencia licencia) {
        // Verificar que el usuario logueado sea admin

        //

        //if (  ) {
            // usuario tiene licencia?
            List<Solicitud> solicitudesAceptadas = solicitudRepository.findByUsuarioAndEstado(usuario, "ACEPTADO");

            for (Solicitud solicitud : solicitudesAceptadas){
                //(solicitud.getLicencia() != null) ? ((solicitud.getLicencia().equals(licencia)) ? break : continue) : continue;

                if (solicitud.getLicencia() != null){

                    // verificar que la licencia no esté fuera de plazo
                    // TODO: CONSULTAR A MATI SI EXPDATE SE REFIERE A LA FECHA HASTA LA QUE LA LICENCIA LE PERTENECE A UN USUARIO. O SI ES UN VENCIMIENTO DE UDEMY Y DEJA DE SER DE GIRE.
                    if (licencia.getExpdate().isBefore(LocalDateTime.now())) {
                        return new ResponseEntity<>("Licencia vencida",HttpStatus.BAD_REQUEST);
                    }

                    if (solicitud.getLicencia().equals(licencia)){
                        return new ResponseEntity<>("Usuario ya tiene asignada esta licencia", HttpStatus.BAD_REQUEST);
                    } else {
                        return new ResponseEntity<>("Usuario ya tiene asignada una licencia", HttpStatus.BAD_REQUEST);
                    }
                }

            }


            // TODO: Anda y se actualiza en la H2 pero revienta cuando lo queremos mostrar en POSTMAN
            Long idMax = solicitudesAceptadas.stream().map(Solicitud::getId).max(Long::compare).get();

            System.out.println("El idMax es: " + idMax);
            Solicitud solicitudIdMax = solicitudesAceptadas.stream().filter(solicitud -> solicitud.getId() == idMax).findAny().get();
            solicitudIdMax.setLicencia(licencia);

            List<Solicitud> listaAux = new ArrayList<>();
            listaAux.add(solicitudIdMax);

            licencia.setSolicitudes(listaAux);
            usuarioRepository.save(usuario);
            solicitudRepository.save(solicitudIdMax);
            licenciaRepository.save(licencia);

            return new ResponseEntity<>("OK", HttpStatus.OK);
        /*
        } else {

        }
        */

    }



}
