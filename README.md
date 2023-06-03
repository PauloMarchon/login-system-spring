# login-system-spring
Sistema de login com Spring Security utilizando chave RSA para geracao de token de acesso

Realizando o registro de usuario atraves da requisicao Post em /register e retornando o email cadastrado
![ImgRegistro](https://github.com/PauloMarchon/login-system-spring/assets/28858219/cc6854f3-f80f-4aa0-b149-189312e656f1)

Ao tentar utilizar um username ou email ja cadastrado a requisicao retorna um conflito
![ImgConflitoDuplicado](https://github.com/PauloMarchon/login-system-spring/assets/28858219/cabf39e9-5975-47c2-a26b-3616f7cfd540)

Ao realizar o login e retornando um token para ser utilizado em futuras requisicoes
![ImgLogin](https://github.com/PauloMarchon/login-system-spring/assets/28858219/490e7a9d-b282-41e5-b663-7317fbd3a9c8)

Utilizando o token para uma requisicao que exige autenticacao
![ImgUtilToknAcess](https://github.com/PauloMarchon/login-system-spring/assets/28858219/c9717f70-5b1f-48a4-b61c-dc28a21d0cb4)

As tabelas no bando H2:
 A tabela appuser registra o dia e hora da criacao do usuario
 A tabela roles ja e persistida antecipadamente atraves do arquivo data.sql
 A tabela appuser_roles faz a associacao entre o usuarios e suas roles
![ImgTabelasBanco](https://github.com/PauloMarchon/login-system-spring/assets/28858219/15073378-de00-44ce-ad76-a28307ca22d9)
