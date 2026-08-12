# TASK-005 — Entidade User + migration

**Fase:** 1 — Usuários & Autenticação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-001 §3
**Depende de:** [TASK-001](task-001.md), [TASK-004](task-004.md)

---

## Objetivo

Criar a entidade User e a migration Flyway correspondente, incluindo papel (role) e status de conta conforme SPEC-001 §3.

## Escopo

- Entidade JPA `User` (id, email, senha hash, role, status, createdAt, updatedAt, lastLoginAt)
- Migration Flyway criando a tabela `users` com constraint de unicidade em `email`
- Enums `UserRole` (CANDIDATE, COMPANY, ADMIN) e `UserStatus` (PENDING_VERIFICATION, ACTIVE, INACTIVE, SUSPENDED)
- `UserRepository` com busca por e-mail

## Critérios de aceite

- [ ] Migration aplica sem erros em banco limpo
- [ ] Constraint de e-mail único é respeitada (teste de conflito)
- [ ] Repository consegue buscar usuário por e-mail

## Testes obrigatórios

- Teste de integração de persistência (salvar/buscar `User`)
- Teste garantindo que e-mail duplicado lança violação de constraint

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
