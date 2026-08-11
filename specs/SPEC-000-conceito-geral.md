# SPEC-000 — Conceito Geral do JobMarket

**Status:** Draft
**Versão:** 0.1
**Tipo:** Visão / Conceito do Produto

---

## 1. Visão

O **JobMarket** é uma plataforma de conexão profissional que busca ir além do modelo tradicional de sites de vagas.

Plataformas convencionais normalmente funcionam da seguinte forma:

```text
Empresa
   │
   ├── publica vaga
   │
   ▼
Candidato procura
   │
   ├── envia currículo
   │
   ▼
Empresa recebe candidaturas
```

Esse modelo coloca grande parte do trabalho nas mãos do candidato.

O JobMarket pretende inverter parcialmente essa lógica:

```text
             ┌─────────────────┐
             │    JobMarket     │
             └────────┬────────┘
                      │
          ┌───────────┴───────────┐
          │                       │
          ▼                       ▼
      Candidato              Empresa
          │                       │
          │                       │
          └───────────┬───────────┘
                      │
                      ▼
                  Matching
                      │
                      ▼
              Oportunidades
               relevantes
```

O objetivo não é simplesmente oferecer uma lista maior de vagas.

O objetivo é **melhorar a qualidade da conexão entre pessoas e oportunidades**.

---

# 2. Problema

O processo tradicional de contratação apresenta diversos problemas.

## Para candidatos

Um candidato frequentemente precisa:

- procurar vagas manualmente;
- interpretar centenas de descrições;
- descobrir se realmente possui aderência;
- adaptar currículo;
- enviar candidaturas;
- acompanhar processos;
- repetir o processo inúmeras vezes.

Além disso, quantidade de candidaturas não significa necessariamente qualidade.

Um candidato pode se candidatar a dezenas de vagas sem possuir uma boa compatibilidade com elas.

---

## Para empresas

As empresas também enfrentam problemas:

- grande quantidade de candidaturas;
- candidatos pouco aderentes;
- dificuldade para identificar bons profissionais;
- currículos pouco padronizados;
- excesso de triagem manual;
- dificuldade para encontrar profissionais com determinadas combinações de habilidades.

Assim, existe uma situação paradoxal:

```text
Muitos candidatos
        +
Muitas vagas
        =
Muitas conexões ruins
```

O problema não é necessariamente falta de oportunidades.

É a **qualidade do matching**.

---

# 3. Hipótese principal

O JobMarket parte da seguinte hipótese:

> Se candidatos e empresas fornecerem informações estruturadas suficientes sobre suas necessidades e características, será possível encontrar oportunidades significativamente mais relevantes do que através de uma simples busca por palavras-chave.

Isso significa que o sistema deve tentar compreender:

### Candidato

```text
Quem sou?
O que sei fazer?
O que quero fazer?
Onde posso trabalhar?
Como quero trabalhar?
O que estou procurando?
O que não quero?
```

### Empresa

```text
Quem somos?
O que precisamos?
Que habilidades são importantes?
Que experiência é necessária?
Como será o trabalho?
Que características são desejáveis?
```

A partir dessas informações:

```text
Candidato
    +
Oportunidade
    ↓
Compatibilidade
    ↓
Score + explicação
```

---

# 4. O conceito de Matching

O matching é o núcleo conceitual do JobMarket.

Uma oportunidade não deve ser avaliada apenas por:

```text
"Java"
"Spring"
"PostgreSQL"
```

O sistema deve considerar diferentes dimensões.

Exemplo conceitual:

```text
                  Candidato
                      │
        ┌─────────────┼─────────────┐
        │             │             │
        ▼             ▼             ▼
    Skills       Experiência    Preferências
        │             │             │
        └─────────────┼─────────────┘
                      │
                      ▼
                  Matching
                      ▲
                      │
        ┌─────────────┼─────────────┐
        │             │             │
        ▼             ▼             ▼
      Skills       Requisitos     Modelo
                   da vaga       de trabalho
```

O resultado poderá ser algo como:

```text
Compatibilidade: 87%

Skills:          95%
Experiência:     90%
Preferências:    80%
Localização:     100%
Modelo trabalho: 85%
```

O número em si não é suficiente.

O sistema deverá, idealmente, explicar **por que** determinada oportunidade é relevante.

---

# 5. Matching explicável

Uma característica importante do conceito é evitar uma "caixa preta".

Em vez de simplesmente:

```text
87% compatível
```

o sistema deverá buscar algo semelhante a:

```text
Por que esta oportunidade combina com você?

✓ Java
✓ Spring Boot
✓ PostgreSQL
✓ Experiência compatível
✓ Trabalho remoto
✓ Localização compatível

⚠ A vaga solicita experiência com AWS,
  que não aparece no seu perfil.
```

Isso permite que o usuário entenda o resultado.

---

# 6. Oportunidades em vez de apenas vagas

O conceito do JobMarket não precisa ficar limitado ao modelo tradicional de "vaga de emprego".

Uma oportunidade pode representar:

- emprego CLT;
- contrato PJ;
- freelancer;
- trabalho temporário;
- projeto;
- estágio;
- oportunidade remota;
- oportunidade híbrida;
- oportunidade presencial.

O modelo de dados deve permitir que o conceito evolua sem ficar preso inicialmente a uma única modalidade.

---

# 7. Perfil profissional

O candidato não deve ser representado apenas por um currículo PDF.

O perfil deve ser uma representação estruturada da pessoa.

Exemplo conceitual:

```text
Perfil
│
├── Informações básicas
│
├── Experiência
│
├── Skills
│
├── Projetos
│
├── Formação
│
├── Preferências
│
├── Disponibilidade
│
├── Localização
│
└── Objetivos profissionais
```

O currículo poderá continuar existindo, mas não deverá ser a única fonte de informação.

---

# 8. Perfil da empresa

Da mesma maneira, a empresa deve ser mais do que um nome associado a uma vaga.

O perfil empresarial poderá conter:

```text
Empresa
│
├── Informações
├── Segmento
├── Cultura
├── Localização
├── Modelo de trabalho
├── Tecnologias
├── Benefícios
├── Oportunidades
└── Informações institucionais
```

Isso permite que o candidato avalie também **a empresa**, e não apenas a vaga.

---

# 9. Preferências do candidato

Uma diferença importante em relação a plataformas tradicionais é permitir que o candidato informe explicitamente suas preferências.

Exemplos:

```text
Modelo:
    Remoto

Localização:
    Brasil

Contratação:
    PJ / CLT

Área:
    Desenvolvimento de software

Senioridade:
    Pleno / Sênior

Tecnologias:
    Java
    Spring Boot
    PostgreSQL

Disponibilidade:
    Imediata
```

Essas informações podem participar do matching.

---

# 10. Preferências da empresa

A empresa também deverá conseguir definir o que realmente importa.

Exemplo:

```text
Experiência mínima:
    3 anos

Tecnologias principais:
    Java
    Spring Boot
    PostgreSQL

Modelo:
    Remoto

Localização:
    Brasil

Senioridade:
    Pleno

Diferenciais:
    AWS
    Docker
```

Isso permite diferenciar:

### Obrigatório

```text
Java
Spring Boot
```

### Desejável

```text
AWS
Docker
```

Essa distinção poderá ser importante para o algoritmo de matching.

---

# 11. O candidato não deve depender apenas da busca

Um dos objetivos conceituais do projeto é reduzir a necessidade de pesquisa manual.

Em vez de:

```text
Candidato
   ↓
Busca
   ↓
Filtros
   ↓
Vagas
```

podemos evoluir para:

```text
Candidato
   ↓
Perfil
   ↓
Preferências
   ↓
Matching contínuo
   ↓
Oportunidades recomendadas
```

O sistema poderá apresentar oportunidades relevantes mesmo quando o candidato não estiver procurando ativamente.

---

# 12. Empresas também podem descobrir candidatos

A lógica pode funcionar nos dois sentidos.

### Modelo tradicional

```text
Candidato → Empresa
```

### JobMarket

```text
Candidato ←→ Empresa
```

Uma empresa poderá encontrar candidatos que possuem alta compatibilidade com uma oportunidade.

Isso cria um verdadeiro mercado de oportunidades.

---

# 13. Privacidade e controle

O candidato deve possuir controle sobre sua exposição.

O sistema deverá considerar diferentes níveis de visibilidade.

Exemplo conceitual:

```text
Perfil privado
    ↓
Somente minhas candidaturas

Perfil disponível
    ↓
Empresas podem encontrar meu perfil

Perfil público
    ↓
Informações profissionais visíveis
```

O usuário deverá conseguir controlar quais informações podem ser utilizadas no matching.

---

# 14. Anti-spam de candidaturas

Um possível problema do modelo tradicional é incentivar candidaturas indiscriminadas.

O JobMarket deverá buscar o comportamento contrário:

```text
100 candidaturas ruins
        ↓
        X

10 candidaturas altamente relevantes
        ↓
        ✓
```

A plataforma deve incentivar **qualidade em vez de quantidade**.

---

# 15. Feedback do processo

O matching não deve ser necessariamente estático.

O sistema poderá aprender com eventos como:

```text
Candidato demonstrou interesse
Candidato recusou
Empresa visualizou
Empresa demonstrou interesse
Entrevista realizada
Candidato contratado
Oportunidade encerrada
```

Esses eventos poderão futuramente contribuir para melhorar recomendações.

---

# 16. IA

IA poderá ser utilizada no projeto, mas não deve ser considerada o produto em si.

A IA poderá auxiliar em tarefas como:

- interpretação de descrições;
- normalização de skills;
- identificação de tecnologias;
- classificação de experiência;
- geração de embeddings;
- matching semântico;
- recomendação;
- explicação de compatibilidade.

A arquitetura deve permitir que essas funcionalidades sejam adicionadas gradualmente.

Não devemos começar assumindo que tudo precisa de IA.

---

# 17. Princípio tecnológico

O projeto deverá priorizar inicialmente:

```text
Simplicidade
    +
Arquitetura clara
    +
Dados estruturados
    +
Evolução incremental
```

Tecnologias adicionais somente devem ser introduzidas quando resolverem problemas reais.

A stack inicial:

```text
Angular
    │
    ▼
Spring Boot
    │
    ├── Spring Security
    ├── Spring Data JPA
    ├── Flyway
    │
    ▼
PostgreSQL
```

---

# 18. Domínios iniciais

Os principais conceitos previstos são:

```text
User
Candidate
Company
Profile
Skill
Experience
Opportunity
Application
Matching
```

A modelagem definitiva deverá ser feita posteriormente.

---

# 19. Evolução arquitetural

O projeto não deverá começar com uma arquitetura distribuída complexa.

Inicialmente:

```text
┌─────────────────────────┐
│        Angular          │
└────────────┬────────────┘
             │ HTTP
             ▼
┌─────────────────────────┐
│      Spring Boot        │
│                         │
│ Controllers             │
│ Services                │
│ Repositories            │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│       PostgreSQL        │
└─────────────────────────┘
```

A arquitetura poderá evoluir conforme surgirem necessidades reais.

---

# 20. O que o JobMarket não pretende ser

O projeto não deve simplesmente tentar ser:

- outro LinkedIn;
- outro Indeed;
- outro agregador de vagas;
- outro sistema de currículo;
- apenas uma interface bonita para listar oportunidades.

O diferencial deverá estar na **forma como candidatos e empresas são conectados**.

---

# 21. Visão de longo prazo

A visão de longo prazo pode ser resumida em:

```text
                JOBMARKET
                    │
        ┌───────────┴───────────┐
        │                       │
    CANDIDATOS              EMPRESAS
        │                       │
        ▼                       ▼
    Perfis                  Oportunidades
        │                       │
        └───────────┬───────────┘
                    │
                    ▼
                 MATCHING
                    │
          ┌─────────┴─────────┐
          │                   │
          ▼                   ▼
    Recomendações       Descoberta mútua
          │                   │
          └─────────┬─────────┘
                    ▼
              CONTRATAÇÕES
```

O objetivo final é transformar o processo de contratação de uma experiência baseada principalmente em **busca e candidatura** em uma experiência baseada em **descoberta, compatibilidade e conexão**.

---

# 22. Princípios do projeto

### 1. Matching antes de volume

Uma boa conexão vale mais que centenas de candidaturas.

### 2. Dados estruturados antes de complexidade

Primeiro devemos construir bons dados.

### 3. Explicabilidade

O usuário deve entender por que uma oportunidade foi recomendada.

### 4. Reciprocidade

Candidatos e empresas devem poder descobrir uns aos outros.

### 5. Privacidade

O usuário deve controlar sua exposição.

### 6. Evolução incremental

Nenhuma tecnologia deve ser adicionada apenas porque está na moda.

### 7. Produto antes da tecnologia

A arquitetura deve servir ao problema, e não o contrário.

---

# 23. Status da SPEC

Esta especificação representa a **visão inicial do produto**.

Ela não define ainda:

- modelo definitivo do banco;
- endpoints;
- regras finais de matching;
- algoritmo;
- arquitetura de produção;
- estratégia de monetização;
- funcionalidades obrigatórias;
- implementação de IA.

Essas decisões deverão ser documentadas em specifications posteriores.

---

## Próximas specifications

Sugestão de organização:

```text
SPEC-000  Conceito geral
SPEC-001  Modelo de usuários
SPEC-002  Perfil profissional
SPEC-003  Empresas
SPEC-004  Oportunidades
SPEC-005  Skills
SPEC-006  Matching
SPEC-007  Candidaturas
SPEC-008  Autenticação e autorização
SPEC-009  API
SPEC-010  Frontend
```

A `SPEC-000` deve permanecer relativamente estável e servir como referência para as demais especificações.
