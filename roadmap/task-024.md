# TASK-024 — CRUD de oportunidades + ciclo de vida

**Fase:** 5 — Oportunidades
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-004 §5
**Depende de:** [TASK-023](task-023.md), [TASK-015](task-015.md)

---

## Objetivo

Implementar CRUD de oportunidades pelo lado da empresa, incluindo as transições de status (DRAFT/OPEN/PAUSED/CLOSED).

## Escopo

- `POST/GET/PUT /api/v1/companies/{id}/opportunities`
- `POST /api/v1/companies/{id}/opportunities/{oppId}/publish|pause|resume|close`
- Autorização por membership
- Bloqueio de publicação por empresa não publicada

## Critérios de aceite

- [ ] Transições de status seguem o diagrama da SPEC-004 §5 (nenhuma transição inválida é permitida)
- [ ] Publicação por empresa não publicada é bloqueada

## Testes obrigatórios

- Testes de integração cobrindo cada transição válida e ao menos duas transições inválidas

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
