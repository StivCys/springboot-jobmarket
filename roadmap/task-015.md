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
* Não usar web ou fontes externas para descobrir informações que devem ser obtidas do próprio repositório.
* Preferir uma única inspeção que responda várias perguntas relacionadas.
* Encerrar assim que os critérios forem satisfeitos.

**Princípio:** gastar tokens para descobrir fatos necessários, não para descrever o processo de descoberta.

## Definition of Done

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
