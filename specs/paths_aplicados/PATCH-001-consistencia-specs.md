# Patch de Consistência — SPEC-002, SPEC-004, SPEC-005

Este documento reúne os ajustes sugeridos para resolver as inconsistências
encontradas na revisão cruzada das specs. Cada bloco indica o arquivo, o que
localizar e o que substituir. Aplique manualmente no repositório.

Resumo do que está sendo corrigido:

1. Dependência circular SPEC-002 ↔ SPEC-005
2. SPEC-004 não declarava depender de SPEC-005 apesar de usá-la
3. Enum de modelo de trabalho divergente entre candidato e oportunidade
4. Enum de tipo de contratação divergente entre candidato e oportunidade
5. Falta de campo de senioridade atual no `CandidateProfile`
6. Sobreposição entre `OpportunityPreference` e skills de categoria `DOMAIN`
7. Falta de nota explicando que `obrigatorios[]/desejaveis[]` (SPEC-004) é a
   visão conceitual do relacionamento `OpportunitySkill.tipo` (SPEC-005)

---

## Arquivo: `specs/SPEC-005-skills.md`

### 1. Cabeçalho — remover dependência circular

**Localizar:**

```
**Status:** Draft **Versão:** 0.2 **Tipo:** Modelo de Domínio **Depende de:** SPEC-000, SPEC-002, SPEC-004
```

**Substituir por:**

```
**Status:** Draft **Versão:** 0.3 **Tipo:** Modelo de Domínio **Depende de:** SPEC-000
```

**Motivo:** `Skill` é uma entidade de base, referenciada por `CandidateProfile`
(SPEC-002) e `Opportunity` (SPEC-004). Ela não precisa depender de quem a
consome — são essas specs que devem apontar para a SPEC-005, não o contrário.
Isso resolve o ciclo SPEC-002 ↔ SPEC-005.

---

## Arquivo: `specs/SPEC-002-perfil-profissional.md`

### 1. Cabeçalho — versão

**Localizar:**

```
**Status:** Draft **Versão:** 0.2 **Tipo:** Modelo de Domínio **Depende de:** SPEC-000, SPEC-001, SPEC-005
```

**Substituir por:**

```
**Status:** Draft **Versão:** 0.3 **Tipo:** Modelo de Domínio **Depende de:** SPEC-000, SPEC-001, SPEC-005
```

(Sem mudança na lista de dependências aqui — SPEC-002 → SPEC-005 continua
válida, o que foi corrigido foi o sentido contrário no arquivo da SPEC-005.)

### 2. Seção 11.1 — modelo de trabalho: alinhar enum com SPEC-003/SPEC-004

**Localizar:**

```
### 11.1 Modelo de trabalho

```

REMOTE
HYBRID
ONSITE

```

```

**Substituir por:**

```
### 11.1 Modelo de trabalho

```

REMOTO
HIBRIDO
PRESENCIAL

```

Este enum é o mesmo utilizado pela modalidade de trabalho da `Opportunity`
(SPEC-004, seção 6) e pelo modelo de trabalho predominante da `Company`
(SPEC-003, seção 7). Os três pontos do domínio devem compartilhar o mesmo
vocabulário para que o matching (SPEC-006) possa comparar os valores
diretamente, sem tradução/normalização adicional.
```

**Motivo:** SPEC-003 e SPEC-004 já usam `REMOTO/HIBRIDO/PRESENCIAL`. Padronizar
a SPEC-002 nesse valor evita que o matching precise de uma camada de
tradução entre "o que o candidato quer" e "o que a vaga oferece".

### 3. Seção 11.2 — tipo de contratação: alinhar enum com SPEC-004

**Localizar:**

```
### 11.2 Tipo de contratação

```

CLT
PJ
FREELANCE
TEMPORARY
INTERNSHIP

```

```

**Substituir por:**

```
### 11.2 Tipo de contratação

```

CLT
PJ
FREELANCER
TEMPORARIO
PROJETO
ESTAGIO

```

Este enum é o mesmo definido para `Opportunity.tipoContratacao` (SPEC-004,
seção 5). Ver SPEC-004 para a definição autoritativa.
```

**Motivo:** elimina a divergência `FREELANCE`/`FREELANCER`,
`TEMPORARY`/`TEMPORARIO`, `INTERNSHIP`/`ESTAGIO`, e inclui `PROJETO`, que
antes só existia do lado da oportunidade.

### 4. Nova seção — senioridade atual do candidato

Adicionar um novo campo estruturado ao `CandidateProfile`, hoje ausente. Sem
ele, a SPEC-006 (seção 3.2) não tem de onde ler "a senioridade do candidato"
para comparar com a da oportunidade — hoje só existe `senioridadesAceitas[]`,
que representa preferência, não estado atual.

**Localizar (seção 4, bloco do modelo conceitual):**

```
├── Disponibilidade
│     ├── status
│     └── dataDisponibilidade
│
├── Experiência[]
```

**Substituir por:**

```
├── Disponibilidade
│     ├── status
│     └── dataDisponibilidade
│
├── Senioridade atual
│     └── nivel
│
├── Experiência[]
```

**Localizar (seção 6, após o bloco de Disponibilidade):**

```
`dataDisponibilidade` permite representar situações em que o candidato só poderá iniciar posteriormente.

---

## 7. Experiência profissional
```

**Substituir por:**

```
`dataDisponibilidade` permite representar situações em que o candidato só poderá iniciar posteriormente.

---

## 6.1 Senioridade atual

```

CurrentSeniority
└── nivel

```

Utiliza o mesmo enum definido para `Opportunity.senioridade` (SPEC-004,
seção 8):

```

JUNIOR
PLENO
SENIOR
ESPECIALISTA

```

Este campo representa o nível profissional **atual** do candidato,
autodeclarado. É diferente de `senioridadesAceitas[]` (seção 11.4), que
representa o que o candidato aceita/deseja — os dois podem divergir (ex:
candidato `SENIOR` que aceita oportunidades `PLENO`).

A senioridade atual é o campo utilizado pela SPEC-006 (seção 3.2) para
comparação com a senioridade exigida pela oportunidade.

---

## 7. Experiência profissional
```

### 5. Seção 16 — regra de negócio nova

**Localizar:**

```
9. A senioridade atual do candidato pode ser diferente da senioridade desejada.
```

**Substituir por:**

```
9. A senioridade atual do candidato pode ser diferente da senioridade desejada.
9.1. `CandidateProfile.senioridadeAtual` é o campo utilizado pelo matching (SPEC-006) para comparação com `Opportunity.senioridade`; `senioridadesAceitas[]` representa preferência e não substitui esse campo.
```

---

## Arquivo: `specs/SPEC-004-oportunidades.md`

### 1. Cabeçalho — declarar dependência de SPEC-005

**Localizar:**

```
**Status:** Draft **Versão:** 0.2 **Tipo:** Modelo de Domínio **Depende de:** SPEC-000, SPEC-003
```

**Substituir por:**

```
**Status:** Draft **Versão:** 0.3 **Tipo:** Modelo de Domínio **Depende de:** SPEC-000, SPEC-003, SPEC-005
```

**Motivo:** as seções 10, 21 e 22 já usam a taxonomia de skills da SPEC-005
para modelar requisitos obrigatórios/desejáveis. A dependência estava
implícita no texto, mas não declarada no cabeçalho.

### 2. Seção 10 — nota sobre o modelo autoritativo de requisitos

**Localizar:**

```
Os requisitos estruturados podem referenciar elementos da taxonomia definida na **SPEC-005**.
```

**Substituir por:**

```
Os requisitos estruturados podem referenciar elementos da taxonomia definida na **SPEC-005**.

Nesta spec, `obrigatorios[]` e `desejaveis[]` representam a visão conceitual
do requisito, organizada por categoria. O modelo relacional autoritativo é
o `OpportunitySkill` definido na SPEC-005 (seção 8), no qual cada associação
entre `Opportunity` e `Skill` carrega um atributo `tipo` (`REQUIRED` ou
`DESIRED`). Os dois arrays desta seção são, na prática, uma projeção desse
relacionamento agrupada por `tipo`.
```

**Motivo:** deixa explícito que existem dois "desenhos" conceituais do mesmo
relacionamento (arrays por categoria vs. lista única com atributo `tipo`) e
qual dos dois é a fonte da verdade para implementação.

### 3. Seção 12 — remover sobreposição com skill de categoria DOMAIN

**Localizar:**

```
Exemplos conceituais:

```

experienciaSetorial = FINTECH
experienciaInternacional = true
experienciaComTimesRemotos = true

```

A estrutura definitiva e a taxonomia dessas preferências serão definidas conforme a evolução do domínio e do sistema de matching.
```

**Substituir por:**

```
Exemplos conceituais:

```

experienciaInternacional = true
experienciaComTimesRemotos = true

```

Experiência setorial/de domínio (ex: "Fintech", "E-commerce", "Saúde") **não**
deve ser modelada como `OpportunityPreference`. Esse tipo de informação já é
coberto pela categoria `DOMAIN` de `Skill` (SPEC-005, seção 15), associada à
oportunidade através de `OpportunitySkill`. Utilizar `OpportunityPreference`
apenas para características que não se encaixam no modelo de skill (ex:
formato de trabalho da equipe, características do processo seletivo).

A estrutura definitiva e a taxonomia dessas preferências serão definidas conforme a evolução do domínio e do sistema de matching.
```

**Motivo:** remove a ambiguidade entre duas formas de representar a mesma
informação (experiência setorial como preferência livre vs. como skill de
categoria `DOMAIN`), que criaria dois caminhos diferentes no matching para o
mesmo dado.

---

## Checklist de aplicação

- [x] SPEC-005: remover SPEC-002/SPEC-004 do cabeçalho `Depende de`
- [x] SPEC-002: enum de modelo de trabalho → `REMOTO/HIBRIDO/PRESENCIAL`
- [x] SPEC-002: enum de tipo de contratação → alinhado com SPEC-004
- [x] SPEC-002: adicionar campo `senioridadeAtual` ao `CandidateProfile`
- [x] SPEC-004: adicionar SPEC-005 ao cabeçalho `Depende de`
- [x] SPEC-004: nota sobre `OpportunitySkill.tipo` ser o modelo autoritativo
- [x] SPEC-004: remover `experienciaSetorial` de `OpportunityPreference`
- [x] Subir todas as três specs para `Versão: 0.3`
