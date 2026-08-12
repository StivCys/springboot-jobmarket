# TASK-045 — Frontend admin

**Fase:** 11 — Admin
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 (extensão — módulo admin)
**Depende de:** [TASK-044](task-044.md), [TASK-034](task-034.md)

---

## Objetivo

Implementar as telas administrativas básicas (moderação de skills, suspensão de contas/empresas).

## Escopo

- Tela de fila de skills pendentes de aprovação
- Tela de busca e suspensão de usuários/empresas

## Critérios de aceite

- [ ] Aprovação de skill reflete na busca pública de skills (task 017)
- [ ] Suspensão reflete no bloqueio de login/publicação

## Testes obrigatórios

- Testes unitários dos componentes principais

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
