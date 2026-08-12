# SPEC-004 — Oportunidades

**Status:** Draft
**Versão:** 0.3
**Tipo:** Modelo de Domínio
**Depende de:** SPEC-000, SPEC-003, SPEC-005

---

## 1. Objetivo

Modelar o conceito de **Oportunidade**, generalizando a ideia tradicional de "vaga de emprego" conforme definido na SPEC-000.

Uma oportunidade representa uma possibilidade concreta de trabalho oferecida por uma empresa e deve conter informações estruturadas suficientes para:

- ser descoberta pelos candidatos;
- ser comparada com os perfis profissionais;
- participar do sistema de matching;
- permitir candidatura;
- permanecer no histórico da empresa após seu encerramento.

A oportunidade pertence a um `Company` e não diretamente ao `User`.

---

## 2. Escopo

Esta spec cobre:

- estrutura conceitual de `Opportunity`;
- relação entre oportunidade e empresa;
- modalidades de trabalho;
- tipos de contratação;
- localização;
- senioridade;
- requisitos obrigatórios e desejáveis;
- preferências da empresa;
- faixa salarial;
- quantidade de vagas;
- benefícios;
- ciclo de vida da oportunidade.

Não cobre:

- taxonomia e normalização de skills — **SPEC-005**;
- algoritmo de matching — **SPEC-006**;
- processo de candidatura — **SPEC-007**;
- autenticação e autorização — **SPEC-008**;
- moderação e aprovação de conteúdo.

---

## 3. Modelo conceitual

```text
Opportunity
│
├── Identificação
│     ├── id
│     ├── titulo
│     └── descricao
│
├── Empresa
│     └── Company
│
├── Contratação
│     ├── tipoContratacao
│     └── modalidade
│
├── Localização
│     ├── pais
│     ├── estado
│     ├── cidade
│     └── aceitaCandidatosDeOutrasLocalizacoes
│
├── Perfil profissional
│     ├── senioridade
│     └── experienciaMinima
│
├── Requisitos
│     ├── obrigatorios[]
│     └── desejaveis[]
│
├── Preferências da empresa
│     └── preferencias[]
│
├── Remuneração
│     └── faixaSalarial
│
├── Benefícios
│     └── beneficios[]
│
├── Quantidade
│     └── quantidadeVagas
│
└── Controle
      ├── status
      ├── dataCriacao
      ├── dataPublicacao
      └── dataEncerramento
```

---

## 4. Relação com a empresa

Uma oportunidade pertence obrigatoriamente a um `Company`.

```text
Company (1)
       │
       │ possui
       │
       └─────────── N Opportunity
```

Regras:

- uma empresa pode possuir várias oportunidades;
- uma oportunidade pertence a uma única empresa;
- a oportunidade não pertence diretamente ao `User`;
- alterações na conta de um usuário não devem alterar a propriedade histórica da oportunidade;
- uma oportunidade encerrada permanece associada à empresa para fins de histórico.

---

## 5. Tipo de contratação

O tipo de contratação representa **como a relação profissional será estabelecida**.

```text
CLT
PJ
FREELANCER
TEMPORARIO
PROJETO
ESTAGIO
```

O tipo de contratação é independente da modalidade de trabalho.

Exemplo:

```text
tipoContratacao = PJ
modalidade = REMOTO
```

ou:

```text
tipoContratacao = CLT
modalidade = HIBRIDO
```

Uma oportunidade pode possuir mais de um tipo de contratação quando isso fizer sentido para a empresa, desde que essa possibilidade seja suportada pelo modelo de domínio.

---

## 6. Modalidade de trabalho

Representa **onde o trabalho será realizado**.

```text
REMOTO
HIBRIDO
PRESENCIAL
```

A modalidade é independente do tipo de contratação.

Exemplo:

```text
CLT + REMOTO
PJ + REMOTO
CLT + HIBRIDO
PJ + PRESENCIAL
```

---

## 7. Localização

Para oportunidades híbridas ou presenciais, a localização deve ser estruturada.

```text
Location
├── pais
├── estado
├── cidade
└── aceitaCandidatosDeOutrasLocalizacoes
```

### Regras

- `pais` é obrigatório quando houver localização definida;
- `estado` e `cidade` são necessários quando a oportunidade depender de uma localização específica;
- oportunidades remotas podem não possuir cidade;
- uma empresa pode indicar que aceita candidatos de outras localidades;
- regras específicas de distância/deslocamento pertencem ao matching e não a esta spec.

Exemplo:

```text
modalidade: HIBRIDO

localizacao:
    pais: Brasil
    estado: Paraná
    cidade: Curitiba

aceitaCandidatosDeOutrasLocalizacoes: false
```

---

## 8. Senioridade

A senioridade representa o nível profissional esperado para a oportunidade.

```text
JUNIOR
PLENO
SENIOR
ESPECIALISTA
```

`ESTAGIO` não pertence à senioridade. Quando aplicável, estágio é representado pelo `tipoContratacao`.

Exemplo:

```text
tipoContratacao = ESTAGIO
senioridade = JUNIOR
```

---

## 9. Experiência mínima

A oportunidade pode definir uma experiência profissional mínima.

```text
experienciaMinima
```

Valor representado em anos.

Exemplo:

```text
experienciaMinima = 3
```

O campo é opcional.

A interpretação de experiência deve considerar o contexto profissional e não apenas o número bruto de anos. Regras mais sofisticadas pertencem ao matching.

---

## 10. Requisitos

Os requisitos representam critérios utilizados para avaliar a compatibilidade entre uma oportunidade e um candidato.

Existem dois grupos:

```text
Requisitos
├── obrigatorios[]
└── desejaveis[]
```

Os requisitos estruturados podem referenciar elementos da taxonomia definida na **SPEC-005**.

Nesta spec, `obrigatorios[]` e `desejaveis[]` representam a visão conceitual
do requisito, organizada por categoria. O modelo relacional autoritativo é
o `OpportunitySkill` definido na SPEC-005 (seção 8), no qual cada associação
entre `Opportunity` e `Skill` carrega um atributo `tipo` (`REQUIRED` ou
`DESIRED`). Os dois arrays desta seção são, na prática, uma projeção desse
relacionamento agrupada por `tipo`.

````

---

### 10.1 Requisitos obrigatórios

Representam características consideradas necessárias pela empresa.

Exemplo:

```text
Java
Spring Boot
PostgreSQL
````

A ausência de um requisito obrigatório pode reduzir fortemente a compatibilidade ou ser utilizada como critério de corte pelo matching.

A regra exata de cálculo pertence à **SPEC-006**.

---

### 10.2 Requisitos desejáveis

Representam características que aumentam a compatibilidade, mas cuja ausência não deve necessariamente desqualificar o candidato.

Exemplo:

```text
AWS
Docker
Kubernetes
```

A ausência de uma característica desejável não deve impedir a candidatura por si só.

---

## 11. Requisitos x Preferências

Requisitos e preferências devem ser tratados como conceitos distintos.

### Requisito

Representa algo que a empresa considera relevante para a execução da oportunidade.

```text
Obrigatório:
    Java

Desejável:
    Spring Boot
```

### Preferência

Representa uma característica que torna determinado candidato mais interessante, sem necessariamente ser uma exigência técnica.

Exemplo:

```text
Preferência:
    experiência em fintech
```

Outro exemplo:

```text
Preferência:
    experiência anterior trabalhando em equipes distribuídas
```

As preferências podem contribuir para o matching, mas não devem funcionar automaticamente como requisitos eliminatórios.

---

## 12. Preferências da empresa para a oportunidade

Uma oportunidade pode possuir preferências específicas que complementam os requisitos.

```text
OpportunityPreference
├── tipo
└── valor
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

````

---

## 13. Faixa salarial

A oportunidade pode definir uma faixa de remuneração.

```text
SalaryRange
├── valorMinimo
├── valorMaximo
└── moeda
````

A faixa salarial pode possuir dois estados de visibilidade:

```text
PUBLICA
OCULTA
```

Quando `OCULTA`:

- o candidato não visualiza os valores;
- os valores continuam disponíveis internamente para o sistema;
- os valores podem ser utilizados no matching, conforme regras da **SPEC-006**.

A ausência de faixa salarial é permitida nesta fase.

---

## 14. Benefícios

A empresa pode informar benefícios associados à oportunidade.

Exemplos:

```text
Vale-refeição
Vale-alimentação
Plano de saúde
Plano odontológico
Auxílio home office
Gympass
```

Benefícios possuem caráter predominantemente informativo nesta fase e não participam diretamente do score de matching.

Isso pode ser revisado futuramente.

---

## 15. Quantidade de vagas

Uma oportunidade pode representar uma ou várias posições.

```text
quantidadeVagas
```

Valor padrão:

```text
1
```

Exemplo:

```text
titulo: Desenvolvedor Java Pleno
quantidadeVagas: 5
```

A candidatura de um candidato continua vinculada à oportunidade, e não necessariamente a uma vaga individual.

A gestão individual das posições não faz parte desta spec.

---

## 16. Ciclo de vida

A oportunidade possui os seguintes estados:

```text
DRAFT
OPEN
PAUSED
CLOSED
```

### DRAFT

Oportunidade em elaboração.

- não é pública;
- não participa do matching;
- pode ser editada pela empresa.

### OPEN

Oportunidade publicada.

- visível para candidatos;
- participa do matching;
- pode receber candidaturas.

### PAUSED

Oportunidade temporariamente pausada.

- deixa de participar do matching;
- pode deixar de aparecer nas buscas ativas;
- não é considerada encerrada;
- pode retornar para `OPEN`.

### CLOSED

Oportunidade encerrada.

- não participa do matching;
- não recebe novas candidaturas;
- permanece no histórico da empresa.

---

## 17. Transições de estado

```text
DRAFT
  │
  │ publicar
  ▼
OPEN
  │
  ├── pausar ──▶ PAUSED
  │                │
  │                │ retomar
  │                ▼
  └────────────── OPEN

OPEN ──encerrar──▶ CLOSED

PAUSED ──encerrar──▶ CLOSED
```

Uma oportunidade `CLOSED` não retorna para `OPEN`.

Caso a empresa queira publicar novamente a mesma posição, deverá criar uma nova oportunidade ou utilizar um mecanismo futuro de duplicação.

---

## 18. Regras de negócio

1. Toda oportunidade pertence obrigatoriamente a um `Company`.

2. Uma empresa pode possuir múltiplas oportunidades.

3. Uma oportunidade só participa do matching quando estiver com status `OPEN`.

4. Uma oportunidade `PAUSED` não participa do matching.

5. Uma oportunidade `CLOSED` permanece disponível no histórico, mas não aparece nas buscas e recomendações ativas.

6. Toda oportunidade deve possuir pelo menos um **critério de compatibilidade estruturado**.

Esse critério pode ser, por exemplo:

```text
skill
senioridade
experienciaMinima
modalidade
tipoContratacao
```

7. Requisitos obrigatórios podem ser utilizados como filtros de corte pelo matching.

8. Requisitos desejáveis contribuem para compatibilidade, mas sua ausência não desqualifica automaticamente o candidato.

9. Preferências da empresa não devem ser tratadas automaticamente como requisitos eliminatórios.

10. Faixa salarial pode ser pública ou oculta.

11. Uma faixa salarial oculta pode ser utilizada internamente pelo sistema de matching.

12. `quantidadeVagas` possui valor padrão `1`.

13. Benefícios são informativos nesta fase e não participam diretamente do score de matching.

14. A oportunidade deve permanecer historicamente associada à empresa mesmo após seu encerramento.

15. A empresa deve possuir um perfil institucional publicado antes de poder publicar uma oportunidade.

---

## 19. Fora de escopo

Não fazem parte desta spec:

- algoritmo de matching — **SPEC-006**;
- candidatura — **SPEC-007**;
- autenticação e autorização — **SPEC-008**;
- moderação e aprovação de oportunidades;
- validação de CNPJ;
- templates de oportunidades;
- publicação automática em plataformas externas;
- oportunidades recorrentes/banco de talentos;
- gestão individual de múltiplas posições dentro de uma oportunidade;
- aplicação de testes técnicos;
- entrevistas.

---

## 20. Perguntas em aberto

### 20.1 Faixa salarial

A faixa salarial deve:

- ser opcional;
- ser obrigatória para determinadas categorias;
- ou ser obrigatória para todas as oportunidades?

---

### 20.2 Tipos de contratação múltiplos

Uma oportunidade poderá aceitar simultaneamente diferentes tipos de contratação?

Exemplo:

```text
CLT ou PJ
```

---

### 20.3 Localização remota

Como representar oportunidades:

```text
Remoto — Brasil
```

versus:

```text
Remoto — qualquer lugar do mundo
```

Essa distinção poderá ser relevante para o matching.

---

### 20.4 Quantidade de vagas

Quando `quantidadeVagas > 1`, devemos tratar a oportunidade como uma única entidade ou permitir futuramente posições individuais?

---

### 20.5 Preferências

Quais categorias de preferências devem ser suportadas inicialmente?

A definição deve ser feita em conjunto com a evolução da **SPEC-005** e da **SPEC-006**.

---

## 21. Exemplo conceitual

```text
Opportunity

Título:
    Desenvolvedor Backend Java

Empresa:
    Empresa XYZ

Tipo de contratação:
    CLT

Modalidade:
    HÍBRIDO

Localização:
    Brasil
    Paraná
    Curitiba

Senioridade:
    PLENO

Experiência mínima:
    3 anos

Requisitos obrigatórios:
    Java
    Spring Boot
    PostgreSQL

Requisitos desejáveis:
    Docker
    AWS

Preferências:
    Experiência em fintech
    Experiência com sistemas distribuídos

Faixa salarial:
    R$ 8.000 — R$ 11.000
    Visibilidade: OCULTA

Quantidade de vagas:
    2

Benefícios:
    Plano de saúde
    Vale-refeição
    Auxílio home office

Status:
    OPEN
```

---

## 22. Relação com as próximas especificações

A oportunidade será uma das principais entidades utilizadas pelo mecanismo de matching.

```text
CandidateProfile
       │
       │
       │      SPEC-005
       ▼
     Skills
       │
       │
       ▼
   SPEC-006
   Matching
       ▲
       │
       │
   Opportunity
       │
       ├── Requirements
       ├── Preferences
       ├── Seniority
       ├── Location
       └── Hiring Model
```

A **SPEC-005** deverá definir como skills e competências são representadas e normalizadas.

A **SPEC-006** deverá definir como os dados do candidato e da oportunidade serão comparados e transformados em compatibilidade.
