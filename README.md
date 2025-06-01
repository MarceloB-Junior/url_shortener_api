# Url Shortener API

## Sobre

Este projeto é uma implementação do desafio [Encurtador de URLs](https://github.com/backend-br/desafios/blob/master/url-shortener/PROBLEM.md) proposto pela comunidade [Back-End Brasil](https://github.com/backend-br). O objetivo é receber uma URL longa e retornar uma versão encurtada, facilitando o compartilhamento.

O que foi implementado:

- **Recebimento da URL longa:** Endpoint POST `/api/v1/urls/shorten-url` para envio e validação da URL.
- **Geração do código encurtado:** Código aleatório entre 5 e 10 caracteres alfanuméricos, garantindo unicidade.
- **Armazenamento:** A URL encurtada, a original e a data de expiração (5 minutos) são salvas no banco de dados.
- **Redirecionamento:** GET `/api/v1/urls/{id}` faz o redirecionamento para a URL original ou retorna 404 se não encontrada.
- **Tratamento de erros:** Exceções são tratadas e retornam respostas padronizadas.
- **Limpeza automática:** URLs expiradas são removidas periodicamente usando `@Scheduled`.

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3.5.0** (Web, Data JPA, Validation)
- **H2 Database** (banco em memória para simplicidade)
- **Lombok**
- **Apache Commons Lang3**
- **Spring Scheduling** (`@Scheduled`)
- **Jakarta Validation**
- **Maven**

## Como começar

Siga os passos abaixo para rodar o projeto localmente:

1. **Clone o repositório**
   ```bash
   git clone https://github.com/MarceloB-Junior/url_shortener_api.git
   cd url_shortener_api
   ```

2. **Certifique-se de ter o Java 21 e Maven instalados**

3. **Instale as dependências e rode a aplicação**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acesse a aplicação**
   - API disponível em: [http://localhost:8080](http://localhost:8080)
   - Console do banco H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     - **Usuário:** `sa`
     - **Senha:** (em branco)

## Endpoints da API

### Encurtar URL

- **POST** `/api/v1/urls/shorten-url`
- **Requisição:**
  ```json
  {
    "url": "https://exemplo.com/minha-url-longa"
  }
  ```
- **Resposta:**
  ```json
  {
    "shortened_url": "http://localhost:8080/abc12"
  }
  ```

### Redirecionar URL

- **GET** `/api/v1/urls/{id}`
- Redireciona para a URL original associada ao código `{id}`.
- Retorna `HTTP 404` se não encontrar a URL.

## O que eu aprendi?

- Utilização do `@Scheduled` para tarefas periódicas, como a limpeza automática de URLs expiradas.

- Aprendi a manipular cabeçalhos HTTP dentro do `ResponseEntity` para realizar redirecionamentos (usando o header `Location` e o status `302 FOUND`), algo que ainda não havia utilizado na prática.

- **Observação:** Embora o projeto utilize o banco H2 em memória por simplicidade e facilidade de testes, para ambientes de produção ou alta escalabilidade, a melhor alternativa seria um banco NoSQL como o MongoDB, que oferece maior flexibilidade e desempenho para esse tipo de aplicação.