# TASK-008 — Login + JWT

**Fase:** 1 — Usuários & Autenticação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-008 §3, §4
**Depende de:** [TASK-007](task-007.md)

---

## Objetivo

Implementar o endpoint de login que autentica o usuário e emite Access Token (JWT) e Refresh Token.

## Escopo

- `POST /api/v1/auth/login` (email, senha)
- Geração de JWT contendo userId, role, status (SPEC-008 §4)
- Bloqueio de login para contas PENDING_VERIFICATION e SUSPENDED (SPEC-008 §7)
- Emissão de refresh token (persistido/hash)

## Critérios de aceite

- [ ] Login com credenciais válidas retorna access token + refresh token
- [ ] Login com senha incorreta retorna 401 padronizado
- [ ] Login de conta PENDING_VERIFICATION ou SUSPENDED é bloqueado com erro apropriado

## Testes obrigatórios

- Teste de integração cobrindo login bem-sucedido e os três cenários de bloqueio

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
