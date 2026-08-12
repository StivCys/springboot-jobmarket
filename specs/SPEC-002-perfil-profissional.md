# SPEC-002 — Perfil Profissional (Candidato)

**Status:** Draft
**Versão:** 0.3
**Tipo:** Modelo de Domínio
**Depende de:** SPEC-000, SPEC-001, SPEC-005

---

## 1. Objetivo

Definir a estrutura do **perfil profissional do candidato (`CandidateProfile`)**.

O perfil representa as informações profissionais estruturadas utilizadas pelo JobMarket para apresentar o candidato, permitir buscas e alimentar o sistema de matching.

O `CandidateProfile` pertence a uma conta `User` com `role = CANDIDATE`.

```text
User
 │
 │ 1
 ▼
CandidateProfile
```

---

## 2. Escopo

Esta spec cobre:

- estrutura do `CandidateProfile`;
- experiências profissionais;
- formação acadêmica;
- projetos;
- skills;
- preferências profissionais;
- objetivos profissionais;
- disponibilidade;
- localização;
- nível de completude do perfil.

Não cobre:

- algoritmo de matching (SPEC-006);
- taxonomia e normalização de skills (SPEC-005);
- autenticação e autorização (SPEC-008);
- parsing automático de currículo;
- validação externa de experiências profissionais.

---

## 3. Modelo conceitual

```text
CandidateProfile
│
├── Informações básicas
│     ├── id
│     ├── nome
│     ├── headline
│     ├── resumo
│     ├── foto
│     └── telefone
│
├── Localização
│     ├── país
│     ├── estado
│     ├── cidade
│     └── abertoAMudanca
│
├── Disponibilidade
│     ├── status
│     └── dataDisponibilidade
│
├── Senioridade atual
│     └── nivel
|
├── Experiências[]
│
├── Formação[]
│
├── Projetos[]
│
├── Skills[]
│
├── Preferências[]
│
├── Objetivos profissionais
│
├── Currículo
│
└── status/completude
```

---

## 4. Informações básicas

```text
CandidateProfile
├── id
├── userId
├── nome
├── headline
├── resumo
├── foto
└── telefone
```

### 4.1 Headline

Texto curto que representa a identidade profissional do candidato.

Exemplos:

```text
Desenvolvedor Backend Java
Full Stack Developer
Engenheiro de Software
Analista de Dados
```

O `headline` não substitui a taxonomia estruturada de cargos e skills.

---

## 5. Localização

```text
Location
├── país
├── estado
├── cidade
└── abertoAMudanca
```

O candidato poderá indicar se aceita oportunidades fora de sua localização atual.

A localização poderá ser utilizada no matching, respeitando o modelo de trabalho da oportunidade.

---

## 6. Disponibilidade

```text
Availability
├── status
└── dataDisponibilidade
```

Estados possíveis:

```text
AVAILABLE
OPEN_TO_OFFERS
UNAVAILABLE
```

### AVAILABLE

Candidato disponível para iniciar uma nova oportunidade.

### OPEN_TO_OFFERS

Candidato atualmente empregado ou comprometido, mas aberto a receber oportunidades.

### UNAVAILABLE

Candidato não deseja receber oportunidades no momento.

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

```text
Experience
├── id
├── candidateProfileId
├── companyName
├── position
├── dataInicio
├── dataFim
├── descricao
└── tecnologias[]
```

`dataFim = null` representa uma experiência atual.

A experiência não precisa estar vinculada a uma empresa cadastrada no JobMarket.

Isso permite que o candidato registre experiências profissionais anteriores mesmo que a empresa não possua cadastro na plataforma.

Futuramente, uma experiência poderá possuir um vínculo opcional com uma `Company`.

```text
Experience
├── companyId (opcional)
└── companyName
```

---

## 8. Formação acadêmica

```text
Education
├── id
├── candidateProfileId
├── institution
├── course
├── level
├── dataInicio
└── dataFim
```

Níveis possíveis incluem:

```text
TECHNICAL
UNDERGRADUATE
GRADUATE
MASTERS
DOCTORATE
OTHER
```

`dataFim = null` representa uma formação em andamento.

---

## 9. Projetos

Projetos permitem que o candidato demonstre experiência prática além de empregos formais.

```text
Project
├── id
├── candidateProfileId
├── nome
├── descricao
├── url
└── tecnologias[]
```

Exemplos:

- projetos open source;
- projetos pessoais;
- sistemas desenvolvidos durante estudos;
- aplicações comerciais;
- portfólio.

---

## 10. Skills

As skills do candidato devem utilizar a taxonomia definida na `SPEC-005`.

Não serão armazenados apenas textos livres.

Conceitualmente:

```text
CandidateProfile
       │
       ▼
CandidateSkill
       │
       ▼
Skill
```

Exemplo:

```text
CandidateSkill
├── skillId → Java
├── nível
└── anosExperiencia
```

O modelo detalhado de `Skill` e sua normalização pertence à `SPEC-005`.

---

## 11. Preferências profissionais

As preferências representam **o tipo de oportunidade que o candidato deseja receber**.

```text
Preference
├── id
├── candidateProfileId
├── modelosDeTrabalho[]
├── tiposDeContratacao[]
├── localizacoesAceitas[]
├── senioridadesAceitas[]
├── faixaSalarial
└── tecnologiasDeInteresse[]
```

### 11.1 Modelo de trabalho

```text
REMOTO
HIBRIDO
PRESENCIAL
```

O candidato pode aceitar mais de um modelo.

Exemplo:

```text
REMOTE
HYBRID
```

### 11.2 Tipo de contratação

```text
CLT
PJ
FREELANCER
TEMPORARIO
PROJETO
ESTAGIO
```

O candidato pode aceitar múltiplos tipos.

### 11.3 Localizações

O candidato pode indicar múltiplas localizações aceitas.

Exemplo:

```text
Curitiba
São Paulo
Remoto
```

### 11.4 Senioridade desejada

O candidato pode indicar uma ou mais senioridades compatíveis com seu objetivo.

Exemplo:

```text
PLENO
SENIOR
```

A senioridade atual do candidato e a senioridade desejada não precisam necessariamente ser iguais.

---

## 12. Objetivos profissionais

```text
CareerObjective
├── cargosDesejados[]
└── descricao
```

Os cargos desejados devem, sempre que possível, utilizar a taxonomia estruturada da plataforma.

O texto livre de `descricao` permite que o candidato explique seus objetivos profissionais.

---

## 13. Faixa salarial

A faixa salarial é opcional.

```text
SalaryExpectation
├── minimo
├── maximo
└── moeda
```

Sua utilização e visibilidade devem respeitar as regras de privacidade definidas pelo produto.

A faixa salarial poderá ser utilizada pelo matching sem necessariamente ser exibida publicamente para outros usuários.

---

## 14. Currículo

O candidato poderá anexar um currículo em formato de arquivo.

O currículo é considerado **informação complementar**.

O arquivo não substitui os dados estruturados do `CandidateProfile`.

Futuramente, a plataforma poderá utilizar IA para extrair informações do currículo e sugerir o preenchimento do perfil.

Essa funcionalidade está fora do escopo atual.

---

## 15. Completude do perfil

O perfil possui diferentes níveis de completude:

```text
INCOMPLETE
BASIC
COMPLETE
```

### INCOMPLETE

Faltam informações essenciais.

### BASIC

Possui informações suficientes para apresentação básica e participação limitada no matching.

### COMPLETE

Possui os principais blocos profissionais preenchidos.

A completude não deve impedir o candidato de utilizar a plataforma, mas poderá influenciar sua exposição e qualidade do matching.

---

## 16. Regras de negócio

1. Um `User` com `role = CANDIDATE` pode possuir no máximo um `CandidateProfile`.
2. Um `CandidateProfile` deve estar associado a exatamente um `User`.
3. Skills devem utilizar referências da taxonomia definida na `SPEC-005`.
4. Experiências profissionais não precisam estar vinculadas a empresas cadastradas no JobMarket.
5. Uma experiência com `dataFim = null` é considerada atual.
6. Uma formação com `dataFim = null` é considerada em andamento.
7. O candidato pode possuir múltiplas preferências profissionais.
8. Uma preferência pode aceitar múltiplos modelos de trabalho, tipos de contratação, localizações e senioridades.
9. A senioridade atual do candidato pode ser diferente da senioridade desejada.
   9.1. `CandidateProfile.senioridadeAtual` é o campo utilizado pelo matching (SPEC-006) para comparação com `Opportunity.senioridade`; `senioridadesAceitas[]` representa preferência e não substitui esse campo.
10. A faixa salarial é opcional.
11. O currículo anexado não substitui os dados estruturados do perfil.
12. Um candidato `UNAVAILABLE` não deve receber novas oportunidades por matching ativo.
13. A completude do perfil não deve ser utilizada como critério eliminatório absoluto.

---

## 17. Fora de escopo

Esta SPEC não define:

- algoritmo de matching;
- peso de cada skill;
- cálculo de compatibilidade;
- parsing automático de currículo;
- validação de experiências;
- validação de formação;
- integração com LinkedIn;
- sistema de recomendações;
- privacidade detalhada dos campos;
- sistema de avaliações profissionais.

---

## 18. Perguntas em aberto

### 18.1 Faixa salarial

**Decisão proposta:** o candidato poderá escolher se deseja tornar sua faixa salarial visível. O sistema poderá utilizá-la no matching independentemente da visibilidade pública.

### 18.2 Validação de experiências

**Decisão proposta:** não haverá validação obrigatória no MVP.

Experiências serão declaradas pelo próprio candidato.

### 18.3 Quantidade de experiências e projetos

**Decisão proposta:** não haverá limite rígido no modelo de domínio.

A interface poderá limitar a quantidade inicialmente exibida e oferecer carregamento sob demanda.

---

## 19. Estado da SPEC

Após validação das decisões acima, esta SPEC poderá evoluir de `Draft` para `Approved`.
