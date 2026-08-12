# TASK-002 — Estrutura do projeto frontend

**Fase:** 0 — Fundação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §3
**Depende de:** Nenhuma

---

## Objetivo

Criar o esqueleto do projeto Angular com os módulos base definidos na SPEC-010 (core, shared, auth, candidate, company) e roteamento inicial.

## Escopo

- Projeto Angular inicializado
- Módulos vazios: core, shared, auth, candidate, company (SPEC-010 §3)
- Roteamento base com lazy loading por módulo
- environment.ts apontando para a API local

## Critérios de aceite

- [ ] `ng build` executa com sucesso
- [ ] `ng serve` sobe a aplicação localmente
- [ ] Navegação entre rotas placeholder de cada módulo funciona

## Testes obrigatórios

- Teste unitário básico do AppComponent (smoke test)

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
