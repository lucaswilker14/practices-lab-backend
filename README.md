# Backend - Prova Técnica Evollo

**Objectivo**: Criar uma aplicação para consumo de informações sobre uma empresa e seus
funcionários, para isso o backend deve disponibilizar algumas APIs para realização de CRUD de
funcionários e consulta de informações.

### Recusos Implementados

**Empresa:**

* Criar uma empresa
* Editar uma empresa
* Recuperar todas as empresas do sistema

**Funcionário:**

* Criar Funcionário
* Atribui-lo a uma empresa
* Editar
* Deletar
* Visualização de todos os funcionários

**Usuário:**

* Autenticação e Autorização - JWT Token
* Criação de um usuário com username + senha
* Envio do email com as credenciais cadastradas para login


### Admin Default cadastrado no sistema para login inicial

```sh
username: admin
password: 123
```

***

### Pre-requisitos de instalação
Antes de começar, certifique-se de ter atendido aos seguintes requisitos:

* [JDK 11+](https://www.oracle.com/java/technologies/javase-downloads.html)
* [Maven 3.6+ -> Set $JAVA_HOME](https://maven.apache.org/download.cgi)
* [Docker](https://docs.docker.com/get-docker/)
* [Docker-compose](https://docs.docker.com/compose/install/) 
* [Postman](https://www.postman.com/downloads/) (opcional)

## Pre-views versões:

### Maven Version

```
$ mvn -version

> Apache Maven 3.6.0
Maven home: /usr/share/maven
Java version: 11.0.10, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "5.4.0-66-generic", arch: "amd64", family: "unix"
```

### Java Version
```
$ java -version

> openjdk version "11.0.10" 2021-01-19
OpenJDK Runtime Environment (build 11.0.10+9-Ubuntu-0ubuntu1.18.04)
OpenJDK 64-Bit Server VM (build 11.0.10+9-Ubuntu-0ubuntu1.18.04, mixed mode, sharing)
```

### Javac Version
```
$ javac -version

> javac 11.0.10
```

### Docker Version
```
$ docker --version

> Docker version 20.10.4, build d3cb89e
```

### docker-compose Version
```
$ docker-compose --version

> docker-compose version 1.21.2, build a133471
```

## Execução

**Dentro da pasta do projeto, execute o maven para a criação do artefato .jar:**

```sh
$ mvn clean compile install
```

**Subir o container Docker com a aplicação:**

```sh
$ docker-compose up
```

**Parte do Log da execução da aplicação quando rodando:**
```sh
$ Tomcat started on port(s): 8080 (http) with context path '/evollo/api'
```

**Link da aplicação:**
[localhost:8080/evollo/api](localhost:8080/evollo/api)

***

### Documentação
[Link Swagger](http://localhost:8080/evollo/api/swagger-ui.html)
