# TASK-018 — Entidade CandidateProfile + subentidades

**Fase:** 4 — Perfil do Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-002 §3-§9 (v0.3)
**Depende de:** [TASK-005](task-005.md), [TASK-016](task-016.md)

---

## Objetivo

Criar CandidateProfile e as subentidades Experience, Education, Project, com suas migrations.

## Escopo

- Entidade `CandidateProfile` (dados básicos, localização, disponibilidade, senioridade atual, objetivos)
- Entidades `Experience`, `Education`, `Project` (1:N com CandidateProfile)

## Critérios de aceite

- [ ] Migrations aplicam sem erros
- [ ] Um User com role CANDIDATE pode ter no máximo um CandidateProfile

## Testes obrigatórios

- Testes de integração de persistência para o agregado completo

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
