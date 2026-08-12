# TASK-025 — Listagem/busca pública de oportunidades

**Fase:** 5 — Oportunidades
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-004, SPEC-009 §6
**Depende de:** [TASK-024](task-024.md)

---

## Objetivo

Implementar a listagem pública de oportunidades (status OPEN) com paginação e filtros básicos, para o lado do candidato.

## Escopo

- `GET /api/v1/opportunities` (filtros: modalidade, tipoContratacao, senioridade, tecnologias)
- Paginação conforme padrão da SPEC-009 §6

## Critérios de aceite

- [ ] Apenas oportunidades OPEN aparecem na listagem pública
- [ ] Filtros combinados funcionam corretamente

## Testes obrigatórios

- Testes de integração cobrindo listagem sem filtro, com filtro único e com filtros combinados

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
