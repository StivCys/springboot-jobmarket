# TASK-001 — Estrutura do projeto backend

**Fase:** 0 — Fundação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-000 §17, SPEC-009
**Depende de:** Nenhuma

---

## Objetivo

> ⚠️ **Pré-condição:** este projeto já possui o Spring Boot configurado e o
> PostgreSQL já roda via Docker. Antes de criar qualquer coisa, execute a
> TASK-000 (se ainda não executada) e leia `roadmap/ESTADO-ATUAL.md`. Esta
> task só deve **criar** o que estiver de fato faltando — o que já existir
> deve apenas ser validado contra os critérios de aceite abaixo, sem
> recriação, sem downgrade/upgrade de versão não solicitado, e sem alterar
> configuração que já funciona.

Criar o esqueleto do projeto Spring Boot (Java 24) com Gradle, estrutura de pacotes por domínio, Flyway configurado e Docker Compose com PostgreSQL para desenvolvimento local.

## Escopo

- Projeto Spring Boot já inicializado com Maven (`pom.xml`, `mvnw`) — validar a versão do Java declarada no `pom.xml` contra o Java 24 esperado, sem forçar downgrade/upgrade não solicitado
- Conferir no `pom.xml` quais dependências já estão presentes e adicionar apenas as que faltarem: Spring Web, Spring Data JPA, Spring Security, Flyway, driver PostgreSQL, Validation
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

> Nota: `application.properties` já existe no projeto (não `.yml`). Mantenha o formato já usado, a menos que haja um motivo concreto para migrar — se migrar, registre isso como decisão explícita no `ESTADO-ATUAL.md` (TASK-000), não como efeito colateral silencioso.

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
