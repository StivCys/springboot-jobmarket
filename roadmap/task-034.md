# TASK-034 — Módulo core do frontend

**Fase:** 8 — Frontend: Core & Auth
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §3, §7
**Depende de:** [TASK-011](task-011.md), [TASK-002](task-002.md)

---

## Objetivo

Implementar o módulo core do Angular — interceptor JWT, guards de rota por role, serviço de sessão.

## Escopo

- AuthInterceptor (anexa access token, trata 401 disparando refresh)
- AuthGuard / RoleGuard
- SessionService (estado do usuário logado)

## Critérios de aceite

- [ ] Requisições autenticadas incluem o token automaticamente
- [ ] Rota protegida redireciona usuário não autenticado para login
- [ ] Rota de candidato bloqueia acesso de usuário com role empresa (e vice-versa)

## Testes obrigatórios

- Testes unitários do interceptor e dos guards

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
