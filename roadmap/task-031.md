# TASK-031 — Gestão de status da candidatura (empresa)

**Fase:** 7 — Candidaturas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-007 §4, §5
**Depende de:** [TASK-030](task-030.md)

---

## Objetivo

Implementar a transição de status da candidatura pelo lado da empresa, registrando eventos.

## Escopo

- `PUT /api/v1/applications/{id}/status` (VIEWED/IN_PROCESS/REJECTED/HIRED)
- Registro automático de ApplicationEvent a cada transição

## Critérios de aceite

- [ ] Transições seguem o diagrama da SPEC-007 §4 (nenhuma transição inválida permitida)
- [ ] Cada transição gera o evento correspondente

## Testes obrigatórios

- Testes de integração cobrindo transições válidas, inválidas e geração de eventos

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
