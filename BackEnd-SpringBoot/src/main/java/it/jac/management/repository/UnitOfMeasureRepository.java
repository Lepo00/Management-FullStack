package it.jac.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.management.model.UnitOfMeasure;

@Repository("unitOfMeasureRepository")
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

}