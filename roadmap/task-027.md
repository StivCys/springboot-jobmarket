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
