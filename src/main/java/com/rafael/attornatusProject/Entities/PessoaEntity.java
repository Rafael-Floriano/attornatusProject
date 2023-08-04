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

    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long IdPessoa;

    @Column(name = "nome")
    String Nome;

    @Column(name = "data_de_nascimento")
    Date DataDeNascimento;

    @OneToMany
    List<EnderecoEntity> Enderecos;

}