package com.rafael.attornatusProject.Entities;

import com.rafael.attornatusProject.Dto.EnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false)
    private Long idEndereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = true)
    private PessoaEntity pessoaEntity;

    @Column(name = "principal")
    private Boolean principal;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "numero", nullable = false)
    private Long numero;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    public EnderecoEntity(EnderecoDto enderecoDto) {
        this.idEndereco = enderecoDto.getIdEndereco();
        this.principal = enderecoDto.getPrincipal();
        this.logradouro = enderecoDto.getLogradouro();
        this.cep = enderecoDto.getCep();
        this.numero = enderecoDto.getNumero();
        this.cidade = enderecoDto.getCidade();
    }

    public EnderecoDto EnderecoEntityToDto() {;
        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setIdEndereco(idEndereco);
        enderecoDto.setCep(cep);
        enderecoDto.setCidade(cidade);
        enderecoDto.setLogradouro(logradouro);
        enderecoDto.setPrincipal(principal);
        enderecoDto.setNumero(numero);
        enderecoDto.setPessoaDto(pessoaEntity.pessoaEntityToDto());
        return enderecoDto;
    }

    @PrePersist
    public void prePersist() {
        if (this.principal == null) {
            this.principal = false;
        }
    }

}
