# SPEC-010 — Frontend

**Status:** Draft
**Versão:** 0.2
**Tipo:** Arquitetura de Frontend
**Depende de:** SPEC-000 a SPEC-009

---

## 1. Objetivo

Definir a estrutura conceitual do frontend Angular do JobMarket, cobrindo as principais telas necessárias para suportar os fluxos descritos nas specs anteriores.

O frontend será responsável pela experiência de uso, navegação, apresentação dos dados e comunicação com a API REST definida na SPEC-009.

As regras de autenticação e autorização serão sempre validadas pelo backend, conforme SPEC-008. Guards e controles de interface no frontend têm como objetivo controlar navegação e experiência do usuário, não substituir a segurança da API.

---

## 2. Escopo

Cobre:

- organização geral de módulos;
- principais telas por perfil de usuário;
- contexto de empresa e `CompanyMembership`;
- estratégia de estado e comunicação com a API (SPEC-009);
- gerenciamento do estado de autenticação;
- guards e interceptors;
- fluxo de navegação de alto nível;
- componentes compartilhados relevantes.

Não cobre:

- design visual/UI kit definitivo;
- detalhamento de componentes individuais;
- implementação detalhada dos serviços;
- aplicação mobile nativa;
- internacionalização (i18n);
- design system definitivo.

---

## 3. Organização de módulos (conceitual)

```text
app
│
├── core
│   ├── auth
│   │   ├── auth.service
│   │   ├── auth.guard
│   │   ├── role.guard
│   │   └── auth.interceptor
│   │
│   ├── company-context
│   │   └── contexto da empresa atualmente selecionada
│   │
│   └── services
│       └── serviços globais
│
├── shared
│   ├── components
│   ├── pipes
│   ├── directives
│   └── models
│
├── auth
│   ├── login
│   ├── registro
│   ├── verificar-email
│   └── recuperar-senha
│
├── candidate
│   ├── perfil
│   ├── oportunidades-recomendadas
│   ├── oportunidade-detalhe
│   └── minhas-candidaturas
│
├── company
│   ├── empresas
│   ├── perfil
│   ├── membros
│   ├── oportunidades
│   ├── candidatos-recomendados
│   └── candidaturas-recebidas
│
└── matching-shared
    ├── match-score
    └── match-explanation
```

A organização acima é conceitual. A implementação poderá utilizar Angular Standalone Components e lazy loading conforme a arquitetura definida durante o desenvolvimento.

---

## 4. Contexto de autenticação

O frontend deverá manter um estado centralizado de autenticação contendo, no mínimo, informações necessárias para determinar o contexto atual da aplicação.

Conceitualmente:

```text
AuthState
├── authenticated
├── user
│   ├── id
│   ├── email
│   ├── role
│   └── status
└── accessToken
```

O `refreshToken` deverá ser tratado de acordo com a estratégia de segurança definida na SPEC-008 e não deverá ser exposto desnecessariamente ao restante da aplicação.

O frontend não deverá assumir que informações armazenadas localmente são suficientes para autorizar uma operação.

A API deverá sempre validar o Access Token e as permissões correspondentes.

---

## 5. Fluxo de autenticação

O fluxo principal será:

```text
Login / Registro
       │
       ▼
Autenticação
       │
       ▼
User.status?
       │
   ┌───┴─────────────────┐
   │                     │
PENDING_VERIFICATION   ACTIVE
   │                     │
   ▼                     ▼
Verificação de e-mail   Papel
                         │
                  ┌──────┴──────┐
                  ▼             ▼
              CANDIDATE      COMPANY
                  │             │
                  ▼             ▼
              Área do        Contexto de
              candidato      empresa
```

Contas `SUSPENDED` não deverão ter acesso à aplicação autenticada.

Contas `INACTIVE` também não deverão acessar as funcionalidades normais da plataforma.

---

## 6. Guards

O frontend utilizará guards para controlar a navegação.

### 6.1 AuthGuard

Responsável por impedir acesso a rotas que exigem autenticação.

```text
rota protegida
      │
      ▼
AuthGuard
      │
   ┌──┴──┐
   ▼     ▼
auth   não auth
   │       │
   ▼       ▼
continua  login
```

### 6.2 RoleGuard

Responsável por impedir navegação para áreas incompatíveis com o papel principal do usuário.

Exemplo:

```text
/candidate/**
    → CANDIDATE

/company/**
    → COMPANY
```

O guard melhora a experiência de navegação, mas não substitui a autorização realizada pela API.

### 6.3 CompanyMembershipGuard

Rotas relacionadas a uma empresa deverão considerar o vínculo do usuário com a empresa.

Conceitualmente:

```text
/company/{companyId}/...
          │
          ▼
CompanyMembershipGuard
          │
          ▼
verifica contexto/autorização
```

A validação definitiva continua sendo realizada no backend.

---

## 7. Contexto de empresa

Como uma conta `COMPANY` pode estar vinculada a uma ou mais empresas através de `CompanyMembership`, o frontend deverá tratar a empresa como um contexto selecionável quando houver mais de uma empresa disponível.

```text
User
 │
 └── COMPANY
       │
       ├── Membership → Company A
       ├── Membership → Company B
       └── Membership → Company C
```

Fluxo conceitual:

```text
Login
  │
  ▼
Usuário COMPANY
  │
  ▼
Possui quantas empresas?
  │
 ┌┴───────────────┐
 ▼                ▼
1                 >1
│                 │
▼                 ▼
Seleciona       Seleciona
automaticamente empresa
│                 │
└────────┬────────┘
         ▼
Contexto da Company
```

O contexto selecionado deverá ser utilizado para determinar qual empresa está sendo administrada na interface.

A troca de empresa deverá atualizar o contexto utilizado pelas telas e pelos serviços correspondentes.

A API continuará sendo responsável por validar se o usuário realmente possui autorização sobre a empresa selecionada.

---

## 8. Telas principais — Autenticação

```text
Login
    → autenticação com e-mail e senha

Registro
    → criação da conta

Verificação de e-mail
    → confirmação da conta PENDING_VERIFICATION

Recuperação de senha
    → solicitação de recuperação

Redefinição de senha
    → definição de nova senha utilizando o fluxo da SPEC-008
```

Após o login:

```text
User.status = PENDING_VERIFICATION
    → tela de verificação

User.status = ACTIVE
    → aplicação principal

User.status = SUSPENDED / INACTIVE
    → acesso bloqueado conforme regra da API
```

---

## 9. Telas principais — Candidato

### 9.1 Meu Perfil

Permite visualizar e editar:

```text
Dados básicos
Experiência
Formação
Skills
Preferências
```

Conforme definido na SPEC-002.

O frontend deverá indicar quando o perfil estiver incompleto.

### 9.2 Oportunidades Recomendadas

```text
Lista de oportunidades
        │
        ├── score
        ├── explicação
        ├── principais compatibilidades
        └── gaps
```

Integração com:

```text
SPEC-004 → Opportunity
SPEC-006 → Matching
```

### 9.3 Detalhe da Oportunidade

Exibe:

```text
Informações da oportunidade
Empresa
Requisitos
Modelo de trabalho
Benefícios
Match
```

Quando permitido, apresenta ação de candidatura.

Integração com:

```text
SPEC-004
SPEC-007
```

### 9.4 Minhas Candidaturas

Exibe:

```text
Oportunidade
Empresa
Data da candidatura
Status atual
Histórico de eventos
Match preservado
```

Conforme SPEC-007.

---

## 10. Telas principais — Empresa

As telas da empresa devem operar dentro do contexto de uma `Company`.

### 10.1 Seleção de empresa

Quando o usuário estiver vinculado a múltiplas empresas:

```text
Minhas empresas
       │
       ├── Empresa A
       ├── Empresa B
       └── Empresa C
```

O usuário seleciona a empresa que deseja administrar.

### 10.2 Perfil da Empresa

Permite visualizar e editar:

```text
Dados institucionais
Cultura
Benefícios
Localização
Modelo de trabalho
Tecnologias
Preferências gerais
Visibilidade
```

Conforme SPEC-003.

As ações disponíveis dependem do `CompanyMembership.role`.

### 10.3 Membros

Tela para gerenciamento dos usuários vinculados à empresa.

Conceitualmente:

```text
Membros
│
├── Nome / usuário
├── Papel
├── Status
└── Ações permitidas
```

Papéis:

```text
OWNER
ADMIN
RECRUITER
MEMBER
```

A interface deverá respeitar as permissões correspondentes, mas a API continuará sendo a autoridade final.

### 10.4 Minhas Oportunidades

Permite:

```text
Listar oportunidades
Criar oportunidade
Editar oportunidade
Publicar
Pausar
Encerrar
```

As operações disponíveis dependerão das permissões do `CompanyMembership`.

Conforme SPEC-004.

### 10.5 Candidatos Recomendados

Exibe candidatos compatíveis com uma oportunidade:

```text
Candidato
   │
   ├── score
   ├── pontos fortes
   └── gaps
```

Integração com SPEC-006.

O usuário somente poderá visualizar candidatos de oportunidades pertencentes às empresas para as quais possui autorização.

### 10.6 Candidaturas Recebidas

Permite:

```text
Listar candidaturas
Visualizar candidato
Visualizar matching
Atualizar status
Visualizar histórico
```

Conforme SPEC-007.

---

## 11. Fluxo de navegação — Candidato

```text
Login / Registro
       │
       ▼
Verificação de e-mail
       │
       ▼
Perfil
       │
       ▼
Oportunidades Recomendadas
       │
       ├──────────────┐
       ▼              ▼
Detalhe          Minhas Candidaturas
       │
       ▼
Candidatura
```

---

## 12. Fluxo de navegação — Empresa

```text
Login / Registro
       │
       ▼
Verificação de e-mail
       │
       ▼
Seleção / Contexto da Company
       │
       ▼
Perfil da Empresa
       │
       ├───────────────┐
       ▼               ▼
Oportunidades       Membros
       │
       ├───────────────┐
       ▼               ▼
Candidatos        Candidaturas
Recomendados      Recebidas
```

---

## 13. Estratégia de estado e comunicação com API

A comunicação seguirá:

```text
Componentes Angular
        │
        ▼
Services / Facades
        │
        ▼
HttpClient
        │
        ▼
API REST — SPEC-009
```

Cada domínio da API deverá possuir serviços correspondentes quando necessário.

Exemplos:

```text
AuthService
CandidateService
CompanyService
CompanyMemberService
OpportunityService
SkillService
MatchingService
ApplicationService
```

Os serviços serão responsáveis pela comunicação HTTP e transformação necessária dos dados para os modelos utilizados pela interface.

---

## 14. Estado global

Não há, nesta fase, necessidade obrigatória de uma biblioteca dedicada de state management como NgRx.

A complexidade inicial prevista pode ser atendida através de:

```text
Services
Signals
RxJS
Facades
```

O estado global deverá ser limitado ao que realmente precisa ser compartilhado entre diferentes áreas da aplicação.

Exemplos:

```text
AuthState
CompanyContext
Preferências globais da aplicação
```

Estados específicos de uma tela deverão permanecer preferencialmente próximos da própria feature.

---

## 15. Interceptor HTTP

O frontend deverá possuir um interceptor responsável por aplicar o Access Token às requisições autenticadas.

Conceitualmente:

```text
HttpClient
    │
    ▼
AuthInterceptor
    │
    ├── adiciona Authorization: Bearer <token>
    │
    ▼
API
```

O interceptor também poderá centralizar o tratamento de respostas relacionadas à autenticação, incluindo:

```text
401
    │
    ▼
tentativa controlada de refresh
    │
    ├── sucesso → repete requisição
    └── falha   → encerra sessão / login
```

O comportamento exato de refresh deverá respeitar a estratégia definida na SPEC-008.

Respostas `403` não devem ser tratadas automaticamente como sessão expirada. Elas representam, em princípio, falta de autorização.

---

## 16. Tratamento de erros

O frontend deverá consumir o contrato de erros definido na SPEC-009.

Exemplo:

```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Existem campos inválidos",
    "details": []
  }
}
```

O código:

```text
error.code
```

deverá ser utilizado para determinar o comportamento da interface quando necessário.

Exemplos:

```text
401
    → autenticação / renovação de sessão

403
    → acesso negado

404
    → recurso não encontrado

409
    → conflito

422
    → regra de negócio / validação
```

O frontend não deverá depender exclusivamente do texto de `message` para lógica de aplicação.

---

## 17. Paginação, filtros e ordenação

Listagens deverão utilizar os parâmetros definidos na SPEC-009:

```text
page
size
sort
```

Exemplo:

```text
/api/v1/opportunities?page=0&size=20&sort=createdAt,desc
```

O frontend deverá apresentar controles de:

```text
Paginação
Ordenação
Filtros
```

quando o recurso oferecer essas capacidades.

Filtros específicos deverão seguir o contrato definido para cada domínio.

---

## 18. Componentes compartilhados relevantes

### 18.1 MatchScoreComponent

Exibe o score de matching.

Exemplo:

```text
87%
```

O componente não deve assumir como o score foi calculado.

O cálculo pertence à SPEC-006.

### 18.2 MatchExplanationComponent

Exibe:

```text
Pontos fortes
    ✓ Java
    ✓ Spring Boot
    ✓ PostgreSQL

Gaps
    ⚠ AWS
```

Reutilizado nas visões de candidato e empresa.

### 18.3 OpportunityCardComponent

Usado em:

```text
Oportunidades recomendadas
Listagens de oportunidades
Outros contextos compatíveis
```

### 18.4 CompanyCardComponent

Usado para apresentar informações resumidas da empresa.

### 18.5 CompanySelectorComponent

Permite ao usuário `COMPANY` selecionar a empresa ativa quando estiver vinculado a múltiplas `Company`.

### 18.6 ApplicationStatusComponent

Apresenta o status atual de uma candidatura conforme os estados definidos na SPEC-007.

---

## 19. Modelos de frontend

Os modelos utilizados pelo frontend deverão representar os contratos da API, evitando acoplamento direto às entidades de persistência do backend.

Conceitualmente:

```text
API DTO
   │
   ▼
Frontend Model / View Model
   │
   ▼
Component
```

O frontend não deve assumir que o JSON retornado pela API corresponde diretamente às entidades JPA do backend.

---

## 20. Regras de negócio — nível de frontend

1. Rotas de candidato e empresa devem ser segregadas por guards de papel.
2. Usuários `PENDING_VERIFICATION` devem ser direcionados para a tela de verificação de e-mail.
3. Usuários `SUSPENDED` ou `INACTIVE` não devem acessar a aplicação autenticada normalmente.
4. Usuários `COMPANY` devem operar dentro do contexto de uma `Company`.
5. Usuários vinculados a múltiplas empresas devem poder alternar o contexto da empresa.
6. A interface deve respeitar as permissões aparentes do `CompanyMembership.role`.
7. A API continua sendo a autoridade final para autorização.
8. O frontend nunca deve considerar a ocultação de um botão ou rota como mecanismo de segurança.
9. Perfis de candidato incompletos devem exibir indicação para conclusão do cadastro antes das funcionalidades que dependam dessas informações.
10. Estados e permissões apresentados na interface devem refletir os dados retornados pela API.
11. Erros de negócio devem ser apresentados de maneira compreensível ao usuário.
12. O frontend não deve expor ou armazenar desnecessariamente informações sensíveis retornadas pela API.
13. Listagens grandes devem utilizar paginação.
14. O frontend deve tratar `401` e `403` de maneira diferente.
15. O frontend não deve implementar regras de negócio críticas exclusivamente no cliente.

---

## 21. Fora de escopo desta spec

- Design system definitivo.
- Cores, tipografia e identidade visual definitiva.
- Biblioteca específica de componentes UI.
- Aplicativo mobile nativo.
- Internacionalização (i18n).
- SSR/SSG como decisão arquitetural definitiva.
- PWA.
- Testes automatizados detalhados.
- Estratégia de CI/CD.
- Observabilidade frontend.
- Analytics e tracking.
- Acessibilidade detalhada de cada componente.

---

## 22. Perguntas em aberto

### 22.1 Landing page pública

Haverá uma landing page pública sem login para divulgar oportunidades e atrair candidatos e empresas?

**Decisão:** permanece em aberto.

### 22.2 Perfil público da empresa

Haverá modo de visualização pública do perfil institucional da empresa?

A SPEC-003 prevê que empresas poderão possuir páginas públicas.

**Decisão:** o frontend deverá suportar essa possibilidade, mas o fluxo e a estratégia de páginas públicas serão detalhados posteriormente.

### 22.3 Gerenciamento de múltiplas empresas

Um usuário `COMPANY` poderá estar vinculado a múltiplas empresas através de `CompanyMembership`.

**Decisão:** o frontend deverá suportar seleção e troca de contexto de empresa.

### 22.4 State management

Será utilizada uma biblioteca dedicada como NgRx?

**Decisão:** não é necessária nesta fase. Services, Signals, RxJS e Facades são suficientes inicialmente.

### 22.5 BFF

O frontend consumirá diretamente a API Spring Boot?

**Decisão:** sim, inicialmente, conforme a arquitetura prevista e a SPEC-009. Uma camada BFF poderá ser introduzida futuramente caso surja necessidade arquitetural.

---

## 23. Relação com as demais specs

```text
SPEC-001
    │
    └── User / roles / status
          │
          ▼
SPEC-008
    │
    └── autenticação / autorização
          │
          ▼
SPEC-009
    │
    └── API REST
          │
          ▼
SPEC-010
    │
    ├── Auth
    ├── Candidate
    ├── Company
    ├── Opportunity
    ├── Matching
    └── Application
```

### Fluxo de dependências

```text
                    User
                     │
          ┌──────────┴──────────┐
          ▼                     ▼
     Candidate              Company
          │                     │
          │              CompanyMembership
          │                     │
          │                     ▼
          │               Opportunities
          │                     │
          └──────────┬──────────┘
                     ▼
                  Matching
                     │
                     ▼
               Applications
                     │
                     ▼
                API — SPEC-009
                     │
                     ▼
             Frontend — SPEC-010
```

A SPEC-010 representa a camada de apresentação e interação do sistema. Ela consome os contratos definidos na SPEC-009 e não substitui as regras de domínio ou segurança definidas nas demais specs.

---

## 24. Estado da SPEC

Após validação da arquitetura de frontend, dos fluxos de navegação e da integração com a API, esta SPEC poderá evoluir de `Draft` para `Approved`.
