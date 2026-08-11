# SPEC-003 — Empresas

**Status:** Draft
**Versão:** 0.2
**Tipo:** Modelo de Domínio
**Depende de:** SPEC-000, SPEC-001

---

## 1. Objetivo

Definir o conceito de **Empresa (`Company`)** dentro do JobMarket.

A empresa é uma entidade de negócio independente de uma conta de usuário. Ela representa a organização responsável pelas oportunidades publicadas na plataforma e possui um perfil institucional próprio, permitindo que candidatos avaliem a empresa além das oportunidades individuais.

Uma empresa pode possuir múltiplos usuários vinculados a ela.

```text
User
 │
 │ CompanyMembership
 ▼
Company
```

---

## 2. Escopo

Esta spec cobre:

- estrutura conceitual da `Company`;
- perfil institucional;
- relacionamento entre `Company` e usuários;
- papéis dos usuários dentro da empresa;
- preferências gerais de contratação;
- visibilidade institucional.

Não cobre:

- oportunidades específicas publicadas pela empresa (SPEC-004);
- autenticação e autorização da conta (SPEC-008);
- estrutura detalhada do perfil de candidato (SPEC-002);
- avaliações da empresa;
- gestão de filiais como entidades independentes.

---

## 3. Modelo conceitual

### 3.1 Company

```text
Company
│
├── Informações institucionais
│     ├── id
│     ├── razaoSocial
│     ├── nomeFantasia
│     ├── cnpj
│     ├── descricao
│     ├── logo
│     └── site
│
├── Segmento
│     ├── setor
│     └── porte
│
├── Cultura
│     ├── valores[]
│     └── descricaoCultura
│
├── Localização
│     ├── pais
│     ├── estado
│     └── cidade
│
├── Modelo de trabalho predominante
│     └── REMOTO | HIBRIDO | PRESENCIAL
│
├── Tecnologias utilizadas[]
│
├── Benefícios[]
│
├── Preferências gerais de contratação
│
├── status
├── createdAt
└── updatedAt
```

---

## 4. Relação entre User e Company

Uma empresa pode possuir múltiplos usuários.

```text
User (1) ──── (0..N) CompanyMembership (0..N) ──── (1) Company
```

Conceitualmente:

```text
                 Company
                    │
          ┌─────────┼─────────┐
          │         │         │
          ▼         ▼         ▼
        User A   User B    User C
        OWNER    ADMIN    RECRUITER
```

### 4.1 CompanyMembership

```text
CompanyMembership
├── id
├── userId
├── companyId
├── role
├── status
├── createdAt
└── updatedAt
```

### 4.2 Papéis dentro da empresa

Os papéis representam a função do usuário dentro da organização.

```text
OWNER
ADMIN
RECRUITER
MEMBER
```

Esses papéis são diferentes do `User.role` definido na SPEC-001.

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

`User.role` define o contexto da conta.

`CompanyMembership.role` define a função do usuário dentro da empresa.

---

## 5. Perfil institucional

O perfil institucional permite que candidatos conheçam a empresa independentemente de uma oportunidade específica.

### 5.1 Informações institucionais

Podem incluir:

- razão social;
- nome fantasia;
- CNPJ;
- descrição;
- logotipo;
- site;
- setor;
- porte.

O CNPJ poderá ser opcional no cadastro inicial e posteriormente utilizado em processos de verificação.

---

## 6. Cultura e benefícios

A empresa poderá informar características institucionais como:

```text
Cultura
├── valores[]
└── descricaoCultura

Benefícios[]
```

Essas informações têm como objetivo permitir que candidatos avaliem a compatibilidade com a empresa.

Nesta fase, cultura e benefícios são predominantemente informativos e não participam diretamente do cálculo do score de matching.

---

## 7. Localização e modelo de trabalho

A empresa deverá informar sua localização institucional principal:

```text
pais
estado
cidade
```

Também poderá informar seu modelo de trabalho predominante:

```text
REMOTO
HIBRIDO
PRESENCIAL
```

O modelo predominante da empresa não substitui o modelo de trabalho definido individualmente em cada oportunidade.

Uma empresa pode, por exemplo, ter:

```text
Empresa
modelo predominante = HIBRIDO

Oportunidade A = REMOTO
Oportunidade B = HIBRIDO
Oportunidade C = PRESENCIAL
```

---

## 8. Tecnologias utilizadas

A empresa poderá informar as tecnologias utilizadas em seu ambiente de trabalho.

Exemplo:

```text
Java
Spring Boot
Angular
PostgreSQL
AWS
Docker
```

Essas informações poderão ser utilizadas no matching reverso, permitindo que a plataforma identifique candidatos potencialmente compatíveis com o ambiente tecnológico da empresa.

A taxonomia detalhada de tecnologias será definida na SPEC-005.

---

## 9. Preferências gerais de contratação

As preferências gerais representam características recorrentes da empresa e podem servir como valores padrão na criação de novas oportunidades.

```text
CompanyHiringPreference
├── modelosDeTrabalhoAceitos[]
├── tiposDeContratacaoAceitos[]
├── senioridadesTipicas[]
└── tecnologiasPrincipais[]
```

Essas preferências são diferentes dos requisitos de uma oportunidade específica.

Exemplo:

```text
Empresa:
tecnologiasPrincipais
├── Java
├── Spring Boot
└── PostgreSQL
```

Uma oportunidade específica poderá exigir:

```text
Oportunidade:
Java
Spring Boot
PostgreSQL
Docker
AWS
```

A oportunidade pode complementar ou restringir as preferências gerais da empresa.

---

## 10. Visibilidade institucional

A empresa possuirá estados relacionados à publicação do perfil:

```text
DRAFT
PUBLISHED
SUSPENDED
```

### DRAFT

Perfil em construção.

Visível apenas para usuários autorizados da empresa.

### PUBLISHED

Perfil institucional publicado.

Pode ser visualizado por candidatos e utilizado pela plataforma no contexto de matching.

### SUSPENDED

Perfil temporariamente indisponível por decisão da plataforma.

Uma empresa suspensa não deve aparecer normalmente nas buscas ou participar de processos de matching.

---

## 11. Regras de negócio

1. Uma `Company` é independente de um `User`.
2. Uma `Company` pode possuir múltiplos `CompanyMemberships`.
3. Um `User` com `role = COMPANY` pode estar vinculado a uma ou mais empresas, desde que autorizado por cada organização.
4. O primeiro usuário responsável pela criação da empresa recebe inicialmente o papel `OWNER`.
5. Uma empresa deve possuir pelo menos um `OWNER` ativo.
6. Uma empresa só pode publicar oportunidades após publicar seu perfil institucional.
7. Uma empresa com perfil `SUSPENDED` não deve aparecer normalmente em buscas ou participar do matching.
8. `setor`, `modelo de trabalho` e `tecnologias utilizadas` podem ser utilizados no matching reverso.
9. Cultura e benefícios são informativos nesta fase e não participam diretamente do cálculo do score.
10. O modelo de trabalho predominante da empresa não substitui o modelo definido individualmente em cada oportunidade.

---

## 12. Fora de escopo

Nesta fase não serão implementados:

- validação de CNPJ;
- avaliações de empresas por candidatos ou ex-funcionários;
- gestão de filiais como entidades independentes;
- hierarquia organizacional interna;
- permissões granulares;
- workflow avançado de aprovação de usuários;
- integração com sistemas corporativos.

---

## 13. Perguntas em aberto

### 13.1 CNPJ

O CNPJ será opcional no cadastro inicial e poderá posteriormente ser utilizado para verificação da empresa.

**Decisão:** manter a obrigatoriedade da validação para uma etapa futura.

### 13.2 Páginas públicas

Empresas poderão possuir páginas públicas indexáveis por mecanismos de busca.

**Decisão:** sim, mas a estratégia de SEO e indexação será definida posteriormente.

### 13.3 Grupos empresariais

Uma organização poderá possuir múltiplas marcas ou nomes fantasia.

**Decisão:** inicialmente cada `Company` representa uma organização independente. O suporte a grupos empresariais será tratado posteriormente caso necessário.

---

## 14. Estado da SPEC

Após validação das decisões acima, esta SPEC poderá evoluir de `Draft` para `Approved`.
