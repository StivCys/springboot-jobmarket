# TASK-003 — Padrão de resposta/erro da API

**Fase:** 0 — Fundação
**Status:** ✅ Concluída
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

- [x] Uma exceção de 'não encontrado' retorna 404 no formato padrão
- [x] Uma exceção de validação retorna 422 no formato padrão
- [x] Erros não tratados retornam 500 sem vazar stacktrace no corpo da resposta

## Testes obrigatórios

- Testes de integração (`@WebMvcTest`/`@SpringBootTest`) para cada tipo de exceção mapeada

### Economia de tokens

O agente deve minimizar deliberadamente o consumo de tokens.

Regras:

* Não narrar cada comando executado.
* Não explicar comandos óbvios.
* Não repetir informações já descobertas.
* Não reproduzir arquivos completos quando apenas alguns valores são necessários.
* Não produzir análises especulativas.
* Não produzir resumos intermediários longos.
* Não perguntar ao usuário algo que possa ser determinado diretamente pelo repositório.
* Não apresentar alternativas arquiteturais não solicitadas.
* Não pesquisar documentação externa.
* Não usar web ou fontes externas para descobrir informações que devem ser obtidas do próprio repositório.
* Preferir uma única inspeção que responda várias perguntas relacionadas.
* Encerrar assim que os critérios forem satisfeitos.

**Princípio:** gastar tokens para descobrir fatos necessários, não para descrever o processo de descoberta.

## Definition of Done

- [x] Código implementado e revisado
- [x] Todos os critérios de aceite marcados como atendidos
- [x] Todos os testes obrigatórios implementados e passando
- [x] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [x] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
