# TASK-023 — Entidade Opportunity + OpportunitySkill

**Fase:** 5 — Oportunidades
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-004 §3, SPEC-005 §8
**Depende de:** [TASK-012](task-012.md), [TASK-016](task-016.md)

---

## Objetivo

Criar Opportunity e OpportunitySkill (requisitos obrigatórios/desejáveis) com migrations.

## Escopo

- Entidade `Opportunity` (título, descrição, tipo, modalidade, localização, senioridade, faixa salarial, status)
- Entidade `OpportunitySkill` (skillId, tipo REQUIRED/DESIRED — modelo autoritativo, patch aplicado)

## Critérios de aceite

- [ ] Migrations aplicam sem erros
- [ ] Toda Opportunity deve ter ao menos um OpportunitySkill do tipo REQUIRED

## Testes obrigatórios

- Testes de integração de persistência e da regra de requisito obrigatório mínimo

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
