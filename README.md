**Library API REST**

Este projeto é uma **API REST** desenvolvida em **Java (21)** com o **Spring Boot**, projetada para gerenciar recursos de uma biblioteca — incluindo usuários, autores e livros.  
A aplicação segue uma arquitetura em camadas, priorizando organização, escalabilidade e facilidade de manutenção.

**Objetivo**

A API foi desenvolvida com o intuito de demonstrar o funcionamento de uma aplicação RESTful modular, utilizando boas práticas como:
- Separação de responsabilidades por pacotes;
- Uso de DTOs para transferência de dados;
- Mapeamento entre entidades e objetos de resposta;
- Tratamento centralizado de exceções;
- Organização e documentação dos endpoints.

**Tecnologias Utilizadas**

- **Java (21)**
- **Spring Boot**
- **Spring Web**
- **Spring Security** (autenticação básica)
- **Maven**

**Pré-requisitos**

Antes de executar o projeto, verifique se você possui instalado:
- [Java JDK (21)](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3+](https://maven.apache.org/)
- Uma IDE (IntelliJ IDEA, Eclipse ou VS Code)

**Como Executar o Projeto**

1. Clone o repositório
 
2. Acesse o diretório do projeto:

cd library-rest-api

3. Compile e execute a aplicação:

mvn spring-boot:run

4. Acesse a aplicação:

http://localhost:8080

Estrutura do Projeto

src/
 └── main/
      ├── java/com/library
      │     ├── authConfig           # Configurações de autenticação e segurança
      │     ├── controller           # Controladores REST
      │     │     └── exceptions     # Exceções específicas de controladores
      │     ├── doc                  # Documentação da API
      │     ├── dto                  # Objetos de Transferência de Dados (Data Transfer Objects)
      │     │     ├── auth
      │     │     ├── authentication
      │     │     ├── author
      │     │     ├── book
      │     │     ├── bookAuthor
      │     │     └── user
      │     ├── entities             # Classes de entidades do domínio
      │     ├── helper               # Classes auxiliares e utilitárias
      │     ├── mapper               # Conversão entre entidades e DTOs
      │     ├── repositories         # Interfaces de repositórios (simulados ou mockados)
      │     └── services             # Camada de serviços (regras de negócio)
      │           └── exceptions     # Exceções específicas da camada de serviço
      └── resources/
            ├── application-dev.properties
            ├── application-prod.properties
            └── application.properties
            
**Endpoints Principais**
A API segue o padrão REST e expõe endpoints para gerenciamento de recursos como autores, livros e usuários.

**Autenticação**
Método	Endpoint	Descrição
POST	/auth/login	Realiza login e retorna token de autenticação
POST	/auth/logout	Realiza o logout da conta

**Usuários**
Método	Endpoint	Descrição
GET	/user	Lista todos os usuários
GET	/user/{id}	Retorna informações de um usuário
POST	/user	Adiciona um novo usuário
PUT	/user/{id}	Atualiza dados de um usuário
DELETE	/user/{id}	Remove um usuário

**Livros**
Método	Endpoint	Descrição
GET	/book	Lista todos os livros
GET	/book/{id}	Retorna um livro específico
POST	/book	Adiciona um novo livro
PUT	/book/{id}	Atualiza dados de um livro
DELETE	/book/{id}	Remove um livro

**Autores**
Método	Endpoint	Descrição
GET	/author	Lista todos os autores
GET	/author/{id}	Retorna informações de um autor
POST	/author	Cria um novo autor
PUT	/author/{id}	Atualiza um autor
DELETE	/author/{id}	Remove um autor

**Autores e Livros**
Método	Endpoint	Descrição
GET	/bookAuthor	Lista todos os autores e os livros de cada autor
GET	/bookAuthor/{id}	Retorna informações de um autor e seus livros
GET	/bookAuthor/books/{authorId}	Retorna os livros de um autor usando seu ID
GET	/bookAuthor/authors/{bookId}	Retorna os autores de um livro usando seu ID
GET /bookAuthor/findLink/author/{authorId}/book/{bookId} procura se um autor e um livro tem algum link
POST	/bookAuthor	Cria um link entre um autor e um livro
DELETE	/bookAuthor/author/{authorId}/book/{bookId}	remove o link entre um author e seu livro

**Padrão de Arquitetura**
O projeto segue o padrão MVC (Model-View-Controller), organizado em camadas independentes:

Controller: Recebe requisições e retorna respostas HTTP.

Service: Contém as regras de negócio.

Repository: Simula o acesso a dados.

DTO / Mapper: Faz a comunicação entre as entidades e o mundo externo.

AuthConfig: Controla autenticação e segurança de rotas.
