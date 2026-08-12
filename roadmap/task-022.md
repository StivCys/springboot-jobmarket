# TASK-022 — Completude do perfil

**Fase:** 4 — Perfil do Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-002 §12
**Depende de:** [TASK-020](task-020.md), [TASK-021](task-021.md)

---

## Objetivo

Implementar o cálculo do nível de completude do perfil (INCOMPLETO/BASICO/COMPLETO).

## Escopo

- Serviço/campo derivado que calcula a completude a partir dos blocos preenchidos
- Exposição do nível de completude no GET /api/v1/candidates/me

## Critérios de aceite

- [ ] Perfil sem skills/localização/disponibilidade é classificado como INCOMPLETO
- [ ] Perfil com todos os blocos preenchidos é classificado como COMPLETO

## Testes obrigatórios

- Testes unitários cobrindo as três classificações com diferentes combinações de campos preenchidos

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
