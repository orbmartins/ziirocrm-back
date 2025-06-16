# ZiiroCRM - Documentação do Projeto

## Visão Geral

O **ZiiroCRM** é uma aplicação backend desenvolvida em Java com Spring Boot, que fornece uma API REST para gerenciamento de usuários e autenticação JWT. O projeto utiliza PostgreSQL como banco de dados e segue boas práticas de segurança, como autenticação baseada em tokens.

---

## Estrutura do Projeto

```
src/
  main/
    java/
      com/
        ziirocrm/
          ziirocrm/
            config/         # Configurações de segurança e JWT
            controller/     # Controllers REST (User, Auth)
            dto/            # Data Transfer Objects (AuthRequest, AuthResponse, RegisterRequest)
            model/          # Entidades JPA (User)
            repository/     # Repositórios Spring Data JPA
            service/        # Serviços de autenticação, JWT e UserDetails
            ZiiroCrmApplication.java # Classe principal
    resources/
      application.properties # Configurações do Spring e banco de dados
test/
  java/
    com/
      ziirocrm/
        ziirocrm/
          ZiiroCrmApplicationTests.java # Teste básico de contexto
```

---

## Funcionalidades

- **Cadastro de Usuário:**  
  Endpoint `/auth/register` para criar um novo usuário, recebendo `name`, `username`, `email` e `password`.

- **Login:**  
  Endpoint `/auth/login` para autenticação, recebendo `email` e `password`. Retorna um token JWT.

- **Proteção de Endpoints:**  
  Endpoints `/api/users/**` protegidos por JWT. Apenas usuários autenticados podem acessar.

- **CRUD de Usuários:**  
  Endpoints para listar, buscar, criar, atualizar e deletar usuários.

---

## Principais Arquivos

- [`User.java`](src/main/java/com/ziirocrm/ziirocrm/model/User.java): Entidade de usuário, implementa `UserDetails`.
- [`UserRepository.java`](src/main/java/com/ziirocrm/ziirocrm/repository/UserRepository.java): Interface de acesso ao banco.
- [`AuthController.java`](src/main/java/com/ziirocrm/ziirocrm/controller/AuthController.java): Endpoints de autenticação.
- [`UserController.java`](src/main/java/com/ziirocrm/ziirocrm/controller/UserController.java): Endpoints de CRUD de usuários.
- [`JwtService.java`](src/main/java/com/ziirocrm/ziirocrm/service/JwtService.java): Geração e validação de tokens JWT.
- [`JwtAuthFilter.java`](src/main/java/com/ziirocrm/ziirocrm/config/JwtAuthFilter.java): Filtro de autenticação JWT.
- [`SecurityConfig.java`](src/main/java/com/ziirocrm/ziirocrm/config/SecurityConfig.java): Configuração de segurança do Spring.

---

## Configuração

- **Banco de Dados:** Configurado para PostgreSQL em `application.properties`.
- **Porta padrão:** 8080

---

## Requisitos

- Java/Java Development Kit (JDK)
- Docker
- Postman ou equivalente

---

## Como Usar

1. **Inicie o container do BD PostgreSQL no Docker** com o comando "docker-compose up -d".
1. **Execute a aplicação** com `mvn spring-boot:run` ou pela IDE.
1. **Registre um usuário** via `POST /auth/register`.
1. **Faça login** via `POST /auth/login` para obter o token JWT.
1. **Acesse os endpoints protegidos** (`/api/users`) usando o token JWT no header `Authorization: Bearer <token>`.

---

## Observações

- O campo `username` é usado como identificador principal para autenticação.
- O projeto não implementa roles/perfis de usuário por padrão.
- O endpoint `/auth/register` é público, os demais exigem autenticação JWT.

---

## Acessar o banco via wsl

- psql -h localhost -p 5432 -U ziirocrm -d ziirocrm


