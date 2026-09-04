# 📋 Aprovação de Despesas Corporativas
 
API REST para gestão de solicitações de despesas corporativas, com aprovação automática por faixa de valor e fluxo de aprovação manual para valores acima do limite. Desenvolvida como projeto de estudo aplicando **Arquitetura Hexagonal (Ports & Adapters)**.
 
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![JUnit](https://img.shields.io/badge/JUnit-Testes-25A162)
 
---
 
## 📖 Sobre o projeto
 
Este projeto simula um sistema de aprovação de despesas de uma empresa: um funcionário solicita registro de uma despesa, e o sistema decide automaticamente se ela é aprovada de imediato ou se precisa passar por aprovação manual de um gestor, com base no valor solicitado.
 
O foco principal do projeto **não é o CRUD em si**, mas sim praticar:
- Separação de responsabilidades com **Arquitetura Hexagonal**
- Regras de negócio protegidas dentro do domínio (**domínio rico**, sem setters soltos)
- **Testes unitários** isolados, sem dependência de framework ou banco
- Organização de código alinhada com padrões usados no mercado

---

## ⚙️ Regras de negócio
 
| # | Regra |
|---|---|
| 1 | Despesa com valor **até R$ 500** é aprovada **automaticamente** ao ser criada |
| 2 | Despesa com valor **acima de R$ 500** nasce com status **PENDENTE**, aguardando aprovação manual |
| 3 | O valor da despesa não pode ser negativo ou zero |
| 4 | Só é possível **aprovar** ou **rejeitar** uma despesa que esteja com status **PENDENTE** |
| 5 | Rejeitar uma despesa **exige** informar um motivo |
| 6 | Toda despesa precisa estar vinculada a um funcionário já cadastrado |
| 7 | Não é possível cadastrar dois funcionários com o mesmo nome |
 
Todas as regras de 1, 3, 4 e 5 são protegidas **dentro da própria entidade** `Despesa` (domínio rico) — não é possível violar essas regras de fora, já que não existem setters públicos para o status.


---

## 🛠️ Tecnologias utilizadas
 
- **Java 17**
- **Spring Boot** (Web, Data JPA)
- **PostgreSQL**
- **Lombok**
- **JUnit 5** — testes unitários de domínio
- **Maven**

  
---

## 🚀 Como executar o projeto
 
O projeto está totalmente dockerizado — aplicação e banco de dados sobem juntos com um único comando, sem precisar instalar PostgreSQL localmente.
 
### Pré-requisitos
- Docker e Docker Compose instalados
### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd aprovacao-de-despesas-api-arquitetura-hexagonal
```
 
### 2. Configure as variáveis de ambiente
 
Crie um arquivo `.env` na raiz do projeto (não versionado no Git):
 
```env
POSTGRES_DB=aprovacao_despesas
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
```
 
### 3. Suba a aplicação e o banco com Docker Compose
 
```bash
docker compose up --build
```
 
Isso vai:
- Construir a imagem da aplicação (build multi-stage com Maven + JRE)
- Subir um container PostgreSQL com os dados persistidos em volume
- Conectar a aplicação ao banco automaticamente
A API fica disponível em `http://localhost:8080`, e o banco em `localhost:5432`.
 
A documentação interativa (Swagger UI) fica disponível em `http://localhost:8080/swagger-ui.html`, onde é possível visualizar e testar todos os endpoints diretamente pelo navegador.
 
Para derrubar os containers:
```bash
docker compose down
```
 
### 4. Rode os testes
 
Os testes de domínio não dependem do Docker/banco, podendo ser executados diretamente com Maven:
 
```bash
mvn test
```
 
<details>
<summary><strong>Alternativa: executar sem Docker</strong></summary>
Se preferir rodar localmente sem containers, é necessário ter um PostgreSQL disponível e configurar `src/main/resources/application.properties`:
 
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aprovacao_despesas
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```
 
E então:
```bash
mvn spring-boot:run
```
</details>
---
 
## 📡 Endpoints da API
 
### Funcionários
 
| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/funcionario` | Cadastra um novo funcionário |
| `GET` | `/funcionario/{id}` | Busca um funcionário pelo id |
| `GET` | `/funcionario` | Lista todos os funcionários |
 
**Exemplo — criar funcionário:**
```http
POST /funcionario
Content-Type: application/json
 
{
  "nomeFuncionario": "Wanderson Luiz",
  "cargo": "Desenvolvedor Java Júnior"
}
```
 
### Despesas
 
| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/despesa` | Cria uma despesa (aplica auto-aprovação) |
| `PUT` | `/despesa/{id}/aprovar` | Aprova uma despesa pendente |
| `PUT` | `/despesa/{id}/rejeitar` | Rejeita uma despesa pendente (motivo obrigatório) |
| `GET` | `/despesa` | Lista todas as despesas |
| `GET` | `/despesa/pendentes` | Lista despesas aguardando aprovação |
| `GET` | `/despesa/funcionario/{funcionarioId}` | Lista despesas de um funcionário |
 
**Exemplo — criar despesa:**
```http
POST /despesa
Content-Type: application/json
 
{
  "valor": 750.00,
  "categoria": "Equipamento",
  "descricao": "Notebook para desenvolvimento",
  "data": "2026-08-20",
  "solicitante": 1
}
```
 
**Exemplo — rejeitar despesa:**
```http
PUT /despesa/3/rejeitar
Content-Type: application/json
 
{
  "motivo": "Fora do orçamento aprovado para o time"
}
```
 
### Tratamento de erros
 
Erros de regra de negócio são capturados globalmente e retornam um corpo padronizado:
 
```json
{
  "status": 404,
  "mensagem": "Despesa não encontrada"
}
```
 
| Status HTTP | Quando ocorre |
|---|---|
| `404 Not Found` | Funcionário ou despesa não encontrados |
| `409 Conflict` | Funcionário duplicado, despesa não pendente, motivo de rejeição ausente |
| `400 Bad Request` | Valor de despesa inválido |
 
---
