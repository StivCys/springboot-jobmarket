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

### Economia de tokens

O agente deve minimizar deliberadamente o consumo de tokens.

Regras:

* Não narrar cada comando executado.
* Não explicar comandos óbvios.
* Não repetir informações já descobertas.
* Não reproduzir arquivos completos quando apenas alguns valores são necessários.
* Não produzir análises especulativas.
* Não produzir resumos intermediários longos.
* Não perguntar ao usuário algo que possa ser determinado diretamente pelo repositório.
* Não apresentar alternativas arquiteturais não solicitadas.
* Não pesquisar documentação externa.
* Não usar web ou fontes externas para descobrir informações que devem ser obtidas do próprio repositório.
* Preferir uma única inspeção que responda várias perguntas relacionadas.
* Encerrar assim que os critérios forem satisfeitos.

**Princípio:** gastar tokens para descobrir fatos necessários, não para descrever o processo de descoberta.

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
