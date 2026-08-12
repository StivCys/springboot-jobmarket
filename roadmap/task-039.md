# TASK-039 — Tela de minhas candidaturas

**Fase:** 9 — Frontend: Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §4, SPEC-007
**Depende de:** [TASK-038](task-038.md), [TASK-032](task-032.md)

---

## Objetivo

Implementar a listagem de candidaturas do candidato com status e histórico de eventos.

## Escopo

- Listagem com status atual
- Ação de retirada de candidatura

## Critérios de aceite

- [ ] Status exibido reflete o estado real da candidatura
- [ ] Retirada funciona e atualiza a lista

## Testes obrigatórios

- Testes unitários do componente

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
