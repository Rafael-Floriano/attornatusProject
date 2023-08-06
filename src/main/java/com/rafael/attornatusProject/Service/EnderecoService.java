package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Dto.EnderecoDto;
import com.rafael.attornatusProject.Entities.EnderecoEntity;
import com.rafael.attornatusProject.Exception.PessoaNotFound;
import com.rafael.attornatusProject.Repository.EnderecoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaService pessoaService;

    @Transactional(rollbackFor = Exception.class)
    public EnderecoDto salvarNovoEndereco(EnderecoDto enderecoDto, Long idPessoa) {
        EnderecoEntity enderecoEntity = new EnderecoEntity(enderecoDto);

        checaSeExisteEnderecoPrincipal(enderecoEntity, idPessoa);

        enderecoEntity.setPessoaEntity(pessoaService.buscaApenasUmaPessoa(idPessoa));
        enderecoRepository.save(enderecoEntity);
        return enderecoEntity.EnderecoEntityToDto();
    }

    @Transactional(readOnly = true)
    public List<EnderecoDto> listarTodosEnderecosPorPessoa(Long idPessoa) {
        List<EnderecoEntity> listaDeEnderecos = buscaPorEnderecosPessoa(idPessoa);

        List<EnderecoDto> enderecoDtos = new ArrayList<EnderecoDto>();
        listaDeEnderecos.stream().forEach((endereco) -> {
            enderecoDtos.add(endereco.EnderecoEntityToDto());
        });

        return enderecoDtos;
    }

    @Transactional(readOnly = true)
    public Long buscaEnderecoRepetido(Long idPessoa) {
        return enderecoRepository.buscaEnderecoRepetido(idPessoa);
    }

    @Transactional(readOnly = true)
    public List<EnderecoDto> buscaEnderecoPorPrincipal(Long idPessoa, Boolean principal) {
        List<EnderecoEntity> listaDeEnderecosEntity = enderecoRepository.buscaEnderecoPorPrincipal(idPessoa, principal);
        List<EnderecoDto> listaEnderecoDto = new ArrayList<>();
        listaDeEnderecosEntity.stream().forEach(enderecoEntity -> {
           listaEnderecoDto.add(enderecoEntity.EnderecoEntityToDto());
        });

        return listaEnderecoDto;
    }

    @Transactional(readOnly = true)
    public void checaSeExisteEnderecoPrincipal(EnderecoEntity enderecoEntity, Long idPessoa){
        if (enderecoEntity.getPrincipal() == true) {
            if (buscaEnderecoRepetido(idPessoa) == 1L) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só pode haver um endereço principal para a pessoa");
            }
        }
    }

    @Transactional(readOnly = true)
    public List<EnderecoEntity>  buscaPorEnderecosPessoa(Long idPessoa) {
        List<EnderecoEntity> listaEnderecoEntity = enderecoRepository.listarTodosEnderecosPorPessoa(idPessoa);

        if (listaEnderecoEntity.isEmpty()) {
            throw new PessoaNotFound("Essa Pessoa não possui endereços");
        }

        return listaEnderecoEntity;
    }

}
