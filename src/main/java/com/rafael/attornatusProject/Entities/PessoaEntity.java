package com.rafael.attornatusProject.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    Long idPessoa;

    @Column(name = "nome", nullable = false)
    String nome;

    @Column(name = "data_de_nascimento")
    String dataDeNascimento;

    @OneToMany
    List<EnderecoEntity> enderecos;

    public PessoaEntity(String nome, String dataDeNascimento) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

}