package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    public PessoaEntity salvarNovaPessoa(String nome, Date dataNascimento) {
        return pessoaRepository.save(new PessoaEntity(nome, dataNascimento));
    }

}
