package com.rafael.attornatusProject.Service;

import com.rafael.attornatusProject.Dto.EnderecoDto;
import com.rafael.attornatusProject.Dto.PessoaDto;
import com.rafael.attornatusProject.Entities.EnderecoEntity;
import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Exception.PessoaNotFound;
import com.rafael.attornatusProject.Repository.EnderecoRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        public void TestesalvarNovoEnderecoComTodosDadosPreenchidos() {
            when(enderecoRepository.buscaEnderecoRepetido(any(Long.class))).thenReturn(0L);
            when(pessoaService.buscaApenasUmaPessoa(any(Long.class))).thenReturn(new PessoaEntity("Rafael",LocalDate.of(2004, 5, 15)));
            when(enderecoRepository.save(any(EnderecoEntity.class))).thenReturn(new EnderecoEntity(enderecoDto));
            when(pessoaService.ChecaSePessoaExiste(any(Long.class))).thenReturn(true);

            EnderecoDto enderecoDtoResultado = enderecoService.salvarNovoEndereco(enderecoDto, 1L);

            assertEquals(enderecoDto.getIdEndereco(), enderecoDtoResultado.getIdEndereco());
            assertEquals(enderecoDto.getNumero(), enderecoDtoResultado.getNumero());
            assertEquals(enderecoDto.getPrincipal(), enderecoDtoResultado.getPrincipal());
            assertEquals(enderecoDto.getCep(), enderecoDtoResultado.getCep());
        }


        @Test
        public void TestesalvarNovoEnderecoSemIdPessoa() {
            when(pessoaService.ChecaSePessoaExiste(null)).thenThrow(PessoaNotFound.class);
            assertThrows(PessoaNotFound.class, () -> enderecoService.salvarNovoEndereco(enderecoDto, null ));
        }

        @Test
        public void TestesalvarNovoEnderecoComPrincipalNulo() {
            enderecoDto.setPrincipal(null);
            when(pessoaService.buscaApenasUmaPessoa(any(Long.class))).thenReturn(new PessoaEntity("Rafael",LocalDate.of(2004, 5, 15)));
            when(enderecoRepository.save(any(EnderecoEntity.class))).thenReturn(new EnderecoEntity(enderecoDto));
            when(pessoaService.ChecaSePessoaExiste(any(Long.class))).thenReturn(true);

            EnderecoDto enderecoDtoResultado = enderecoService.salvarNovoEndereco(enderecoDto, 1L);

            assertEquals(false, enderecoDtoResultado.getPrincipal());
        }
    }

    @Nested
    class listarTodosEnderecosPorPessoa {

        @Test
        public void TestelistarTodosEnderecosPorPessoaTodoDadosPreenchidos() {
            PessoaEntity pessoaEntity = new PessoaEntity("Rafael", LocalDate.of(2004, 5, 15));
            List<EnderecoEntity> enderecoEntities = Arrays.asList(new EnderecoEntity(
                    1L, pessoaEntity, true, "Dr.antonio alves", "48431-5464", 101L, "Capivari"
            ));

            when(enderecoRepository.listarTodosEnderecosPorPessoa(any(Long.class))).thenReturn(enderecoEntities);
            when(enderecoService.buscaPorEnderecosPessoa(any(Long.class))).thenReturn(enderecoEntities);
            List<EnderecoDto> enderecoDtoResultado = enderecoService.listarTodosEnderecosPorPessoa(1L);

            assertEquals(1L, enderecoDtoResultado.get(0).getIdEndereco());
            assertEquals("Dr.antonio alves", enderecoDtoResultado.get(0).getLogradouro());
            assertEquals(101L, enderecoDtoResultado.get(0).getNumero());
            assertEquals("48431-5464", enderecoDtoResultado.get(0).getCep());
            assertEquals("Capivari", enderecoDtoResultado.get(0).getCidade());
        }

        @Test
        public void TestelistarTodosEnderecosCasoPessoaNaoTenhaEndereco() {
            PessoaEntity pessoaEntity = new PessoaEntity("Rafael", LocalDate.of(2004, 5, 15));
            List<EnderecoEntity> enderecoEntities = Arrays.asList(new EnderecoEntity(
                    1L, pessoaEntity, true, "Dr.antonio alves", "48431-5464", 101L, "Capivari"
            ));

            assertThrows(PessoaNotFound.class, () -> enderecoService.listarTodosEnderecosPorPessoa(1L));
        }

    }

    @Nested
    class buscaEnderecoPorPrincipal{

        @Test
        public void TestebuscaEnderecoPorPrincipalComDadosPreenchidosPrincipalTrue() {
            List<EnderecoEntity> enderecoList = new ArrayList<>();
            PessoaEntity pessoa = new PessoaEntity("Maria", LocalDate.of(1990, 7, 25));
            enderecoList.add(new EnderecoEntity(2L, pessoa, true, "Rua B", "98765-432", 200L, "Rio de Janeiro"));
            when(enderecoRepository.buscaEnderecoPorPrincipal(any(Long.class), any(Boolean.class))).thenReturn(new ArrayList<EnderecoEntity>(enderecoList));
            List<EnderecoDto> enderecoDtoListaResultado = enderecoService.buscaEnderecoPorPrincipal(1L, true);

            assertEquals("Maria", enderecoDtoListaResultado.get(0).getPessoaDto().getNome());
            assertEquals(enderecoList.get(0).getIdEndereco(), enderecoDtoListaResultado.get(0).getIdEndereco());
            assertEquals(enderecoList.get(0).getLogradouro(), enderecoDtoListaResultado.get(0).getLogradouro());
            assertEquals(enderecoList.get(0).getNumero(), enderecoDtoListaResultado.get(0).getNumero());
            assertEquals(enderecoList.get(0).getCep(), enderecoDtoListaResultado.get(0).getCep());
            assertEquals(enderecoList.get(0).getCidade(), enderecoDtoListaResultado.get(0).getCidade());
        }

        @Test
        public void TestebuscaEnderecoPorPrincipalComDadosPreenchidosPrincipalFalse() {
            List<EnderecoEntity> enderecoList = new ArrayList<>();
            PessoaEntity pessoa = new PessoaEntity("Sara", LocalDate.of(2005, 7, 25));
            enderecoList.add(new EnderecoEntity(2L, pessoa, false, "Rua B", "98765-432", 200L, "Rio de Janeiro"));
            when(enderecoRepository.buscaEnderecoPorPrincipal(any(Long.class), any(Boolean.class))).thenReturn(new ArrayList<EnderecoEntity>(enderecoList));
            List<EnderecoDto> enderecoDtoListaResultado = enderecoService.buscaEnderecoPorPrincipal(1L, false);

            assertEquals("Sara", enderecoDtoListaResultado.get(0).getPessoaDto().getNome());
            assertEquals(enderecoList.get(0).getIdEndereco(), enderecoDtoListaResultado.get(0).getIdEndereco());
            assertEquals(enderecoList.get(0).getLogradouro(), enderecoDtoListaResultado.get(0).getLogradouro());
            assertEquals(enderecoList.get(0).getNumero(), enderecoDtoListaResultado.get(0).getNumero());
            assertEquals(enderecoList.get(0).getCep(), enderecoDtoListaResultado.get(0).getCep());
            assertEquals(enderecoList.get(0).getCidade(), enderecoDtoListaResultado.get(0).getCidade());
        }

    }

    @Nested
    class checaSeExisteEnderecoPrincipal {
        @Test
        public void TestechecaSeExisteEnderecoPrincipalCasoJaExista() {
            PessoaEntity pessoa = new PessoaEntity("Sara", LocalDate.of(2005, 7, 25));
            EnderecoEntity endereco = new EnderecoEntity(2L, pessoa, true, "Rua B", "98765-432", 200L, "Rio de Janeiro");
            when(enderecoService.buscaEnderecoRepetido(any(Long.class))).thenReturn(1L);
            assertThrows(ResponseStatusException.class, () -> enderecoService.checaSeExisteEnderecoPrincipal(endereco,1L));
        }

        @Test
        public void TestechecaSeExisteEnderecoPrincipalCasoNaoExista() {
            PessoaEntity pessoa = new PessoaEntity("Sara", LocalDate.of(2005, 7, 25));
            EnderecoEntity endereco = new EnderecoEntity(2L, pessoa, true, "Rua B", "98765-432", 200L, "Rio de Janeiro");
            when(enderecoService.buscaEnderecoRepetido(any(Long.class))).thenReturn(0L);
            enderecoService.checaSeExisteEnderecoPrincipal(endereco,1L);
        }

        @Test
        public void TestechecaSeExisteEnderecoPrincipalCasoPrincipalFalse() {
            PessoaEntity pessoa = new PessoaEntity("Sara", LocalDate.of(2005, 7, 25));
            EnderecoEntity endereco = new EnderecoEntity(2L, pessoa, false, "Rua B", "98765-432", 200L, "Rio de Janeiro");
            enderecoService.checaSeExisteEnderecoPrincipal(endereco,1L);
        }
    }

}
