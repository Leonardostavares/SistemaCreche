# Sistema de Gestão de Creche

Sistema para gerenciamento de matrículas e dados de crianças em creche pública.

## Tecnologias Utilizadas

- **Backend**: Spring Boot 3.1.5
- **Frontend**: JavaFX 21
- **Banco de Dados**: H2 (padrão) / MySQL 8.0 (opcional)
- **Build**: Maven
- **Java**: 21

## Como Executar

### Pré-requisitos
- Java 21 instalado
- Maven (opcional, pode usar o wrapper incluído)

### 🚀 Executando Rapidamente (Recomendado para Testes)
```bash
# Clona o repositório
git clone https://github.com/Leonardostavares/SistemaCreche.git
cd SistemaCreche

# Executa o projeto (usa H2 em memória)
./mvnw spring-boot:run
```

### Executando o Frontend JavaFX
```bash
java -cp target/classes main.frontend.CrecheFXApplication
```

### Usando o Script Automático
```bash
./executar.bat
```

## 🎯 Configurações de Banco de Dados

### Opção 1: H2 Database (PADRÃO - Recomendado para Testes)
**✅ Vantagens**: Não precisa instalar nada, funciona imediatamente
- O projeto já vem configurado para H2
- Dados são criados automaticamente na memória
- Console web disponível em: http://localhost:8080/h2-console
  - URL: `jdbc:h2:mem:creche`
  - Usuário: `sa`
  - Senha: (deixar em branco)

### Opção 2: MySQL (Para Produção)
**Para usar MySQL**, edite o arquivo `application.properties`:
1. Comente as configurações do H2
2. Descomente as configurações do MySQL
3. Configure suas credenciais:

```properties
# Desabilitar H2 (comentar estas linhas)
#spring.datasource.url=jdbc:h2:mem:creche
#spring.datasource.driver-class-name=org.h2.Driver
# ... outras configurações H2

# Habilitar MySQL (descomentar estas linhas)
spring.datasource.url=jdbc:mysql://localhost:3306/creche?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## Funcionalidades

- ✅ Cadastro de formulários completos
- ✅ Gerenciamento de status (PENDENTE, APROVADO, REPROVADO)
- ✅ Seleção de status pelo usuário durante cadastro
- ✅ Busca por CPF
- ✅ Interface gráfica JavaFX
- ✅ API REST completa
- ✅ Validação de dados
- ✅ Console web H2 para visualizar dados

## Endpoints da API

- `POST /api/formulario` - Criar formulário
- `GET /api/formulario/buscar/cpf/{cpf}` - Buscar por CPF
- `PUT /api/formulario/editar/{id}` - Editar formulário
- `GET /api/formularios/buscar/status/{status}` - Buscar por status
- `GET /api/formularios/status/opcoes` - Listar status disponíveis

## 🛠️ Para Desenvolvedores

### Acessos Úteis
- **Aplicação**: http://localhost:8080
- **Console H2**: http://localhost:8080/h2-console
- **Health Check**: http://localhost:8080/actuator/health
- **API Documentation**: http://localhost:8080/swagger-ui.html (se disponível)

### Estrutura do Projeto
```
src/
├── main/java/main/
│   ├── controller/     # Controllers REST
│   ├── service/        # Lógica de negócio
│   ├── model/          # Entidades JPA
│   ├── repository/     # Repositórios de dados
│   ├── enums/          # Enumerações
│   └── frontend/       # Interface JavaFX
└── main/resources/
    ├── application.properties      # Configuração principal
    ├── application-h2.properties  # Configuração alternativa H2
    ├── fxml/                      # Arquivos de interface
    └── css/                       # Estilos da interface
```

## 📝 Notas Importantes

- **Para testes rápidos**: Use a configuração padrão (H2). Não precisa configurar nada!
- **Para produção**: Configure MySQL seguindo as instruções acima
- **Dados de teste**: Com H2, os dados são perdidos ao reiniciar (isso é normal para testes)
- **Console H2**: Acesse para ver as tabelas criadas e os dados inseridos

## 🤝 Contribuição

1. Fork o projeto
2. Crie sua feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request