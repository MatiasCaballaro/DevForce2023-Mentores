package com.devForce.learning.repository;

import com.devForce.learning.model.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Solicitud,Long> {



}
