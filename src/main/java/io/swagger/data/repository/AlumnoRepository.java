package io.swagger.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.swagger.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, String> {

}