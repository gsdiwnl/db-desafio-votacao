# Instruções para aplicação

- No repositório, há um arquivo `docker-compose.yaml`, responsável por subir o banco utilizado na aplicação, para executá-lo, abra o console e digite `docker-compose up`.
- Há disponibilizado também, uma collections do Postman, basta baixar o arquivo e importá-lo no Postman.
- O link para acessar o Swagger é `/swagger-ui/index.html` ou `/swagger.html`.

# Arquitetura do projeto

A aplicação está dividida em versões, cada versão tem sua pasta especifica (nomenclatura: v1, v2.. ), após essa separação há a pasta modules, que centraliza os módulos da aplicação.
Cada modulo tem sua pasta especifica, separado pelos **controllers**, **services** e **database**.

### Controllers
    - Responsáveis por expor, lidar e processar as requisições HTTP.
    - Deve sempre extender a classe `Controller.java`, que disponibilizará métodos de retorno a serem utilizados em meio ao código, sem necessidade de utilizar ResponseEntity.
    - Deve sempre implementar uma interface `Swagger.java`, com as documentações do Swagger para o método.
    - Não deve haver chamadas para Services que não seja da Entidade trabalhada no Controller.
    - Path para mapeamento deve sempre terminar no plural.

### Services
    - Implementação das regras de negocio.
    - Pode haver chamadas para outras services, mas deve haver interação apenas com o Repository da Entidade em questão.

### Database
    - Terá os repositories, que serão responsáveis pela conexão e persistência dos dados no banco.
    A pasta database, terá subpastas, como:
        - **DTO**: Armazenar os DTOs das requisições, seja para Request ou Response, nos DTOs, também há as validações dos dados obrigatórios e nos formatos requiridos.
        - **ENUMS**: Define enums uteis a serem utilizados pelo modulo.
        - **MODELS**: Pasta que define as Entidades que o modulo utiliza.

# Organização do código
Para melhor organização, há duas pastas no inicio da aplicação, **config** e **misc**:

**config:** centraliza arquivos de configuração, juntamente com arquivos de contexto para aplicação.

**misc:** há classes uteis utilizadas em toda aplicação, como annotations, custom exceptions e exception handlers.

# Tratamento de erros e exceções
    - O tratamento de exceções e erros disparados pela aplicação é realizada por ExceptionHandlers, onde cada Exception deve ser mapeada seguindo o padrão de nomenclatura `${nome_da_exception}Handler.java`.
    - Cada Handler deve implementar a interface `Handler<T>` e realizar a implementação do método `handle( T ex )` utilizando como retorno o método `ResponseEntity<Error> response( String message, HttpStatus status )`.
    - A interface Handler, é utilizada para facilitar a implementação de um novo ExceptionHandler, centralizando a validação e Response retornada para as exceptions e erros.