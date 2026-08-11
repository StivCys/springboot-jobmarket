# SPEC-008 — Autenticação e Autorização

**Status:** Draft
**Versão:** 0.2
**Tipo:** Infraestrutura / Segurança
**Depende de:** SPEC-000, SPEC-001, SPEC-003

---

## 1. Objetivo

Definir a estratégia conceitual de autenticação e autorização do JobMarket, com base na stack prevista na seção 17 da SPEC-000 (Spring Security).

A autenticação é responsável por verificar a identidade do usuário e estabelecer uma sessão autenticada.

A autorização é responsável por determinar quais recursos e operações o usuário pode acessar, considerando seu `User.role`, sua relação com a `Company` através de `CompanyMembership` e as regras de propriedade dos recursos.

---

## 2. Escopo

Cobre:

- fluxo de autenticação (login, registro, verificação de e-mail);
- estratégia de tokens de acesso e renovação;
- estratégia de autorização baseada em papéis;
- controle de acesso a recursos por perfil (candidato/empresa/admin);
- autorização de usuários vinculados a empresas através de `CompanyMembership`;
- recuperação de senha;
- invalidação de sessões e tokens em situações relevantes.

Não cobre:

- login social/OAuth com terceiros (fora de escopo nesta fase, ver SPEC-001);
- detalhes de infraestrutura de deploy/segredos;
- MFA;
- rate limiting e proteção avançada contra ataques de autenticação;
- permissões granulares administrativas.

---

## 3. Fluxo de autenticação

```text
Registro
   │
   ▼
Conta PENDING_VERIFICATION (SPEC-001)
   │
   ▼
Confirmação de e-mail
   │
   ▼
Conta ACTIVE
   │
   ▼
Login (email + senha)
   │
   ▼
Emissão de tokens
```

### 3.1 Registro

Durante o registro:

```text
Dados de registro
    │
    ├── email
    ├── senha
    └── role
          │
          ├── CANDIDATE
          └── COMPANY
```

A conta é inicialmente criada com:

```text
status = PENDING_VERIFICATION
```

A confirmação do e-mail altera o status para:

```text
ACTIVE
```

O fluxo de criação de `CandidateProfile` ou `Company` segue as regras dos respectivos domínios.

Para uma conta `COMPANY`, a criação de uma `Company` e o vínculo através de `CompanyMembership` seguem as regras definidas na SPEC-003.

### 3.2 Componentes técnicos previstos

```text
Spring Security
    │
    ├── Filtro de autenticação (JWT)
    ├── PasswordEncoder (hash de senha)
    ├── UserDetailsService (carrega User por email)
    └── Regras de autorização por rota/role/membership
```

---

## 4. Estratégia de token

```text
Access Token (JWT)
    ├── curta duração
    ├── usado nas requisições autenticadas
    └── carrega informações necessárias para autenticação/autorização

Refresh Token
    ├── duração maior
    ├── usado para renovar o Access Token
    └── não é utilizado como credencial de acesso às APIs de negócio
```

Fluxo:

```text
Login
   │
   ▼
Access Token + Refresh Token
   │
   ▼
Requisições à API
   │
   ▼
Access Token expira
   │
   ▼
Refresh Token
   │
   ▼
Novo Access Token
```

Quando o Refresh Token expirar ou for invalidado:

```text
Refresh Token inválido/expirado
        │
        ▼
Novo login obrigatório
```

### 4.1 Conteúdo conceitual do Access Token

O Access Token poderá carregar informações necessárias para identificar o usuário autenticado, como:

```text
userId
role
status
```

Informações de `CompanyMembership` não precisam ser necessariamente incorporadas ao JWT.

A autorização relacionada a uma `Company` deverá considerar o vínculo atual do usuário com a empresa e seu `CompanyMembership.role`, conforme definido na SPEC-003.

Isso evita que alterações de membership dependam da expiração de um Access Token para surtirem efeito.

---

## 5. Autorização

A autorização do JobMarket possui três níveis conceituais:

```text
1. Autenticação
       ↓
2. Papel da conta (User.role)
       ↓
3. Contexto e permissões sobre o recurso
```

### 5.1 User.role

Os papéis definidos na SPEC-001 são:

```text
CANDIDATE
COMPANY
ADMIN
```

`User.role` representa o tipo de conta na plataforma.

Ele não representa a função de um usuário dentro de uma empresa.

### 5.2 CompanyMembership.role

Quando o usuário atua no contexto de uma empresa, sua autorização é determinada pelo respectivo `CompanyMembership`.

```text
User
 │
 │ role = COMPANY
 ▼
CompanyMembership
 │
 ├── companyId
 └── role
       ├── OWNER
       ├── ADMIN
       ├── RECRUITER
       └── MEMBER
```

Assim:

```text
User.role
    ↓
define o contexto da conta

CompanyMembership.role
    ↓
define a função dentro da Company
```

Os papéis e suas regras detalhadas são definidos na SPEC-003.

---

## 6. Autorização por recurso

A autorização não deve depender exclusivamente do `User.role`.

Cada requisição deve considerar o contexto do recurso acessado.

### 6.1 Recursos do candidato

```text
CANDIDATE
   │
   └── CandidateProfile
```

Um candidato pode acessar e editar seu próprio perfil.

```text
User A
   │
   ▼
CandidateProfile A
```

Um candidato não pode alterar o `CandidateProfile` de outro usuário.

### 6.2 Recursos da empresa

```text
COMPANY
   │
   ▼
CompanyMembership
   │
   ▼
Company
```

O acesso aos recursos da empresa depende de o usuário possuir um `CompanyMembership` válido para aquela `Company`.

Exemplo:

```text
User A
role = COMPANY
       │
       ▼
Membership
company = ACME
role = RECRUITER
       │
       ▼
ACME
```

O usuário pode acessar os recursos da ACME somente conforme as permissões associadas ao seu membership.

Um usuário vinculado à empresa A não possui automaticamente acesso aos recursos da empresa B.

### 6.3 Recursos administrativos

```text
ADMIN
   │
   ▼
Recursos administrativos
```

Usuários com `User.role = ADMIN` podem acessar funcionalidades administrativas previstas pela plataforma.

A granularidade interna das permissões administrativas permanece fora do escopo desta versão.

---

## 7. Propriedade e autorização

A regra geral de autorização é:

> Estar autenticado não implica possuir acesso a todos os recursos.

A API deve validar:

```text
Usuário autenticado?
       │
       ▼
Possui o papel/contexto necessário?
       │
       ▼
Possui relação com o recurso?
       │
       ▼
Possui permissão para a operação?
```

Exemplos:

```text
Candidate A
   └── pode editar CandidateProfile A
       └── não pode editar CandidateProfile B
```

```text
Company User A
   └── Membership → Company X
       └── pode acessar recursos de X
           conforme seu membership
```

```text
Company User A
   └── Membership → Company X
       └── não pode acessar recursos de Company Y
```

A autorização deve ser aplicada no backend e não pode depender exclusivamente dos guards ou controles de navegação do frontend.

---

## 8. Fluxos específicos de conta

### 8.1 Conta PENDING_VERIFICATION

```text
PENDING_VERIFICATION
        │
        ├── pode confirmar e-mail
        │
        └── não pode realizar autenticação normal
```

Após confirmação:

```text
PENDING_VERIFICATION
        │
        ▼
      ACTIVE
```

### 8.2 Conta ACTIVE

Pode realizar login e utilizar os recursos disponíveis para seu papel e seus vínculos.

### 8.3 Conta INACTIVE

Uma conta desativada pelo usuário não pode realizar autenticação normal.

A reativação segue o fluxo definido pela aplicação.

### 8.4 Conta SUSPENDED

Uma conta suspensa pela plataforma não pode realizar autenticação normal, mesmo com credenciais corretas.

Além disso:

```text
SUSPENDED
   ├── não participa de matching
   └── não aparece normalmente em buscas
```

---

## 9. Logout e invalidação de sessão

O logout deve invalidar a sessão de autenticação do usuário.

Como o Access Token é stateless e possui duração curta, sua invalidação imediata pode ser tratada através da invalidação/rotação do mecanismo de refresh.

O Refresh Token deve ser invalidável pelo servidor.

Situações que podem exigir invalidação dos tokens/sessões incluem:

```text
- logout;
- alteração de senha;
- recuperação de senha concluída;
- suspensão da conta;
- comprometimento da sessão;
- outras situações de segurança definidas pela implementação.
```

A estratégia exata de armazenamento, rotação e revogação dos Refresh Tokens será definida durante a implementação.

---

## 10. Recuperação de senha

```text
Solicita recuperação
        │
        ▼
Informa e-mail
        │
        ▼
Token de recuperação
        │
        ├── curta duração
        └── uso único
        │
        ▼
Define nova senha
        │
        ▼
Invalida sessões/tokens anteriores
```

O sistema não deve revelar, durante a solicitação, se um determinado e-mail possui ou não uma conta válida.

O token de recuperação deve:

- possuir prazo de expiração curto;
- ser de uso único;
- ser invalidado após utilização;
- não expor a senha existente.

---

## 11. Verificação de e-mail

O fluxo de verificação de e-mail é:

```text
Registro
   │
   ▼
PENDING_VERIFICATION
   │
   ▼
Envio de token/link de verificação
   │
   ▼
Confirmação
   │
   ▼
ACTIVE
```

A verificação de e-mail é obrigatória antes da participação em matching.

O mecanismo de geração, armazenamento, expiração e consumo dos tokens de verificação será definido durante a implementação.

---

## 12. Regras de negócio

1. Contas `PENDING_VERIFICATION` não podem autenticar-se normalmente até confirmar o e-mail.
2. Contas `SUSPENDED` têm login bloqueado, mesmo com credenciais corretas.
3. Contas `INACTIVE` não podem autenticar-se normalmente enquanto estiverem desativadas.
4. Toda rota protegida deve validar autenticação.
5. Toda rota que expõe dados de candidato ou empresa deve validar autorização além da autenticação.
6. Um candidato só pode alterar seu próprio `CandidateProfile`.
7. Um usuário `COMPANY` só pode acessar recursos de uma `Company` quando possuir um `CompanyMembership` válido para aquela empresa.
8. O acesso de um usuário `COMPANY` aos recursos da empresa deve respeitar o `CompanyMembership.role`.
9. O `User.role` não substitui o `CompanyMembership.role` para autorização dentro de uma empresa.
10. Um usuário vinculado a uma `Company` não recebe automaticamente acesso a outras empresas às quais não esteja vinculado.
11. Usuários `ADMIN` podem acessar recursos administrativos conforme as permissões administrativas disponíveis nesta fase.
12. Toda autorização deve ser aplicada no backend; controles de frontend não são considerados mecanismo de segurança.
13. Tokens de recuperação de senha são de uso único e expiram em prazo curto.
14. A conclusão da recuperação de senha deve invalidar sessões/tokens anteriores.
15. A verificação de e-mail é obrigatória antes da participação em matching.
16. Usuários `SUSPENDED` não devem participar de matching nem aparecer normalmente em buscas.

---

## 13. Fora de escopo desta spec

- Autenticação multifator (MFA).
- Login social (Google/LinkedIn) — evolução futura.
- Auditoria detalhada de tentativas de login.
- Rate limiting e bloqueio avançado por tentativas.
- Permissões administrativas granulares.
- Single Sign-On (SSO).
- Gestão de identidade corporativa externa.
- Detalhes de infraestrutura de deploy e armazenamento de segredos.

---

## 14. Perguntas em aberto

### 14.1 Expiração dos tokens

Qual será o tempo de expiração padrão do:

```text
Access Token
Refresh Token
```

A definição exata fica para a implementação/configuração de segurança.

### 14.2 Rotação de Refresh Token

Será utilizada rotação de Refresh Token a cada renovação?

**Decisão:** deixar para a implementação de segurança.

### 14.3 Sessões simultâneas

Um usuário poderá manter sessões ativas em múltiplos dispositivos?

**Decisão:** múltiplas sessões são permitidas inicialmente, salvo necessidade futura de política de sessão única.

### 14.4 Granularidade administrativa

O papel `ADMIN` terá granularidade adicional, por exemplo:

```text
ADMIN_CONTENT
ADMIN_SUPPORT
ADMIN_MODERATION
```

**Decisão:** não definir nesta fase. A granularidade administrativa poderá ser introduzida posteriormente.

### 14.5 Permissões dentro da empresa

Os papéis:

```text
OWNER
ADMIN
RECRUITER
MEMBER
```

serão suficientes inicialmente ou precisarão de permissões granulares?

**Decisão:** os papéis definidos na SPEC-003 serão utilizados inicialmente. Permissões granulares permanecem fora de escopo.

---

## 15. Relação com outras specs

```text
SPEC-001
   │
   ├── User.role
   ├── User.status
   └── ciclo de vida da conta
        │
        ▼
SPEC-008
   │
   ├── autenticação
   ├── tokens
   └── autorização
        │
        ├───────────────┐
        ▼               ▼
SPEC-002           SPEC-003
Candidate          Company
Profile            + CompanyMembership
                        │
                        ▼
                 Membership.role
```

A autorização da API deve respeitar as definições desta SPEC e os relacionamentos de domínio definidos nas respectivas specs.

---

## 16. Decisões

- A autenticação será baseada em email e senha.
- O backend utilizará Spring Security.
- A autenticação da API utilizará Access Tokens JWT.
- Refresh Tokens serão utilizados para renovação da sessão.
- `User.role` representa o tipo de conta na plataforma.
- `CompanyMembership.role` representa a função do usuário dentro de uma empresa.
- A autorização de recursos de `Company` será baseada no vínculo do usuário através de `CompanyMembership`.
- O frontend poderá utilizar guards para controle de navegação, mas a autorização efetiva será sempre realizada no backend.
- A verificação de e-mail será obrigatória antes da participação em matching.
- Contas `SUSPENDED` e `INACTIVE` não podem realizar autenticação normal.
- Tokens de recuperação de senha serão de uso único e terão curta duração.
- A recuperação de senha invalidará sessões/tokens anteriores.
- Usuários `ADMIN` terão uma interface administrativa própria, a ser implementada em fase posterior.
