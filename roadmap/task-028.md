# TASK-028 — Endpoints de matching

**Fase:** 6 — Matching
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-006 §6, SPEC-009 §12
**Depende de:** [TASK-027](task-027.md)

---

## Objetivo

Expor os endpoints de matching nas duas direções (candidato→oportunidades e empresa→candidatos), incluindo versionamento do algoritmo.

## Escopo

- `GET /api/v1/matching/opportunities` (candidato autenticado)
- `GET /api/v1/matching/candidates/{opportunityId}` (empresa autenticada)
- Campo matchAlgorithmVersion na resposta

## Critérios de aceite

- [ ] Apenas oportunidades OPEN e candidatos com perfil ao menos BASICO participam
- [ ] Resultado inclui score + explicação + versão do algoritmo

## Testes obrigatórios

- Testes de integração cobrindo as duas direções do matching

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
