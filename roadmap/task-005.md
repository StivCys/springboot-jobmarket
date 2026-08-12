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

### Economia de tokens

O agente deve minimizar deliberadamente o consumo de tokens.

Regras:

* Não narrar cada comando executado.
* Não explicar comandos óbvios.
* Não repetir informações já descobertas.
* Não reproduzir arquivos completos quando apenas alguns valores são necessários.
* Não produzir análises especulativas.
* Não produzir resumos intermediários longos.
* Não perguntar ao usuário algo que possa ser determinado diretamente pelo repositório.
* Não apresentar alternativas arquiteturais não solicitadas.
* Não pesquisar documentação externa.
* Não usar web ou fontes externas para descobrir informações que devem ser obtidas do próprio repositório.
* Preferir uma única inspeção que responda várias perguntas relacionadas.
* Encerrar assim que os critérios forem satisfeitos.

**Princípio:** gastar tokens para descobrir fatos necessários, não para descrever o processo de descoberta.

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
