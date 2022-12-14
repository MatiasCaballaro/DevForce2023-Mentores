package com.devForce.learning.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", length = 50, nullable = false)
    private String nombre;

    @Column(name = "lastname", length = 50, nullable = false)
    private String apellido;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "mail", length = 100)
    private String mail;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "rol", length = 25, nullable = false)
    private String rol;

    @Column(name = "hasTeams")
    private Boolean hasTeams;


    //Relación con solicitud
    @OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
    private Set<Solicitud> solicitudes;



    //Constructor


    public Usuario(String nombre, String apellido, String username, String mail, String password, String phone, String rol, Boolean hasTeams) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.rol = rol;
        this.hasTeams = hasTeams;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", rol='" + rol + '\'' +
                ", hasTeams=" + hasTeams +
//                ", solicitudes=" + solicitudes.stream().map(s -> s.getSolicitudId()).collect(Collectors.toList()) +
                '}';
    }
}
