package com.rafael.attornatusProject.Dto;

import com.rafael.attornatusProject.Entities.PessoaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

    private Long idEndereco;

    private PessoaDto pessoaDto;

    private Boolean principal;

    private String logradouro;

    private String cep;

    private Long numero;

    private String cidade;

}
