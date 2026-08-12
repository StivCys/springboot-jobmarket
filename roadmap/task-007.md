# TASK-007 — Verificação de e-mail

**Fase:** 1 — Usuários & Autenticação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-001 §3.3, SPEC-008 §3
**Depende de:** [TASK-006](task-006.md)

---

## Objetivo

Implementar a confirmação de e-mail que transiciona a conta de PENDING_VERIFICATION para ACTIVE.

## Escopo

- Token de verificação de uso único, com expiração
- `POST /api/v1/auth/verify-email` (recebe token)
- Transição de status validada (não permite verificar conta já ativa/suspensa)

## Critérios de aceite

- [ ] Token válido ativa a conta
- [ ] Token expirado ou já usado retorna erro apropriado (422/409)
- [ ] Login não é permitido enquanto a conta estiver PENDING_VERIFICATION

## Testes obrigatórios

- Teste de integração cobrindo: verificação bem-sucedida, token expirado, token reutilizado

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
