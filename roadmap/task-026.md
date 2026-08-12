# TASK-026 — Serviço de cálculo de score

**Fase:** 6 — Matching
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-006 §3-§4
**Depende de:** [TASK-022](task-022.md), [TASK-025](task-025.md), [TASK-020](task-020.md)

---

## Objetivo

Implementar o serviço de cálculo de compatibilidade entre candidato e oportunidade, com subscores por dimensão (skills, experiência/senioridade, localização, preferências).

## Escopo

- Cálculo de subscore de skills (obrigatórias vs. desejáveis, regras SPEC-006 §4)
- Cálculo de subscore de senioridade (usando senioridadeAtual do candidato — patch aplicado)
- Cálculo de subscore de localização e modelo de trabalho (enums unificados — patch aplicado)
- Composição do score final com pesos configuráveis

## Critérios de aceite

- [ ] Ausência de skill obrigatória reduz fortemente o subscore de skills
- [ ] Skills desejáveis somam pontos sem desqualificar na ausência
- [ ] Score final é a composição ponderada dos subscores

## Testes obrigatórios

- Testes unitários cobrindo cada dimensão isoladamente e a composição final, incluindo casos de borda (match perfeito, incompatibilidade total, requisito obrigatório ausente)

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
