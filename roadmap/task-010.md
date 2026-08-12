# TASK-010 — Recuperação de senha

**Fase:** 1 — Usuários & Autenticação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-008 §6
**Depende de:** [TASK-008](task-008.md)

---

## Objetivo

Implementar o fluxo de recuperação de senha conforme SPEC-008 §6.

## Escopo

- `POST /api/v1/auth/forgot-password` (token de uso único, curta duração)
- `POST /api/v1/auth/reset-password` (define nova senha, invalida sessões/tokens anteriores)

## Critérios de aceite

- [ ] Solicitação de recuperação gera token válido para o e-mail informado
- [ ] Redefinição de senha com token válido funciona e invalida o token após uso
- [ ] Login com a senha antiga deixa de funcionar após o reset

## Testes obrigatórios

- Teste de integração cobrindo o fluxo completo (solicitar → redefinir → login com nova senha)

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
