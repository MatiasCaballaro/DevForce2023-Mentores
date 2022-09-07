package com.devForce.learning.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipo", length = 25, nullable = false)
    private String tipo;

    @Column(name = "descripcion", length = 500, nullable = false)
    private String descripcion;

    @Column(name = "apruebaMentorID")
    private int apruebaMentorID;

    @Column(name = "apruebaAdminID")
    private int apruebaAdminID;

    @Column(name = "estado", length = 25, nullable = false)
    private String estado;


    //Relación con usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    //Relación con licencia
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="licencia_id")
    private Licencia licencia;


    //@JsonIgnore
    public Usuario getUsuario() {
        return usuario;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                //", tipo='" + tipo + '\'' +
                //", descripcion='" + descripcion + '\'' +
                //", apruebaMentorID=" + apruebaMentorID +
                //", apruebaAdminID=" + apruebaAdminID +
                ", estado='" + estado + '\'' +
                ", usuario=" + usuario +
                //", licencia=" + licencia +
                '}';
    }
}
