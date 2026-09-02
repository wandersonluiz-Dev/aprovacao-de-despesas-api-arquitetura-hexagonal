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
