# TASK-002 — Estrutura do projeto frontend

**Fase:** 0 — Fundação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §3
**Depende de:** Nenhuma

---

## Objetivo

> ⚠️ **Localização:** o frontend Angular deve ser criado no subdiretório
> `frontend/` na raiz deste repositório (decisão já tomada), convivendo com
> o projeto Spring Boot Maven já existente. Use `ng new frontend` a partir
> da raiz do repositório (ou `ng new` dentro de uma pasta `frontend/` já
> criada, conforme o fluxo do Angular CLI). Garanta que nenhum arquivo do
> backend (`pom.xml`, `mvnw`, `docker-compose.yml`, `src/`) seja tocado no
> processo, e que o `.gitignore` combinado (definido na TASK-000) já cubra
> `frontend/node_modules/` e `frontend/dist/`.

Criar o esqueleto do projeto Angular com os módulos base definidos na SPEC-010 (core, shared, auth, candidate, company) e roteamento inicial.

## Escopo

- Projeto Angular inicializado
- Módulos vazios: core, shared, auth, candidate, company (SPEC-010 §3)
- Roteamento base com lazy loading por módulo
- environment.ts apontando para a API local

## Critérios de aceite

- [ ] `ng build` executa com sucesso
- [ ] `ng serve` sobe a aplicação localmente
- [ ] Navegação entre rotas placeholder de cada módulo funciona

## Testes obrigatórios

- Teste unitário básico do AppComponent (smoke test)

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

- [ ] Código implementado e revisado
- [ ] Todos os critérios de aceite marcados como atendidos
- [ ] Todos os testes obrigatórios implementados e passando
- [ ] Suíte de testes completa do projeto continua passando (nenhuma task anterior quebrada)
- [ ] Task registrada como concluída neste arquivo e no `ROADMAP.md` antes de iniciar a próxima
