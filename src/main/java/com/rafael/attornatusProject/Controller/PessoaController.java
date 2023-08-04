package com.rafael.attornatusProject.Controller;

import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public PessoaEntity salvarNovaPessoa(@RequestParam String nome,@RequestParam String dataDeNascimento) {
        return pessoaService.salvarNovaPessoa(nome,dataDeNascimento);
    }

}
