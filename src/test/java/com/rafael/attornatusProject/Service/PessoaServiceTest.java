package com.rafael.attornatusProject.Service;
import com.rafael.attornatusProject.Dto.PessoaDto;
import com.rafael.attornatusProject.Entities.PessoaEntity;
import com.rafael.attornatusProject.Exception.NotFound;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        public void TestesalvarNovaPessoaComDadosPreenchidos() {
            when(pessoaRepository.save(any(PessoaEntity.class))).thenReturn(new PessoaEntity(nome, dataDeNascimento));
            PessoaDto pessoaDto = pessoaService.salvarNovaPessoa(nome, dataDeNascimento);
            assertEquals(nome, pessoaDto.getNome());
            assertEquals(dataDeNascimento, pessoaDto.getDataDeNascimento());
        }

        @Test
        public void TestesalvarNovaPessoaComNomeNulo() {
            assertThrows(UnprocessableEntityException.class, () -> pessoaService.salvarNovaPessoa(null, dataDeNascimento));
        }

        @Test
        public void TestesalvarNovaPessoaComDataDeNascimentoNulo() {
            assertThrows(NullPointerException.class, () -> pessoaService.salvarNovaPessoa(nome, null));
        }

    }

    @Nested
    class listarTodasPessoas{
        @Test
        public void TesteListarTodasPessoas() {
            List<PessoaEntity> pessoaEntityList = Arrays.asList(
                    new PessoaEntity("Rafael", LocalDate.of(2004, 5, 15)),
                    new PessoaEntity("Sara", LocalDate.of(2005, 10, 25))
            );
            when(pessoaRepository.findAll()).thenReturn(pessoaEntityList);

            List<PessoaDto> pessoaDtoList = pessoaService.listarTodasPessoas();

            assertEquals(2, pessoaDtoList.size());
            assertEquals("Rafael", pessoaDtoList.get(0).getNome());
            assertEquals(LocalDate.of(2004, 5, 15), pessoaDtoList.get(0).getDataDeNascimento());
            assertEquals("Sara", pessoaDtoList.get(1).getNome());
            assertEquals(LocalDate.of(2005, 10, 25), pessoaDtoList.get(1).getDataDeNascimento());
        }
    }

    @Nested
    class buscaApenasUmaPessoa {
        @Test
        public void TestebuscaApenasUmaPessoaComIdPessoa() {
            PessoaEntity pessoaEntity = new PessoaEntity(1L, "Rafael", LocalDate.of(2004, 5, 15), null);
            when(pessoaRepository.findById(any(Long.class))).thenReturn(Optional.of(pessoaEntity));
            PessoaEntity resultadoPessoaEntity = pessoaService.buscaApenasUmaPessoa(1L);

            assertEquals("Rafael", resultadoPessoaEntity.getNome());
            assertEquals(LocalDate.of(2004, 5, 15), resultadoPessoaEntity.getDataDeNascimento());
            assertEquals(1L, resultadoPessoaEntity.getIdPessoa());
        }

        @Test()
        public void TestebuscaApenasUmaPessoaComIdPessoaNulo() {
            when(pessoaRepository.findById(any(Long.class))).thenThrow(NullPointerException.class);
            assertThrows(NullPointerException.class, () -> pessoaService.buscaApenasUmaPessoa(1L));
        }

        @Test
        public void TestebuscaApenasUmaPessoaNaoEncontraRegistroNoBanco() {
            when(pessoaRepository.findById(any(Long.class))).thenReturn(Optional.empty());
            assertThrows(NotFound.class, () -> pessoaService.buscaApenasUmaPessoa(1L));
        }
    }

    @Nested
    class editarPessoa {
        @Test
        public void TesteEditarPessoaTodosOsDadosPreenchidos() {
            PessoaDto pessoaDto = new PessoaDto(1L, "Rafael", LocalDate.of(2004, 5, 15));
            PessoaEntity pessoaEntity = new PessoaEntity(1L, "Alice", LocalDate.of(2004, 5, 15), null);
            when(pessoaRepository.findById(any(Long.class))).thenReturn(Optional.of(pessoaEntity));
            when(pessoaRepository.save(any(PessoaEntity.class))).thenReturn(pessoaEntity);
            PessoaDto resultadoPessoaDto = pessoaService.editarPessoa(pessoaDto);

            assertEquals("Rafael" ,resultadoPessoaDto.getNome());
            assertEquals(LocalDate.of(2004, 5, 15) ,resultadoPessoaDto.getDataDeNascimento());
        }

        @Test
        public void TesteEditarPessoaSemNomePreenchido() {
            PessoaDto pessoaDto = new PessoaDto(1L, null, LocalDate.of(2004, 5, 15));
            PessoaEntity pessoaEntity = new PessoaEntity(1L, "Alice", LocalDate.of(2004, 5, 15), null);
            when(pessoaRepository.findById(any(Long.class))).thenReturn(Optional.of(pessoaEntity));
            when(pessoaRepository.save(any(PessoaEntity.class))).thenThrow(NullPointerException.class);

            assertThrows(NullPointerException.class, () -> pessoaService.editarPessoa(pessoaDto));
        }

    }

    @Nested
    class listaPessoaEntityParaListaPessoaDto{

        @Test
        public void TestelistaPessoaEntityParaListaPessoaDtoComListaVazia() {
            List<PessoaEntity> pessoaListaNula = Arrays.asList(new PessoaEntity());
            List<PessoaDto> pessoaListaEsperada = Arrays.asList(new PessoaDto());
            assertEquals(pessoaListaEsperada.size(), pessoaService.listaPessoaEntityParaListaPessoaDto(pessoaListaNula).size());
        }

        @Test
        public void TestelistaPessoaEntityParaListaPessoaDtoComDadosPreenchidos() {
            List<PessoaEntity> pessoaEntityList = Arrays.asList(
                    new PessoaEntity(1L,"Rafael", LocalDate.of(2004, 5, 15),null));
            List<PessoaDto> pessoaDtoListaEsperada = Arrays.asList(
                    new PessoaDto(1L, "Rafael", LocalDate.of(2004, 5, 15)));

            List<PessoaDto> pessoaDtoResultado = pessoaService.listaPessoaEntityParaListaPessoaDto(pessoaEntityList);

            assertEquals(pessoaDtoListaEsperada.size(), pessoaDtoResultado.size());
            assertEquals(pessoaDtoListaEsperada.get(0).getIdPessoa(), pessoaDtoResultado.get(0).getIdPessoa());
            assertEquals(pessoaDtoListaEsperada.get(0).getNome(), pessoaDtoResultado.get(0).getNome());
            assertEquals(pessoaDtoListaEsperada.get(0).getDataDeNascimento(), pessoaDtoResultado.get(0).getDataDeNascimento());

        }

    }

    @Nested
    class ChecaSePessoaExiste {
        @Test
        public void TesteChecaSePessoaExisteCasoExista() {
            PessoaEntity pessoaEntity = new PessoaEntity(1L, "Alice", LocalDate.of(2004, 5, 15), null);
            when(pessoaRepository.findById(any(Long.class))).thenReturn(Optional.of(pessoaEntity));
            assertEquals(true, pessoaService.ChecaSePessoaExiste(1L));
        }

        @Test
        public void TesteChecaSePessoaExisteCasoNaoExista() {
            PessoaEntity pessoaEntity = new PessoaEntity(1L, "Alice", LocalDate.of(2004, 5, 15), null);
            when(pessoaRepository.findById(any(Long.class))).thenReturn(Optional.empty());
            assertEquals(false, pessoaService.ChecaSePessoaExiste(1L));
        }
    }
}

