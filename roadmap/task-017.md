# TASK-017 — Busca/autocomplete de skills

**Fase:** 3 — Skills
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-005 §4
**Depende de:** [TASK-016](task-016.md)

---

## Objetivo

Implementar endpoint de busca de skills com suporte a sinônimos, usado tanto no cadastro de candidato quanto no de oportunidade.

## Escopo

- `GET /api/v1/skills?query=...`
- Normalização de termo digitado via tabela de sinônimos

## Critérios de aceite

- [ ] Buscar por 'JS' retorna a skill canônica 'JavaScript'
- [ ] Busca é paginada (SPEC-009 §6)

## Testes obrigatórios

- Teste de integração cobrindo busca direta e busca por sinônimo

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
