# TASK-040 — Tela de perfil da empresa + membros

**Fase:** 10 — Frontend: Empresa
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §5, SPEC-003
**Depende de:** [TASK-035](task-035.md), [TASK-014](task-014.md), [TASK-015](task-015.md)

---

## Objetivo

Implementar a tela de edição do perfil institucional e gestão de membros da empresa.

## Escopo

- Formulário de perfil institucional
- Gestão de membros (adicionar/remover/alterar role)
- Ação de publicação do perfil

## Critérios de aceite

- [ ] Edição e publicação do perfil funcionam ponta a ponta
- [ ] Gestão de membros respeita as regras de autorização (ex: regra do último OWNER)

## Testes obrigatórios

- Testes unitários dos componentes principais

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
