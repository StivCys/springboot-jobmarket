# TASK-041 — Tela de gestão de oportunidades

**Fase:** 10 — Frontend: Empresa
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §5, SPEC-004
**Depende de:** [TASK-040](task-040.md), [TASK-024](task-024.md)

---

## Objetivo

Implementar CRUD de oportunidades pelo lado da empresa, incluindo transições de status.

## Escopo

- Formulário de criação/edição de oportunidade (requisitos obrigatórios/desejáveis)
- Ações de publicar/pausar/retomar/encerrar

## Critérios de aceite

- [ ] CRUD completo funciona ponta a ponta
- [ ] Transições de status inválidas são bloqueadas na UI

## Testes obrigatórios

- Testes unitários dos componentes de formulário e de transição de status

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
