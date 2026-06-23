# Simplified Banking

Um sistema bancário simplificado desenvolvido com Spring Boot e Java 21, oferecendo funcionalidades básicas de gerenciamento de usuários e transações.

## 📋 Descrição

Este projeto implementa uma API REST para um sistema bancário com suporte a:
- Criação e gerenciamento de usuários (tipos: COMMON, MERCHANT)
- Realização de transações entre usuários
- Validação de transações
- Notificações por email
- Autorização de transações através de serviço externo

## 🛠️ Tecnologias

- **Java**: 21
- **Framework**: Spring Boot 4.1.0
- **Banco de Dados**: H2 (em memória)
- **ORM**: JPA/Hibernate
- **Build**: Maven
- **Dependências principais**:
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - H2 Database
  - Lombok
  - Spring Boot DevTools

## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/simplifiedBanking/
│   │   ├── SimplifiedBankingApplication.java    # Classe principal
│   │   ├── controllers/
│   │   │   └── UserController.java              # Endpoints de usuários
│   │   ├── domain/
│   │   │   ├── user/
│   │   │   │   ├── User.java                    # Entidade usuário
│   │   │   │   └── UserType.java                # Enum de tipos de usuário
│   │   │   └── transaction/
│   │   │       └── Transaction.java             # Entidade transação
│   │   ├── dtos/
│   │   │   ├── UserRequest.java                 # DTO para criar usuário
│   │   │   ├── TransactionRequest.java          # DTO para criar transação
│   │   │   └── NotificationRequest.java         # DTO para notificação
│   │   ├── services/
│   │   │   ├── UserService.java                 # Lógica de usuários
│   │   │   ├── TransactionService.java          # Lógica de transações
│   │   │   └── NotificationService.java         # Lógica de notificações
│   │   ├── repositories/
│   │   │   ├── UserRepository.java              # Acesso a dados de usuários
│   │   │   └── TransactionRepository.java       # Acesso a dados de transações
│   │   └── infra/
│   │       └── AppConfig.java                   # Configurações da aplicação
│   └── resources/
│       ├── application.properties               # Configurações da aplicação
│       ├── static/                              # Arquivos estáticos
│       └── templates/                           # Templates HTML
└── test/
    └── java/com/simplifiedBanking/
        └── SimplifiedBankingApplicationTests.java
```

## 🚀 Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6+

### Passos

1. **Clone o repositório**
```bash
git clone <repository-url>
cd simplified-banking
```

2. **Compile o projeto**
```bash
mvn clean compile
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### Console H2
O console H2 está habilitado e acessível em:
```
http://localhost:8080/h2-console
```

**Credenciais:**
- URL: `jdbc:h2:mem:banking-db`
- Usuário: `sa`
- Senha: (deixar em branco)

## 📚 Endpoints da API

### Usuários

#### Criar Usuário
```http
POST /users
Content-Type: application/json

{
  "firstName": "João",
  "lastName": "Silva",
  "document": "12345678900",
  "email": "joao@example.com",
  "password": "senha123",
  "balance": 1000,
  "type": "COMMON"
}
```

**Resposta:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "firstName": "João",
  "lastName": "Silva",
  "document": "12345678900",
  "email": "joao@example.com",
  "password": "senha123",
  "balance": 1000,
  "type": "COMMON"
}
```

#### Listar Todos os Usuários
```http
GET /users
```

**Resposta:**
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "firstName": "João",
    "lastName": "Silva",
    "document": "12345678900",
    "email": "joao@example.com",
    "password": "senha123",
    "balance": 1000,
    "type": "COMMON"
  }
]
```

## ⚙️ Configuração

### Variáveis de Ambiente

O projeto utiliza as seguintes variáveis de ambiente:

```properties
AUTHORIZE_TRANSACTION_SERVICE_URL=http://localhost:8080/authorize
SEND_EMAIL_SERVICE_URL=http://localhost:8080/send-email
```

Configure-as no seu sistema ou no arquivo `application.properties`:

```properties
authorize.transaction.url=${AUTHORIZE_TRANSACTION_SERVICE_URL}
send.email.url=${SEND_EMAIL_SERVICE_URL}
```

## 📝 Modelo de Dados

### Usuário (User)
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | UUID | Identificador único |
| firstName | String | Primeiro nome |
| lastName | String | Sobrenome |
| document | String | CPF/CNPJ (único) |
| email | String | Email (único) |
| password | String | Senha |
| balance | BigInteger | Saldo da conta |
| type | UserType | COMMON ou MERCHANT |

### Transação (Transaction)
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | UUID | Identificador único |
| value | BigInteger | Valor da transação |
| sender | User | Usuário que enviou |
| receiver | User | Usuário que recebeu |
| timestamp | LocalDateTime | Data/hora da transação |

### Tipo de Usuário (UserType)
- **COMMON**: Usuário comum (pode enviar e receber)
- **MERCHANT**: Comerciante (apenas recebe)

## 🔐 Validações

### Transações
- ✓ Verificar se o remetente é do tipo COMMON
- ✓ Validar se o remetente tem saldo suficiente
- ✓ Autorizar transação através do serviço externo
- ✓ Atualizar saldos
- ✓ Enviar notificação

## 🐛 Problemas Conhecidos

1. O método `getAllUsers()` em `UserController` necessita da anotação `@GetMapping`
2. A classe `User` necessita de um construtor sem argumentos (`@NoArgsConstructor`)

## 📖 Documentação Adicional

Para mais informações, consulte:
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [JPA/Hibernate Documentation](https://hibernate.org/orm/documentation/)

## 👥 Autores

Projeto desenvolvido como exemplo de sistema bancário simplificado.

## 📄 Licença

Este projeto está sob a licença MIT.
