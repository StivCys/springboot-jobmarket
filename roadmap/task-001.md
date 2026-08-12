# TASK-001 — Estrutura do projeto backend

**Fase:** 0 — Fundação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-000 §17, SPEC-009
**Depende de:** Nenhuma

---

## Objetivo

Criar o esqueleto do projeto Spring Boot (Java 24) com Gradle, estrutura de pacotes por domínio, Flyway configurado e Docker Compose com PostgreSQL para desenvolvimento local.

## Escopo

- Projeto Spring Boot inicializado (Gradle, Java 24)
- Dependências: Spring Web, Spring Data JPA, Spring Security, Flyway, PostgreSQL driver, Validation
- Estrutura de pacotes por domínio (ex: com.jobmarket.user, .company, .candidate, .opportunity, .skill, .matching, .application) conforme SPEC-000 §18
- docker-compose.yml com serviço PostgreSQL para ambiente local
- application.yml com profiles (local/test)
- Flyway configurado apontando para src/main/resources/db/migration

## Critérios de aceite

- [ ] `./gradlew build` executa com sucesso
- [ ] Aplicação sobe localmente (`./gradlew bootRun`) e conecta ao Postgres do docker-compose
- [ ] Health check (`/actuator/health`) retorna UP
- [ ] Estrutura de pacotes documentada no README do backend

## Testes obrigatórios

- Teste de contexto (`@SpringBootTest`) garantindo que o contexto da aplicação sobe sem erros

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
