package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Dto.PessoaDto;
import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Exception.PessoaNotFound;
import com.rafael.attornatusProject.Repository.PessoaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(rollbackFor = Exception.class)
    public PessoaEntity salvarNovaPessoa(String nome, String dataNascimento) {
        return pessoaRepository.save(new PessoaEntity(nome, dataNascimento)); 
    }

    @Transactional(readOnly = true)
    public List<PessoaEntity> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PessoaEntity buscaApenasUmaPessoa(Long idPessoa) {
        Optional<PessoaEntity> optionalPessoaEntity = pessoaRepository.findById(idPessoa);

        if (!optionalPessoaEntity.isPresent()) {
            throw new PessoaNotFound("Não foi possível encontrar essa pessoa");
        }

        return optionalPessoaEntity.get();
    }

    @Transactional(rollbackFor = Exception.class)
    public PessoaEntity editarPessoa(PessoaDto pessoaDto) {
        PessoaEntity pessoaEntity = buscaApenasUmaPessoa(pessoaDto.getIdPessoa());
        pessoaEntity.setNome(pessoaDto.getNome());
        pessoaEntity.setDataDeNascimento(pessoaDto.getDataDeNascimento());
        return pessoaRepository.save(pessoaEntity);
    }
}
