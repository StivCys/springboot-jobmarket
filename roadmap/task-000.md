# TASK-000 — Levantamento do estado atual do projeto

**Fase:** 0 — Fundação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-000 §17, SPEC-009
**Depende de:** Nenhuma

---

## Objetivo

Antes de qualquer implementação, mapear exatamente o que já existe no
repositório (projeto Spring Boot já configurado, Postgres já rodando via
Docker) e confrontar com o roadmap, para que nenhuma task futura recrie algo
que já existe ou entre em conflito com a configuração atual.

Esta é uma task **somente de leitura e documentação** — nenhuma alteração de
código de produção deve ser feita aqui.

## Escopo

- Confirmar formalmente: build tool é **Maven** (`pom.xml`, `mvnw`),
  configuração em `application.properties` (não `.yml`), pacote base
  `com.jobmarket`. Registrar a versão exata do Java declarada no `pom.xml`
  e do Spring Boot, e conferir se batem com o esperado (Java 24)
- Verificar se o Flyway já está configurado e listar as migrations que já
  existem (se houver)
- Confirmar como o Postgres já está sendo executado (arquivo
  `docker-compose.yml` existente, credenciais, porta, nome do banco) —
  **sem criar um novo docker-compose**
- Documentar que o frontend Angular será criado em `frontend/` na raiz do
  repositório (decisão já tomada), e ajustar/criar um `.gitignore` combinado
  na raiz cobrindo `target/` (Maven), `node_modules/` e `dist/` (Angular),
  evitando que artefatos de build de um lado poluam o outro
- Atualizar `ROADMAP.md` marcando como concluída (✅) ou parcialmente
  concluída (🟡, com nota do que falta) qualquer task cujo escopo já esteja,
  no todo ou em parte, satisfeito pelo que já existe no repositório
  (candidatas mais prováveis: TASK-001, TASK-002, TASK-004)
- Confirmar que `db/migration` está vazio (nenhuma migration Flyway criada
  ainda) e que o Flyway ainda não está nas dependências do `pom.xml`
- Registrar tudo isso em `roadmap/ESTADO-ATUAL.md`

## Critérios de aceite

- [ ] `roadmap/ESTADO-ATUAL.md` criado, contendo: estrutura atual do
      backend, status do Flyway/migrations, como o Postgres é executado,
      estrutura definida para o frontend na raiz, e lista de divergências
      encontradas (se houver) entre o que existe e o que as specs/roadmap
      descrevem
- [ ] `ROADMAP.md` atualizado refletindo o status real das TASK-001,
      TASK-002 e TASK-004
- [ ] Nenhum arquivo de configuração existente (build, docker-compose,
      application.yml) foi sobrescrito ou recriado nesta task

## Testes obrigatórios

- Não aplicável — task de diagnóstico, sem alteração de código de produção

## Definition of Done

- [ ] `ESTADO-ATUAL.md` revisado e aprovado antes de iniciar qualquer outra task
- [ ] `ROADMAP.md` com status atualizado
- [ ] Nenhuma outra task foi iniciada em paralelo a esta
