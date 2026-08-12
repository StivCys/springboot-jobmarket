# TASK-006 — Endpoint de registro

**Fase:** 1 — Usuários & Autenticação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-001 §3.3, SPEC-008 §3
**Depende de:** [TASK-005](task-005.md)

---

## Objetivo

Implementar o endpoint de registro de usuário, criando a conta com status PENDING_VERIFICATION.

## Escopo

- `POST /api/v1/auth/register` (email, senha, role)
- Hash de senha (BCrypt) antes de persistir
- Validação de e-mail único (409 em conflito, SPEC-009 §5.2)
- Envio de e-mail de verificação (stub/log nesta task)

## Critérios de aceite

- [ ] Registro com dados válidos cria usuário com status PENDING_VERIFICATION
- [ ] Registro com e-mail já existente retorna 409 no formato padrão de erro
- [ ] Senha nunca é retornada em nenhuma resposta da API

## Testes obrigatórios

- Teste de integração do endpoint (caso feliz + caso de conflito)
- Teste garantindo que a senha é armazenada com hash, nunca em texto puro

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
