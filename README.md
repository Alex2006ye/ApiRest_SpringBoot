API de Cadastro de Usuários

API REST desenvolvida em Java com Spring Boot, permitindo o cadastro e autenticação de usuários,
aplicando conceitos de **Design Patterns** e boas práticas de arquitetura, tudo isso para melhorar e consolidar o meu aprendizado.

Arquitetura do Projeto

O projeto segue uma arquitetura em camadas:

- Controller: Camada de entrada (HTTP)
- Service: Regras de negócio
- Repository: Acesso ao banco de dados
- Strategy: Lógica de criação de usuários por tipo
- DTO: Usados para o transporte seguro dos dados

Design Patterns Aplicados
  Strategy Pattern
    Utilizado para definir diferentes comportamentos de criação de usuários de acordo com seu papel (ROLE).

O `PessoaService` seleciona dinamicamente a estratégia correta com base no tipo de usuário:

CriarPessoaStrategy strategy = strategies.get(dto.role().name()); basicamente aqui, foi usado um Map(Onde a chave é tipo de usuário, e o valor é a estratégia que seria usada no cadastro)
strategy.criarPessoa(dto, encryptedPassword);

é valido mencionar também, que eu ainda não realizei a aplicação do JWT para uma maior segurança do projeto
mas pretendo sim colocá-lo futuramente
