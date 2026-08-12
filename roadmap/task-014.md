# TASK-014 — Gestão de membros da empresa

**Fase:** 2 — Empresas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-001 §4.2, SPEC-003
**Depende de:** [TASK-013](task-013.md)

---

## Objetivo

Implementar convite e gestão de membros de uma empresa (roles OWNER/ADMIN/RECRUITER/MEMBER).

## Escopo

- `POST /api/v1/companies/{id}/members` (convite/adição)
- `PUT /api/v1/companies/{id}/members/{userId}` (alteração de role)
- `DELETE /api/v1/companies/{id}/members/{userId}` (remoção)
- Regra: não permitir remover o último OWNER

## Critérios de aceite

- [ ] Adição de membro funciona e respeita autorização (apenas OWNER/ADMIN adicionam)
- [ ] Remoção do último OWNER é bloqueada
- [ ] Alteração de role respeita hierarquia de permissões

## Testes obrigatórios

- Testes de integração cobrindo os três cenários acima

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
