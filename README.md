# users-api

[![Build Status](https://travis-ci.org/mabernardo/cadastro-api.svg?branch=master)](https://travis-ci.org/mabernardo/cadastro-api)

# cadastro-api
Cadastro REST API

## Para iniciar o projeto

    ./gradlew

## Exemplo de Uso

### Criar uma pessoa
Request:

    POST /api/pessoas
    Content-Type: application/json

Body:

    {
        "nome": "Marcio",
        "sobrenome": "Bernardo",
        "cpf": "11111111111",
        "nascimento": "1978-05-08",
        "status": "ATIVO",
        "endereco": {
            "logradouro": "Av. dos Programadores",
            "numero": "10",
            "cidade": "Sao Paulo",
            "estado": "SP"
        },
        "telefones": [{
                "ddd": "55",
                "numero": "5555555",
                "tipo": "CELULAR"
            },
            {
                "ddd": "55",
                "numero": "11111111",
                "tipo": "RESIDENCIAL"
            }
        ]
    }

Response Headers:

    Status: 201 Created
    Location: {URI/api/pessoas/1

Response Body:

    {
        "id": 1,
        "nome": "Marcio",
        "sobrenome": "Bernardo",
        "cpf": "11111111111",
        "nascimento": "1978-05-08",
        "status": "ATIVO",
        "endereco": {
            "id": 1,
            "logradouro": "Av. dos Programadores",
            "numero": "10",
            "complemento": null,
            "bairro": null,
            "cep": null,
            "cidade": "Sao Paulo",
            "estado": "SP"
        },
        "telefones": [
            {
                "id": 1,
                "ddd": "55",
                "numero": "5555555",
                "tipo": "CELULAR"
            },
            {
                "id": 2,
                "ddd": "55",
                "numero": "11111111",
                "tipo": "RESIDENCIAL"
            }
        ]
    }

Mais informações sobre os métodos disponíveis podem ser obtidos acessando o link abaixo após iniciar o projeto:
http://localhost:8080/swagger-ui.html

Se iniciado com profile de desenvolvimento (default), é possível acessar o console do banco de dados em memória H2
através do seguinte endereço:
http://localhost:8080/h2-console


## Profiles

Por default a aplicação irá iniciar com o profile de desenvolvimento.

No profile de desenvolvimento a segurança foi desabilitada para facilitar os testes. Nos profiles de teste e produção
ela estará habilitada e as chamadas à API deverão conter o token JWT no cabeçalho.

Para iniciar a aplicação com o profile de produção inicie a aplicação da seguinte forma:

    ./gradlew -Pprod

Note que a aplicação está configurada para utilizar um banco de dados MySQL em produção e será necessário que este
esteja rodando antes de iniciar a aplicação.

Para iniciar o MySql em um container docker, execute:
    [sudo] docker-compose -f src/main/docker/mysql.yml up -d

Para parar e remover o container, execute:
    [sudo] docker-compose -f src/main/docker/mysql.yml down

É possível empacotar a aplicação em um arquivo WAR para deploy em algum container da seguinte forma:
    ./gradlew -Pprod bootRepackage

É possível ainda "dockerizar" completamente a aplicação com todos os serviços que ela depende.
Para isso, primeiro construa a imagem docker executando:

    [sudo] ./gradlew bootRepackage -Pprod buildDocker

E então rode-a:

    [sudo] docker-compose -f src/main/docker/app.yml up -d

## Dependencies
JRE 1.8+
Docker & Docker Compose (para iniciar MySql em container ou rodar a aplicação como imagem docker)
