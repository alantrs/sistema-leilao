# sistema-leilao

Projeto final da matéria de Linguagem de programação 2

# Projeto Micronaut com Java 17

Este é um projeto Micronaut criado com Java 17. Este projeto pode ser facilmente importado em sua IDE Java de escolha e configurado para rodar no Swagger na porta 8080.

## Requisitos

Certifique-se de ter os seguintes requisitos instalados em sua máquina antes de executar o projeto:

- [Java 17](https://www.oracle.com/java/technologies/javase-downloads.html) ou uma versão compatível.
- [Gradle](https://gradle.org/)

## Clone o projeto para sua máquina

1. Clone o repositório em sua máquina local pelo terminal:

      git clone https://github.com/alantrs/sistema-leilao.git

## Construindo o Projeto com Gradle

Para construir a aplicação usando o Gradle, siga estas etapas:

1. Abra um terminal na raiz do projeto.

2. Execute o seguinte comando Gradle para construir a aplicação:

      gradlew.bat build


4. Isso irá compilar o código-fonte, executar testes e criar um arquivo JAR na pasta `build/libs` do projeto.

## Executando a Aplicação

Após construir o projeto, siga estas etapas para executá-lo:

1. Encontre o JAR criado na pasta 'build/libs' do projeto
2. Execute-o com o comando:
   
      java -jar {nome_do_jar.jar}
4. Isso iniciará o aplicativo Micronaut na porta 8080.

## Documentação Swagger

O projeto Micronaut inclui uma documentação Swagger para facilitar o entendimento da API. Você pode acessar a documentação Swagger em seu navegador:

[http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

