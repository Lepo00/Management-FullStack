package it.jac.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.management.model.Tail;

@Repository("tailRepository")
public interface TailRepository extends JpaRepository<Tail, Long> {

}
