# SPEC-006 — Matching

**Status:** Draft
**Versão:** 0.2
**Tipo:** Regra de Negócio / Algoritmo Conceitual
**Depende de:** SPEC-000, SPEC-002, SPEC-004, SPEC-005

---

## 1. Objetivo

Definir o conceito de **matching** do JobMarket, responsável por avaliar a compatibilidade entre um `CandidateProfile` e uma `Opportunity`.

O matching não deve representar apenas uma correspondência de palavras ou quantidade de skills em comum.

Ele deve avaliar a **compatibilidade contextual entre candidato e oportunidade**, considerando competências, experiência, localização, modalidade de trabalho, tipo de contratação, senioridade e objetivos/preferências profissionais.

O resultado deve ser:

- quantitativo, através de um score de compatibilidade;
- qualitativo, através de uma explicação estruturada;
- bidirecional, permitindo que candidatos encontrem oportunidades e empresas encontrem candidatos.

---

## 2. Princípios

O matching deve seguir os seguintes princípios:

### 2.1 Compatibilidade, não qualidade absoluta

O score representa a compatibilidade entre duas entidades específicas.

```text
Não significa:

"Candidato X é 87% bom."

Significa:

"Candidato X possui 87% de compatibilidade
com a oportunidade Y."
```

Um candidato pode ter excelente compatibilidade com uma oportunidade e baixa compatibilidade com outra.

---

### 2.2 Matching explicável

O sistema nunca deve apresentar apenas um percentual.

Todo resultado deve permitir identificar:

- o que combina;
- o que falta;
- o que diverge;
- quais fatores contribuíram para o resultado.

---

### 2.3 Matching bidirecional

O mesmo conceito de compatibilidade deve permitir:

```text
Candidato
    │
    └──▶ oportunidades compatíveis

Oportunidade
    │
    └──▶ candidatos compatíveis
```

A perspectiva de apresentação pode mudar, mas a avaliação deve permanecer baseada nos mesmos dados.

---

### 2.4 Evolução independente do algoritmo

O modelo de domínio não deve depender de uma tecnologia específica.

O matching poderá inicialmente utilizar regras determinísticas e pesos.

Futuramente poderá incorporar:

- embeddings;
- análise semântica;
- IA;
- histórico de comportamento;
- feedback de usuários.

Essas evoluções não devem exigir alteração do conceito fundamental de `MatchResult`.

---

# 3. Dimensões avaliadas

O matching avalia principalmente cinco dimensões:

```text
┌─────────────────────────────┐
│           MATCH             │
├─────────────────────────────┤
│ Skills                      │
│ Experiência / Senioridade   │
│ Localização                 │
│ Preferências                │
│ Objetivos profissionais     │
└─────────────────────────────┘
```

---

## 3.1 Skills

Compara as habilidades do candidato com os requisitos da oportunidade.

Considera:

- skills obrigatórias;
- skills desejáveis;
- nível mínimo exigido;
- nível declarado pelo candidato;
- experiência associada à skill, quando disponível.

Exemplo:

```text
Candidato:
Java
Spring Boot
PostgreSQL
Docker

Oportunidade:

Obrigatórias:
Java
Spring Boot

Desejáveis:
AWS
Docker
```

Resultado conceitual:

```text
✓ Java
✓ Spring Boot
✓ Docker

⚠ AWS não encontrada no perfil
```

---

## 3.2 Experiência e senioridade

Avalia a compatibilidade entre:

- senioridade do candidato;
- senioridade esperada pela oportunidade;
- experiência profissional;
- experiência mínima exigida;
- experiência relevante para os requisitos.

Exemplo:

```text
Candidato:
Senior

Oportunidade:
Senior

→ alta compatibilidade
```

Também devem ser possíveis situações como:

```text
Candidato:
Senior

Oportunidade:
Pleno

→ compatível, mas potencialmente abaixo
do interesse profissional do candidato.
```

Portanto, senioridade não deve ser tratada exclusivamente como filtro binário.

---

## 3.3 Localização

Avalia a compatibilidade geográfica entre candidato e oportunidade.

Considera:

- cidade;
- estado;
- país;
- localização da oportunidade;
- disponibilidade para mudança;
- modalidade de trabalho.

Exemplo:

```text
Candidato:
Curitiba
Não aceita mudança

Oportunidade:
São Paulo
Presencial

→ incompatibilidade forte
```

---

## 3.4 Preferências

Compara aquilo que o candidato procura com aquilo que a oportunidade oferece.

Pode considerar:

```text
Modelo de trabalho:
- remoto
- híbrido
- presencial

Tipo de contratação:
- CLT
- PJ
- freelancer
- temporário
- estágio

Localizações aceitas

Faixa salarial

Senioridade desejada
```

As preferências representam aquilo que o candidato **quer**, e não necessariamente aquilo que ele é capaz de fazer.

---

## 3.5 Objetivos profissionais

Avalia a aderência da oportunidade aos objetivos declarados pelo candidato.

Exemplo:

```text
Objetivo:
Backend Java

Oportunidade:
Backend Java + Spring Boot

→ alta aderência
```

Outro exemplo:

```text
Objetivo:
Especializar-se em Cloud

Oportunidade:
Backend + AWS + Kubernetes

→ alta aderência ao objetivo
```

Essa dimensão é importante porque uma oportunidade pode ser tecnicamente compatível com o candidato sem necessariamente ser uma boa oportunidade **para aquele momento profissional**.

---

# 4. Composição do score

Cada dimensão produz um subscore.

Conceitualmente:

```text
                    ┌── Skills
                    │
                    ├── Experiência / Senioridade
                    │
Compatibilidade ────┼── Localização
                    │
                    ├── Preferências
                    │
                    └── Objetivos profissionais
```

O score final é derivado da composição dessas dimensões.

Os pesos exatos **não são definidos nesta versão da especificação**.

Exemplo ilustrativo:

```text
Compatibilidade: 87%

Skills:                  95%
Experiência:             90%
Localização:            100%
Preferências:            80%
Objetivos profissionais: 85%
```

Os valores acima são apenas ilustrativos.

---

# 5. Requisitos obrigatórios e desejáveis

As oportunidades podem definir requisitos como:

```text
Obrigatórios
Desejáveis
```

### Obrigatórios

A ausência de um requisito obrigatório representa uma **incompatibilidade relevante**.

Entretanto, o sistema não deve necessariamente remover automaticamente o resultado.

Exemplo:

```text
Compatibilidade: 72%

✓ Java
✓ Spring Boot
✓ PostgreSQL
✓ Senioridade compatível

⚠ AWS é requisito obrigatório e
  não aparece no perfil.
```

A plataforma poderá posteriormente definir políticas de corte para determinados contextos.

---

### Desejáveis

A presença de uma skill desejável aumenta a compatibilidade.

A ausência não deve desqualificar o candidato.

```text
✓ Docker

⚠ AWS é desejável e não aparece no perfil.
```

---

# 6. Incompatibilidades

Algumas divergências possuem natureza diferente de uma simples skill ausente.

Exemplos:

```text
✕ Oportunidade presencial
  Candidato aceita somente remoto.

✕ Faixa salarial incompatível
  Expectativa do candidato acima
  da faixa informada.

✕ Localização incompatível
  Candidato não aceita mudança.
```

Essas situações devem ser representadas separadamente dos simples pontos de atenção.

---

# 7. Resultado do Matching

O resultado conceitual do matching é representado por:

```text
MatchResult
├── candidateId
├── opportunityId
├── score
├── status
├── dimensions[]
└── explanation
```

---

## 7.1 Status

O status representa uma classificação geral do resultado.

Sugestão inicial:

```text
STRONG_MATCH
    alta compatibilidade

GOOD_MATCH
    boa compatibilidade

POSSIBLE_MATCH
    compatibilidade parcial

LOW_MATCH
    baixa compatibilidade

INCOMPATIBLE
    existem incompatibilidades críticas
```

Os limites numéricos de cada classificação serão definidos posteriormente.

---

# 8. Dimensão do Matching

Cada dimensão pode ser representada conceitualmente por:

```text
MatchDimension
├── type
├── score
├── weight
├── matched[]
├── missing[]
└── conflicts[]
```

Exemplo:

```text
Skills
score: 91

matched:
- Java
- Spring Boot
- PostgreSQL

missing:
- AWS

conflicts:
[]
```

---

# 9. Explicação do Matching

Todo `MatchResult` deve possuir uma explicação estruturada.

```text
MatchExplanation
├── pontosFortes[]
├── pontosDeAtencao[]
└── incompatibilidades[]
```

---

## 9.1 Pontos fortes

Representam fatores que contribuíram positivamente.

Exemplo:

```text
✓ Java
✓ Spring Boot
✓ PostgreSQL
✓ Experiência compatível
✓ Trabalho remoto
```

---

## 9.2 Pontos de atenção

Representam divergências que não necessariamente impedem o match.

Exemplo:

```text
⚠ AWS não aparece no perfil.
⚠ Experiência com Kubernetes não informada.
```

---

## 9.3 Incompatibilidades

Representam conflitos relevantes.

Exemplo:

```text
✕ A oportunidade é presencial.
  O candidato aceita somente trabalho remoto.
```

---

# 10. Exemplo completo

Candidato:

```text
Senior Backend Developer

Skills:
Java
Spring Boot
PostgreSQL
Docker

Preferências:
Remoto
PJ
Backend
Senior
```

Oportunidade:

```text
Senior Backend Java

Obrigatórias:
Java
Spring Boot
PostgreSQL

Desejáveis:
AWS
Docker

Modelo:
Remoto

Contratação:
PJ

Senioridade:
Senior
```

Resultado:

```text
Compatibilidade: 94%

Status:
STRONG_MATCH
```

Dimensões:

```text
Skills:                  92%
Experiência:             96%
Localização:            100%
Preferências:            95%
Objetivos profissionais: 94%
```

Explicação:

```text
Pontos fortes:

✓ Java
✓ Spring Boot
✓ PostgreSQL
✓ Docker
✓ Senioridade compatível
✓ Trabalho remoto
✓ Contratação PJ compatível
✓ Objetivo profissional alinhado

Pontos de atenção:

⚠ AWS é desejável e não aparece no perfil.

Incompatibilidades:

Nenhuma.
```

---

# 11. Direção do Matching

O matching deve funcionar nos dois sentidos.

### 11.1 Candidato → Oportunidade

O candidato recebe oportunidades ordenadas por compatibilidade.

```text
Candidato
   │
   ▼
Oportunidades OPEN
   │
   ▼
Matching
   │
   ▼
Ranking de oportunidades
```

---

### 11.2 Oportunidade → Candidato

A empresa pode descobrir candidatos compatíveis.

```text
Oportunidade
   │
   ▼
Candidatos elegíveis
   │
   ▼
Matching
   │
   ▼
Ranking de candidatos
```

A empresa deve receber a mesma lógica explicável.

Exemplo:

```text
Candidato: João

Compatibilidade: 91%

✓ Java
✓ Spring Boot
✓ PostgreSQL
✓ Senioridade compatível
✓ Disponibilidade compatível

⚠ AWS não encontrada.
```

---

# 12. Matching contínuo

O matching não depende exclusivamente de uma busca manual.

Eventos relevantes podem provocar uma nova avaliação:

```text
Nova oportunidade publicada
        │
        ▼
Identificar candidatos afetados
        │
        ▼
Calcular compatibilidade
        │
        ▼
Atualizar recomendações
```

Também:

```text
Candidato atualiza perfil
        │
        ▼
Identificar oportunidades afetadas
        │
        ▼
Recalcular compatibilidade
        │
        ▼
Atualizar recomendações
```

Outros eventos poderão ser adicionados futuramente.

---

# 13. Regras de negócio

1. Somente oportunidades com status `OPEN` participam do matching ativo.

2. Candidatos precisam possuir perfil ao menos `BASICO` para participar do matching.

3. O score deve ser recalculado quando informações relevantes de qualquer um dos lados forem alteradas.

4. A explicação deve ser derivada dos mesmos fatores utilizados para produzir o score.

5. O sistema não deve gerar uma explicação genérica que não corresponda aos fatores reais do resultado.

6. Requisitos obrigatórios ausentes devem ser identificados explicitamente.

7. Requisitos desejáveis ausentes não devem, isoladamente, tornar o candidato incompatível.

8. Incompatibilidades de localização, modalidade ou contratação podem representar conflitos mais relevantes que a ausência de uma skill desejável.

9. O score representa compatibilidade entre duas entidades e não uma avaliação absoluta da qualidade do candidato ou da oportunidade.

10. O algoritmo deve ser independente da camada de apresentação.

11. A mesma estrutura conceitual deve permitir o matching candidato → oportunidade e oportunidade → candidato.

12. Alterações futuras no algoritmo não devem exigir mudanças no conceito de `CandidateProfile` ou `Opportunity`.

---

# 14. Pesos

Os pesos das dimensões serão inicialmente definidos pela plataforma.

Não haverá, no MVP, configuração livre de pesos por candidato ou empresa.

Exemplo conceitual:

```text
Skills                  → peso definido pela plataforma
Experiência             → peso definido pela plataforma
Localização             → peso definido pela plataforma
Preferências            → peso definido pela plataforma
Objetivos profissionais → peso definido pela plataforma
```

A possibilidade de pesos contextuais poderá ser avaliada posteriormente.

---

# 15. Versionamento do algoritmo

O algoritmo de matching deverá ser versionável.

Conceitualmente:

```text
Matching Algorithm
        │
        ├── v1
        ├── v2
        └── v3
```

Um resultado poderá posteriormente registrar a versão utilizada:

```text
MatchResult
├── score
├── status
├── algorithmVersion
└── explanation
```

Isso permitirá comparar resultados entre versões e evitar inconsistências históricas.

A estratégia técnica de versionamento será definida em uma especificação futura.

---

# 16. Evolução futura

O modelo poderá evoluir para incorporar:

### 16.1 Matching semântico

Utilização de embeddings para identificar relações que não dependem de correspondência literal.

Exemplo:

```text
Candidato:
"desenvolvimento de APIs REST"

Oportunidade:
"desenvolvimento de serviços backend"
```

---

### 16.2 IA

IA poderá auxiliar na interpretação de:

- descrições profissionais;
- requisitos;
- objetivos;
- experiências;
- competências implícitas.

A IA não deve substituir necessariamente as regras determinísticas, mas poderá complementar o mecanismo.

---

### 16.3 Feedback comportamental

Interações poderão fornecer sinais adicionais:

```text
visualizou oportunidade
salvou oportunidade
ignorou oportunidade
candidatou-se
empresa demonstrou interesse
empresa rejeitou
entrevista realizada
contratação realizada
```

Esses dados poderão posteriormente alimentar modelos de recomendação.

---

# 17. Fora de escopo

Esta especificação não define:

- implementação do algoritmo;
- linguagem ou framework;
- banco de dados;
- embeddings;
- modelo de IA;
- infraestrutura de processamento;
- persistência dos resultados;
- sistema de notificações;
- processo de candidatura;
- definição definitiva dos pesos;
- treinamento baseado em comportamento.

Esses aspectos serão definidos em especificações próprias.

---

# 18. Perguntas em aberto

As seguintes questões permanecem para evolução do domínio:

1. Quais serão os limites numéricos para `STRONG_MATCH`, `GOOD_MATCH`, `POSSIBLE_MATCH`, `LOW_MATCH` e `INCOMPATIBLE`?

2. Quais incompatibilidades devem ser consideradas críticas?

3. Faixa salarial incompatível deve impedir o match ou apenas reduzir o score?

4. Como tratar candidatos cuja senioridade seja superior à exigida pela oportunidade?

5. Como tratar oportunidades que aceitam múltiplas modalidades de trabalho?

6. Como considerar experiência parcialmente relacionada a uma skill?

7. Como incorporar feedback comportamental sem criar um sistema que penalize excessivamente usuários com pouca atividade?

8. Como avaliar a qualidade do algoritmo sem favorecer artificialmente determinados perfis?

9. Quando a IA/semântica for introduzida, qual parte do score continuará sendo determinística?

10. Como medir se o matching realmente produz melhores contratações, e não apenas maiores taxas de interação?

---

# 19. Critério de conclusão desta SPEC

A SPEC-006 será considerada conceitualmente concluída quando estiverem definidos:

- dimensões do matching;
- conceito de compatibilidade;
- requisitos obrigatórios e desejáveis;
- tratamento de incompatibilidades;
- estrutura conceitual de `MatchResult`;
- estrutura de explicação;
- matching bidirecional;
- matching contínuo;
- princípio de versionamento;
- separação entre regra de negócio e implementação.

A definição dos pesos e do algoritmo matemático detalhado poderá ocorrer em uma especificação técnica posterior.
