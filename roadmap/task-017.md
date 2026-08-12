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

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
