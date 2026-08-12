# TASK-021 — Preferências do candidato

**Fase:** 4 — Perfil do Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-002 §11 (patch — enums unificados)
**Depende de:** [TASK-019](task-019.md)

---

## Objetivo

Implementar o cadastro de preferências do candidato usando os enums unificados com a SPEC-004 (modelo de trabalho, tipo de contratação, localizações aceitas, senioridade aceita).

## Escopo

- `PUT /api/v1/candidates/me/preferences`
- Uso dos enums REMOTO/HIBRIDO/PRESENCIAL e CLT/PJ/FREELANCER/TEMPORARIO/PROJETO/ESTAGIO (patch aplicado)

## Critérios de aceite

- [ ] Preferências são persistidas e recuperáveis
- [ ] Valores fora do enum são rejeitados com 422

## Testes obrigatórios

- Teste de integração cobrindo atualização válida e valor inválido de enum

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
