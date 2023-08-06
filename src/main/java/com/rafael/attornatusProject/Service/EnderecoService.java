package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Dto.EnderecoDto;
import com.rafael.attornatusProject.Entities.EnderecoEntity;
import com.rafael.attornatusProject.Repository.EnderecoRepository;
import com.rafael.attornatusProject.Repository.PessoaRepository;
import jakarta.persistence.PersistenceException;
import lombok.NoArgsConstructor;
import org.hibernate.query.sqm.function.SelfRenderingOrderedSetAggregateFunctionSqlAstExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
    public List<EnderecoDto> listarTodosEnderecos() {
        List<EnderecoEntity> listaDeEnderecos = enderecoRepository.findAll();

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

}
