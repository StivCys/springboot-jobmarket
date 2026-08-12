# TASK-044 — Módulo admin (backend)

**Fase:** 11 — Admin
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-001 §3.2, SPEC-005 §5, SPEC-003
**Depende de:** [TASK-016](task-016.md), [TASK-015](task-015.md), [TASK-011](task-011.md)

---

## Objetivo

Implementar endpoints administrativos: moderação de skills sugeridas e suspensão de contas/empresas.

## Escopo

- `PUT /api/v1/admin/skills/{id}/approve` (ativa skill sugerida)
- `PUT /api/v1/admin/users/{id}/suspend`
- `PUT /api/v1/admin/companies/{id}/suspend`
- Restrito a role ADMIN

## Critérios de aceite

- [ ] Apenas usuários ADMIN acessam essas rotas (403 para os demais)
- [ ] Suspensão de conta bloqueia login imediatamente

## Testes obrigatórios

- Testes de integração cobrindo autorização e efeito das ações administrativas

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
