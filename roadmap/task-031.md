# TASK-031 — Gestão de status da candidatura (empresa)

**Fase:** 7 — Candidaturas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-007 §4, §5
**Depende de:** [TASK-030](task-030.md)

---

## Objetivo

Implementar a transição de status da candidatura pelo lado da empresa, registrando eventos.

## Escopo

- `PUT /api/v1/applications/{id}/status` (VIEWED/IN_PROCESS/REJECTED/HIRED)
- Registro automático de ApplicationEvent a cada transição

## Critérios de aceite

- [ ] Transições seguem o diagrama da SPEC-007 §4 (nenhuma transição inválida permitida)
- [ ] Cada transição gera o evento correspondente

## Testes obrigatórios

- Testes de integração cobrindo transições válidas, inválidas e geração de eventos

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
