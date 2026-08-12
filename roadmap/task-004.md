# TASK-004 — Configuração base de testes

**Fase:** 0 — Fundação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-000 §22 (evolução incremental)
**Depende de:** [TASK-001](task-001.md), [TASK-002](task-002.md)

---

## Objetivo

> ⚠️ **Pré-condição:** o PostgreSQL já está rodando via Docker neste
> projeto. Esta task NÃO deve criar um novo `docker-compose.yml` nem alterar
> o existente — apenas configurar o Testcontainers para os testes de
> integração (que sobe um banco isolado próprio, independente do container
> de desenvolvimento já em uso).

Configurar a infraestrutura de testes usada por todas as tasks seguintes — Testcontainers para testes de integração com Postgres real no backend, e testes unitários no frontend.

## Escopo

- Testcontainers configurado para subir Postgres em testes de integração
- Perfil de teste (`application-test.yml`) isolado do perfil local
- Cobertura de testes configurada (Jacoco) no backend
- Configuração padrão de testes unitários no frontend (Angular CLI)

## Critérios de aceite

- [ ] Um teste de integração de exemplo sobe um Postgres via Testcontainers e roda uma migration Flyway
- [ ] Relatório de cobertura é gerado no backend
- [ ] `ng test` executa com sucesso no frontend

## Testes obrigatórios

- Teste de integração de exemplo validando a infraestrutura (sem regra de negócio ainda)

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
