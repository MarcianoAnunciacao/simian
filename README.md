# Simian

### Descrição

API que realiza a checagem de DNA para saber se o DNA pertence a um humano ou a um simio.

### Recursos externos

O Simian possui como dependências externas:
- Um banco relacional MySQL chamado **simiandb**

## 1. Pré-requisitos de instalação

- **1.1 Java 8**: Java 8 ou Superior.
- **1.2 Docker**: Plataforma para criar e executar containers em ambiente Linux.

#### 1.1 Java JDK 8

**Sdkman**: Instale esse gerenciador de pacotes de desenvolvimento pra facilitar a manutenção de dependências com versões diferentes do Java. Execute cada linha de comando, uma após a outra, separadamente no seu terminal Linux.
```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version
```
**Java JDK 8**: Instale o Java JDK versão 8 ou superior. Execute cada linha de comando, uma após a outra, separadamente no seu terminal Linux.
```bash
sdk list java
sdk install java 8.0.212-zulu
sdk use java 8.0.212-zulu
```

#### 1.2 Docker

Execute separadamente cada linha de comando abaixo no seu terminal Linux.
```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update
apt-cache policy docker-ce
sudo apt-get install -y docker-ce
```

Por fim, use esse comando para checar o status da sua instalação Docker.
```bash
sudo systemctl status docker
```

#### MySQL

crie um container com o MySQL 8.0 da seguinte forma:

```bash
sudo docker run --name simiandb -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_ROOT_HOST=% -p 3306 --net=host -d mysql/mysql-server:8.0
```

Para ter certeza de que o mysql está rodando (ele roda na porta 3306),
liste os containers:

```bash
sudo docker container ls
```
### Configuração local

Entre no MySQL via usuário root e crie 1 base com o nome **simiandb**:
```bash
docker exec -it simian bash -l
CREATE DATABASE simiandb;
```

### Como rodar o projeto local

Primeiro, é necessário compilar todo o projeto:
```bash
mvn clean build
``` 

Depois, para subir a aplicação basta digitar na linha de comando:
```bash
mvn run
```

Por fim, sempre que quiser rodar os testes após fazer alguma alteração, basta digitar:
```bash
mvn clean test
```

A aplicação ficará disponível na porta 8080. Acesse `http://localhost:8080/health`.

### API

A seguir definimos todos os end-points do serviço e exemplos.

#### Checagem de pendências de CPF

`POST` `/events` Payload:
```json
{
  
}
```

Response:
```json
{

}
```
