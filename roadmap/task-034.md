# TASK-034 — Módulo core do frontend

**Fase:** 8 — Frontend: Core & Auth
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-010 §3, §7
**Depende de:** [TASK-011](task-011.md), [TASK-002](task-002.md)

---

## Objetivo

Implementar o módulo core do Angular — interceptor JWT, guards de rota por role, serviço de sessão.

## Escopo

- AuthInterceptor (anexa access token, trata 401 disparando refresh)
- AuthGuard / RoleGuard
- SessionService (estado do usuário logado)

## Critérios de aceite

- [ ] Requisições autenticadas incluem o token automaticamente
- [ ] Rota protegida redireciona usuário não autenticado para login
- [ ] Rota de candidato bloqueia acesso de usuário com role empresa (e vice-versa)

## Testes obrigatórios

- Testes unitários do interceptor e dos guards

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
