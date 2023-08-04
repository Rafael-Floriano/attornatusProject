package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    public void salvarNovaPessoa() {
        String mockNome = "Rafael";
        String mockNascimento = "2004-10-19";

        when(pessoaService.salvarNovaPessoa(eq("String by matcher"),eq("String by matcher"))).thenReturn(new PessoaEntity(mockNome, mockNascimento));
        PessoaEntity pessoaSalva = pessoaService.salvarNovaPessoa(mockNome, mockNascimento);

        assertNotNull(pessoaSalva);
        assertEquals(mockNome, pessoaSalva.getNome());
        assertEquals(mockNascimento, pessoaSalva.getDataDeNascimento());
    }


}
