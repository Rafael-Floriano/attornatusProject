package com.rafael.attornatusProject.Repository;

import com.rafael.attornatusProject.Entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {

}
