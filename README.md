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

* [JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Docker](https://docs.docker.com/get-docker/)
* [Docker-compose](https://docs.docker.com/compose/install/) 
* [Postman](https://www.postman.com/downloads/) (opcional)


### Execução

**Dentro da pasta do projeto, execute o maven para a criação do artefato .jar:**

```sh
$ mvn clear compile install
```

**Subir o container Docker com a aplicação:**

```sh
$ docker-compose up
```

**Parte do Log da execução da aplicação**
```sh
$ Tomcat started on port(s): 8080 (http) with context path '/evollo/api'
```

**Link da aplicação:**
[localhost:8080/evollo/api](localhost:8080/evollo/api)
