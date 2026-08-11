# SPEC-001 — Modelo de Usuários

**Status:** Draft
**Versão:** 0.2
**Tipo:** Modelo de Domínio
**Depende de:** SPEC-000

---

## 1. Objetivo

Definir o conceito de **usuário** dentro do JobMarket, servindo como base comum para os dois grandes perfis previstos na SPEC-000: **Candidato** e **Empresa**.

O `User` representa a conta de acesso à plataforma. `Candidate` representa o perfil de negócio associado diretamente à conta, enquanto `Company` representa uma entidade de negócio independente de uma conta de usuário, à qual usuários com papel `COMPANY` podem estar vinculados através de `CompanyMembership` (SPEC-003).

```text
User
 │
 ├── é um Candidato  → CandidateProfile (SPEC-002)
 │
 └── atua como Empresa → CompanyMembership → Company (SPEC-003)
```

---

## 2. Escopo

Esta spec cobre:

- modelo conceitual de `User`;
- papéis (roles) de usuário;
- ciclo de vida da conta;
- relação entre `User`, `CandidateProfile` e `Company`;
- distinção entre o papel da conta (`User.role`) e o papel do usuário dentro de uma empresa (`CompanyMembership.role`).

Não cobre:

- autenticação/autorização (SPEC-008);
- estrutura detalhada do perfil profissional (SPEC-002) ou da empresa (SPEC-003);
- estrutura e regras de `CompanyMembership` (SPEC-003).

---

## 3. Modelo conceitual

### 3.1 User

```text
User
├── id
├── email (único)
├── senha (hash)
├── papel (role)
├── status
├── dataCriacao
├── dataAtualizacao
└── ultimoLogin
```

### 3.2 Papéis (roles)

```text
CANDIDATE   → usuário que busca oportunidades
COMPANY     → usuário que atua em uma ou mais empresas
ADMIN       → uso interno / operação da plataforma
```

Um `User` possui **um único papel principal**.

O campo `User.role` representa o **tipo de conta dentro da plataforma**. Ele não representa a função ou as permissões do usuário dentro de uma empresa.

Para usuários com `role = COMPANY`, a função exercida dentro de cada empresa é definida pelo respectivo `CompanyMembership.role`, conforme definido na SPEC-003.

Não há, nesta fase, contas híbridas entre `CANDIDATE` e `COMPANY`.

### 3.3 Status da conta

```text
PENDING_VERIFICATION → conta criada, aguardando confirmação de e-mail
ACTIVE               → conta ativa e utilizável
INACTIVE             → desativada pelo próprio usuário
SUSPENDED            → suspensa pela plataforma
```

Diagrama de transição:

```text
PENDING_VERIFICATION ──confirma e-mail──▶ ACTIVE
ACTIVE ──desativa──▶ INACTIVE ──reativa──▶ ACTIVE
ACTIVE ──violação──▶ SUSPENDED
```

---

## 4. Relação com Candidate e Company

### 4.1 Candidate

Um `User` com papel `CANDIDATE` possui um `CandidateProfile` associado à sua conta.

```text
User (1) ──── (0..1) CandidateProfile
```

- Um `User` com papel `CANDIDATE` deve possuir exatamente um `CandidateProfile`.
- A criação da conta pode ocorrer antes da conclusão do perfil.
- O perfil pode permanecer incompleto enquanto a conta estiver ativa.

### 4.2 Company

Uma `Company` é uma entidade de negócio independente de um `User`.

Usuários com papel `COMPANY` são vinculados às empresas através de `CompanyMembership`, conforme definido na SPEC-003.

```text
User (1) ──── (0..N) CompanyMembership (N..1) ──── Company (1)
```

Conceitualmente:

```text
                    User
                     │
          ┌──────────┴──────────┐
          │                     │
          ▼                     ▼
CandidateProfile       CompanyMembership
                               │
                               ▼
                            Company
```

Um usuário com `role = COMPANY` pode estar vinculado a uma ou mais empresas, desde que possua um `CompanyMembership` válido para cada organização.

Uma empresa pode possuir múltiplos usuários vinculados a ela.

Os papéis exercidos pelos usuários dentro de uma empresa são definidos por `CompanyMembership.role`:

```text
CompanyMembership.role
├── OWNER
├── ADMIN
├── RECRUITER
└── MEMBER
```

Esses papéis são diferentes de `User.role`.

```text
User.role
└── COMPANY
       ↓
CompanyMembership.role
├── OWNER
├── ADMIN
├── RECRUITER
└── MEMBER
```

A estrutura, os estados e as regras de `CompanyMembership` são definidos na SPEC-003.

---

## 5. Regras de negócio

1. O e-mail é único em toda a plataforma, independentemente do papel.
2. A senha nunca é armazenada em texto puro (detalhes de hashing ficam a cargo da SPEC-008).
3. Um usuário `SUSPENDED` não deve aparecer em buscas nem participar de matching.
4. A troca de papel de um usuário (de `CANDIDATE` para `COMPANY`, por exemplo) não é suportada nesta fase — exigiria uma nova conta.
5. Um `User` com papel `CANDIDATE` deve possuir exatamente um `CandidateProfile`.
6. Um `User` com papel `COMPANY` pode estar vinculado a uma ou mais `Company` através de `CompanyMembership`.
7. Uma `Company` pode possuir múltiplos usuários vinculados através de `CompanyMembership`.
8. As permissões e a função de um usuário dentro de uma `Company` são determinadas pelo `CompanyMembership.role`, e não pelo `User.role`.
9. Um perfil de candidato pode estar incompleto mesmo quando a conta do usuário estiver `ACTIVE`.

---

## 6. Fora de escopo desta spec

- Login social (Google, LinkedIn etc.) — decisão futura.
- Recuperação de senha, tokens de sessão — SPEC-008.
- Estrutura e regras de `CompanyMembership` — SPEC-003.
- Permissões granulares dentro da empresa — SPEC-003.
- Gestão de oportunidades publicadas pela empresa — SPEC-004.

---

## 7. Decisões

- Usuários com `role = COMPANY` poderão estar vinculados a múltiplas empresas através de `CompanyMembership`.
- Uma `Company` poderá possuir múltiplos usuários vinculados através de `CompanyMembership`.
- O primeiro usuário responsável pela criação de uma `Company` receberá inicialmente o papel `OWNER`, conforme definido na SPEC-003.
- A verificação de e-mail será obrigatória antes da participação em matching.
- Usuários ADMIN terão uma interface administrativa própria, a ser implementada em uma fase posterior do projeto.
- `User.role` representa o tipo de conta na plataforma, enquanto `CompanyMembership.role` representa a função do usuário dentro de uma empresa.
