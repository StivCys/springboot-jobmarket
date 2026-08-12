# TASK-043 — Tela de candidaturas recebidas

**Fase:** 10 — Frontend: Empresa
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §5, SPEC-007
**Depende de:** [TASK-042](task-042.md), [TASK-031](task-031.md)

---

## Objetivo

Implementar a gestão de candidaturas recebidas pela empresa (mudança de status, histórico de eventos).

## Escopo

- Listagem de candidaturas por oportunidade
- Ação de mudança de status com registro de evento

## Critérios de aceite

- [ ] Mudança de status reflete corretamente no backend e na lista

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
