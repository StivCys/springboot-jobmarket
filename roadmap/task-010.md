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
