# TASK-018 — Entidade CandidateProfile + subentidades

**Fase:** 4 — Perfil do Candidato
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-002 §3-§9 (v0.3)
**Depende de:** [TASK-005](task-005.md), [TASK-016](task-016.md)

---

## Objetivo

Criar CandidateProfile e as subentidades Experience, Education, Project, com suas migrations.

## Escopo

- Entidade `CandidateProfile` (dados básicos, localização, disponibilidade, senioridade atual, objetivos)
- Entidades `Experience`, `Education`, `Project` (1:N com CandidateProfile)

## Critérios de aceite

- [ ] Migrations aplicam sem erros
- [ ] Um User com role CANDIDATE pode ter no máximo um CandidateProfile

## Testes obrigatórios

- Testes de integração de persistência para o agregado completo

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
