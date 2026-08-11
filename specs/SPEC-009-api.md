# SPEC-009 — API

**Status:** Draft
**Versão:** 0.2
**Tipo:** Infraestrutura / Contrato de Comunicação
**Depende de:** SPEC-000 a SPEC-008

---

## 1. Objetivo

Definir as convenções gerais da API REST que servirá de contrato entre o frontend Angular (SPEC-010) e o backend Spring Boot, cobrindo os domínios definidos nas specs anteriores.

Esta spec estabelece as convenções arquiteturais e de comunicação da API, enquanto os detalhes específicos de cada domínio permanecem definidos nas respectivas specs.

---

## 2. Escopo

Cobre:

- convenções de rotas e versionamento;
- formato das requisições e respostas;
- autenticação e autorização da API;
- padrão de resposta e de erro;
- métodos HTTP e semântica geral das operações;
- paginação, filtros e ordenação;
- mapeamento conceitual de recursos por domínio;
- convenções gerais de nomenclatura.

Não cobre:

- definição exaustiva de todos os endpoints;
- implementação interna dos serviços;
- contratos de eventos assíncronos/websocket;
- documentação interativa completa da API;
- detalhes de infraestrutura de deploy;
- rate limiting e throttling.

---

## 3. Convenções gerais

```text
Base:         /api/v1
Formato:      JSON
Autenticação: Bearer Token (JWT) — SPEC-008
Nomenclatura: recursos em plural
JSON:         camelCase
```

### 3.1 Versionamento

A API será versionada através do prefixo da URL:

```text
/api/v1/...
```

Mudanças incompatíveis com o contrato existente deverão gerar uma nova versão:

```text
/api/v2/...
```

A versão anterior poderá permanecer disponível durante um período de transição definido pela estratégia de evolução da API.

Alterações compatíveis, como inclusão de novos campos ou novos endpoints, não exigem necessariamente uma nova versão.

---

## 4. Autenticação

Rotas protegidas utilizarão:

```http
Authorization: Bearer <access-token>
```

O mecanismo de autenticação e emissão dos tokens é definido na SPEC-008.

### 4.1 Rotas públicas e protegidas

Cada recurso deverá definir quais operações podem ser realizadas sem autenticação.

Conceitualmente:

```text
Rota pública
    │
    └── não exige Access Token

Rota protegida
    │
    └── exige Access Token válido
             │
             ▼
        autorização
```

A existência de um guard no frontend não substitui a validação de autenticação e autorização no backend.

---

## 5. Autorização

A API deve aplicar as regras de autorização definidas na SPEC-008.

A autorização não deve considerar apenas o `User.role`.

### 5.1 Candidato

Para recursos pertencentes ao candidato:

```text
User
 │
 └── role = CANDIDATE
          │
          ▼
   CandidateProfile
```

O usuário pode acessar ou alterar somente os recursos aos quais possui autorização, especialmente seu próprio `CandidateProfile`.

### 5.2 Empresa

Para recursos pertencentes a uma empresa:

```text
User
 │
 └── role = COMPANY
          │
          ▼
CompanyMembership
          │
          ├── companyId
          └── role
                 │
                 ▼
              Company
```

O acesso deverá validar:

1. que o usuário está autenticado;
2. que possui `User.role = COMPANY`;
3. que possui um `CompanyMembership` válido para a empresa;
4. que o `CompanyMembership.role` permite a operação solicitada.

Um usuário vinculado à `Company A` não possui automaticamente acesso à `Company B`.

### 5.3 Administração

Usuários com:

```text
User.role = ADMIN
```

podem acessar recursos administrativos conforme as regras da plataforma.

A granularidade das permissões administrativas permanece fora do escopo desta versão.

---

## 6. Métodos HTTP

A API seguirá a semântica HTTP convencional.

```text
GET
    → consulta recursos

POST
    → cria recursos ou executa operações que não representam atualização simples

PUT
    → substitui ou atualiza integralmente um recurso quando aplicável

PATCH
    → atualiza parcialmente um recurso

DELETE
    → remove ou desativa um recurso quando aplicável
```

A escolha entre `PUT`, `PATCH` e operações específicas deverá respeitar o comportamento de cada domínio.

Nem toda mudança de estado de negócio precisa ser representada como `PATCH` genérico.

Quando uma operação possuir semântica própria, poderá ser utilizado um endpoint de ação, desde que isso torne o contrato mais claro.

Exemplos conceituais:

```text
POST /api/v1/applications/{id}/withdraw
POST /api/v1/opportunities/{id}/publish
POST /api/v1/opportunities/{id}/pause
```

A definição final das operações de cada recurso permanece nas respectivas specs de domínio e na implementação.

---

## 7. Recursos por domínio

O mapeamento conceitual dos principais recursos é:

```text
/api/v1/auth
    → autenticação e gerenciamento de sessão
    → SPEC-008

/api/v1/users
    → operações relacionadas à conta do usuário
    → SPEC-001

/api/v1/candidates
    → perfis de candidatos
    → SPEC-002

/api/v1/companies
    → empresas e seus perfis institucionais
    → SPEC-003

/api/v1/opportunities
    → oportunidades
    → SPEC-004

/api/v1/skills
    → taxonomia de skills
    → SPEC-005

/api/v1/matching
    → recomendações e matching
    → SPEC-006

/api/v1/applications
    → candidaturas
    → SPEC-007
```

O mapeamento acima é conceitual.

A existência de uma entidade de domínio não implica automaticamente a existência de um endpoint CRUD público correspondente.

---

## 8. Autenticação e conta

Os principais recursos de autenticação serão agrupados em:

```text
/api/v1/auth
```

Operações conceituais:

```text
POST /api/v1/auth/register
POST /api/v1/auth/login
POST /api/v1/auth/refresh
POST /api/v1/auth/logout
POST /api/v1/auth/forgot-password
POST /api/v1/auth/reset-password
POST /api/v1/auth/verify-email
```

Os detalhes de payload, tokens e regras de segurança são definidos na SPEC-008.

O recurso `User` não deve ser tratado como um CRUD público genérico.

Por exemplo, não se presume a existência de:

```text
GET /api/v1/users
GET /api/v1/users/{id}
```

simplesmente porque `User` existe no modelo de domínio.

Operações relacionadas à conta devem expor somente os dados e comportamentos necessários ao usuário ou às funcionalidades autorizadas.

---

## 9. Empresas e CompanyMembership

A empresa é uma entidade independente de um usuário.

```text
User
  │
  │ CompanyMembership
  ▼
Company
```

As operações de empresa devem respeitar o contexto da organização e as permissões do membership.

Exemplos conceituais:

```text
GET   /api/v1/companies/{companyId}
PATCH /api/v1/companies/{companyId}
```

Para operações administrativas sobre membros, a API poderá utilizar recursos aninhados:

```text
GET    /api/v1/companies/{companyId}/members
POST   /api/v1/companies/{companyId}/members
PATCH  /api/v1/companies/{companyId}/members/{memberId}
DELETE /api/v1/companies/{companyId}/members/{memberId}
```

Esses endpoints são conceituais e não significam que todas as operações serão necessariamente implementadas nesta fase.

A autorização deverá considerar o `CompanyMembership.role`.

Exemplo:

```text
CompanyMembership
    │
    ├── OWNER
    ├── ADMIN
    ├── RECRUITER
    └── MEMBER
```

As permissões específicas de cada papel são definidas na SPEC-003 e na SPEC-008.

---

## 10. Oportunidades

As oportunidades pertencem a uma `Company`.

```text
Company
   │
   └── Opportunity
```

O acesso e gerenciamento de oportunidades por usuários `COMPANY` devem respeitar o `CompanyMembership` correspondente.

Exemplos conceituais:

```text
GET  /api/v1/opportunities
GET  /api/v1/opportunities/{opportunityId}

POST /api/v1/opportunities

PATCH /api/v1/opportunities/{opportunityId}
```

O backend deve verificar se o usuário possui autorização para a `Company` responsável pela oportunidade antes de permitir operações privadas.

Operações de mudança de estado poderão utilizar endpoints específicos quando isso tornar o contrato mais claro:

```text
POST /api/v1/opportunities/{id}/publish
POST /api/v1/opportunities/{id}/pause
POST /api/v1/opportunities/{id}/close
```

Os estados e regras do ciclo de vida são definidos na SPEC-004.

---

## 11. Candidaturas

As candidaturas representam a relação entre um `CandidateProfile` e uma `Opportunity`.

```text
CandidateProfile
        │
        ▼
   Application
        ▲
        │
   Opportunity
```

O recurso principal será:

```text
/api/v1/applications
```

Exemplos conceituais:

```text
POST /api/v1/applications
GET  /api/v1/applications/{applicationId}
```

As listagens poderão ser contextualizadas de acordo com o usuário autenticado:

```text
GET /api/v1/applications
```

Para um candidato, a API retorna suas candidaturas.

Para um usuário de empresa, a API poderá retornar candidaturas recebidas pelas empresas às quais possui autorização.

A implementação deverá impedir que um usuário consulte candidaturas pertencentes a outro candidato ou a empresas às quais não possui acesso.

As regras de ciclo de vida e histórico da candidatura são definidas na SPEC-007.

---

## 12. Matching

O matching possui dois contextos principais:

```text
Candidato
    │
    ▼
Recomendações de oportunidades

Empresa
    │
    ▼
Candidatos compatíveis com uma oportunidade
```

Endpoints conceituais:

```text
GET /api/v1/matching/opportunities
    → oportunidades recomendadas para o candidato autenticado

GET /api/v1/matching/candidates/{opportunityId}
    → candidatos recomendados para uma oportunidade
```

O segundo endpoint exige:

```text
usuário autenticado
        +
User.role = COMPANY
        +
CompanyMembership válido
        +
autorização sobre a Opportunity
```

O resultado poderá conter:

```json
{
  "data": [
    {
      "opportunityId": "...",
      "score": 87,
      "explanation": {
        "strengths": ["Java", "Spring Boot", "PostgreSQL"],
        "gaps": ["AWS"]
      }
    }
  ]
}
```

O formato exato do resultado e o algoritmo de matching são definidos na SPEC-006.

---

## 13. Padrão de resposta

### 13.1 Sucesso

As respostas de sucesso utilizarão, quando aplicável, o envelope:

```json
{
  "data": { ... },
  "meta": { ... }
}
```

Para respostas que não necessitam de metadados:

```json
{
  "data": { ... }
}
```

Listagens continuarão utilizando `data` como coleção:

```json
{
  "data": [
    { ... },
    { ... }
  ],
  "meta": {
    "page": 0,
    "size": 20,
    "totalElements": 134,
    "totalPages": 7
  }
}
```

O envelope tem como objetivo manter uma estrutura previsível para o frontend e permitir evolução do contrato sem alterar a estrutura principal da resposta.

---

## 14. Padrão de erro

Erros serão retornados utilizando o seguinte formato:

```json
{
  "error": {
    "code": "RESOURCE_NOT_FOUND",
    "message": "Oportunidade não encontrada",
    "details": []
  }
}
```

### 14.1 `code`

`code` representa um identificador estável do erro e deve ser utilizado pelo frontend para tratamento programático.

Exemplo:

```text
RESOURCE_NOT_FOUND
INVALID_CREDENTIALS
EMAIL_NOT_VERIFIED
FORBIDDEN
VALIDATION_ERROR
APPLICATION_ALREADY_EXISTS
```

O frontend não deve depender do texto de `message` para determinar o comportamento da aplicação.

### 14.2 `message`

`message` fornece uma descrição compreensível do erro.

As mensagens de validação de negócio devem ser suficientemente claras para que o frontend possa apresentá-las ao usuário sem precisar inferir o significado a partir do código.

### 14.3 `details`

`details` poderá conter informações adicionais, especialmente em erros de validação.

Exemplo:

```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Existem campos inválidos",
    "details": [
      {
        "field": "email",
        "code": "INVALID_FORMAT",
        "message": "E-mail inválido"
      }
    ]
  }
}
```

---

## 15. Status HTTP

A API utilizará os principais códigos HTTP de acordo com a natureza da resposta.

```text
200 → operação realizada com sucesso
201 → recurso criado
204 → operação realizada sem conteúdo de resposta

400 → requisição inválida
401 → não autenticado
403 → autenticado, mas sem permissão
404 → recurso não encontrado
409 → conflito
422 → validação ou regra de negócio não satisfeita

500 → erro interno
```

### 15.1 `401 Unauthorized`

Utilizado quando a requisição exige autenticação e:

```text
- não possui Access Token;
- possui token inválido;
- possui token expirado;
- não possui sessão válida.
```

### 15.2 `403 Forbidden`

Utilizado quando o usuário está autenticado, mas não possui autorização para executar a operação.

Exemplo:

```text
User
  │
  └── COMPANY
        │
        └── Membership → Company A
                         │
                         └── tenta alterar Company B
```

Resultado:

```text
403 FORBIDDEN
```

### 15.3 `404 Not Found`

Utilizado quando o recurso solicitado não existe ou, conforme a estratégia de segurança adotada, quando não deve ser revelada sua existência ao usuário.

### 15.4 `409 Conflict`

Utilizado quando a operação entra em conflito com o estado atual do recurso.

Exemplos:

```text
- candidatura duplicada;
- operação incompatível com o estado atual;
- conflito de dados únicos.
```

### 15.5 `422 Unprocessable Entity`

Utilizado quando a requisição possui estrutura válida, mas viola uma regra de negócio ou validação específica do domínio.

---

## 16. Paginação

Listagens potencialmente grandes deverão ser paginadas.

Parâmetros padrão:

```text
page
size
sort
```

Exemplo:

```text
GET /api/v1/opportunities?page=0&size=20&sort=createdAt,desc
```

Resposta:

```json
{
  "data": [
    { ... }
  ],
  "meta": {
    "page": 0,
    "size": 20,
    "totalElements": 134,
    "totalPages": 7
  }
}
```

### 16.1 Limite de página

A API deverá estabelecer um tamanho máximo para `size`.

Exemplo conceitual:

```text
size <= limite configurado
```

O valor exato do limite será definido na implementação.

Solicitações acima do limite poderão ser rejeitadas ou limitadas ao máximo permitido, conforme a política adotada.

### 16.2 Coleções não paginadas

Coleções pequenas e controladas, como determinadas listas de enumerações ou configurações, poderão ser retornadas sem paginação.

Listagens potencialmente grandes, como:

```text
opportunities
candidates
applications
skills
```

deverão ser paginadas.

---

## 17. Ordenação

A ordenação será feita através do parâmetro:

```text
sort
```

Exemplo:

```text
?sort=createdAt,desc
```

Múltiplos critérios poderão ser utilizados:

```text
?sort=score,desc&sort=createdAt,desc
```

Os campos disponíveis para ordenação serão definidos por cada recurso.

A API não deve permitir ordenação arbitrária por campos internos que não façam parte do contrato público do recurso.

---

## 18. Filtros

Filtros simples serão preferencialmente representados por query parameters.

Exemplo:

```text
GET /api/v1/opportunities
    ?modalidade=REMOTO
    &senioridade=SENIOR
```

Filtros poderão ser combinados:

```text
GET /api/v1/opportunities
    ?modalidade=REMOTO
    &tipoContratacao=PJ
    &senioridade=SENIOR
    &page=0
    &size=20
```

Os filtros específicos de cada domínio serão definidos nas respectivas specs.

### 18.1 Busca

A busca textual simples poderá utilizar query parameters.

Exemplo conceitual:

```text
GET /api/v1/opportunities?search=java
```

Um endpoint `/search` dedicado somente deverá ser criado quando houver necessidade de uma semântica de busca diferente da filtragem convencional.

---

## 19. Validação de entrada

A API deverá validar:

```text
- campos obrigatórios;
- formatos;
- limites;
- enums;
- relacionamentos;
- regras de negócio.
```

A validação ocorrerá no backend mesmo que o frontend já realize validações equivalentes.

Exemplo:

```text
Frontend
   │
   ├── valida formulário
   │
   ▼
API
   │
   └── valida novamente
```

Nenhuma validação realizada exclusivamente pelo frontend deve ser considerada mecanismo de segurança.

---

## 20. Idempotência e operações repetidas

Operações que possam ser repetidas acidentalmente deverão considerar o comportamento idempotente ou a detecção de duplicidade quando aplicável.

Exemplo:

```text
POST /api/v1/applications
```

Uma tentativa de criar uma candidatura já existente para a mesma combinação:

```text
CandidateProfile + Opportunity
```

deve ser rejeitada conforme as regras da SPEC-007, normalmente utilizando:

```text
409 CONFLICT
```

A implementação poderá adotar mecanismos adicionais de idempotência para operações sensíveis ou sujeitas a repetição por rede.

---

## 21. Recursos públicos e privados

A existência de um recurso no modelo de domínio não determina que ele seja público.

Exemplo:

```text
Company
   │
   ├── perfil publicado → pode possuir acesso público
   │
   └── dados administrativos → protegidos
```

Da mesma forma:

```text
Opportunity
   │
   ├── oportunidade publicada → pode ser consultável conforme regras do domínio
   │
   └── DRAFT → acesso restrito aos usuários autorizados da Company
```

A visibilidade específica de cada recurso é definida pela respectiva spec de domínio.

---

## 22. Regras de negócio gerais da API

1. Toda rota protegida deve validar um Access Token válido.
2. Toda operação protegida deve validar autorização no backend.
3. `User.role` define o contexto geral da conta, conforme SPEC-001.
4. `CompanyMembership.role` deve ser utilizado para autorização dentro de uma `Company`.
5. Um usuário `COMPANY` não possui acesso automático a todas as empresas.
6. Um candidato só pode alterar seus próprios recursos privados.
7. Recursos de empresa devem validar o `CompanyMembership` antes de permitir operações privadas.
8. Listagens potencialmente grandes devem ser paginadas.
9. O frontend não deve ser considerado mecanismo de segurança.
10. O frontend deve utilizar `error.code` para tratamento programático de erros, e não depender do texto de `message`.
11. Erros de regra de negócio devem retornar códigos HTTP coerentes com a natureza da falha.
12. Recursos internos do modelo de domínio não precisam necessariamente possuir endpoints públicos.
13. Filtros e ordenação devem utilizar somente campos definidos no contrato público de cada recurso.
14. A API deve preservar compatibilidade dentro da mesma versão.
15. Mudanças incompatíveis devem gerar nova versão da API.
16. A API deve evitar expor informações internas desnecessárias do modelo de domínio.
17. Dados de autenticação, como senha e credenciais internas, nunca devem ser retornados pelas APIs.
18. Tokens e informações sensíveis devem seguir as regras definidas na SPEC-008.

---

## 23. Fora de escopo desta spec

- Documentação interativa completa (Swagger/OpenAPI) — recomendada, mas detalhamento fica para a implementação.
- Rate limiting e throttling.
- Webhooks para integrações externas.
- Eventos assíncronos.
- WebSockets.
- GraphQL.
- BFF ou gateway específico.
- Estratégias de cache distribuído.
- Monitoramento e observabilidade da API.
- Detalhes de infraestrutura de deploy.

---

## 24. Perguntas em aberto

### 24.1 Gateway/BFF

Haverá um gateway/BFF entre Angular e os serviços, ou o frontend consumirá a API Spring Boot diretamente?

A arquitetura prevista na SPEC-000 sugere consumo direto nesta fase.

**Decisão:** manter consumo direto da API Spring Boot inicialmente.

### 24.2 Estratégia de busca

Os filtros de busca de oportunidades e candidatos serão padronizados via query params ou haverá endpoints dedicados de busca?

**Decisão:** utilizar query params inicialmente. Endpoints `/search` poderão ser introduzidos quando houver necessidade de uma semântica de busca mais complexa.

### 24.3 Documentação OpenAPI

A API será documentada através de OpenAPI/Swagger?

**Decisão:** recomendado, mas a definição da ferramenta e do processo de geração da documentação fica para a implementação.

### 24.4 Formato de erro

O formato:

```json
{
  "error": {
    "code": "...",
    "message": "...",
    "details": []
  }
}
```

será utilizado como contrato padrão para todas as APIs?

**Decisão:** sim.

### 24.5 Campos de resposta

A API deverá retornar somente os campos necessários para cada contexto, evitando expor diretamente entidades persistidas.

**Decisão:** utilizar DTOs/representações de API em vez de expor diretamente as entidades de persistência.

---

## 25. Relação com as demais specs

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
    ├── contrato HTTP
    ├── respostas
    ├── erros
    ├── paginação
    └── convenções da API
          │
    ┌─────┼─────────┬──────────┬──────────┐
    ▼     ▼         ▼          ▼          ▼
SPEC-002 SPEC-003 SPEC-004  SPEC-006   SPEC-007
Candidate Company Opportunity Matching Application
                  │
                  └── Company
                      + CompanyMembership
```

A SPEC-009 estabelece as convenções comuns de comunicação. Os contratos específicos de cada domínio continuam sendo definidos pelas respectivas specs.

---

## 26. Estado da SPEC

Após validação das convenções de API e alinhamento com as specs de domínio, esta SPEC poderá evoluir de `Draft` para `Approved`.
