Você tem razão. Eu quebrei o Markdown em blocos por causa da formatação interna. Para esse caso, o correto é entregar **um único conteúdo Markdown**, pronto para copiar e colar.

# TASK-000 — Levantamento do estado atual do projeto

**Fase:** 0 — Fundação
**Status:** 🔲 Não iniciada
**Specs relacionadas:** SPEC-000 §17, SPEC-009
**Depende de:** Nenhuma

---

## Objetivo

Antes de qualquer implementação, mapear exatamente o que já existe no repositório e confrontar o estado encontrado com o roadmap, evitando que tasks futuras recriem componentes existentes ou entrem em conflito com a configuração atual.

O projeto já possui:

* Backend Spring Boot configurado;
* Maven como build tool;
* PostgreSQL executando via Docker.

Esta é uma task de **diagnóstico e documentação**, com alterações restritas aos arquivos explicitamente autorizados nesta task.

**Nenhuma alteração de código de produção deve ser feita.**

---

## Escopo

### 1. Backend

Confirmar formalmente:

* Build tool: **Maven**.
* Existência de `pom.xml`.
* Existência de `mvnw`.
* Configuração Spring em `application.properties`.
* Verificar se `application.yml` existe.
* Pacote base: `com.jobmarket`.
* Versão exata do Java declarada no `pom.xml`.
* Versão exata do Spring Boot.
* Verificar se a versão do Java corresponde ao esperado: **Java 24**.
* Registrar qualquer divergência encontrada.

**Não alterar `pom.xml` nesta task.**

### 2. Flyway e migrations

Verificar:

* Se Flyway já está presente nas dependências do `pom.xml`.
* Se existe o diretório `db/migration`.
* Se existem migrations dentro dele.
* Listar as migrations existentes, caso existam.

Estado esperado:

* Flyway **não está** nas dependências do `pom.xml`.
* `db/migration` está vazio.
* Nenhuma migration Flyway foi criada.

Se o estado real for diferente, **registrar a divergência sem corrigi-la**.

Não:

* adicionar Flyway;
* remover Flyway;
* criar migrations;
* alterar migrations existentes.

### 3. PostgreSQL / Docker

Confirmar como o PostgreSQL existente está sendo executado.

Inspecionar o `docker-compose.yml` existente e registrar:

* serviço/container do PostgreSQL;
* imagem utilizada;
* porta;
* nome do banco;
* usuário;
* configuração relevante de credenciais;
* volumes relevantes, se existirem.

Não:

* criar outro `docker-compose.yml`;
* substituir o `docker-compose.yml` existente;
* iniciar ou reiniciar containers sem necessidade;
* alterar configuração do PostgreSQL.

O PostgreSQL existente deve ser considerado a infraestrutura oficial do projeto para as próximas tasks.

### 4. Frontend

Documentar a decisão arquitetural já estabelecida:

`frontend/`

O frontend Angular será criado na raiz do repositório, separado do backend.

Não implementar o frontend nesta task.

Não criar estrutura Angular nesta task.

### 5. `.gitignore`

Verificar o `.gitignore` existente na raiz.

Criar ou ajustar o `.gitignore` somente para garantir cobertura dos artefatos necessários:

* `target/`
* `node_modules/`
* `dist/`

Preservar todas as regras existentes que continuem válidas.

Não substituir o `.gitignore` inteiro sem necessidade.

O objetivo é possuir um `.gitignore` combinado para backend Maven e frontend Angular.

### 6. ROADMAP

Comparar o estado real do repositório com as tasks existentes no `ROADMAP.md`.

Atualizar o status somente das tasks cujo escopo já esteja, total ou parcialmente, satisfeito pelo que já existe.

Candidatas mais prováveis:

* `TASK-001`
* `TASK-002`
* `TASK-004`

Regras:

* Não marcar uma task como concluída apenas porque parte do escopo existe.
* Usar `✅` quando todo o escopo aplicável já estiver satisfeito.
* Usar `🟡` quando somente parte do escopo estiver satisfeita.
* Quando usar `🟡`, registrar objetivamente o que já existe e o que falta.
* Não alterar a ordem das tasks.
* Não alterar dependências sem necessidade.
* Não iniciar nenhuma dessas tasks.

### 7. Documento de estado atual

Criar:

`roadmap/ESTADO-ATUAL.md`

O documento deve registrar objetivamente:

1. Estrutura atual do backend.
2. Build tool e versões relevantes.
3. Configuração Spring encontrada.
4. Pacote base.
5. Status do Flyway.
6. Status do diretório `db/migration`.
7. Configuração do PostgreSQL existente.
8. Estrutura planejada para o frontend.
9. Estado do `.gitignore`.
10. Status das tasks do roadmap afetadas pelo estado atual.
11. Divergências encontradas entre:

    * repositório;
    * specs;
    * roadmap.

O documento deve registrar **fatos observados**, não suposições.

---

## Diretivas para o Agente

### 1. Modo de execução

Esta task deve ser executada em modo **read-first**:

1. Inspecionar.
2. Comparar com o escopo.
3. Documentar.
4. Fazer somente as alterações autorizadas.
5. Validar.
6. Parar.

Não implementar funcionalidades.

Não iniciar outras tasks.

Não antecipar trabalho de outras specs.

Não refatorar código existente.

Não corrigir problemas encontrados, exceto os explicitamente previstos nesta task.

Não instalar dependências.

Não executar builds, testes ou comandos pesados sem necessidade para confirmar um fato que possa ser obtido diretamente pela inspeção dos arquivos.

Não iniciar ou reiniciar containers.

### 2. Exploração mínima do repositório

A investigação deve começar pela raiz do projeto.

Inspecionar primeiro somente os seguintes itens:

* `pom.xml`
* `mvnw`
* `application.properties`
* `application.yml`
* `docker-compose.yml`
* `.gitignore`
* `ROADMAP.md`
* `roadmap/`
* `src/`
* `db/migration/`

Os caminhos podem estar em seus respectivos diretórios, conforme a estrutura real do projeto.

Não fazer varredura recursiva indiscriminada em:

* `.git/`
* `target/`
* `node_modules/`
* `dist/`

Ao utilizar `find`, excluir diretórios de dependências e artefatos.

Não abrir arquivos cujo conteúdo não seja relevante para os critérios desta task.

Se uma informação já estiver confirmada por um arquivo, não procurar a mesma informação em outros arquivos sem motivo.

### 3. Regra de não exploração

Não procurar informações hipotéticas.

A investigação deve ser guiada exclusivamente pelos critérios de aceite e pelo escopo desta task.

Se uma informação não for necessária para determinar o estado de uma condição desta task, **não investigá-la**.

O objetivo não é compreender todo o projeto.

O objetivo é determinar o estado necessário para executar corretamente as próximas tasks.

### 4. Comandos

Preferir comandos de inspeção simples e de baixo volume, como:

* `pwd`
* `ls`
* `find`
* `grep`
* `cat`
* `head`
* `sed`
* `git status`
* `git diff`

Preferir filtros em vez de imprimir arquivos completos quando apenas uma informação específica for necessária.

Exemplos:

* procurar a versão do Java em vez de imprimir todo o `pom.xml`;
* procurar a versão do Spring Boot em vez de imprimir todo o arquivo;
* listar migrations em vez de abrir arquivos inexistentes;
* verificar existência de `application.yml` sem ler seu conteúdo se ele não existir.

Evitar comandos que produzam grandes volumes de saída.

Não repetir comandos quando a informação já tiver sido obtida.

### 5. Alterações permitidas

As únicas alterações permitidas nesta task são:

1. Criar ou atualizar `roadmap/ESTADO-ATUAL.md`.
2. Atualizar `ROADMAP.md`.
3. Criar ou ajustar `.gitignore`.

Qualquer outra alteração é proibida.

### 6. Proteção contra alterações acidentais

Nunca:

* sobrescrever `pom.xml`;
* sobrescrever `application.properties`;
* sobrescrever `application.yml`;
* sobrescrever `docker-compose.yml`;
* criar um novo `docker-compose.yml`;
* adicionar Flyway ao `pom.xml`;
* remover Flyway caso ele já exista;
* criar migrations;
* alterar migrations existentes;
* alterar código em `src/`;
* criar o projeto Angular;
* instalar dependências npm;
* alterar configurações de produção.

Se uma alteração aparentemente necessária estiver fora do escopo, **registrar a divergência em `ESTADO-ATUAL.md` em vez de corrigi-la**.

### 7. Decisões já estabelecidas

As seguintes decisões já estão tomadas e não devem ser questionadas nem substituídas por alternativas:

* Build tool: **Maven**.
* Configuração Spring: **`application.properties`**.
* Pacote base: **`com.jobmarket`**.
* Java esperado: **24**.
* Frontend: **Angular**.
* Diretório do frontend: **`frontend/`** na raiz.
* PostgreSQL existente via Docker deve ser reutilizado.
* Não criar outro ambiente PostgreSQL nesta task.
* Flyway não deve ser instalado nesta task.
* Migrations não devem ser criadas nesta task.

Se o estado real do projeto divergir dessas decisões, registrar a divergência.

**Não corrigir a divergência automaticamente.**

### 8. ROADMAP

Ao atualizar o `ROADMAP.md`:

* alterar somente o que for necessário para refletir o estado real;
* preservar o restante do documento;
* não reescrever tasks inteiras;
* não modificar descrições sem necessidade;
* não modificar dependências sem necessidade;
* não criar novas tasks.

Status:

* `✅` = escopo completamente satisfeito;
* `🟡` = escopo parcialmente satisfeito;
* `🔲` = escopo ainda não satisfeito.

Uma task só pode receber `✅` quando todo o escopo relevante estiver efetivamente satisfeito.

### 9. Documentação

`roadmap/ESTADO-ATUAL.md` deve ser curto, objetivo e factual.

Não incluir:

* tutorial de Spring Boot;
* tutorial de Maven;
* tutorial de Docker;
* explicações genéricas sobre PostgreSQL;
* sugestões arquiteturais;
* melhorias futuras;
* alternativas tecnológicas;
* decisões que ainda não foram tomadas;
* conteúdo duplicado desnecessariamente do `ROADMAP.md`;
* logs extensos;
* saída integral de comandos.

Registrar apenas:

* estado encontrado;
* evidência relevante;
* divergências;
* alterações realizadas nesta task.

### 10. Evidência

Sempre que possível, associar uma constatação ao arquivo que a comprova.

Exemplo:

`Java: 24 — Origem: pom.xml`

ou:

`PostgreSQL: executado pelo serviço postgres — Origem: docker-compose.yml`

Não inferir configurações que não estejam presentes no repositório.

Se uma informação não puder ser confirmada, registrar:

`Não identificado no repositório.`

em vez de assumir um valor.

### 11. Critério de parada

Assim que todas as informações necessárias para os critérios de aceite estiverem determinadas:

1. parar a exploração;
2. criar/atualizar os arquivos permitidos;
3. revisar as alterações;
4. executar `git diff`;
5. confirmar que nenhuma alteração fora do escopo foi realizada;
6. encerrar a task.

Não continuar investigando o projeto após os critérios de aceite estarem satisfeitos.

### 12. Economia de tokens

O agente deve minimizar deliberadamente o consumo de tokens.

Regras:

* Não narrar cada comando executado.
* Não explicar comandos óbvios.
* Não repetir informações já descobertas.
* Não reproduzir arquivos completos quando apenas alguns valores são necessários.
* Não produzir análises especulativas.
* Não produzir resumos intermediários longos.
* Não perguntar ao usuário algo que possa ser determinado diretamente pelo repositório.
* Não apresentar alternativas arquiteturais não solicitadas.
* Não pesquisar documentação externa.
* Não usar web ou fontes externas para descobrir informações que devem ser obtidas do próprio repositório.
* Preferir uma única inspeção que responda várias perguntas relacionadas.
* Encerrar assim que os critérios forem satisfeitos.

**Princípio:** gastar tokens para descobrir fatos necessários, não para descrever o processo de descoberta.

---

## Critérios de aceite

* [ ] `roadmap/ESTADO-ATUAL.md` criado, contendo:

  * [ ] estrutura atual do backend;
  * [ ] build tool;
  * [ ] versão do Java;
  * [ ] versão do Spring Boot;
  * [ ] configuração Spring;
  * [ ] pacote base;
  * [ ] status do Flyway;
  * [ ] status das migrations;
  * [ ] configuração do PostgreSQL existente;
  * [ ] estrutura definida para o frontend;
  * [ ] estado do `.gitignore`;
  * [ ] divergências encontradas entre repositório, specs e roadmap.

* [ ] `ROADMAP.md` atualizado refletindo o estado real das tasks afetadas, especialmente:

  * [ ] `TASK-001`;
  * [ ] `TASK-002`;
  * [ ] `TASK-004`.

* [ ] `.gitignore` cobre os artefatos necessários:

  * [ ] `target/`;
  * [ ] `node_modules/`;
  * [ ] `dist/`.

* [ ] Nenhum arquivo de código de produção foi alterado.

* [ ] Nenhuma migration foi criada.

* [ ] Flyway não foi adicionado ao `pom.xml`.

* [ ] Nenhum novo `docker-compose.yml` foi criado.

* [ ] Nenhuma configuração existente foi sobrescrita ou recriada desnecessariamente.

* [ ] `git diff` revisado após as alterações.

---

## Testes obrigatórios

Não aplicável.

Esta é uma task de diagnóstico e documentação, sem alteração de código de produção.

Não executar build ou suíte de testes apenas por formalidade.

---

## Definition of Done

* [ ] `roadmap/ESTADO-ATUAL.md` criado e revisado.
* [ ] `ROADMAP.md` atualizado com o estado real.
* [ ] `.gitignore` ajustado somente se necessário.
* [ ] Todas as divergências relevantes documentadas.
* [ ] Nenhuma task foi iniciada em paralelo.
* [ ] Nenhuma alteração de código de produção realizada.
* [ ] `git diff` revisado.
* [ ] Critérios de aceite satisfeitos.
* [ ] Task encerrada.

Após concluir esta task, **não iniciar automaticamente a próxima task**. A próxima task somente deve ser executada mediante instrução explícita.
