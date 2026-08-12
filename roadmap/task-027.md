# TASK-027 — Explicação do matching

**Fase:** 6 — Matching
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-006 §5
**Depende de:** [TASK-026](task-026.md)

---

## Objetivo

Implementar a geração da explicação estruturada do matching (pontos fortes / pontos de atenção), derivada diretamente do cálculo do score.

## Escopo

- Geração de MatchExplanation (pontosFortes[], pontosDeAtencao[]) a partir dos mesmos dados usados no score

## Critérios de aceite

- [ ] Toda skill obrigatória atendida aparece como ponto forte
- [ ] Toda skill obrigatória ausente aparece como ponto de atenção
- [ ] A explicação nunca diverge do score calculado (mesmos dados de entrada)

## Testes obrigatórios

- Testes unitários cobrindo geração da explicação para os mesmos casos de borda da task 026

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
