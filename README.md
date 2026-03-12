# API

> Implementacao da camada de persistencia utilizando Spring Data JPA.

Esta branch demonstra a aplicacao da arquitetura base utilizando:

- Spring Boot (Web + Data JPA)
- Spring Data JPA, Jakarta Persistence, MySQL Connector/J
- MySQL (configurado por `application.properties` + profile `dev`)

---

## Objetivo desta Implementacao

Esta versao resolve a exposicao do dominio de formas geometricas como API REST,
com operacoes CRUD e endpoints de calculo (area/perimetro).

A tecnologia foi escolhida para acelerar produtividade com repositorios JPA,
organizacao em camadas e padronizacao de tratamento de erros HTTP.

O foco arquitetural e separar claramente interface HTTP, regras de aplicacao
e acesso a dados com Spring Boot.

---

## Estrutura de Camadas

Entities  
Services  
DAO / Repository  
Controller / Resource

Aplicacao nesta versao:

- `src/main/java/com/lunarvoid/entities`: entidades de dominio (`Forma`, `Circulo`, `Quadrado`, `Retangulo`)
- `src/main/java/com/lunarvoid/repositories`: repositorios JPA por entidade
- `src/main/java/com/lunarvoid/services`: servicos com regras de aplicacao e operacoes CRUD
- `src/main/java/com/lunarvoid/resources`: endpoints REST (`/circulos`, `/quadrados`, `/retangulos`, `/formas`)
- `src/main/java/com/lunarvoid/resources/exceptions`: handler global de excecoes e payload padrao de erro

---

## Modelagem de Dominio

O dominio base permanece o mesmo:

- Forma (classe abstrata)
- Circulo
- Quadrado
- Retangulo
- TipoFormas (enum)

Adaptacoes para persistencia:

- `Forma` esta como `@MappedSuperclass`, compartilhando `id` e `tipo`
- Subclasses sao entidades JPA com tabelas proprias (`tb_circulo`, `tb_quadrado`, `tb_retangulo`)
- O enum `TipoFormas` e armazenado pelo codigo numerico em `Forma`

---

## Estrategia de Persistencia

- Persistencia via `JpaRepository` (`save`, `findAll`, `findById`, `deleteById`)
- Mapeamento objeto-relacional feito com anotacoes JPA nas entidades
- Conexao e propriedades gerenciadas pelo Spring Boot (`application.properties` e profile ativo)
- Uso de ORM (Hibernate via Spring Data JPA), sem SQL manual na camada de aplicacao

---

## Principais Caracteristicas Tecnicas

- [ ] Controle manual de conexao
- [x] Uso de ORM
- [x] Injecao de dependencia
- [x] Transacoes automaticas
- [ ] Queries customizadas
- [x] Separacao clara entre dominio e infraestrutura

---

## Vantagens desta Abordagem

- Alta produtividade para evolucao de endpoints e persistencia
- Menos codigo repetitivo com repositorios Spring Data
- Tratamento centralizado de erros da API
- Estrutura pronta para crescimento em modulos REST

---

## Limitacoes ou Desafios

- Dependencia maior de framework e convencoes Spring
- Necessidade de configurar corretamente ambiente e profile de banco
- Comportamentos de ORM (lazy loading, flush, etc.) exigem atencao em cenarios avancados
- `application.properties` ativa profile `dev`, mas o arquivo `application-dev.properties` precisa existir e estar consistente no projeto

---

## Como Executar

### 1. Clonar o repositorio e acessar a branch

```bash
git clone <url-do-repositorio>
cd projeto-hibernate-dao-api
git checkout api
```

### 2. Configurar banco de dados (profile dev)

Garanta que o arquivo `src/main/resources/application-dev.properties` esteja configurado com:

- URL JDBC
- usuario
- senha
- estrategia JPA/Hibernate desejada

### 3. Executar a aplicacao

```bash
mvn clean spring-boot:run
```

A aplicacao sobe por padrao em `http://localhost:8080`.

### 4. Endpoints principais

- `GET /formas`
- `GET, POST, PUT, DELETE /circulos`
- `GET /circulos/{id}/area`
- `GET /circulos/{id}/perimetro`
- `GET, POST, PUT, DELETE /quadrados`
- `GET /quadrados/{id}/area`
- `GET /quadrados/{id}/perimetro`
- `GET, POST, PUT, DELETE /retangulos`
- `GET /retangulos/{id}/area`
- `GET /retangulos/{id}/perimetro`
