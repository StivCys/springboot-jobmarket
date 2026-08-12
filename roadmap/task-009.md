# TASK-009 — Refresh token

**Fase:** 1 — Usuários & Autenticação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-008 §4
**Depende de:** [TASK-008](task-008.md)

---

## Objetivo

Implementar a renovação do access token via refresh token, incluindo invalidação em caso de expiração.

## Escopo

- `POST /api/v1/auth/refresh`
- Validação de refresh token (existência, expiração, não revogado)
- Emissão de novo access token

## Critérios de aceite

- [ ] Refresh token válido gera novo access token
- [ ] Refresh token expirado/inválido retorna 401

## Testes obrigatórios

- Teste de integração cobrindo refresh válido e inválido

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
