# SPEC-007 — Candidaturas

**Status:** Draft
**Versão:** 0.2
**Tipo:** Modelo de Domínio / Fluxo de Processo
**Depende de:** SPEC-000, SPEC-002, SPEC-004, SPEC-006

---

## 1. Objetivo

Modelar o processo de **candidatura a uma oportunidade**, representando a relação formal entre um candidato e uma oportunidade.

A candidatura é o ponto em que o candidato deixa de ser apenas uma recomendação do sistema e passa a participar efetivamente do processo seletivo.

A SPEC também define o histórico de eventos associados à candidatura, permitindo rastrear sua evolução ao longo do processo.

O modelo segue o princípio de **qualidade em vez de quantidade**, estabelecido na SPEC-000, evitando incentivar candidaturas indiscriminadas e pouco aderentes.

---

## 2. Escopo

Esta spec cobre:

- estrutura da entidade `Application`;
- relação entre candidato e oportunidade;
- origem da candidatura;
- snapshot do matching no momento da candidatura;
- ciclo de vida da candidatura;
- eventos do processo seletivo;
- regras básicas de candidatura;
- mecanismos conceituais de redução de spam.

Não cobre:

- algoritmo de matching (SPEC-006);
- implementação de autenticação/autorização;
- comunicação ou chat entre candidato e empresa;
- agendamento de entrevistas;
- avaliação estruturada de candidatos;
- contratação trabalhista propriamente dita.

---

# 3. Modelo conceitual

```text
CandidateProfile
       │
       │ 1
       │
       ▼
   Application
       │
       │ N
       ▼
  Opportunity
```

Um candidato pode possuir várias candidaturas.

Uma oportunidade pode receber várias candidaturas.

```text
CandidateProfile (1) ──── (N) Application (N) ──── (1) Opportunity
```

---

## 3.1 Application

```text
Application
│
├── id
├── candidate
├── opportunity
│
├── origem
│
├── matchScore
├── matchAlgorithmVersion
│
├── status
│
├── dataCriacao
├── dataAtualizacao
└── eventos[]
```

### Origem

A origem representa como a candidatura foi iniciada.

```text
DIRECT
    → candidato encontrou a oportunidade e se candidatou

RECOMMENDED
    → candidatura originada a partir de uma recomendação do matching

INVITED
    → empresa convidou o candidato
```

A origem não altera a natureza da candidatura. Todas passam a seguir o mesmo ciclo de vida.

---

# 4. Snapshot do Matching

No momento em que uma candidatura é criada, o sistema deve preservar o resultado do matching utilizado naquele momento.

```text
Application
├── matchScore
└── matchAlgorithmVersion
```

Exemplo:

```text
matchScore = 87
matchAlgorithmVersion = "v1"
```

O score não deve ser recalculado retroativamente para alterar o histórico da candidatura.

Isso permite que o sistema responda:

> "Qual era a compatibilidade do candidato com esta oportunidade quando ele se candidatou?"

Mesmo que posteriormente:

- o candidato altere suas skills;
- a empresa altere os requisitos;
- os pesos do algoritmo sejam modificados;
- uma nova versão do algoritmo seja publicada.

---

## 4.1 Explicação do matching

Quando aplicável, a candidatura também pode preservar a explicação apresentada ao candidato no momento da candidatura.

```text
ApplicationMatchSnapshot
├── score
├── algorithmVersion
├── pontosFortes[]
└── pontosDeAtencao[]
```

Exemplo:

```text
Score: 87%

Pontos fortes:
✓ Java
✓ Spring Boot
✓ PostgreSQL
✓ Experiência compatível

Pontos de atenção:
⚠ AWS não aparece no perfil
```

Isso garante consistência entre aquilo que o sistema apresentou ao candidato e o histórico posterior da candidatura.

---

# 5. Ciclo de vida

A candidatura possui um status que representa seu estado atual.

```text
APPLIED
    │
    ▼
VIEWED
    │
    ▼
IN_PROCESS
    │
    ├──────────────▶ REJECTED
    │
    ├──────────────▶ WITHDRAWN
    │
    └──────────────▶ HIRED
```

## 5.1 Status

### `APPLIED`

Candidatura enviada pelo candidato ou criada a partir de um convite da empresa.

### `VIEWED`

A empresa visualizou a candidatura ou o perfil associado.

### `IN_PROCESS`

A empresa demonstrou interesse e o candidato está participando do processo seletivo.

Entrevistas, testes e outras etapas podem ocorrer nesse estado.

### `REJECTED`

A empresa encerrou a participação do candidato no processo.

### `WITHDRAWN`

O candidato retirou sua candidatura.

### `HIRED`

O candidato foi selecionado para aquela oportunidade.

---

# 6. Eventos da candidatura

O `status` representa o estado atual.

Os eventos representam o **histórico de tudo que aconteceu**.

```text
ApplicationEvent
│
├── id
├── application
├── tipo
├── dataEvento
├── actor
└── observacao
```

---

## 6.1 Tipos de eventos

Eventos iniciais previstos:

```text
APPLICATION_CREATED
    → candidatura criada

APPLICATION_VIEWED
    → empresa visualizou a candidatura

COMPANY_INTERESTED
    → empresa demonstrou interesse

CANDIDATE_WITHDREW
    → candidato retirou a candidatura

COMPANY_REJECTED
    → empresa recusou o candidato

INTERVIEW_SCHEDULED
    → entrevista agendada

INTERVIEW_COMPLETED
    → entrevista realizada

OPPORTUNITY_CLOSED
    → oportunidade foi encerrada

CANDIDATE_HIRED
    → candidato foi selecionado

APPLICATION_REOPENED
    → candidatura voltou a um processo ativo, caso essa funcionalidade seja suportada
```

A lista pode evoluir sem alterar necessariamente o modelo principal da candidatura.

---

# 7. Actor do evento

Sempre que possível, o sistema deve registrar quem originou o evento.

```text
Actor
├── CANDIDATE
├── COMPANY
└── SYSTEM
```

Exemplo:

```text
APPLICATION_VIEWED
actor = COMPANY
```

```text
APPLICATION_CREATED
actor = CANDIDATE
```

```text
OPPORTUNITY_CLOSED
actor = COMPANY
```

```text
APPLICATION_REJECTED_DUE_TO_CLOSURE
actor = SYSTEM
```

O registro do ator é importante para auditoria e para futuras funcionalidades de análise do processo seletivo.

---

# 8. Regras de candidatura

## 8.1 Candidatura duplicada

Um candidato não pode possuir duas candidaturas ativas para a mesma oportunidade.

```text
Candidate + Opportunity
        │
        ▼
   uma candidatura
```

Uma nova candidatura só poderá ocorrer caso as regras da oportunidade permitam uma nova tentativa após uma candidatura anterior encerrada.

Essa decisão será definida em versão futura.

---

## 8.2 Oportunidade aberta

Por padrão:

```text
Opportunity.status = OPEN
```

é condição necessária para uma candidatura iniciada pelo candidato.

Candidaturas não devem ser criadas para oportunidades `CLOSED`.

---

## 8.3 Candidato elegível

Somente candidatos com perfil suficientemente preenchido podem realizar candidaturas normais.

O candidato deve possuir, no mínimo:

```text
CandidateProfile
├── skills
├── localização
└── disponibilidade
```

A definição exata de completude permanece na SPEC-002.

---

## 8.4 Score baixo

Um score baixo não deve necessariamente impedir uma candidatura.

O sistema deve priorizar orientação e transparência.

Exemplo:

```text
Compatibilidade: 42%

⚠ Esta oportunidade possui baixa compatibilidade
com seu perfil.

Você ainda pode se candidatar.
```

A aplicação de um bloqueio obrigatório por score mínimo deverá ser uma decisão futura baseada em dados reais de utilização.

---

# 9. Anti-spam

O sistema deve desencorajar candidaturas em massa sem transformar o matching em uma barreira artificial.

Mecanismos possíveis:

```text
                    ┌────────────────────┐
                    │ Candidatura        │
                    └─────────┬──────────┘
                              │
                              ▼
                     Avaliação de contexto
                              │
             ┌────────────────┼────────────────┐
             ▼                ▼                ▼
         Match baixo    Muitas recentes    Match adequado
             │                │                │
             ▼                ▼                ▼
          Aviso          Limitação        Fluxo normal
```

Possíveis mecanismos:

- aviso de baixa compatibilidade;
- limitação de candidaturas simultâneas;
- priorização das candidaturas mais aderentes;
- indicação de oportunidades com maior compatibilidade;
- prevenção de múltiplas candidaturas repetitivas.

Esses mecanismos devem ser configuráveis e não devem prejudicar candidatos que possuem perfis legítimos e diversificados.

---

# 10. Candidatura espontânea

Uma candidatura pode ser iniciada diretamente pelo candidato:

```text
Candidate
    │
    ▼
Busca oportunidade
    │
    ▼
Visualiza oportunidade
    │
    ▼
Candidata-se
    │
    ▼
Application
```

Nesse caso:

```text
origem = DIRECT
```

---

# 11. Candidatura recomendada

O sistema pode recomendar uma oportunidade ao candidato através do matching.

```text
Candidate
    │
    ▼
Matching
    │
    ▼
Opportunity
    │
    ▼
Recommendation
    │
    ▼
Candidate applies
    │
    ▼
Application
```

Nesse caso:

```text
origem = RECOMMENDED
```

O sistema deve preservar o snapshot do matching utilizado na recomendação/candidatura.

---

# 12. Convite da empresa

Uma empresa pode demonstrar interesse em um candidato recomendado pelo sistema.

Fluxo conceitual:

```text
Company
   │
   ▼
Matching
   │
   ▼
Candidate
   │
   ▼
Company invites
   │
   ▼
Application
```

Nesse caso:

```text
origem = INVITED
```

O candidato deve poder aceitar ou recusar o convite.

A necessidade de um estado específico para convite antes da criação formal de `Application` poderá ser avaliada posteriormente.

---

# 13. Fechamento da oportunidade

Quando uma oportunidade é encerrada:

```text
Opportunity
     │
     ▼
CLOSED
```

as candidaturas associadas que ainda estiverem em processo devem ser tratadas conforme a política definida para encerramento.

Conceitualmente:

```text
OPEN Opportunity
       │
       ▼
    CLOSED
       │
       ▼
Candidaturas ativas
       │
       ├── encerradas automaticamente
       │
       └── mantidas para histórico
```

A decisão final sobre o comportamento exato será definida em uma spec de processo seletivo/moderação futura.

O evento:

```text
OPPORTUNITY_CLOSED
```

deve ser registrado nas candidaturas afetadas quando aplicável.

---

# 14. Histórico e auditoria

A candidatura deve manter histórico suficiente para reconstruir sua evolução.

Exemplo:

```text
Application
│
├── 11/08 10:30
│     APPLICATION_CREATED
│
├── 11/08 14:20
│     APPLICATION_VIEWED
│
├── 12/08 09:00
│     COMPANY_INTERESTED
│
├── 13/08 15:00
│     INTERVIEW_SCHEDULED
│
├── 15/08 16:00
│     INTERVIEW_COMPLETED
│
└── 20/08 11:30
      CANDIDATE_HIRED
```

O histórico deve ser imutável após o registro, salvo mecanismos administrativos específicos de correção/auditoria.

---

# 15. Regras de negócio consolidadas

1. Um candidato pode possuir várias candidaturas.
2. Uma oportunidade pode receber várias candidaturas.
3. Um candidato não pode possuir mais de uma candidatura ativa para a mesma oportunidade.
4. Candidaturas normais só podem ser realizadas para oportunidades `OPEN`.
5. O perfil do candidato deve atender ao nível mínimo de completude definido na SPEC-002.
6. O score de matching é congelado no momento da candidatura.
7. A versão do algoritmo utilizada no cálculo deve ser preservada.
8. A explicação do matching apresentada ao candidato pode ser preservada como snapshot.
9. Uma candidatura pode ser originada diretamente pelo candidato, por recomendação ou por convite da empresa.
10. O status atual da candidatura não substitui seu histórico de eventos.
11. Eventos relevantes devem ser registrados para permitir auditoria.
12. Score baixo deve gerar orientação antes de qualquer bloqueio automático.
13. Uma candidatura encerrada não deve desaparecer do histórico do candidato ou da empresa.
14. O encerramento da oportunidade deve ser refletido nas candidaturas ainda ativas, conforme a política definida para o processo seletivo.
15. `HIRED` representa a contratação referente àquela oportunidade específica.

---

# 16. Fora de escopo desta spec

- Algoritmo de matching — SPEC-006.
- Estrutura detalhada de CandidateProfile — SPEC-002.
- Estrutura detalhada de Opportunity — SPEC-004.
- Autenticação e autorização.
- Chat entre candidato e empresa.
- Agendamento detalhado de entrevistas.
- Testes técnicos.
- Avaliações de candidatos.
- Processo trabalhista de contratação.
- Folha de pagamento.
- Integrações com sistemas externos.
- Aprendizado automático baseado nos resultados das candidaturas.

---

# 17. Evoluções futuras

Possíveis evoluções:

```text
Application
    │
    ├── etapas personalizadas
    │
    ├── entrevistas
    │
    ├── testes técnicos
    │
    ├── avaliações
    │
    ├── feedback estruturado
    │
    ├── comunicação
    │
    └── contratação
```

Os dados históricos das candidaturas também poderão alimentar futuramente mecanismos de melhoria do matching:

```text
Matching
    │
    ▼
Recommendation
    │
    ▼
Application
    │
    ▼
Processo seletivo
    │
    ▼
Resultado
    │
    ▼
Feedback
    │
    ▼
Melhoria do Matching
```

Esse mecanismo não faz parte da implementação inicial.

---

# 18. Perguntas em aberto

1. Uma candidatura `WITHDRAWN` poderá ser recriada para a mesma oportunidade?
2. Uma candidatura `REJECTED` poderá ser reaberta pela empresa?
3. O convite da empresa deve criar imediatamente uma `Application` ou possuir uma entidade própria de `Invitation`?
4. Quantas candidaturas simultâneas em `IN_PROCESS` um candidato poderá manter?
5. Como o encerramento de uma oportunidade afetará candidaturas que estão em processo?
6. Haverá etapas configuráveis por empresa para o processo seletivo?
7. O score mínimo deverá gerar apenas um aviso ou poderá bloquear determinados tipos de candidatura?
8. A explicação completa do matching deverá ser armazenada na candidatura ou poderá ser reconstruída usando a versão histórica do algoritmo?
9. Quais eventos serão visíveis ao candidato e quais serão exclusivamente internos da empresa?
10. Empresas poderão reabrir oportunidades encerradas e, nesse caso, as candidaturas anteriores continuarão válidas?
