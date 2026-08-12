# TASK-011 — Spring Security — filtro JWT e guards por role

**Fase:** 1 — Usuários & Autenticação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-008 §3, §5
**Depende de:** [TASK-008](task-008.md)

---

## Objetivo

Configurar o filtro de autenticação JWT e as regras de autorização por papel (role), protegendo as rotas conforme SPEC-008 §5.

## Escopo

- Filtro JWT validando o access token em cada requisição
- `SecurityFilterChain` com regras por rota/role
- Regra: usuário só acessa/altera recursos próprios, exceto recursos públicos

## Critérios de aceite

- [ ] Requisição sem token a rota protegida retorna 401
- [ ] Requisição com token de role incorreta a rota protegida por role retorna 403
- [ ] Requisição autenticada e autorizada passa normalmente

## Testes obrigatórios

- Testes de integração cobrindo os três cenários acima para pelo menos uma rota protegida de exemplo

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
