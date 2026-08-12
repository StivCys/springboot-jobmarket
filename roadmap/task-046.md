# TASK-046 — Testes end-to-end dos fluxos principais

**Fase:** 12 — Fechamento
**Status:** 🔲 Não iniciada
**Specs relacionadas:** Todas as specs
**Depende de:** [TASK-039](task-039.md), [TASK-043](task-043.md), [TASK-045](task-045.md)

---

## Objetivo

Cobrir com testes end-to-end os fluxos críticos: cadastro → matching → candidatura (candidato) e cadastro de empresa → oportunidade → gestão de candidaturas (empresa).

## Escopo

- Cenário e2e: candidato se cadastra, completa perfil, recebe recomendação, se candidata
- Cenário e2e: empresa se cadastra, publica perfil, cria oportunidade, recebe e gerencia candidatura

## Critérios de aceite

- [ ] Os dois cenários rodam de ponta a ponta sem intervenção manual

## Testes obrigatórios

- Suite e2e (ex: Playwright/Cypress) cobrindo os dois cenários

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
