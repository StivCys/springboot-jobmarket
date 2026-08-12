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

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
