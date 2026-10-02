# 🏫 Sistema Escolar — Roadmap do Projeto

Projeto de estudo com **Java + Spring Boot + JPA/Hibernate + PostgreSQL**, desenvolvido em etapas incrementais, com commits progressivos documentando a evolução.

> 🔧 **Status atual:** API REST completa para as 7 entidades (Aluno, Professor, Turma, Disciplina, TurmaDisciplinaProfessor, Nota, Falta), com CRUD completo, PostgreSQL + JPA/Hibernate e tratamento global de erros (`@RestControllerAdvice`) já implementado. Próximos passos: regras de negócio de Nota/Falta, seed de dados, DTOs e documentação da API.

---

## 📌 Sobre o projeto

Sistema de gerenciamento escolar simples, com cadastro de alunos, professores, disciplinas e turmas, além de lançamento de notas e faltas. Projeto criado para consolidar conceitos de JPA/Hibernate, arquitetura em camadas e boas práticas com Spring Boot.

---

## 🛠️ Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- (futuramente) Spring Security, Bean Validation, Swagger

---

## 🗺️ Diagrama ER

```mermaid
erDiagram
    ALUNO {
        long id PK
        string nome
        date dataNascimento
        string email
        string nomeResponsavel
        string celularResponsavel
        string endereco
        long turma_id FK
    }

    PROFESSOR {
        long id PK
        string nome
        date dataNascimento
        string email
        string celular
        string endereco
    }

    TURMA {
        long id PK
        string identificador
        string serie
        string turno
        int anoLetivo
    }

    DISCIPLINA {
        long id PK
        string nome
        int cargaHoraria
    }

    TURMA_DISCIPLINA_PROFESSOR {
        long id PK
        long turma_id FK
        long disciplina_id FK
        long professor_id FK
    }

    NOTA {
        long id PK
        long aluno_id FK
        long disciplina_id FK
        int bimestre
        string descricao
        double valor
        date dataLancamento
    }

    FALTA {
        long id PK
        long aluno_id FK
        date data
        boolean justificada
    }

    USUARIO {
        long id PK
        string login
        string senha
        string perfil
        long aluno_id FK
        long professor_id FK
    }

    TURMA ||--o{ ALUNO : possui
    TURMA ||--o{ TURMA_DISCIPLINA_PROFESSOR : oferece
    DISCIPLINA ||--o{ TURMA_DISCIPLINA_PROFESSOR : "é lecionada em"
    PROFESSOR ||--o{ TURMA_DISCIPLINA_PROFESSOR : leciona
    ALUNO ||--o{ NOTA : recebe
    DISCIPLINA ||--o{ NOTA : "refere-se a"
    ALUNO ||--o{ FALTA : registra
    ALUNO ||--o| USUARIO : acessa
    PROFESSOR ||--o| USUARIO : acessa
```


**Resumo das relações:**
- **Aluno** e **Professor** são entidades independentes (sem superclasse), cada uma com seus próprios campos: nome, data de nascimento, e-mail, celular e endereço (campo único de texto)
- No `Aluno`, os campos `nomeResponsavel` e `celularResponsavel` (não `celular`) representam sempre os dados do responsável, independente da idade do aluno. Já em `Professor`, o campo `celular` é o contato da própria pessoa
- Um **Aluno** pertence a **uma única Turma** (fixo, como no modelo brasileiro do infantil ao médio)
- Uma **Turma** tem vários **Alunos**
- Uma **Turma** pode ter **vários Professores**, cada um responsável por uma ou mais **Disciplinas** — isso é resolvido pela entidade associativa `TurmaDisciplinaProfessor`, que representa "esse professor leciona essa disciplina, nessa turma"
- **Nota** representa **cada avaliação individual** (ex: "Prova 1", "Trabalho em grupo") vinculada a `Aluno` + `Disciplina` + `bimestre`. A **média do bimestre não é armazenada** — ela é calculada em tempo real pelo `NotaService`, somando/tirando a média de todas as avaliações daquele aluno, disciplina e bimestre
- **Falta** é registrada **por dia inteiro** (não por disciplina/aula específica) — cada linha representa "esse aluno faltou nesse dia", com flag de `justificada`
- **Usuário** é a base para os perfis de acesso futuros (`ALUNO`, `PROFESSOR`, `DIRECAO`) — ele se relaciona opcionalmente com `Aluno` ou `Professor`, dependendo de quem está logando. Perfil `DIRECAO` não precisa vincular a nenhum dos dois, já que é acesso administrativo

**Por que uma entidade associativa (`TurmaDisciplinaProfessor`) em vez de relação direta?**
Porque no modelo a partir do 6º ano, uma turma tem vários professores (um de Matemática, outro de Português, etc). Se colocássemos `professor_id` direto na `Turma`, só daria pra guardar um professor por turma. Com a entidade associativa, cada linha representa "Professor X leciona Disciplina Y na Turma Z", permitindo N professores por turma e N disciplinas por professor.

---

## ✅ Roadmap de Desenvolvimento

### Etapa 1 — Setup do projeto
- [x] Criar projeto Spring Boot (via Spring Initializr ou STS)
- [x] Configurar `pom.xml` (Java 17, dependências: Web, JPA, PostgreSQL Driver)
- [x] Configurar `application.properties` (conexão com banco)
- [x] Criar banco de dados no PostgreSQL
- [x] Subir projeto e validar conexão com o banco
- [x] Primeiro commit: "chore: setup inicial do projeto"

### Etapa 2 — Camada de domínio (entidades)
- [x] Criar entidade `Turma`
- [x] Criar entidade `Aluno` (com `@ManyToOne` pra `Turma`)
- [x] Criar entidade `Professor`
- [x] Criar entidade `Disciplina`
- [x] Criar entidade associativa `TurmaDisciplinaProfessor` — já com `@Entity`, `@Id` e os 3 `@ManyToOne` (Turma, Disciplina, Professor)
- [x] Criar entidade `Nota`
- [x] Criar entidade `Falta`
- [x] Mapear relacionamentos (`@OneToMany`, `@ManyToOne`) — mapeamentos corrigidos e conferidos
- [x] Commit: "feat: criação das entidades do domínio"

### Etapa 3 — Camada de persistência
- [x] Criar `TurmaRepository`
- [x] Criar `AlunoRepository`
- [x] Criar `ProfessorRepository`
- [x] Criar `DisciplinaRepository`
- [x] Criar `TurmaDisciplinaProfessorRepository`
- [x] Criar `NotaRepository`
- [x] Criar `FaltaRepository`
- [x] Commit: "feat: repositórios JPA"

### Etapa 4 — Seed de dados de teste
- [ ] Criar classe de configuração (`CommandLineRunner`) para popular o banco
- [ ] Commit: "feat: dados de teste (seed)"

### Etapa 5 — Camada de serviço
- [x] Criar `TurmaService` (CRUD completo: `salvar`, `buscarPorId`, `buscarTodos`, `atualizar`, `deletarPorId`)
- [x] Criar `AlunoService` (CRUD completo; busca e vincula `Turma`; trata turma inexistente com exceção customizada)
- [x] Criar `ProfessorService` (CRUD completo)
- [x] Criar `TurmaDisciplinaProfessorService` (CRUD completo; valida existência de Turma, Disciplina e Professor)
- [ ] Criar `NotaService` — CRUD completo implementado (incluindo `atualizar`/`deletarPorId`), mas **ainda falta a lógica de cálculo de média** por aluno/disciplina/bimestre
- [ ] Criar `FaltaService` — CRUD completo implementado, mas **ainda falta a lógica de contagem/percentual** de faltas por aluno
- [x] Criar exceções customizadas — `NaoEncontradoException` + `ErroPadrao`, usadas de forma **consistente em todos os 7 services** via `.orElseThrow()`
- [x] Commit: "feat: camada de serviço"

### Etapa 6 — Camada REST (Controllers)
- [x] Criar `TurmaController` (CRUD completo: `POST`, `GET` all/by id, `PUT`, `DELETE`)
- [x] Criar `AlunoController` (CRUD completo)
- [x] Criar `ProfessorController` (CRUD completo)
- [x] Criar `TurmaDisciplinaProfessorController` (CRUD completo)
- [x] Criar `NotaController` (CRUD completo)
- [x] Criar `FaltaController` (CRUD completo)
- [x] Criar handler global de exceções (`@RestControllerAdvice`) — `NaoEncontradoExceptionHandler`, convertendo `NaoEncontradoException` em resposta `404` com corpo padronizado (`ErroPadrao`: timestamp, status, error, path)
- [x] Testar endpoints via Postman/Insomnia
- [x] Commit: "feat: endpoints REST"

### Etapa 7 — Melhorias e boas práticas
- [ ] Criar DTOs (separar entidade de payload de API)
- [ ] Validações com Bean Validation (`@NotBlank`, `@Email`, etc.)
- [ ] Documentar API com Swagger/OpenAPI
- [ ] Configurar CORS
- [ ] Commit: "feat: DTOs, validações e documentação da API"

### Etapa 8 — Deploy e documentação final
- [x] Criar `README.md` completo (este roadmap virou parte dele — em atualização contínua conforme o projeto avança)
- [ ] Adicionar instruções de execução local
- [ ] (Opcional) Deploy em serviço gratuito (Render, Railway, etc.)
- [ ] Commit: "docs: documentação final do projeto"

### Etapa 9 — Perfis de acesso (futuro)
- [ ] Criar entidade `Usuario` (login, senha, perfil)
- [ ] Definir enum `Perfil` (`ALUNO`, `PROFESSOR`, `DIRECAO`)
- [ ] Adicionar Spring Security ao projeto
- [ ] Implementar autenticação (login/token JWT)
- [ ] Restringir endpoints por perfil (ex: só `DIRECAO` pode criar `Turma`; só `PROFESSOR` pode lançar `Nota`; `ALUNO` só visualiza suas próprias notas/faltas)
- [ ] Commit: "feat: autenticação e autorização por perfil"

> 💡 Essa etapa foi deixada por último de propósito — segurança/autenticação costuma ser mais fácil de entender depois que o CRUD básico já está funcionando e testado.


## 🎯 Próximos passos

Toda a camada REST está completa e o tratamento global de erros já está no ar (`@RestControllerAdvice` + `NaoEncontradoException` + `ErroPadrao`), padronizado em todos os 7 recursos. O que fica registrado para as próximas etapas:

1. Implementar a lógica de negócio pendente em `NotaService` (cálculo de média por aluno/disciplina/bimestre) e `FaltaService` (contagem/percentual de faltas por aluno)
2. Criar seed de dados de teste (`CommandLineRunner`) pra facilitar testes manuais e futuras demos
3. Pequena limpeza: remover verificação `if (professor != null)` redundante em `ProfessorController.buscarPorId` (código morto — o `Service` já lança exceção antes de chegar lá)
4. Etapa 7: DTOs, Bean Validation, Swagger/OpenAPI, CORS
5. Etapa 9 (futuro): perfis de acesso com Spring Security

---


## 🚀 Como rodar o projeto localmente

### Pré-requisitos
- Java 17 instalado
- Maven instalado (ou usar o wrapper `./mvnw`)
- PostgreSQL instalado e rodando

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/AndreiSGomes/sistema-escolar.git
   cd sistema-escolar
   ```

2. **Crie o banco de dados no PostgreSQL**
   ```sql
   CREATE DATABASE sistema_escolar;
   ```

3. **Configure as credenciais**

   Edite o arquivo `src/main/resources/application.properties` com seus dados de acesso:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_escolar
   spring.datasource.username=postgres
   spring.datasource.password=sua_senha

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. **Execute o projeto**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Acesse a aplicação**

   A API estará disponível em:
   ```
   http://localhost:8080
   ```

   Endpoints disponíveis, por exemplo:
   ```
   GET  /alunos
   GET  /alunos/{id}
   POST /alunos
   ```

---

## 📄 Licença

Este projeto tem fins educacionais e está sob a licença MIT.
