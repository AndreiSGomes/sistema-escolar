# 🏫 Sistema Escolar — Roadmap do Projeto

Projeto de estudo com **Java + Spring Boot + JPA/Hibernate + PostgreSQL**, desenvolvido em etapas incrementais, com commits progressivos documentando a evolução.

> 🔧 **Status atual:** CRUD básico de Aluno, Professor e Turma funcionando via API REST, com PostgreSQL + JPA/Hibernate. Relacionamentos entre entidades corrigidos e conferidos. Próximos passos: entidade `TurmaDisciplinaProfessor`, tratamento global de erros e endpoints CRUD restantes.

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
- [ ] Criar entidade associativa `TurmaDisciplinaProfessor` — classe já existe, mas **ainda sem anotações JPA** (`@Entity`, `@Id`, `@ManyToOne`); hoje é uma classe Java comum, não persistida
- [x] Criar entidade `Nota`
- [x] Criar entidade `Falta`
- [x] Mapear relacionamentos (`@OneToMany`, `@ManyToOne`) — mapeamentos corrigidos e conferidos
- [x] Commit: "feat: criação das entidades do domínio"

### Etapa 3 — Camada de persistência
- [x] Criar `TurmaRepository`
- [x] Criar `AlunoRepository`
- [x] Criar `ProfessorRepository`
- [x] Criar `DisciplinaRepository`
- [ ] Criar `TurmaDisciplinaProfessorRepository` — depende da entidade ser mapeada primeiro
- [x] Criar `NotaRepository` (ainda sem métodos de busca customizados por aluno/bimestre — só CRUD padrão)
- [x] Criar `FaltaRepository` (ainda sem métodos de contagem customizados — só CRUD padrão)
- [x] Commit: "feat: repositórios JPA"

### Etapa 4 — Seed de dados de teste
- [ ] Criar classe de configuração (`CommandLineRunner`) para popular o banco
- [ ] Commit: "feat: dados de teste (seed)"

### Etapa 5 — Camada de serviço
- [x] Criar `TurmaService` (`insert`, `findById`, `findAll`)
- [x] Criar `AlunoService` (`save` já busca e vincula `Turma`; trata turma inexistente com exceção customizada)
- [x] Criar `ProfessorService` (`insert`, `findById`, `findAll`)
- [ ] Criar `TurmaDisciplinaProfessorService`
- [ ] Criar `NotaService` — CRUD básico existe (`insert`, `findById`, `findAll`), mas **ainda falta a lógica de cálculo de média** por aluno/disciplina/bimestre
- [ ] Criar `FaltaService` — CRUD básico existe, mas **ainda falta a lógica de contagem/percentual** de faltas por aluno
- [ ] Criar exceções customizadas — `ControllerNotFoundException` já existe, mas só é usada no `AlunoService`; os demais services usam `.get()` direto no `Optional` (gera erro genérico se o ID não existir, em vez de uma exceção tratada)
- [x] Commit: "feat: camada de serviço"

### Etapa 6 — Camada REST (Controllers)
- [ ] Criar `TurmaController` — só `POST` implementado; faltam `GET` (all/by id), `PUT`, `DELETE`
- [ ] Criar `AlunoController` — `GET` (all/by id) e `POST` prontos; faltam `PUT`, `DELETE`
- [ ] Criar `ProfessorController` — `GET` (all/by id) e `POST` prontos; faltam `PUT`, `DELETE`
- [ ] Criar `NotaController`
- [ ] Criar `FaltaController`
- [ ] Criar handler global de exceções (`@ControllerAdvice`) — **próxima etapa prioritária**
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


## 🎯 Próximos passos (a retomar futuramente)

O mapeamento de relacionamentos (`@OneToMany`/`mappedBy`) já foi corrigido e conferido. Os itens abaixo ficam registrados para uma próxima sessão de desenvolvimento:

1. Mapear `TurmaDisciplinaProfessor` como entidade JPA (`@Entity`, `@Id`, `@ManyToOne` nos 3 relacionamentos), e então criar seu `Repository`, `Service` e `Controller`
2. Padronizar o tratamento de "não encontrado" em todos os services (hoje só `AlunoService` trata isso; os demais usam `.get()` direto no `Optional`)
3. Criar o `@ControllerAdvice` (handler global de exceções), convertendo `ControllerNotFoundException` em resposta `404`
4. Completar os endpoints CRUD que faltam (`PUT`, `DELETE` em Aluno/Professor/Turma; controllers inteiros de Disciplina/Nota/Falta)
5. Implementar a lógica de negócio pendente em `NotaService` (cálculo de média) e `FaltaService` (contagem/percentual)

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
