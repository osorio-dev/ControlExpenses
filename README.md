# 💰 ControlExpenses API

API REST para **controle de despesas pessoais**, tratamento de erros e documentação via Swagger.

---

## 📦 Estrutura do Projeto

src

└── main

└── java

└── com.dev.osorio.ControlExpenses

├── controllers # Controladores REST

├── dtos # Data Transfer Objects

├── entitys # Modelos da entidade

├── exceptions # Exceções personalizadas

├── mappers # Conversão entre Model e DTO

├── repositories # Interfaces JPA

├── services # Lógica de negócio

└── ControlExpensesApplication.java


---

## 🚀 Como Rodar o Projeto Localmente

### ✅ Pré-requisitos

- Java 17+
- Maven
- PostgreSQL

### ▶️ Instruções

```bash
# Clone o repositório
git clone https://github.com/osorio-dev/ControlExpenses.git
cd ControlExpenses

# Configure o banco de dados (PostgreSQL)
# Crie o schema e defina no application.properties

# Rode a aplicação
./mvnw spring-boot:run
```

🧪 Documentação da API
Após iniciar a aplicação, acesse:

🔗 http://localhost:8080/swagger-ui.html



📬 Endpoints Básicos

➕ Criar despesa

```
POST /api/expense/create
```

Body:
```
{
	"name": "Viagem",
	"value": 189.99,
	"category": "Lazer",
	"dateTime": "2025-07-01 20:30:00"
}
```

📋 Listar despesas

```
GET /api/expense/allexpenses
```

📝 Atualizar despesa

````
PUT /api/expense/update?id=request-param
````

❌ Remover despesa

````
DELETE /api/expense/delete?id=request-param
````



⚙️ Exemplo de application.properties

````
spring.datasource.url=jdbc:postgresql://localhost:5432/control_expenses
spring.datasource.username=postgres
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

springdoc.swagger-ui.path=/swagger-ui.html
````



🛠️ Tecnologias Usadas

- Java 17

- Spring Boot

- Spring Web

- Spring Data JPA

- PostgreSQL

- Flyway

- Swagger / SpringDoc OpenAPI
