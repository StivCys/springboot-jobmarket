# TASK-013 — CRUD de perfil institucional da empresa

**Fase:** 2 — Empresas
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-003 §3, §5
**Depende de:** [TASK-012](task-012.md), [TASK-011](task-011.md)

---

## Objetivo

Implementar os endpoints de criação e edição do perfil institucional da empresa.

## Escopo

- `POST /api/v1/companies` (cria empresa + membership OWNER para o criador)
- `GET/PUT /api/v1/companies/{id}`
- Autorização: apenas membros com role adequado podem editar

## Critérios de aceite

- [ ] Criação de empresa gera membership OWNER automaticamente para o criador
- [ ] Edição por membro sem permissão retorna 403
- [ ] Edição por OWNER/ADMIN funciona corretamente

## Testes obrigatórios

- Testes de integração cobrindo criação, edição autorizada e edição não autorizada

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
