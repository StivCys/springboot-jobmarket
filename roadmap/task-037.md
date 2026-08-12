# TASK-037 — Tela de oportunidades recomendadas

**Fase:** 9 — Frontend: Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §4, §8, SPEC-006
**Depende de:** [TASK-036](task-036.md), [TASK-028](task-028.md)

---

## Objetivo

Implementar a listagem de oportunidades recomendadas com score e explicação de matching.

## Escopo

- OpportunityCardComponent, MatchScoreComponent, MatchExplanationComponent (componentes compartilhados)

## Critérios de aceite

- [ ] Lista exibe score e explicação (pontos fortes/atenção) de cada oportunidade

## Testes obrigatórios

- Testes unitários dos componentes de matching reutilizáveis

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
