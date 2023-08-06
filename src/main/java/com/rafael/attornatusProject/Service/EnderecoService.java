package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Entities.EnderecoEntity;
import com.rafael.attornatusProject.Repository.EnderecoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoEntity salvarNovoEndereco() {
        return new EnderecoEntity();
    }
}
