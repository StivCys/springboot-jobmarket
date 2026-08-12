# TASK-003 — Padrão de resposta/erro da API

**Fase:** 0 — Fundação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-009 §5
**Depende de:** [TASK-001](task-001.md)

---

## Objetivo

Implementar o envelope padrão de resposta (data/meta) e o tratamento global de exceções (error.code/message/details) definidos na SPEC-009 §5.

## Escopo

- Classe genérica de resposta de sucesso (`ApiResponse<T>`)
- `@ControllerAdvice` global mapeando exceções para os códigos HTTP da SPEC-009 §5.2 (400/401/403/404/409/422/500)
- Exceções de domínio base (`ResourceNotFoundException`, `BusinessRuleException`, `ConflictException`)

## Critérios de aceite

- [ ] Uma exceção de 'não encontrado' retorna 404 no formato padrão
- [ ] Uma exceção de validação retorna 422 no formato padrão
- [ ] Erros não tratados retornam 500 sem vazar stacktrace no corpo da resposta

## Testes obrigatórios

- Testes de integração (`@WebMvcTest`/`@SpringBootTest`) para cada tipo de exceção mapeada

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
