package com.rafael.attornatusProject.Entities;

import com.rafael.attornatusProject.Dto.PessoaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa")
public class PessoaEntity {

    @Id
    @Column(name = "id_pessoa", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EnderecoEntity> endereco;

    public PessoaEntity(String nome, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    public PessoaDto pessoaEntityToDto() {
        return new PessoaDto(idPessoa,nome,dataDeNascimento);
    }

}