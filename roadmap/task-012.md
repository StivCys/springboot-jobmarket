# TASK-012 — Entidade Company + CompanyMembership

**Fase:** 2 — Empresas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-003 §3, SPEC-001 §4.2
**Depende de:** [TASK-005](task-005.md)

---

## Objetivo

Criar as entidades Company e CompanyMembership e suas migrations, conforme modelo revisado (empresa independente do usuário, ligada via membership).

## Escopo

- Entidade `Company` (dados institucionais da SPEC-003 §3, status DRAFT/PUBLISHED/SUSPENDED)
- Entidade `CompanyMembership` (userId, companyId, role [OWNER/ADMIN/RECRUITER/MEMBER], status)
- Migrations Flyway correspondentes

## Critérios de aceite

- [ ] Migrations aplicam sem erros
- [ ] Um User pode estar associado a mais de uma Company via membership
- [ ] Constraint evita membership duplicado (mesmo user + company)

## Testes obrigatórios

- Testes de integração de persistência para Company e CompanyMembership

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
