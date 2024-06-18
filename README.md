# Agrix

Boas-vindas ao repositório Agrix.

Este projeto apresenta uma API RESTful que facilita a gestão e o monitoramento de fazendas, possibilitando às pessoas usuárias criar, visualizar, atualizar e excluir fazendas, plantações e fertilizantes de forma intuitiva e prática. A API oferece endpoints específicos para operações CRUD (Create, Read, Update, Delete) em listas de plantações e fazendas, visando proporcionar uma experiência consistente e confiável. Além de um autenticação e autorização de rotas.

## Tecnologias e Ferramentas Utilizadas

### Tecnologias

- [Java Versão 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)

### Ferramentas

- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/)
- [Insomnia](https://insomnia.rest/)

### Qualidade de código

No projeto, é utilizado o
arquivo de
configuração [intellij-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)
do Google, que define um conjunto de
diretrizes de codificação baseadas nas práticas recomendadas do Google para Java.

Para executar a análise, utilize o comando:

   ```sh
   mvn checkstyle:check
   ```

## Como executar o Projeto

### Instalação e Execução

1. Clone o repositório (Utilizar Link SSH)

2. Instalação e compilação dos recursos necessários
   ```sh
   mvn install -DskipTests
    ```

3. Inicializar projeto
   ```sh
   mvn spring-boot:run
    ```

## Documentação

A documentação da API é gerada automaticamente através
do [Springdoc-openapi](https://springdoc.org/) e estará disponível em Swagger UI através do
endpoint http://localhost:8080/swagger-ui/index.html
quando a aplicação estiver em execução. A documentação inclui detalhes sobre os endpoints
disponíveis,
os parâmetros necessários, os códigos de resposta e exemplos de solicitações.

<details>
  <summary><strong>Endpoints da Aplicação</strong></summary>

### Criação de Contato

- Método: POST
- URL: /persons
- Descrição: Cria novo usuário

### Autenticação

- Método: POST
- URL: /auth/login
- Descrição: Autenticação de usuário

### Criação de Fazenda

- Método: POST
- URL: /farms
- Descrição: Cria nova fazenda

### Recuperação de Fazendas

- Método: GET
- URL: /farms
- Descrição: Retorna todas as fazendas disponíveis

### Recuperação de Fazenda por ID

- Método: GET
- URL: /farms/{farmId}
- Descrição: Retorna informações de uma fazenda específica

### Criação de Plantação

- Método: POST
- URL: /farms/{farmId}/crops
- Descrição: Cria nova plantação

### Recuperação de Plantações

- Método: GET
- URL: /farms/{farmId}/crops
- Descrição: Retorna todas as plantações disponíveis em uma fazenda específica

>Nota: Os outros endpoints podem ser encontrados na documentação no endpoint /swagger-ui/index.html

</details>

## Contribuições

[Yago Moreira](https://www.linkedin.com/in/yagobmoreira/)

[Trybe](https://www.betrybe.com/)