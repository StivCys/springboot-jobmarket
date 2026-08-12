# TASK-028 — Endpoints de matching

**Fase:** 6 — Matching
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-006 §6, SPEC-009 §12
**Depende de:** [TASK-027](task-027.md)

---

## Objetivo

Expor os endpoints de matching nas duas direções (candidato→oportunidades e empresa→candidatos), incluindo versionamento do algoritmo.

## Escopo

- `GET /api/v1/matching/opportunities` (candidato autenticado)
- `GET /api/v1/matching/candidates/{opportunityId}` (empresa autenticada)
- Campo matchAlgorithmVersion na resposta

## Critérios de aceite

- [ ] Apenas oportunidades OPEN e candidatos com perfil ao menos BASICO participam
- [ ] Resultado inclui score + explicação + versão do algoritmo

## Testes obrigatórios

- Testes de integração cobrindo as duas direções do matching

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
