# TASK-035 — Telas de login/registro/recuperação de senha

**Fase:** 8 — Frontend: Core & Auth
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §4, SPEC-008
**Depende de:** [TASK-034](task-034.md), [TASK-010](task-010.md)

---

## Objetivo

Implementar as telas de autenticação do frontend.

## Escopo

- Tela de login
- Tela de registro (com seleção de role)
- Tela de recuperação/redefinição de senha
- Tela de aviso de verificação de e-mail pendente

## Critérios de aceite

- [ ] Fluxo completo de registro → verificação → login funciona ponta a ponta
- [ ] Fluxo de recuperação de senha funciona ponta a ponta

## Testes obrigatórios

- Testes unitários dos componentes + ao menos um teste e2e do fluxo de login

## Economia de tokens

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
* Não usar web ou fontes externas para descobrir informações que devam ser obtidas do próprio repositório.
* Preferir uma única inspeção que responda várias perguntas relacionadas.
* Encerrar assim que os critérios forem satisfeitos.

**Princípio:** gastar tokens para descobrir fatos necessários, não para descrever o processo de descoberta.

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
