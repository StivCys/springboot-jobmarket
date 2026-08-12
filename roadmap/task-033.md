# TASK-033 — Regras de anti-spam

**Fase:** 7 — Candidaturas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-007 §6
**Depende de:** [TASK-030](task-030.md)

---

## Objetivo

Implementar as regras de anti-spam de candidaturas (score mínimo configurável, limite de candidaturas simultâneas em andamento).

## Escopo

- Configuração de score mínimo para candidatura espontânea
- Limite de candidaturas simultâneas em IN_PROCESS por candidato (configurável)

## Critérios de aceite

- [ ] Candidatura abaixo do score mínimo é sinalizada
- [ ] Candidato acima do limite de candidaturas simultâneas recebe erro apropriado

## Testes obrigatórios

- Testes de integração cobrindo os dois limites

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
