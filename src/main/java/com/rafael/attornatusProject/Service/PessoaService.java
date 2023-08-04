package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Repository.PessoaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@NoArgsConstructor
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    public PessoaEntity salvarNovaPessoa(String nome, String dataNascimento) {
        return pessoaRepository.save(new PessoaEntity(nome, dataNascimento)); 
    }

}
