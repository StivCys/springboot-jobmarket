# TASK-015 — Publicação do perfil da empresa

**Fase:** 2 — Empresas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-003 §4, §5.1
**Depende de:** [TASK-013](task-013.md)

---

## Objetivo

Implementar a transição de status do perfil institucional (DRAFT → PUBLISHED) e a regra de que oportunidades só podem ser publicadas por empresa com perfil publicado.

## Escopo

- `POST /api/v1/companies/{id}/publish`
- Validação de campos mínimos obrigatórios antes de publicar (setor, modelo de trabalho, localização)

## Critérios de aceite

- [ ] Publicação com perfil incompleto retorna erro 422 listando os campos faltantes
- [ ] Publicação com perfil completo transiciona o status corretamente

## Testes obrigatórios

- Testes de integração cobrindo publicação bem-sucedida e bloqueada por perfil incompleto

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
