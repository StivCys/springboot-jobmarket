# ROADMAP — JobMarket

Índice geral de todas as tasks de implementação, agrupadas por fase.
Cada task vive em `roadmap/task-XXX.md`, com escopo, critérios de aceite,
testes obrigatórios e Definition of Done. Uma task só é considerada
concluída quando todos os testes obrigatórios estiverem passando — só então
a próxima task pode começar.

| Task | Fase | Título | Specs | Depende de | Status |
|---|---|---|---|---|---|
| [000](task-000.md) | 0 — Fundação | Levantamento do estado atual do projeto | SPEC-000 §17, SPEC-009 | — | 🔲 |
| [001](task-001.md) | 0 — Fundação | Estrutura do projeto backend | SPEC-000 §17, SPEC-009 | — | 🔲 |
| [002](task-002.md) | 0 — Fundação | Estrutura do projeto frontend | SPEC-010 §3 | — | 🔲 |
| [003](task-003.md) | 0 — Fundação | Padrão de resposta/erro da API | SPEC-009 §5 | 001 | 🔲 |
| [004](task-004.md) | 0 — Fundação | Configuração base de testes | SPEC-000 §22 (evolução incremental) | 001, 002 | 🔲 |
| [005](task-005.md) | 1 — Usuários & Autenticação | Entidade User + migration | SPEC-001 §3 | 001, 004 | 🔲 |
| [006](task-006.md) | 1 — Usuários & Autenticação | Endpoint de registro | SPEC-001 §3.3, SPEC-008 §3 | 005 | 🔲 |
| [007](task-007.md) | 1 — Usuários & Autenticação | Verificação de e-mail | SPEC-001 §3.3, SPEC-008 §3 | 006 | 🔲 |
| [008](task-008.md) | 1 — Usuários & Autenticação | Login + JWT | SPEC-008 §3, §4 | 007 | 🔲 |
| [009](task-009.md) | 1 — Usuários & Autenticação | Refresh token | SPEC-008 §4 | 008 | 🔲 |
| [010](task-010.md) | 1 — Usuários & Autenticação | Recuperação de senha | SPEC-008 §6 | 008 | 🔲 |
| [011](task-011.md) | 1 — Usuários & Autenticação | Spring Security — filtro JWT e guards por role | SPEC-008 §3, §5 | 008 | 🔲 |
| [012](task-012.md) | 2 — Empresas | Entidade Company + CompanyMembership | SPEC-003 §3, SPEC-001 §4.2 | 005 | 🔲 |
| [013](task-013.md) | 2 — Empresas | CRUD de perfil institucional da empresa | SPEC-003 §3, §5 | 012, 011 | 🔲 |
| [014](task-014.md) | 2 — Empresas | Gestão de membros da empresa | SPEC-001 §4.2, SPEC-003 | 013 | 🔲 |
| [015](task-015.md) | 2 — Empresas | Publicação do perfil da empresa | SPEC-003 §4, §5.1 | 013 | 🔲 |
| [016](task-016.md) | 3 — Skills | Entidade Skill | SPEC-005 §3 | 001, 004 | 🔲 |
| [017](task-017.md) | 3 — Skills | Busca/autocomplete de skills | SPEC-005 §4 | 016 | 🔲 |
| [018](task-018.md) | 4 — Perfil do Candidato | Entidade CandidateProfile + subentidades | SPEC-002 §3-§9 (v0.3) | 005, 016 | 🔲 |
| [019](task-019.md) | 4 — Perfil do Candidato | CRUD do perfil do candidato | SPEC-002 §3-§9 | 018, 011 | 🔲 |
| [020](task-020.md) | 4 — Perfil do Candidato | CandidateSkill + senioridade atual | SPEC-005 §3.2, SPEC-002 §6.1 (patch) | 019, 017 | 🔲 |
| [021](task-021.md) | 4 — Perfil do Candidato | Preferências do candidato | SPEC-002 §11 (patch — enums unificados) | 019 | 🔲 |
| [022](task-022.md) | 4 — Perfil do Candidato | Completude do perfil | SPEC-002 §12 | 020, 021 | 🔲 |
| [023](task-023.md) | 5 — Oportunidades | Entidade Opportunity + OpportunitySkill | SPEC-004 §3, SPEC-005 §8 | 012, 016 | 🔲 |
| [024](task-024.md) | 5 — Oportunidades | CRUD de oportunidades + ciclo de vida | SPEC-004 §5 | 023, 015 | 🔲 |
| [025](task-025.md) | 5 — Oportunidades | Listagem/busca pública de oportunidades | SPEC-004, SPEC-009 §6 | 024 | 🔲 |
| [026](task-026.md) | 6 — Matching | Serviço de cálculo de score | SPEC-006 §3-§4 | 022, 025, 020 | 🔲 |
| [027](task-027.md) | 6 — Matching | Explicação do matching | SPEC-006 §5 | 026 | 🔲 |
| [028](task-028.md) | 6 — Matching | Endpoints de matching | SPEC-006 §6, SPEC-009 §12 | 027 | 🔲 |
| [029](task-029.md) | 7 — Candidaturas | Entidade Application + ApplicationEvent | SPEC-007 §3, §5 | 028 | 🔲 |
| [030](task-030.md) | 7 — Candidaturas | Endpoint de candidatura | SPEC-007 §4, §7 | 029 | 🔲 |
| [031](task-031.md) | 7 — Candidaturas | Gestão de status da candidatura (empresa) | SPEC-007 §4, §5 | 030 | 🔲 |
| [032](task-032.md) | 7 — Candidaturas | Retirada de candidatura | SPEC-007 §4, §7 | 030 | 🔲 |
| [033](task-033.md) | 7 — Candidaturas | Regras de anti-spam | SPEC-007 §6 | 030 | 🔲 |
| [034](task-034.md) | 8 — Frontend: Core & Auth | Módulo core do frontend | SPEC-010 §3, §7 | 011, 002 | 🔲 |
| [035](task-035.md) | 8 — Frontend: Core & Auth | Telas de login/registro/recuperação de senha | SPEC-010 §4, SPEC-008 | 034, 010 | 🔲 |
| [036](task-036.md) | 9 — Frontend: Candidato | Tela de perfil do candidato | SPEC-010 §4, SPEC-002 | 035, 022 | 🔲 |
| [037](task-037.md) | 9 — Frontend: Candidato | Tela de oportunidades recomendadas | SPEC-010 §4, §8, SPEC-006 | 036, 028 | 🔲 |
| [038](task-038.md) | 9 — Frontend: Candidato | Tela de detalhe da oportunidade + candidatura | SPEC-010 §4, SPEC-004, SPEC-007 | 037, 030 | 🔲 |
| [039](task-039.md) | 9 — Frontend: Candidato | Tela de minhas candidaturas | SPEC-010 §4, SPEC-007 | 038, 032 | 🔲 |
| [040](task-040.md) | 10 — Frontend: Empresa | Tela de perfil da empresa + membros | SPEC-010 §5, SPEC-003 | 035, 014, 015 | 🔲 |
| [041](task-041.md) | 10 — Frontend: Empresa | Tela de gestão de oportunidades | SPEC-010 §5, SPEC-004 | 040, 024 | 🔲 |
| [042](task-042.md) | 10 — Frontend: Empresa | Tela de candidatos recomendados | SPEC-010 §5, §8, SPEC-006 | 041, 028 | 🔲 |
| [043](task-043.md) | 10 — Frontend: Empresa | Tela de candidaturas recebidas | SPEC-010 §5, SPEC-007 | 042, 031 | 🔲 |
| [044](task-044.md) | 11 — Admin | Módulo admin (backend) | SPEC-001 §3.2, SPEC-005 §5, SPEC-003 | 016, 015, 011 | 🔲 |
| [045](task-045.md) | 11 — Admin | Frontend admin | SPEC-010 (extensão — módulo admin) | 044, 034 | 🔲 |
| [046](task-046.md) | 12 — Fechamento | Testes end-to-end dos fluxos principais | Todas as specs | 039, 043, 045 | 🔲 |
| [047](task-047.md) | 12 — Fechamento | Documentação OpenAPI | SPEC-009 §9 | 033, 028 | 🔲 |
