# TASK-021 — Preferências do candidato

**Fase:** 4 — Perfil do Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-002 §11 (patch — enums unificados)
**Depende de:** [TASK-019](task-019.md)

---

## Objetivo

Implementar o cadastro de preferências do candidato usando os enums unificados com a SPEC-004 (modelo de trabalho, tipo de contratação, localizações aceitas, senioridade aceita).

## Escopo

- `PUT /api/v1/candidates/me/preferences`
- Uso dos enums REMOTO/HIBRIDO/PRESENCIAL e CLT/PJ/FREELANCER/TEMPORARIO/PROJETO/ESTAGIO (patch aplicado)

## Critérios de aceite

- [ ] Preferências são persistidas e recuperáveis
- [ ] Valores fora do enum são rejeitados com 422

## Testes obrigatórios

- Teste de integração cobrindo atualização válida e valor inválido de enum

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
