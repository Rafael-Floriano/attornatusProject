package com.rafael.attornatusProject.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    Long IdEndereco;

    @Column(name = "logradouro")
    String Logradouro;

    @Column(name = "cep")
    String Cep;

    @Column(name = "numero")
    Long Numero;

    @Column(name = "cidade")
    String cidade;

}
