# Roadmap de Implementação — JobMarket

Este diretório contém o roadmap incremental de implementação do JobMarket,
derivado das specs em `/specs` (SPEC-000 a SPEC-010, v0.3). Cada unidade de
trabalho é uma **task** autocontida em `task-XXX.md`, pensada para ser
executada de forma agêntica, uma de cada vez, em ordem.

## Estado do projeto

Este roadmap assume, por padrão, um projeto novo (greenfield). **Não é o
caso aqui**: o projeto Spring Boot já está configurado e o PostgreSQL já
roda via Docker. Por isso, a primeira coisa a executar é sempre a
**TASK-000**, que faz o levantamento do que já existe e produz
`roadmap/ESTADO-ATUAL.md` — as tasks seguintes (em especial 001, 002 e 004)
devem ser lidas em conjunto com esse arquivo antes de qualquer criação.

## Como usar

1. Abra `ROADMAP.md` para ver o panorama geral (todas as fases e tasks).
2. Pegue a próxima task **não concluída** cujas dependências (`Depende de`)
   já estejam todas concluídas.
3. Implemente **apenas** o escopo descrito na task — nada de adiantar
   trabalho de tasks futuras.
4. Escreva os testes obrigatórios listados na task e rode a suíte completa
   do projeto (não só os testes novos).
5. Marque os `Critérios de aceite` e o `Definition of Done` da task como
   concluídos.
6. Atualize o status da task em `ROADMAP.md` (🔲 → ✅).
7. Só então siga para a próxima task.

## Regra de ouro (não recriar o que já existe)

Antes de criar qualquer arquivo de configuração, projeto ou estrutura
(build.gradle, docker-compose.yml, pom.xml, `ng new`, migrations, etc.), o
agente deve:

1. Verificar se já existe (via `roadmap/ESTADO-ATUAL.md` e inspeção direta
   do repositório) — nunca assumir que não existe.
2. Se existir, **usar e complementar**, nunca recriar/sobrescrever, a menos
   que a task explicitamente peça uma migração/alteração.
3. Se encontrar algo que já existe mas diverge do que a task/spec descreve,
   **parar e reportar a divergência** em vez de decidir sozinho qual versão
   prevalece.

## Regra de ouro (testes antes de avançar)

> Nenhuma task é considerada concluída, e nenhuma próxima task deve começar,
> enquanto os testes obrigatórios daquela task não estiverem implementados e
> passando, e a suíte completa do projeto não continuar verde.

Isso vale mesmo que a implementação "pareça" funcionar manualmente — o
critério de aprovação é o teste automatizado, não a inspeção visual.

## Estrutura de uma task

Cada `task-XXX.md` segue o mesmo formato:

- **Fase** — a qual etapa do roadmap pertence (fundação, autenticação,
  empresas, skills, perfil do candidato, oportunidades, matching,
  candidaturas, frontend candidato/empresa/admin, fechamento).
- **Specs relacionadas** — quais SPECs (`/specs/SPEC-XXX-*.md`) fundamentam
  as decisões de modelo/regra daquela task. Em caso de dúvida durante a
  implementação, a spec é a fonte da verdade — não o roadmap.
- **Depende de** — quais tasks precisam estar concluídas antes desta.
- **Objetivo** — o que a task entrega, em uma ou duas frases.
- **Escopo** — o que exatamente deve ser feito (e, implicitamente, o que
  fica fora — se não está listado, não é desta task).
- **Critérios de aceite** — checklist funcional, verificável.
- **Testes obrigatórios** — o mínimo de cobertura de teste exigido para
  aprovar a task.
- **Definition of Done** — checklist final de fechamento.

## Fases do roadmap

| Fase | Conteúdo | Tasks |
|---|---|---|
| 0 — Fundação | Setup de backend, frontend, padrão de API e testes | 001–004 |
| 1 — Usuários & Autenticação | User, registro, verificação, login, JWT, refresh, recuperação de senha, autorização | 005–011 |
| 2 — Empresas | Company, CompanyMembership, CRUD, membros, publicação | 012–015 |
| 3 — Skills | Taxonomia de skills, sinônimos, busca | 016–017 |
| 4 — Perfil do Candidato | CandidateProfile, experiências, skills, preferências, completude | 018–022 |
| 5 — Oportunidades | Opportunity, requisitos, ciclo de vida, listagem pública | 023–025 |
| 6 — Matching | Cálculo de score, explicabilidade, endpoints | 026–028 |
| 7 — Candidaturas | Application, eventos, status, retirada, anti-spam | 029–033 |
| 8 — Frontend: Core & Auth | Interceptors, guards, telas de login/registro | 034–035 |
| 9 — Frontend: Candidato | Perfil, recomendações, candidatura, minhas candidaturas | 036–039 |
| 10 — Frontend: Empresa | Perfil da empresa, oportunidades, candidatos recomendados, candidaturas recebidas | 040–043 |
| 11 — Admin | Moderação de skills, suspensão de contas/empresas (backend + frontend) | 044–045 |
| 12 — Fechamento | Testes e2e dos fluxos principais e documentação OpenAPI | 046–047 |

## Observações

- O roadmap segue a ordem de dependência dos domínios definida nas próprias
  specs: usuários → empresas/skills → perfil do candidato → oportunidades →
  matching → candidaturas → frontend → admin → fechamento.
- Backend de um domínio vem antes do frontend correspondente, para que cada
  tela já tenha uma API real e testada para consumir.
- Se, durante a implementação de uma task, for identificada uma
  inconsistência ou lacuna nas specs, ela deve ser corrigida na spec
  primeiro (como foi feito no `PATCH-consistencia-specs.md`), e só depois a
  task deve prosseguir — o roadmap não deve "compensar" um problema de spec
  com uma decisão de implementação não documentada.
- Novas tasks podem ser adicionadas ao final de uma fase (ex: `task-017a.md`
  não é necessário — prefira renumerar ou inserir como `task-048.md` em
  diante, mantendo a ordem cronológica de execução) caso o escopo de uma
  fase precise crescer.
