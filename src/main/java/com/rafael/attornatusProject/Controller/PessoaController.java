package com.rafael.attornatusProject.Controller;

import com.rafael.attornatusProject.Dto.PessoaDto;
import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public PessoaDto salvarNovaPessoa(@RequestParam String nome,@Validated @RequestParam LocalDate dataDeNascimento) {
        return pessoaService.salvarNovaPessoa(nome,dataDeNascimento);
    }

    @GetMapping("/buscarTodasPessoas")
    public List<PessoaDto> buscaTodasPessoas() {
        return pessoaService.listarTodasPessoas();
    }

    @GetMapping
    public PessoaDto buscaApenasUmaPessoa(@RequestParam Long idPessoa) {
        return pessoaService.buscaApenasUmaPessoa(idPessoa).pessoaEntityToDto();
    }

    @PutMapping
    public PessoaDto editarPessoa(@RequestParam Long idPessoa, @RequestParam String nome,@Validated @RequestParam LocalDate dataDeNascimento) {
        return pessoaService.editarPessoa(new PessoaDto(idPessoa, nome, dataDeNascimento));
    }

}
