# TASK-047 — Documentação OpenAPI

**Fase:** 12 — Fechamento
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-009 §9
**Depende de:** [TASK-033](task-033.md), [TASK-028](task-028.md)

---

## Objetivo

Gerar e revisar a documentação OpenAPI/Swagger de toda a API implementada.

## Escopo

- Configuração do springdoc-openapi
- Revisão manual da documentação gerada (descrições, exemplos)

## Critérios de aceite

- [ ] /swagger-ui acessível e reflete todos os endpoints implementados

## Testes obrigatórios

- Verificação manual (checklist de revisão da documentação gerada — não é teste automatizado)

## Economia de tokens

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
* Não usar web ou fontes externas para descobrir informações que devam ser obtidas do próprio repositório.
* Preferir uma única inspeção que responda várias perguntas relacionadas.
* Encerrar assim que os critérios forem satisfeitos.

**Princípio:** gastar tokens para descobrir fatos necessários, não para descrever o processo de descoberta.

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
