# 📚 Library REST API

API REST desenvolvida em **Java 21** com **Spring Boot**, projetada para gerenciar recursos de uma biblioteca — incluindo **usuários**, **autores** e **livros**.  
A aplicação segue uma **arquitetura em camadas**, priorizando **organização**, **escalabilidade** e **facilidade de manutenção**.

---

## 🎯 Objetivo

A API foi criada com o intuito de demonstrar o funcionamento de uma aplicação **RESTful modular**, utilizando boas práticas como:

- [x] Separação de responsabilidades por pacotes  
- [x] Uso de **DTOs** para transferência de dados  
- [x] Mapeamento entre **entidades e objetos de resposta**  
- [x] Tratamento centralizado de **exceções**  
- [x] Organização e documentação dos **endpoints**

---

## 🧰 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Web**
- **Spring Security**
- **Maven**

---

## ⚙️ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3+](https://maven.apache.org/)
- [IDE de sua preferência](https://www.jetbrains.com/idea/) (IntelliJ IDEA, Eclipse ou VS Code)

---

## 🏃‍♂️ Como Executar o Projeto

1. **Clone o repositório**
   ```bash
    git clone https://github.com/Lucas-Henschel/library-api-rest.git
   ```

2. **Acesse o diretório do projeto**
   ```bash
   cd library-rest-api
   ```

3. **Compile e execute a aplicação**
   - Localize e rode a classe principal:
   ```bash
   LibraryApplication.java
   ```

4. **Acesse no navegador**
   ```bash
   http://localhost:8080
   ```

---

## 🧱 Estrutura do Projeto

A aplicação segue uma arquitetura modular, conforme exemplo abaixo:

<img width="827" height="618" alt="image" src="https://github.com/user-attachments/assets/fc46eb63-9393-42f2-828f-cd267487795c" />

---

## 🌍 Endpoints Principais

A API segue o padrão RESTful e disponibiliza endpoints para gerenciamento de usuários, livros e autores.

---

### 🔐 Autenticação

| Método | Endpoint | Descrição |
|--------|-----------|------------|
| **POST** | `/auth/login` | Realiza login e retorna token JWT |
| **POST** | `/auth/logout` | Encerra a sessão do usuário |

---

### 👥 Usuários

| Método | Endpoint | Descrição |
|--------|-----------|------------|
| **GET** | `/user` | Lista todos os usuários |
| **GET** | `/user/{id}` | Retorna um usuário específico |
| **POST** | `/user` | Adiciona um novo usuário |
| **PUT** | `/user/{id}` | Atualiza os dados de um usuário |
| **DELETE** | `/user/{id}` | Remove um usuário |

---

### 📖 Livros

| Método | Endpoint | Descrição |
|--------|-----------|------------|
| **GET** | `/book` | Lista todos os livros |
| **GET** | `/book/{id}` | Retorna informações de um livro específico |
| **POST** | `/book` | Cria um novo livro |
| **PUT** | `/book/{id}` | Atualiza um livro |
| **DELETE** | `/book/{id}` | Remove um livro |

---

### ✍️ Autores

| Método | Endpoint | Descrição |
|--------|-----------|------------|
| **GET** | `/author` | Lista todos os autores |
| **GET** | `/author/{id}` | Retorna informações de um autor |
| **POST** | `/author` | Cria um novo autor |
| **PUT** | `/author/{id}` | Atualiza um autor existente |
| **DELETE** | `/author/{id}` | Remove um autor |

---

### 🔗 Relação Autor ↔ Livro

| Método | Endpoint | Descrição |
|--------|-----------|------------|
| **GET** | `/bookAuthor` | Lista autores e seus livros |
| **GET** | `/bookAuthor/{id}` | Retorna um autor e seus livros |
| **GET** | `/bookAuthor/books/{authorId}` | Retorna livros de um autor |
| **GET** | `/bookAuthor/authors/{bookId}` | Retorna autores de um livro |
| **GET** | `/bookAuthor/findLink/author/{authorId}/book/{bookId}` | Verifica se há relação entre autor e livro |
| **POST** | `/bookAuthor` | Cria um vínculo entre autor e livro |
| **DELETE** | `/bookAuthor/author/{authorId}/book/{bookId}` | Remove vínculo entre autor e livro |

---

## 🧩 Padrão de Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)**, organizado em camadas independentes:

| Camada | Responsabilidade |
|--------|------------------|
| **Controller** | Recebe as requisições HTTP e retorna as respostas |
| **Service** | Contém as regras de negócio |
| **Repository** | Gerencia o acesso a dados (simulado/local) |
| **DTO / Mapper** | Converte entidades em objetos de transporte |
| **AuthConfig** | Gerencia autenticação e segurança de rotas |

---

## 🧑‍💻 Contribuição

Quer contribuir?

1. Faça um fork do projeto

2. Crie uma branch com sua feature:
   ```bash
   git checkout -b feature/minha-feature
   ```

3. Faça o commit das alterações:
   ```bash
   git commit -m "feat: adiciona nova funcionalidade"
   ```

4. Envie sua branch:
   ```bash
   git push origin feature/minha-feature
   ```

5. Abra um Pull Request