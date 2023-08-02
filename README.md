
## Desafio SOS Tecnologia

Projeto JAVA, com criação de  API REST para o *gerenciamento de patrimônios de uma empresa*.

O projeto foi desenvolvido para o gerenciamento de patrimônios de uma empresa. A API permite que os usuários realizem operações de criação, leitura, atualização e exclusão *(CRUD)* de patrimônios.

## Funcionalidades Principais

-   Cadastro de Patrimônios: Os usuários podem cadastrar novos patrimônios fornecendo informações como nome, descrição e número do tombo.

-   Atualização de Patrimônios: Os dados de um patrimônio existente podem ser atualizados.

-   Exclusão de Patrimônios: Os patrimônios podem ser excluídos do sistema.

-   Listagem de Patrimônios: É possível listar todos os patrimônios cadastrados.

-   Detalhes do Patrimônio: Os usuários podem visualizar detalhes de um patrimônio específico.


## Tecnologias Utilizadas

O projeto utiliza as seguintes tecnologias:

-   Java
-   Spring Boot (Framework para desenvolvimento de aplicações Java)
-   Spring Data JPA (Biblioteca para persistência de dados)
-   Banco de Dados (MySQL)
-  Maven


## Como Executar o Projeto

Para executar o projeto, siga as instruções abaixo:

1.  Certifique-se de ter o Java+8 e o Maven instalados em seu sistema.
2.  Faça o clone do repositório do projeto utilizando o comando `git clone https://github.com/agnr-gab/sosDocs`.
3.  Acesse o diretório do projeto clonado.
4. Configure as informações do banco de dados (por exemplo, URL, usuário e senha) no arquivo application.properties.
![exemplo de config de banco de dados](https://i.ibb.co/yRH25S0/image.png)
5.  Execute o comando `mvn clean install` para compilar o projeto e baixar as dependências necessárias.
6.  Execute o comando `mvn spring-boot:run` para iniciar a aplicação.
7.  A WEB API estará disponível em `http://localhost:8080`
8. Para fazer os testes dos endpoints utilize o Swagger no endereço `http://localhost:8080/swagger-ui/index.html#/`
9. Utilizar `GET /popular` ([popular-controller](http://localhost:8080/swagger-ui/index.html#/popular-controller)) para popular o banco de dados.
![enter link description here](https://i.ibb.co/fdsYfgf/image.png) 
![enter link description here](https://i.ibb.co/nQT2yT1/image.png)



## Patrimônio

**Campos:** 
*• Nome - obrigatório 
• MarcaId - obrigatório 
• Descrição 
• Nº do tombo*

## Endpoints

A API possui os seguintes endpoints:

-   `GET /patrimonios`: Retorna uma lista de todos os patrimônios cadastrados.

-   `GET /patrimonios/{id}`: Retorna os detalhes de um patrimônio específico com base no seu ID.

-   `POST /patrimonios`: Cria um novo patrimônio com base nos dados fornecidos no corpo da requisição.

-   `PUT /patrimonios/{id}`: Atualiza os dados de um patrimônio existente com base no seu ID e nos dados fornecidos no corpo da requisição.

-   `DELETE /patrimonios/{id}`: Exclui um patrimônio com base no seu ID.

![enter image description here](https://i.ibb.co/DLWmjkn/image.png)

## Marca

**Campos:** 
*• Nome – obrigatório 
• MarcaId - obrigatório*

• `GET /marcas` - Retorna uma lista de todos as marcas cadastradas.

• `GET /marcas/{id}` - Retorna os detalhes de uma marca específica com base no seu ID.

• `GET /marcas/{id}/patrimonios` –  Retorna os detalhes de um patrimônio específico com base no seu ID da marca vinculada.

• `POST /marcas` - Cria uma nova marca com base nos dados fornecidos no corpo da requisição.

• `PUT /marca/{id}` - Atualiza os dados de uma marca existente com base no seu ID e nos dados fornecidos no corpo da requisição. 

• `DELETE /marca/{id}` - Exclui uma marca com base no seu ID.

![enter link description here](https://i.ibb.co/khXwp03/image.png)

## Regras
• O nº do tombo deve ser gerado automaticamente pelo sistema, e não pode ser alterado pelos usuários.
• Não deve permitir a existência de duas marcas com o mesmo nome.

