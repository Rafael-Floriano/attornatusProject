package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Dto.PessoaDto;
import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Exception.PessoaNotFound;
import com.rafael.attornatusProject.Repository.PessoaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(rollbackFor = Exception.class)
    public PessoaDto salvarNovaPessoa(String nome, LocalDate dataNascimento) {
        PessoaEntity pessoaEntity = pessoaRepository.save(new PessoaEntity(nome, dataNascimento));
        return pessoaEntity.pessoaEntityToDto();
    }

    @Transactional(readOnly = true)
    public List<PessoaDto> listarTodasPessoas() {
        return listaPessoaEntityParaListaPessoaDto(pessoaRepository.findAll());
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
    public PessoaDto editarPessoa(PessoaDto pessoaDto) {
        PessoaEntity pessoaEntity = buscaApenasUmaPessoa(pessoaDto.getIdPessoa());
        pessoaEntity.setNome(pessoaDto.getNome());
        pessoaEntity.setDataDeNascimento(pessoaDto.getDataDeNascimento());
        return pessoaRepository.save(pessoaEntity).pessoaEntityToDto();
    }

    public List<PessoaDto> listaPessoaEntityParaListaPessoaDto(List<PessoaEntity> pessoaEntities) {
        List<PessoaDto> pessoaDtoLista = new ArrayList<PessoaDto>();
        pessoaEntities.stream().forEach((pessoaEntity) -> {
            pessoaDtoLista.add(pessoaEntity.pessoaEntityToDto());
        });

        return pessoaDtoLista;
    }
}
