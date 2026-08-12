# TASK-029 — Entidade Application + ApplicationEvent

**Fase:** 7 — Candidaturas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-007 §3, §5
**Depende de:** [TASK-028](task-028.md)

---

## Objetivo

Criar Application e ApplicationEvent com migrations.

## Escopo

- Entidade `Application` (candidateId, opportunityId, matchScore congelado, matchAlgorithmVersion, origem, status)
- Entidade `ApplicationEvent` (tipo, dataEvento, observação)

## Critérios de aceite

- [ ] Migrations aplicam sem erros
- [ ] Constraint evita duas candidaturas ativas do mesmo candidato para a mesma oportunidade

## Testes obrigatórios

- Testes de integração de persistência e da constraint de duplicidade

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
