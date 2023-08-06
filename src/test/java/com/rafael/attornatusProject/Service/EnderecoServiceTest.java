package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Dto.EnderecoDto;
import com.rafael.attornatusProject.Dto.PessoaDto;
import com.rafael.attornatusProject.Entities.EnderecoEntity;
import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Repository.EnderecoRepository;
import com.rafael.attornatusProject.Repository.PessoaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private PessoaService pessoaService;

    PessoaDto pessoaDto = new PessoaDto(1L, "Rafael", LocalDate.of(2004, 5, 15));
    EnderecoDto enderecoDto = new EnderecoDto(1L, pessoaDto, true, "Dr.antonio alves", "48431-5464", 101L, "Capivari");

    @Nested
    class salvarNovoEndereco {

        @Test
        public void salvarNovoEnderecoComTodosDadosPreenchidos() {
            when(enderecoRepository.buscaEnderecoRepetido(any(Long.class))).thenReturn(0L);
            when(pessoaService.buscaApenasUmaPessoa(any(Long.class))).thenReturn(new PessoaEntity("Rafael",LocalDate.of(2004, 5, 15)));
            when(enderecoRepository.save(any(EnderecoEntity.class))).thenReturn(new EnderecoEntity(enderecoDto));

            EnderecoDto enderecoDtoResultado = enderecoService.salvarNovoEndereco(enderecoDto, 1L);

            assertEquals(enderecoDto.getIdEndereco(), enderecoDtoResultado.getIdEndereco());
            assertEquals(enderecoDto.getNumero(), enderecoDtoResultado.getNumero());
            assertEquals(enderecoDto.getPrincipal(), enderecoDtoResultado.getPrincipal());
            assertEquals(enderecoDto.getCep(), enderecoDtoResultado.getCep());
        }


        @Test
        public void salvarNovoEnderecoSemIdPessoa() {
            when(enderecoRepository.buscaEnderecoRepetido(any(Long.class))).thenReturn(0L);
            when(pessoaService.buscaApenasUmaPessoa(any(Long.class))).thenReturn(new PessoaEntity("Rafael",LocalDate.of(2004, 5, 15)));
            when(enderecoRepository.save(any(EnderecoEntity.class))).thenReturn(new EnderecoEntity(enderecoDto));

            EnderecoDto enderecoDtoResultado = enderecoService.salvarNovoEndereco(enderecoDto, 1L);

            assertEquals(enderecoDto.getIdEndereco(), enderecoDtoResultado.getIdEndereco());
            assertEquals(enderecoDto.getNumero(), enderecoDtoResultado.getNumero());
            assertEquals(enderecoDto.getPrincipal(), enderecoDtoResultado.getPrincipal());
            assertEquals(enderecoDto.getCep(), enderecoDtoResultado.getCep());
        }
    }


}
