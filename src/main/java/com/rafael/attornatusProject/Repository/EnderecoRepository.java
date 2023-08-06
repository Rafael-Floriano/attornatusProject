package com.rafael.attornatusProject.Repository;

import com.rafael.attornatusProject.Entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    @Query("SELECT COUNT(*) FROM EnderecoEntity WHERE pessoaEntity.idPessoa = :idPessoa")
    Long buscaEnderecoRepetido(@Param("idPessoa") Long idPessoa);
}
