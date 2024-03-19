package org.tcc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tcc.api.model.Prioridade;

@Repository
public interface PrioridadeRepository extends JpaRepository<Prioridade,Long> {
}
