# TASK-032 — Retirada de candidatura

**Fase:** 7 — Candidaturas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-007 §4, §7
**Depende de:** [TASK-030](task-030.md)

---

## Objetivo

Implementar a retirada de candidatura pelo lado do candidato.

## Escopo

- `POST /api/v1/applications/{id}/withdraw`
- Só permitido antes do status HIRED

## Critérios de aceite

- [ ] Retirada antes de HIRED funciona e muda status para WITHDRAWN
- [ ] Retirada após HIRED é bloqueada

## Testes obrigatórios

- Testes de integração cobrindo os dois cenários

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
