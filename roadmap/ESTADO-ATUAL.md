# Estado Atual do Projeto

Este documento registra o estado atual do projeto no repositório, em preparação para as tasks de desenvolvimento, de acordo com o levantamento inicial da TASK-000.

## 1. Estrutura atual do backend
O backend está inicializado como um projeto Spring Boot. A classe principal `JobMarketApplication.java` existe no diretório `src/main/java/com/jobmarket`, mas não existem subpacotes estruturados por domínio.

## 2. Build tool e versões
- **Build tool**: Maven (`pom.xml` e `mvnw` presentes).
- **Versão do Java**: 25 (Origem: `pom.xml`).
- **Versão do Spring Boot**: 4.1.0 (Origem: `pom.xml`).

## 3. Configuração Spring
- O arquivo `src/main/resources/application.properties` existe.
- O arquivo `application.yml` não existe.

## 4. Pacote base
- **Pacote base**: `com.jobmarket`.

## 5. Status do Flyway
O Flyway já está declarado nas dependências do `pom.xml` (`spring-boot-starter-flyway`) e habilitado no `application.properties`.

## 6. Status das migrations
O diretório `db/migration` não existe e não há migrations criadas.

## 7. Configuração do PostgreSQL existente
O banco PostgreSQL roda via `docker-compose.yml`:
- **Imagem**: `postgres:17`
- **Porta**: `5432`
- **Banco/Usuário/Senha**: definidos por variáveis de ambiente (`DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`).
- **Volumes**: Mapeamento para `postgres_data`.

## 8. Estrutura definida para o frontend
O frontend Angular ficará no diretório `frontend/` na raiz do repositório, que já existe mas está vazio (Angular ainda não inicializado).

## 9. Estado do `.gitignore`
O arquivo `.gitignore` já possuía `target/`. Foi atualizado para incluir `node_modules/` e `dist/`.

## 10. Status das tasks do roadmap afetadas
- **TASK-000**: Concluída (`✅`).
- **TASK-001**: O Spring Boot já está inicializado via Maven com as dependências básicas presentes, e o `docker-compose.yml` para banco de dados local também existe. Falta a estrutura de pacotes. Status atualizado para parcialmente satisfeito (`🟡`).
- **TASK-002**: Apenas o diretório `frontend/` foi criado, estrutura não existe (`🔲`).
- **TASK-004**: Testcontainers ainda não configurados (`🔲`).

## 11. Divergências encontradas
- **Versão do Java**: O projeto espera Java 24 segundo as especificações, mas o `pom.xml` declara `<java.version>25</java.version>`.
- **Build Tool**: A `TASK-001` no ROADMAP indica o uso do Gradle, bem como sua sintaxe (`./gradlew`), mas o projeto real já se estabeleceu em **Maven** (`pom.xml`).
- **Flyway**: O estado esperado indicava que o Flyway não deveria estar nas dependências do `pom.xml`, mas ele já está configurado.
