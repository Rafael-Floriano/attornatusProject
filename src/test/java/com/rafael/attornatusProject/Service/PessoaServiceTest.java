package com.rafael.attornatusProject.Service;
import com.rafael.attornatusProject.Dto.PessoaDto;
import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Exception.UnprocessableEntityException;
import com.rafael.attornatusProject.Repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;
    @InjectMocks
    private PessoaService pessoaService;

    String nome;
    LocalDate dataDeNascimento;

    @BeforeEach
    public void setUp() {
        nome = "Rafael";
        dataDeNascimento = LocalDate.of(2004, 10, 19);
    }

    @Nested
    class salvarNovaPessoa{
        @Test
        public void salvarNovaPessoaComDadosPreenchidos() {
            when(pessoaRepository.save(any(PessoaEntity.class))).thenReturn(new PessoaEntity(nome, dataDeNascimento));
            PessoaDto pessoaDto = pessoaService.salvarNovaPessoa(nome, dataDeNascimento);
            assertEquals(nome, pessoaDto.getNome());
            assertEquals(dataDeNascimento, pessoaDto.getDataDeNascimento());
        }

        @Test
        public void salvarNovaPessoaComNomeNulo() {
            assertThrows(UnprocessableEntityException.class, () -> pessoaService.salvarNovaPessoa(null, dataDeNascimento));
        }

        @Test
        public void salvarNovaPessoaComDataDeNascimentoNulo() {
            assertThrows(NullPointerException.class, () -> pessoaService.salvarNovaPessoa(nome, null));
        }

    }

}
