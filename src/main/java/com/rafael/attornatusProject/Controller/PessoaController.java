package com.rafael.attornatusProject.Controller;

import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public PessoaEntity salvarNovaPessoa(@RequestParam String nome,@RequestParam String dataDeNascimento) {
        return pessoaService.salvarNovaPessoa(nome,dataDeNascimento);
    }

    @GetMapping("/buscarTodasPessoas")
    public List<PessoaEntity> buscaTodasPessoas() {
        return pessoaService.listarTodasPessoas();
    }

    @GetMapping
    public PessoaEntity buscaApenasUmaPessoa(@RequestParam Long idPessoa) {
        return pessoaService.buscaApenasUmaPessoa(idPessoa);
    }

    @PutMapping
    public PessoaEntity editarPessoa(@RequestParam Long idPessoa, @RequestParam String nome,@RequestParam String dataDeNascimento) {
        return pessoaService.editarPessoa(idPessoa, nome, dataDeNascimento);
    }

}
