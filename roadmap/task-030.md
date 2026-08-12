# TASK-030 — Endpoint de candidatura

**Fase:** 7 — Candidaturas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-007 §4, §7
**Depende de:** [TASK-029](task-029.md)

---

## Objetivo

Implementar o endpoint de candidatura, congelando o matchScore no momento do envio.

## Escopo

- `POST /api/v1/opportunities/{id}/apply`
- Bloqueio de candidatura a oportunidades não OPEN
- Snapshot do matchScore/matchAlgorithmVersion no momento da candidatura

## Critérios de aceite

- [ ] Candidatura a oportunidade OPEN funciona e congela o score atual
- [ ] Candidatura a oportunidade CLOSED/PAUSED/DRAFT é bloqueada
- [ ] Candidatura duplicada é bloqueada (409)

## Testes obrigatórios

- Testes de integração cobrindo os três cenários acima

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
