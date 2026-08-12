# TASK-029 — Entidade Application + ApplicationEvent

**Fase:** 7 — Candidaturas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-007 §3, §5
**Depende de:** [TASK-028](task-028.md)

---

## Objetivo

Criar Application e ApplicationEvent com migrations.

## Escopo

- Entidade `Application` (candidateId, opportunityId, matchScore congelado, matchAlgorithmVersion, origem, status)
- Entidade `ApplicationEvent` (tipo, dataEvento, observação)

## Critérios de aceite

- [ ] Migrations aplicam sem erros
- [ ] Constraint evita duas candidaturas ativas do mesmo candidato para a mesma oportunidade

## Testes obrigatórios

- Testes de integração de persistência e da constraint de duplicidade

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
