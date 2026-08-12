# TASK-019 — CRUD do perfil do candidato

**Fase:** 4 — Perfil do Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-002 §3-§9
**Depende de:** [TASK-018](task-018.md), [TASK-011](task-011.md)

---

## Objetivo

Implementar os endpoints de edição do perfil do candidato (dados básicos, experiências, formação, projetos).

## Escopo

- `GET/PUT /api/v1/candidates/me`
- `POST/PUT/DELETE /api/v1/candidates/me/experiences`
- `POST/PUT/DELETE /api/v1/candidates/me/educations`
- `POST/PUT/DELETE /api/v1/candidates/me/projects`

## Critérios de aceite

- [ ] Um candidato só edita o próprio perfil (nunca o de outro usuário)
- [ ] CRUD de experiências/formação/projetos funciona de ponta a ponta

## Testes obrigatórios

- Testes de integração cobrindo CRUD completo e tentativa de editar perfil de outro usuário (deve retornar 403)

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
