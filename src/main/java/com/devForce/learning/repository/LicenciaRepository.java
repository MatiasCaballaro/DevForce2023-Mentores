package com.devForce.learning.repository;

import com.devForce.learning.model.entity.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenciaRepository extends JpaRepository <Licencia, Long> {
}
