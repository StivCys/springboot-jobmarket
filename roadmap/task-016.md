# TASK-016 — Entidade Skill

**Fase:** 3 — Skills
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-005 §3
**Depende de:** [TASK-001](task-001.md), [TASK-004](task-004.md)

---

## Objetivo

Criar a entidade Skill com sinônimos e categorias, migrations e uma carga inicial (seed) de skills comuns.

## Escopo

- Entidade `Skill` (nome canônico, categoria, sinônimos, ativo)
- Migration Flyway + seed inicial (linguagens, frameworks, bancos de dados mais comuns)

## Critérios de aceite

- [ ] Migrations e seed aplicam sem erros
- [ ] Busca por sinônimo retorna a skill canônica correspondente

## Testes obrigatórios

- Teste de integração de persistência e de resolução de sinônimo

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
