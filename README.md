# AttornatusProject


## Features

- [Pessoas] É possível criar, editar, procurar por pessoas especificas e por todas as pessoas;
- [Endereço] É possível

## Endpoints de Pessoa:

#### Para Cadastrar um usuário:  
[POST] https://attornatusproject-production.up.railway.app/api/pessoa
- Parametros: nome, dataDeNascimento;

#### Buscar lista de pessoas
[GET] https://attornatusproject-production.up.railway.app/api/pessoa/buscarTodasPessoas
- Parametros: nenhum;

#### Buscar por uma pessoa
[GET] https://attornatusproject-production.up.railway.app/api/pessoa
- Parametros: idPessoa;

#### Para editar um usuário:  
[POST] https://attornatusproject-production.up.railway.app/api/pessoa
- Parametros: idPessoa, nome, dataDeNascimento;

## Endpoints de Endereço

#### Para salvar um endereço
[POST] https://attornatusproject-production.up.railway.app/api/endereco
- Parametros: logradouro, cep, numero, cidade, idPessoa, principal;

[PUT] https://attornatusproject-production.up.railway.app/api/endereco
- Parametros: logradouro, cep, numero, cidade, idPessoa, principal;

#### Buscar endereço
[GET] https://attornatusproject-production.up.railway.app/api/endereco
- Parametros: idPessoa

#### Buscar endereço por principal
[GET] https://attornatusproject-production.up.railway.app/api/endereco
- Parametros: idPessoa, principal


**Fluxo de requests prontos**

- https://attornatusproject-production.up.railway.app/api/pessoa?nome=Rafael&dataDeNascimento=2004-10-19
- https://attornatusproject-production.up.railway.app/api/pessoa/buscarTodasPessoas
- https://attornatusproject-production.up.railway.app/api/pessoa?idPessoa=1
- https://attornatusproject-production.up.railway.app/api/pessoa?idPessoa=1&nome=RafaelEditado&dataDeNascimento=2004-10-19
- https://attornatusproject-production.up.railway.app/api/endereco?principal=true&logradouro=Dr. antoinio alves&cep=345456464&numero=101&cidade=capivari de baixo&idPessoa=1
- https://attornatusproject-production.up.railway.app/api/endereco?principal=false&logradouro=Dr. antoinio alves&cep=345456464&numero=101&cidade=capivari de baixo&idPessoa=1
- https://attornatusproject-production.up.railway.app/api/endereco?idPessoa=1
- https://attornatusproject-production.up.railway.app/api/endereco/buscaEnderecoPorPrincipal?idPessoa=1&principal=true
- https://attornatusproject-production.up.railway.app/api/endereco/buscaEnderecoPorPrincipal?idPessoa=1&principal=false
- https://attornatusproject-production.up.railway.app/api/endereco?principal=true&logradouro=Dr. Antônio Alves&cep=345456464&numero=101&cidade=capivari de baixo&idPessoa=1&idEndereco=2






