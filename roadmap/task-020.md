# TASK-020 — CandidateSkill + senioridade atual

**Fase:** 4 — Perfil do Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-005 §3.2, SPEC-002 §6.1 (patch)
**Depende de:** [TASK-019](task-019.md), [TASK-017](task-017.md)

---

## Objetivo

Implementar a associação de skills ao perfil do candidato (nível, anos de experiência) e o campo de senioridade atual.

## Escopo

- `POST/PUT/DELETE /api/v1/candidates/me/skills` (skillId, nível, anosExperiencia)
- `PUT /api/v1/candidates/me/seniority` (JUNIOR/PLENO/SENIOR/ESPECIALISTA)

## Critérios de aceite

- [ ] Associação de skill referencia obrigatoriamente uma Skill cadastrada (não texto livre)
- [ ] Atualização de senioridade atual persiste corretamente

## Testes obrigatórios

- Testes de integração cobrindo associação de skill válida/inválida e atualização de senioridade

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
