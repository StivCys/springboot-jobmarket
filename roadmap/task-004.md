# TASK-004 — Configuração base de testes

**Fase:** 0 — Fundação
**Status:** ✅ Concluída
**Specs relacionadas:** SPEC-000 §22 (evolução incremental)
**Depende de:** [TASK-001](task-001.md), [TASK-002](task-002.md)

---

## Objetivo

> ⚠️ **Pré-condição:** o PostgreSQL já está rodando via Docker neste
> projeto. Esta task NÃO deve criar um novo `docker-compose.yml` nem alterar
> o existente — apenas configurar o Testcontainers para os testes de
> integração (que sobe um banco isolado próprio, independente do container
> de desenvolvimento já em uso).

Configurar a infraestrutura de testes usada por todas as tasks seguintes — Testcontainers para testes de integração com Postgres real no backend, e testes unitários no frontend.

## Escopo

- Testcontainers configurado para subir Postgres em testes de integração
- Perfil de teste (`application-test.yml`) isolado do perfil local
- Cobertura de testes configurada (Jacoco) no backend
- Configuração padrão de testes unitários no frontend (Angular CLI)

## Critérios de aceite

- [x] Um teste de integração de exemplo sobe um Postgres via Testcontainers e roda uma migration Flyway
- [x] Relatório de cobertura é gerado no backend
- [x] `ng test` executa com sucesso no frontend

## Testes obrigatórios

- Teste de integração de exemplo validando a infraestrutura (sem regra de negócio ainda)

### Economia de tokens

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

- [x] Código implementado e revisado
- [x] Todos os critérios de aceite marcados como atendidos
- [x] Todos os testes obrigatórios implementados e passando
- [x] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [x] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
