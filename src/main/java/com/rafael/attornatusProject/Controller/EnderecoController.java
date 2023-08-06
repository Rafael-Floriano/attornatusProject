package com.rafael.attornatusProject.Controller;

import com.rafael.attornatusProject.Dto.EnderecoDto;
import com.rafael.attornatusProject.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public EnderecoDto salvarNovoEndereco(Boolean principal, String logradouro, String cep, Long numero, String cidade, Long idPessoa) {
        return enderecoService.salvarNovoEndereco(new EnderecoDto(null,null ,principal,logradouro,cep,numero,cidade), idPessoa);
    }

    @GetMapping
    public List<EnderecoDto> buscaTodosEnderecos() {
        return enderecoService.listarTodosEnderecos();
    }
}
