package it.jac.ManagementSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.ManagementSpring.model.UnitOfMeasure;

@Repository("UnitOfMeasureRepository")
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

}