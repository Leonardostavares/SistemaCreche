# Sistema de Creche

Sistema para gerenciamento de formulários de matrícula em creche desenvolvido com JavaFX e Spring Boot.

## Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6 ou superior
- MySQL 8.0 (com banco `creche` criado)

### Executar o Sistema

1. **Clone o repositório:**
```bash
git clone https://github.com/Leonardostavares/SistemaCreche.git
cd SistemaCreche
```

2. **Configure o banco de dados MySQL:**
   - Certifique-se que o MySQL está rodando
   - O banco `creche` deve existir
   - Configure usuário/senha no arquivo `src/main/resources/application.properties` se necessário

3. **Execute o comando:**
```bash
mvn spring-boot:run
```

O sistema irá:
- Iniciar o backend na porta 8081
- Criar automaticamente as tabelas no banco
- Abrir a interface JavaFX automaticamente

## Funcionalidades

- ✅ Cadastro completo de formulários de matrícula
- ✅ Validação de campos obrigatórios (CPF, Nome, Data de Nascimento)
- ✅ Busca por CPF
- ✅ Listagem de formulários cadastrados
- ✅ Armazenamento em banco MySQL
- ✅ Interface gráfica intuitiva

## Tecnologias Utilizadas

- **Backend:** Spring Boot 3.1.5, JPA/Hibernate, MySQL
- **Frontend:** JavaFX 21.0.1
- **Build:** Maven
- **Java:** 21

## Observações

- O sistema criará automaticamente todas as tabelas necessárias no primeiro uso
- Certifique-se de que o MySQL esteja rodando antes de executar
- A interface JavaFX abrirá automaticamente após a inicialização do backend
- Backend roda na porta 8081