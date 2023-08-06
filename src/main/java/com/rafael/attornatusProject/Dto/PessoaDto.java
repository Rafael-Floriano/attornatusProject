package com.rafael.attornatusProject.Dto;

import com.rafael.attornatusProject.Entities.EnderecoEntity;
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
public class PessoaDto {

    private Long idPessoa;

    private String nome;

    private LocalDate dataDeNascimento;

//    private List<EnderecoDto> endereco;

}
