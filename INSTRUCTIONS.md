# Instruções para aplicação

- No repositório, há um arquivo `docker-compose.yaml`, responsável por subir o banco utilizado na aplicação, para executa-lo, abra o console e digite `docker-compose up`.
- Há disponibilizado também, uma collections do Postman, basta baixar o arquivo e importa-lo no Postman.
- O link para acessar o Swagger é `/swagger-ui/index.html` ou `/swagger.html`.

# Arquitetura do projeto

A aplicação esta dividida em versões, cada versão terá sua pasta especifica (nomenclatura: v1, v2.. ), após essa separação teremos a pasta modules, que centralizará os módulos da aplicação.
Cada modulo terá sua pasta especifica, separado pelos **controllers**, **services** e **database**.

### Controllers
    - Responsáveis por expor, lidar e processar as requisições HTTP.
    - Deve sempre extender a classe `Controller.java`, que disponibilizará métodos de retorno a serem utilizados em meio ao código, sem necessidade de utilizar ResponseEntity.
    - Deve sempre implementar uma interface `Swagger.java`, com as documentações do Swagger para o método.
    - Não deve-se haver chamadas para Services que não seja da Entidade trabalhada no Controller.
    - Path para mapeamento deve sempre terminar no plural.

### Services
    - Implementação das regras de negocio.
    - Validações necessárias para realizar a chamada que recebeu do Controller.
    - Pode haver chamadas para outras services, mas deve haver interação apenas com o Repository da Entidade em questão.
    - Cada método chamado, haverá um `logger.info`, informando qual método foi chamado, mantendo o mapeamento das ações realizadas pelo sistema.

### Database
    - Terá os repositories, que serão reesposáveis pela conexão e persistência dos dados no banco.
    A pasta database, terá subpastas, como:
        - **DTO**: Armazenar os DTOs das requisições, seja para Request ou Response, nos DTOs, também, haverá as validações dos dados obrigatórios e nos formatos requiridos.
        - **ENUMS**: Define enums uteis a serem utilizados pelo modulo.
        - **MODELS**: Pasta que define as Entidades que o modulo utiliza.

# Organização do código
Para melhor organização, há duas pastas no inicio da aplicação, **config** e **misc**.

**config:** centralizará arquivos de configuração, juntamente com arquivos de contexto para aplicação.

**misc:** terá classes uteis utilizadas em toda aplicação, como annotations, custom exceptions e exception handlers.

# Tratamento de erros e exceções
    - O tratamento de exceções e erros disparadas pela aplicação é realizada por ExceptionHandlers, onde cada Exception deve ser mapeada seguindo o padrão de nomenclatura `${nome_da_exception}Handler.java`.
    - Obrigação de mapear cada exception, criando a necessidade de estar ciente das exceptions disparadas pela aplicação, caso contrario a API irá retornar um **500**.
    - Cada Handler deve implementar a interface `Handler<T>` e realizar a implementação do método `handle( T ex )` utilizando como retorno o método `ResponseEntity<Error> response( String message, HttpStatus status )`.
    - A interface Handler, é utilizada para facilitar a implementação de um novo ExceptionHandler, centralizando a validação e Response retornada para as exceptions e erros.