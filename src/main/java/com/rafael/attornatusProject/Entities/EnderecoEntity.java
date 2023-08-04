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
    @Column(name = "id_endereco", nullable = false)
    Long idEndereco;

    @Column(name = "logradouro", nullable = false)
    String logradouro;

    @Column(name = "cep", nullable = false)
    String cep;

    @Column(name = "numero", nullable = false)
    Long numero;

    @Column(name = "cidade", nullable = false)
    String cidade;

}
