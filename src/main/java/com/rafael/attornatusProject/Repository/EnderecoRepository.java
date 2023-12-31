package com.rafael.attornatusProject.Repository;

import com.rafael.attornatusProject.Entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    @Query("SELECT COUNT(*) FROM EnderecoEntity WHERE pessoaEntity.idPessoa = :idPessoa")
    Long buscaEnderecoRepetido(@Param("idPessoa") Long idPessoa);

    @Query("SELECT end FROM EnderecoEntity end WHERE end.pessoaEntity.idPessoa = :idPessoa AND end.principal = :principal")
    List<EnderecoEntity> buscaEnderecoPorPrincipal(Long idPessoa, Boolean principal);

    @Query("SELECT end FROM EnderecoEntity end WHERE end.pessoaEntity.idPessoa = :idPessoa")
    List<EnderecoEntity> listarTodosEnderecosPorPessoa(Long idPessoa);

    @Modifying
    @Query("UPDATE EnderecoEntity end SET principal = false Where end.pessoaEntity.idPessoa = :idPessoa")
    void alteraPrincipalParaFalso(Long idPessoa);

}
