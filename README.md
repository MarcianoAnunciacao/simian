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
export MYSQL_CONTAINER=simiandb
export MYSQL_ROOT_USER=root
export MYSQL_ROOT_PASSWORD=Masterkey321
export MYSQL_HOST=127.0.0.1
export MYSQL_PORT=3306
export MYSQL_DB=simiandb

docker run --rm -p $MYSQL_HOST:$MYSQL_PORT:3306 \
    --name $MYSQL_CONTAINER \
    -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
    -e MYSQL_DATABASE=$MYSQL_DB \
    -e MYSQL_ROOT_HOST=% \
    -d mysql:8.0

docker logs $MYSQL_CONTAINER --follow 

 ```

Para ter certeza de que o mysql está rodando (ele roda na porta 3306),
liste os containers:

```bash
docker container ls
```
### Configuração local

Para entrar no container do mysql com o client:
```bash
docker exec -it  $MYSQL_CONTAINER bash -l


# mysql -uroot -pMasterkey321
```

### Como rodar o projeto local
```bash
mvn spring-boot:run -Dspring.profiles.active=local
```

Por fim, sempre que quiser rodar os testes após fazer alguma alteração, basta digitar:
```bash
mvn test
```

A aplicação ficará disponível na porta 8080. Acesse `http://localhost:8080/health`.

### API

A seguir definimos todos os end-points do serviço e exemplos:
http://localhost:8080/simian/swagger-ui/
