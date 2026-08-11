# JobMarket

Plataforma de oportunidades profissionais construída com **Spring Boot, Java 25, PostgreSQL e Angular**.

O objetivo do projeto é explorar uma abordagem diferente para plataformas tradicionais de vagas, indo além do simples modelo de:

> empresa publica vaga → candidato envia currículo.

A proposta é evoluir para um sistema baseado em **perfil profissional, interesses, disponibilidade e matching entre candidatos e oportunidades**.

> 🚧 **Projeto em desenvolvimento**

---

## 🧰 Stack

### Backend

- Java 25
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Bean Validation
- Flyway
- Maven

### Banco de dados

- PostgreSQL 17

### Frontend

- Angular

### Infraestrutura

- Docker
- Docker Compose

---

## 📁 Estrutura atual

```text
JobMarket/
├── docker-compose.yml
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── jobmarket/
    │   │           └── JobMarketApplication.java
    │   │
    │   └── resources/
    │       ├── application.properties
    │       ├── db/
    │       │   └── migration/
    │       ├── static/
    │       └── templates/
    │
    └── test/
        └── java/
            └── com/
                └── jobmarket/
                    └── JobMarketApplicationTests.java
```

A estrutura será expandida conforme os módulos forem implementados.

---

# 🚀 Pré-requisitos

Antes de executar o projeto, tenha instalado:

- Java 25
- Docker
- Docker Compose

O projeto utiliza o **Maven Wrapper**, portanto não é necessário instalar Maven globalmente.

Verificar Java:

```bash
java -version
```

Verificar Maven utilizado pelo projeto:

```bash
./mvnw -version
```

O Maven deve indicar Java 25.

---

# ⚙️ Configuração inicial

Clone o projeto e entre na pasta:

```bash
git clone <repository-url>
cd JobMarket
```

Dê permissão de execução ao Maven Wrapper, caso necessário:

```bash
chmod +x mvnw
```

---

# 🐘 PostgreSQL

O PostgreSQL utilizado no desenvolvimento roda através do Docker Compose.

Configuração atual:

```text
Database: jobmarket
User:     jobmarket
Password: jobmarket
Host:     localhost
Port:     5432
```

## Subir PostgreSQL

```bash
docker compose up -d
```

Verificar:

```bash
docker compose ps
```

Esperado:

```text
jobmarket-postgres
```

---

## Ver logs do PostgreSQL

```bash
docker compose logs -f postgres
```

---

## Parar PostgreSQL

```bash
docker compose down
```

---

## Remover banco e volume

⚠️ **Cuidado:** este comando remove o volume do PostgreSQL.

```bash
docker compose down -v
```

Use somente quando quiser recriar o banco de desenvolvimento do zero.

---

# 🗄️ Acessar PostgreSQL

É possível acessar o banco diretamente pelo container:

```bash
docker exec -it jobmarket-postgres psql \
  -U jobmarket \
  -d jobmarket
```

Dentro do PostgreSQL:

### Listar tabelas

```sql
\dt
```

### Listar relações

```sql
\d
```

### Sair

```sql
\q
```

---

# 🌱 Flyway

As migrations ficam em:

```text
src/main/resources/db/migration/
```

O padrão utilizado pelo Flyway é:

```text
V1__create_users.sql
V2__create_companies.sql
V3__create_candidates.sql
```

Cada migration deve possuir um número de versão único.

Exemplo:

```text
V1__create_users.sql
```

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE
);
```

As migrations são executadas automaticamente pelo Spring Boot durante a inicialização da aplicação.

---

# ▶️ Executar a aplicação

Com o PostgreSQL funcionando:

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada na porta padrão:

```text
http://localhost:8080
```

---

# 🧪 Testes

Executar os testes:

```bash
./mvnw test
```

Limpar o projeto e executar novamente:

```bash
./mvnw clean test
```

---

# 📦 Build

Gerar o artefato:

```bash
./mvnw clean package
```

O resultado será criado em:

```text
target/
```

Para gerar o pacote sem executar os testes:

```bash
./mvnw clean package -DskipTests
```

---

# 🧹 Limpeza

Remover os arquivos gerados pelo Maven:

```bash
./mvnw clean
```

Isso remove o diretório:

```text
target/
```

---

# 📚 Dependências Maven

Ver todas as dependências:

```bash
./mvnw dependency:tree
```

Consultar o POM efetivo:

```bash
./mvnw help:effective-pom
```

---

# 🐳 Comandos Docker úteis

### Subir serviços

```bash
docker compose up -d
```

### Subir mostrando logs

```bash
docker compose up
```

### Ver containers

```bash
docker compose ps
```

### Ver todos os logs

```bash
docker compose logs -f
```

### Ver somente PostgreSQL

```bash
docker compose logs -f postgres
```

### Parar serviços

```bash
docker compose down
```

---

# 🔧 Desenvolvimento

Fluxo recomendado durante o desenvolvimento:

### 1. Subir o banco

```bash
docker compose up -d
```

### 2. Verificar o banco

```bash
docker compose ps
```

### 3. Iniciar Spring Boot

```bash
./mvnw spring-boot:run
```

### 4. Executar testes

Em outro terminal:

```bash
./mvnw test
```

---

# 🧠 Conceito do produto

O JobMarket não pretende ser apenas mais um agregador de vagas.

O modelo tradicional é:

```text
Empresa
   │
   ▼
Publica vaga
   │
   ▼
Candidato procura
   │
   ▼
Candidato se candidata
```

A proposta do JobMarket é evoluir para:

```text
                 ┌──────────────────┐
                 │    JobMarket     │
                 └────────┬─────────┘
                          │
             ┌────────────┴────────────┐
             │                         │
             ▼                         ▼
       Perfil candidato          Oportunidade
             │                         │
             │                         │
             └──────────┬──────────────┘
                        ▼
                    Matching
                        │
                        ▼
                 Compatibilidade
```

O candidato poderá informar não apenas suas experiências, mas também características como:

- habilidades
- tecnologias
- experiência
- localização
- preferência por trabalho remoto/presencial/híbrido
- disponibilidade
- expectativa profissional
- interesses
- tipos de oportunidade desejados

Empresas poderão definir as características das oportunidades.

O sistema poderá então calcular a compatibilidade entre os dois lados.

---

# 🏗️ Arquitetura planejada

A aplicação será dividida inicialmente em:

```text
JobMarket
│
├── Backend
│   └── Spring Boot
│
├── Frontend
│   └── Angular
│
└── Database
    └── PostgreSQL
```

Backend:

```text
com.jobmarket
├── controller
├── service
├── repository
├── entity
├── dto
├── config
└── exception
```

A arquitetura poderá ser reorganizada posteriormente caso a complexidade do sistema justifique.

---

# 👤 Domínios planejados

Os primeiros conceitos do sistema deverão envolver:

```text
User
Candidate
Company
Opportunity
Skill
Application
Profile
Matching
```

A modelagem definitiva ainda será definida durante o desenvolvimento.

---

# 🔐 Segurança

O projeto utiliza:

```text
Spring Security
```

A estratégia de autenticação e autorização ainda será implementada.

Possíveis recursos futuros:

- autenticação
- autorização baseada em roles
- perfil de candidato
- perfil empresarial
- recuperação de senha
- controle de acesso
- OAuth2/social login

---

# 🧪 Estratégia de testes

O projeto utiliza a infraestrutura de testes do Spring Boot.

Os testes ficam em:

```text
src/test/java/
```

Executar:

```bash
./mvnw test
```

À medida que o projeto evoluir, serão adicionados:

- testes unitários
- testes de integração
- testes de controllers
- testes de repositories
- testes dos serviços de matching

---

# 📋 Comandos rápidos

## Desenvolvimento

```bash
docker compose up -d
./mvnw spring-boot:run
```

## Testes

```bash
./mvnw test
```

## Limpar e testar

```bash
./mvnw clean test
```

## Build

```bash
./mvnw clean package
```

## Status Docker

```bash
docker compose ps
```

## Logs PostgreSQL

```bash
docker compose logs -f postgres
```

## Acessar PostgreSQL

```bash
docker exec -it jobmarket-postgres psql \
  -U jobmarket \
  -d jobmarket
```

## Versões

```bash
java -version
./mvnw -version
docker --version
docker compose version
```

---

# 📌 Roadmap

## Fase 1 — Fundação

- [x] Criar projeto Spring Boot
- [x] Configurar Java 25
- [x] Configurar Maven Wrapper
- [x] Adicionar PostgreSQL
- [x] Adicionar Flyway
- [x] Adicionar Spring Data JPA
- [x] Adicionar Spring Security
- [ ] Configurar conexão PostgreSQL
- [ ] Criar primeira migration
- [ ] Criar endpoint de health check

## Fase 2 — Usuários

- [ ] User
- [ ] Cadastro
- [ ] Login
- [ ] Autenticação
- [ ] Autorização
- [ ] Perfil

## Fase 3 — Candidatos

- [ ] Candidate
- [ ] Perfil profissional
- [ ] Skills
- [ ] Experiência
- [ ] Preferências profissionais
- [ ] Disponibilidade

## Fase 4 — Empresas

- [ ] Company
- [ ] Perfil empresarial
- [ ] Usuários da empresa
- [ ] Configurações

## Fase 5 — Oportunidades

- [ ] Criar oportunidade
- [ ] Editar oportunidade
- [ ] Publicar oportunidade
- [ ] Encerrar oportunidade
- [ ] Busca
- [ ] Filtros

## Fase 6 — Matching

- [ ] Modelo de compatibilidade
- [ ] Matching candidato ↔ oportunidade
- [ ] Score de compatibilidade
- [ ] Explicação do score
- [ ] Recomendações personalizadas

## Fase 7 — Frontend

- [ ] Criar aplicação Angular
- [ ] Autenticação
- [ ] Dashboard
- [ ] Perfil candidato
- [ ] Perfil empresa
- [ ] Busca de oportunidades
- [ ] Recomendações
- [ ] Matching

---

# 🔮 Possibilidades futuras

Conforme o produto evoluir, poderão ser avaliadas tecnologias como:

- Redis
- mensageria
- busca avançada
- processamento assíncrono
- IA para matching
- recomendação de oportunidades
- análise de perfil
- notificações
- observabilidade
- métricas

Essas tecnologias só devem ser adicionadas quando houver uma necessidade concreta do produto.

---

# 📄 Licença

Projeto em desenvolvimento.

A licença definitiva será definida posteriormente.
