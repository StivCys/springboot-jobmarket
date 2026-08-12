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
