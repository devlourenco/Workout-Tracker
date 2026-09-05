# Workout Tracker API

API REST desenvolvida em Java com Spring Boot para gerenciamento de treinos, exercícios e séries.

O projeto foi criado com o objetivo de praticar o desenvolvimento de uma aplicação backend estruturada em camadas, utilizando persistência de dados, validação de requisições, tratamento de exceções, testes unitários e documentação de API.

## Funcionalidades

### Treinos

- Cadastro de treinos contendo exercícios e séries
- Listagem de todos os treinos
- Consulta de treino por ID
- Atualização parcial de nome e dia da semana
- Exclusão de treino

### Exercícios

- Consulta de exercício por ID
- Atualização do nome do exercício
- Atualização do grupo muscular

### Séries

- Consulta de série por ID
- Atualização de repetições
- Atualização de RIR
- Atualização de carga

### Recursos adicionais

- Validação de dados de entrada
- Tratamento global de exceções
- Respostas HTTP apropriadas para recursos inexistentes e dados inválidos
- Documentação interativa com Swagger / OpenAPI
- Testes unitários da camada de Service

---

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Jakarta Bean Validation
- JUnit
- Mockito
- Swagger / OpenAPI
- Maven
- Git

---

## Arquitetura

A aplicação segue uma estrutura em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Também são utilizados DTOs e Mappers para separar os objetos expostos pela API das entidades responsáveis pela persistência.

O domínio principal possui a seguinte relação:

```text
Treino
  └── Exercícios
        └── Séries
```

Um treino pode possuir vários exercícios, e cada exercício pode possuir várias séries.

---

## Endpoints

### Treinos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/treinos` | Cadastra um novo treino |
| GET | `/treinos` | Lista todos os treinos |
| GET | `/treinos/{id}` | Busca um treino por ID |
| PATCH | `/treinos/{id}` | Atualiza parcialmente um treino |
| DELETE | `/treinos/{id}` | Remove um treino |

### Exercícios

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/exercicios/{id}` | Busca um exercício por ID |
| PATCH | `/exercicios/{id}/nome` | Atualiza o nome de um exercício |
| PATCH | `/exercicios/{id}/grupo-muscular` | Atualiza o grupo muscular |

### Séries

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/series/{id}` | Busca uma série por ID |
| PATCH | `/series/{id}/rir` | Atualiza o RIR |
| PATCH | `/series/{id}/reps` | Atualiza o número de repetições |
| PATCH | `/series/{id}/carga` | Atualiza a carga |

---

## Exemplo de cadastro

### POST `/treinos`

```json
{
  "nome": "Treino A",
  "diaDaSemana": "MONDAY",
  "exercicios": [
    {
      "nome": "Supino Reto",
      "grupoMuscular": "PEITO",
      "series": [
        {
          "reps": 8,
          "rir": 2,
          "carga": 30
        }
      ]
    }
  ]
}
```

Os valores informados em `grupoMuscular` devem corresponder aos valores definidos no enum da aplicação.

---

## Validação de dados

A API utiliza Bean Validation para validar os dados recebidos nas requisições.

Entre as regras implementadas estão:

- Nome do treino obrigatório
- Dia da semana obrigatório
- Treino com pelo menos um exercício
- Nome do exercício obrigatório
- Grupo muscular obrigatório
- Exercício com pelo menos uma série
- Número de repetições maior que zero
- RIR não negativo
- Carga não negativa

Requisições com dados inválidos retornam:

```text
400 Bad Request
```

Exemplo de resposta:

```json
{
  "nome": "O nome do treino é obrigatório"
}
```

As validações também são aplicadas aos objetos aninhados, permitindo identificar erros em exercícios e séries enviados durante o cadastro de um treino.

---

## Tratamento de exceções

O tratamento de exceções é centralizado por meio de um `RestExceptionHandler`.

Quando um recurso solicitado não é encontrado, a API retorna:

```text
404 Not Found
```

Exemplos de mensagens:

```text
Treino não encontrado
Exercício não encontrado
Série não encontrada
```

Essa abordagem evita duplicação de tratamento nos Controllers e mantém as respostas da API consistentes.

---

## Documentação da API

A aplicação utiliza Swagger / OpenAPI para documentar seus endpoints.

Com o projeto em execução, a interface interativa pode ser acessada em:

```text
http://localhost:8080/swagger-ui.html
```

A especificação OpenAPI em JSON está disponível em:

```text
http://localhost:8080/v3/api-docs
```

A documentação apresenta os endpoints separados por domínio:

```text
Treinos
Exercícios
Séries
```

---

## Executando o projeto

Clone o repositório:

```bash
git clone https://github.com/devlourenco/Workout-Tracker.git
```

Acesse o diretório:

```bash
cd Workout-Tracker
```

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Após iniciar a aplicação, a API estará disponível em:

```text
http://localhost:8080
```

---

## Banco de dados

O projeto utiliza H2 Database com persistência em arquivo.

As credenciais podem ser configuradas pelas seguintes variáveis de ambiente:

```text
H2_USERNAME
H2_PASSWORD
```

Caso não sejam informadas, são utilizados os valores padrão definidos pela aplicação.

Os arquivos locais do banco de dados não são versionados no repositório.

---

## Testes

A camada de Service possui testes unitários desenvolvidos com JUnit e Mockito.

Os testes abrangem cenários como:

- Cadastro de treino
- Listagem de treinos
- Busca por ID
- Atualização de treino
- Exclusão de treino
- Tentativas de acesso a recursos inexistentes
- Atualização de exercícios
- Atualização de séries

Para executar os testes:

### Windows

```powershell
.\mvnw.cmd test
```

### Linux / macOS

```bash
./mvnw test
```

Também é possível executar uma compilação limpa seguida pelos testes:

```powershell
.\mvnw.cmd clean test
```

---

## Próximas melhorias

Entre as funcionalidades planejadas para futuras versões estão:

- Histórico de evolução dos treinos
- Progressão de carga
- Regras de progressão baseadas em RIR
- Sugestões de treino
- Expansão da cobertura de testes
- Novos filtros e consultas
- Evolução das regras de negócio relacionadas ao acompanhamento de desempenho

Essas funcionalidades representam evoluções planejadas e ainda não fazem parte da versão atual da aplicação.

---

## Sobre o desenvolvimento

O Workout Tracker foi desenvolvido como projeto de estudo e portfólio, com foco no aprofundamento de conceitos relacionados a Java, Spring Boot, APIs REST, modelagem de dados, validação, tratamento de exceções e testes automatizados.

Ferramentas de inteligência artificial foram utilizadas como apoio ao processo de aprendizado, revisão de código e discussão de decisões técnicas. A implementação, os testes e a validação das funcionalidades foram realizados durante o desenvolvimento do projeto.

---

## Autor

**Guilherme Simões**

Estudante de Sistemas de Informação com foco em desenvolvimento de software backend.

[GitHub](https://github.com/devlourenco) · [LinkedIn](https://www.linkedin.com/in/guilherme-simoes-lourenco/)

---

## Repositório

[github.com/devlourenco/Workout-Tracker](https://github.com/devlourenco/Workout-Tracker)