# SPEC-005 — Skills e Competências

**Status:** Draft
**Versão:** 0.2
**Tipo:** Modelo de Domínio
**Depende de:** SPEC-000, SPEC-002, SPEC-004

---

## 1. Objetivo

Definir como **skills e competências** são representadas, normalizadas e reutilizadas entre candidatos e oportunidades.

A `Skill` representa um conhecimento ou competência que pode ser associado a diferentes entidades da plataforma, principalmente:

- `CandidateProfile` — aquilo que o candidato possui;
- `Opportunity` — aquilo que a empresa procura.

A estrutura deve servir como base comum para:

- busca;
- filtros;
- recomendações;
- matching;
- descoberta de candidatos;
- descoberta de oportunidades.

O cálculo de compatibilidade propriamente dito pertence à **SPEC-006**.

---

## 2. Escopo

Esta spec cobre:

- entidade `Skill`;
- categorias de skills;
- nomes canônicos;
- sinônimos;
- associação entre candidato e skill;
- associação entre oportunidade e skill;
- níveis de proficiência;
- competências técnicas e comportamentais;
- idiomas;
- ciclo de vida das skills.

Não cobre:

- cálculo do score de matching — **SPEC-006**;
- geração automática de skills por IA;
- validação externa de competências;
- certificações;
- parsing automático de currículos;
- geração de embeddings;
- algoritmo semântico de matching.

---

## 3. Modelo conceitual

```text
Skill
│
├── id
├── nomeCanonico
├── categoria
├── descricao
├── ativo
└── sinonimos[]
```

Exemplo:

```text
Skill

id:
    123

nomeCanonico:
    JavaScript

categoria:
    PROGRAMMING_LANGUAGE

sinonimos:
    JS
    Javascript
    ECMAScript
```

---

# 4. Categorias

As skills devem possuir uma categoria controlada.

Categorias iniciais:

```text
PROGRAMMING_LANGUAGE
FRAMEWORK
LIBRARY
DATABASE
CLOUD
DEVOPS
TOOL
METHODOLOGY
SOFT_SKILL
DOMAIN
LANGUAGE
OTHER
```

Exemplos:

```text
Java
    → PROGRAMMING_LANGUAGE

Spring Boot
    → FRAMEWORK

React
    → LIBRARY

PostgreSQL
    → DATABASE

AWS
    → CLOUD

Docker
    → DEVOPS

Git
    → TOOL

Scrum
    → METHODOLOGY

Liderança
    → SOFT_SKILL

Fintech
    → DOMAIN

Inglês
    → LANGUAGE
```

A taxonomia poderá evoluir conforme o domínio crescer.

---

# 5. Skill canônica

Cada skill deve possuir um único nome canônico.

Exemplo:

```text
Skill
    nomeCanonico = "JavaScript"
```

As seguintes entradas devem apontar para a mesma entidade:

```text
JS
Javascript
javascript
ECMAScript
```

Isso evita a criação de múltiplas skills representando essencialmente o mesmo conhecimento.

---

# 6. Sinônimos

Sinônimos representam formas alternativas utilizadas para identificar uma mesma skill.

Conceitualmente:

```text
Skill
│
└── SkillAlias[]
      ├── valor
      └── skill
```

Exemplo:

```text
Skill
    JavaScript

Aliases
    JS
    Javascript
    ECMAScript
```

Um alias nunca representa uma nova skill.

```text
"JS"
   │
   ▼
SkillAlias
   │
   ▼
"JavaScript"
```

---

# 7. Associação com candidato

A relação entre candidato e skill não deve ser uma simples lista.

O candidato pode possuir informações adicionais sobre sua experiência.

```text
CandidateSkill
├── candidate
├── skill
├── nivel
├── anosExperiencia
└── principal
```

### Nível de proficiência

```text
BASIC
INTERMEDIATE
ADVANCED
EXPERT
```

Exemplo:

```text
CandidateSkill

skill:
    Java

nivel:
    ADVANCED

anosExperiencia:
    6

principal:
    true
```

---

# 8. Associação com oportunidade

Uma oportunidade possui requisitos relacionados às skills.

```text
OpportunitySkill
├── opportunity
├── skill
├── tipo
└── nivelMinimo
```

Onde:

```text
tipo
├── REQUIRED
└── DESIRED
```

Exemplo:

```text
OpportunitySkill

skill:
    Java

tipo:
    REQUIRED

nivelMinimo:
    INTERMEDIATE
```

Outro exemplo:

```text
OpportunitySkill

skill:
    Docker

tipo:
    DESIRED

nivelMinimo:
    BASIC
```

---

# 9. Requisito obrigatório x desejável

A classificação pertence à associação entre `Opportunity` e `Skill`, e não à entidade `Skill`.

Isso é importante.

A mesma skill pode ser:

```text
Java
    → REQUIRED em uma oportunidade

Java
    → DESIRED em outra oportunidade
```

Portanto:

```text
Skill
    │
    ├── OpportunitySkill → REQUIRED
    │
    └── OpportunitySkill → DESIRED
```

A `Skill` permanece independente da oportunidade.

---

# 10. Níveis de proficiência

Os níveis devem ser padronizados para permitir comparação entre candidatos e oportunidades.

```text
BASIC
INTERMEDIATE
ADVANCED
EXPERT
```

O candidato informa seu nível.

A empresa pode informar um nível mínimo.

Exemplo:

```text
Candidato:

Java
ADVANCED


Oportunidade:

Java
REQUIRED
INTERMEDIATE
```

A interpretação dessa relação pertence à **SPEC-006**.

---

# 11. Anos de experiência

O candidato pode informar uma estimativa de experiência relacionada à skill.

```text
anosExperiencia
```

O campo é opcional.

Exemplo:

```text
Java
nivel = ADVANCED
anosExperiencia = 5
```

O número de anos não deve ser utilizado isoladamente para determinar proficiência.

Exemplo:

```text
10 anos ≠ automaticamente EXPERT
```

A combinação entre nível declarado, experiência e outros fatores será responsabilidade futura do matching.

---

# 12. Skills principais

O candidato pode marcar algumas skills como principais.

```text
principal = true
```

Isso permite diferenciar:

```text
Skills principais
    Java
    Spring Boot
    PostgreSQL

Skills adicionais
    Docker
    AWS
    Redis
```

A marcação de skill principal não significa necessariamente maior nível de proficiência.

Ela representa a importância daquela competência para o perfil profissional do candidato.

---

# 13. Idiomas

Idiomas fazem parte do domínio de skills, mas possuem características próprias.

Exemplo:

```text
Inglês
Espanhol
Português
Francês
```

A categoria será:

```text
LANGUAGE
```

Entretanto, o nível de idioma não deve necessariamente utilizar exatamente a mesma escala das skills técnicas.

Futuramente poderá ser adotado um modelo específico, por exemplo:

```text
A1
A2
B1
B2
C1
C2
```

ou outro padrão de proficiência.

A definição detalhada dessa escala fica em aberto.

---

# 14. Soft skills

Soft skills podem ser representadas como skills:

```text
Liderança
Comunicação
Negociação
Trabalho em equipe
Pensamento crítico
```

Categoria:

```text
SOFT_SKILL
```

Nesta fase, soft skills podem ser declaradas tanto por candidatos quanto utilizadas em oportunidades.

Entretanto, a plataforma não deve assumir que uma soft skill autodeclarada possui o mesmo grau de confiabilidade que uma competência técnica observável.

O peso dessas competências no matching será definido na **SPEC-006**.

---

# 15. Domínios de conhecimento

A taxonomia pode representar conhecimentos específicos de determinados setores.

Exemplos:

```text
Fintech
E-commerce
Saúde
Logística
Seguros
Bancário
```

Categoria:

```text
DOMAIN
```

Isso permite que o sistema represente situações como:

```text
Candidato
    Java
    Spring Boot
    Fintech

Oportunidade
    Java
    Spring Boot
    Fintech
```

Esse tipo de correspondência poderá posteriormente ser utilizado pelo matching.

---

# 16. Ciclo de vida da Skill

Uma skill pode possuir os seguintes estados:

```text
ACTIVE
INACTIVE
```

### ACTIVE

Skill disponível para uso na plataforma.

### INACTIVE

Skill não pode ser utilizada em novas associações, mas permanece no banco para preservar histórico.

---

# 17. Criação e curadoria

As skills podem ser administradas pela plataforma.

Usuários podem sugerir novas skills:

```text
Usuário
   │
   ▼
Sugestão de Skill
   │
   ▼
Revisão
   │
   ├── aprovada → ACTIVE
   │
   └── rejeitada → descartada
```

Uma sugestão não deve criar imediatamente uma nova skill ativa.

Isso evita fragmentação da taxonomia.

---

# 18. Normalização

A normalização deve ocorrer antes da associação definitiva.

Exemplo:

```text
Entrada:
    "JS"

       ↓

Busca de alias

       ↓

Skill:
    JavaScript
```

Outro exemplo:

```text
Entrada:
    "Postgres"

       ↓

Alias

       ↓

Skill:
    PostgreSQL
```

A normalização inicial pode ser baseada em:

- nome canônico;
- aliases conhecidos;
- comparação case-insensitive;
- regras determinísticas.

Soluções baseadas em IA ou similaridade semântica ficam fora do escopo inicial.

---

# 19. Regras de negócio

1. Toda skill utilizada por um candidato ou oportunidade deve referenciar uma `Skill` existente.

2. Não devem existir skills duplicadas representando o mesmo conceito.

3. Aliases não criam novas skills.

4. Uma skill possui um único nome canônico.

5. Skills inativas não podem ser utilizadas em novas associações.

6. Skills existentes não devem ser removidas fisicamente quando já houver referências, preservando o histórico.

7. O nível de proficiência do candidato é autodeclarado nesta fase.

8. `anosExperiencia` é opcional.

9. A classificação `REQUIRED` ou `DESIRED` pertence à associação `OpportunitySkill`, não à entidade `Skill`.

10. A skill `Java`, por exemplo, pode ser obrigatória em uma oportunidade e desejável em outra.

11. Uma skill pode estar associada a milhares de candidatos e oportunidades.

12. O sistema deve permitir adicionar novas categorias e skills sem alteração estrutural significativa no domínio.

13. O cálculo da compatibilidade entre skills pertence exclusivamente à **SPEC-006**.

---

# 20. Fora de escopo

Não fazem parte desta spec:

- embeddings;
- LLM;
- classificação automática por IA;
- parsing de currículo;
- validação de LinkedIn;
- certificação de competências;
- testes técnicos;
- provas de proficiência;
- recomendações automáticas;
- score de matching;
- ranking de candidatos;
- ranking de oportunidades.

---

# 21. Perguntas em aberto

### 21.1 Curadoria inicial

A plataforma começará com uma lista inicial de skills pré-cadastradas ou a taxonomia será construída progressivamente através das sugestões dos usuários?

---

### 21.2 Idiomas

Devemos utilizar:

```text
A1 → C2
```

ou uma escala própria para idiomas?

---

### 21.3 Soft skills

Soft skills participarão do matching?

Caso participem:

- terão peso menor?
- terão o mesmo peso?
- serão utilizadas apenas como fator complementar?

---

### 21.4 Hierarquia de skills

Futuramente poderemos representar relações como:

```text
JavaScript
   │
   ├── TypeScript
   ├── React
   └── Node.js
```

ou:

```text
Java
   │
   └── Spring Boot
```

Essa hierarquia deve ser avaliada antes de ser implementada.

---

### 21.5 Skills compostas

Como representar requisitos como:

```text
"Java + Spring Boot + PostgreSQL"
```

A princípio, cada item deve ser uma skill independente.

A combinação entre elas deve ser responsabilidade da oportunidade e do matching.

---

# 22. Exemplo completo

### Candidato

```text
CandidateProfile
│
├── Skills
│
├── Java
│     nivel: ADVANCED
│     anosExperiencia: 6
│     principal: true
│
├── Spring Boot
│     nivel: ADVANCED
│     anosExperiencia: 5
│     principal: true
│
├── PostgreSQL
│     nivel: ADVANCED
│     anosExperiencia: 5
│     principal: true
│
├── Docker
│     nivel: INTERMEDIATE
│     anosExperiencia: 3
│     principal: false
│
└── AWS
      nivel: BASIC
      anosExperiencia: 1
      principal: false
```

### Oportunidade

```text
Opportunity
│
├── REQUIRED
│     ├── Java
│     │     nivelMinimo: INTERMEDIATE
│     │
│     ├── Spring Boot
│     │     nivelMinimo: INTERMEDIATE
│     │
│     └── PostgreSQL
│           nivelMinimo: BASIC
│
└── DESIRED
      ├── Docker
      │     nivelMinimo: BASIC
      │
      └── AWS
            nivelMinimo: BASIC
```

A comparação desses dados não é realizada nesta spec.

Ela será responsabilidade da **SPEC-006 — Matching**.

---

# 23. Relação com o domínio

```text
CandidateProfile
       │
       │ possui
       ▼
CandidateSkill
       │
       │ referencia
       ▼
     Skill
       ▲
       │ referencia
       │
OpportunitySkill
       ▲
       │ possui
       │
Opportunity
```

A `Skill` funciona como uma linguagem comum entre candidatos e empresas.

```text
Candidato
    │
    ├── Java
    ├── Spring Boot
    └── PostgreSQL
           │
           ▼
        MATCHING
           ▲
           │
    ┌──────┴──────┐
    │             │
Opportunity   Opportunity
    │             │
    ├── Java      ├── Java
    ├── Spring    ├── AWS
    └── PostgreSQL└── Docker
```

Essa estrutura permite que o JobMarket evolua posteriormente de uma simples busca textual de vagas para um sistema de descoberta baseado em **competências, contexto e compatibilidade**.
