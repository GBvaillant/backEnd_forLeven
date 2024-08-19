## BackEnd_ForLeven
Esta API permite a criação de um estudante com os seguintes dados: nome, sobrenome, matrícula e número de telefone.

 ## Configuração do projeto
 ### Clonar o repositório 
 - git clone https://github.com/GBvaillant/backEnd_forLeven
 - cd backend_forleven

 ### Configurar o banco de dados
 - Docker
   - Digite no cmd: `docker compose up`
     
 ### Configurar propriedades
 No arquivo `application.properties` configure os valores de acordo com o banco de dados.

 ## Endpoints
Essa API conta com os seguintes endpoints: 
- POST `${URL}/student/create`
  - Cria um novo estudante
- GET `${URL}/student/list`
  - Busca todos os estudantes
- GET `${URL}/student/listRegister/:REGISTRATION`
  - Busca o estudante pela matrícula
- GET `${URL}/student/listId/:ID`
  - Busca o estudante pelo id
- PUT `${URL}/student/update/:ID`
  - Realiza o update dos dados do estudante
- DELETE `${URL}/student/delete/:ID`
  - Deleta completamente o estudante
- DELETE `${URL}/phone/delete/:ID`
  - Deleta o numero de telefone pelo Id
    
## Exemplo de Requisição
``` {
	"id": "0a22e353-c836-4009-8fa3-3f9a945b1750",
	"name": "Gabriel",
	"lastName": "Vaillant",
	"registration": "2024679400",
	"phones": [
		{
			"id": "03aec30a-ea53-4176-a3aa-1a4196abbbd6",
			"number": "123456789"
		}
	]
}
```
## Tecnologias Ultilizadas
- Java
- SpringBoot
- SpringDataJpa
- SpringWeb
- Lombook
- H2database
- Docker

  
  
