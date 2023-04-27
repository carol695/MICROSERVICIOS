# TAREA DE MICROSERVICIOS

1. Diseñe un API y cree un monolito  Quarkus que permita a los usuarios hacer posts de 140 caracteres e ir registrandolos en un stream único de posts (a la Twitter). Piense en tres entidades Usuario, hilo(stream), posts.
2. Cree un aplicación JS para usar el servicio. Depliegue la aplicación en S3. Asegúrese que esté disponible sobre internet.
3. Pruebe la aplicación Web
4. Agregue seguridad usando JWT con el servicio cognito de AWS.
5. Separe el monolito en tres microservicios independientes.
6. Despliegue el servicio en AWS con EC2 y realice pruebas (Puede usar docker o 3 máquinas AWS)
7. Entregue el código desarrollado en Github, un reporte de la arquitectura, un reporte de las pruebas, y un video con el experimento funcionando y bien configurado (Todo en el README).
**** 

## :newspaper: DISEÑO 

La arquitectura consiste en conectar un cliente por medio del protocolo seguros https, que es la convinacion de ssl y http y a su vez se debe crear otro servidor que también sea accesible por https pero estos servidores deben poder conectarse mutuamente entre si por un medio seguro como lo es https.

## :mag_right: ARQUITECTURA

![image](https://user-images.githubusercontent.com/63822072/234689996-77e6c5e7-7421-4baf-b28d-855f0c9806ce.png)


****

## :bulb:CONSTRUCCIÓN 

### :label: CREACIÓN DE INSTANCIA

![image](https://user-images.githubusercontent.com/63822072/234681937-183c2ccc-08f0-453d-8bb4-3117a453d83e.png)

## DESPLIEGUE
Para clonar la aplicacion utilice el siguiente comando:
```
git clone https://github.com/carol695/QUARKUS-AREP.git
```
Una vez descargado acceda a la carpeta y corra el siguiente comando
```
mvn clean install
```
Despues empaquetamos con el siguiente comando
```
mvn package
```

Finalmente para desplegar la aplicación de forma local use el siguiente comando
```
mvn compile quarkus:dev
```

![image](https://user-images.githubusercontent.com/63822072/234682566-f3259bc7-e860-40a4-a51d-1b1633c8f4b1.png)


## DESPLIEGUE EN AWS

En primera intancia creamos la imagen en docker

```
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/code-with-quarkus-jvm .
```

Posteriormente corremos usando el siguiente comando

```
docker run -i --rm -p 8080:8080 quarkus/code-with-quarkus-jvm
```

Luego le hacemos push con el siguiente comando 

```
docker push carolcely14/taller-microservicios:latest
```

![image](https://user-images.githubusercontent.com/63822072/234683385-d7b3060a-0546-4fc9-8669-067941c9cf37.png)


Una vez se tenga la instancia de una máquina EC2 en la nube, agregamos tres reglas de entrada
en los puertos (42000, 42001 y 42002).

![image](https://user-images.githubusercontent.com/63822072/234683532-c0c5ae1e-00b2-45ea-ad6f-4e5e1c853275.png)

seguido a esto instale docker en la consola de la máquina con los siguientes comandos:

```
sudo yum update -y
sudo yum install docker
sudo service docker start
```
Una vez instalado docker corremos el siguiente comando con los diferentes puertos que queramos colocar

```
docker run -d -p 42000:8080 --name quarkus1 carol695/taller-microservicios
```

Para ver las imagenes:

````
docker ps
````

![image](https://user-images.githubusercontent.com/63822072/234684057-90405b7d-4376-48e9-8543-4f92e609194b.png)

### :chart: VIDEO

https://www.youtube.com/watch?v=Jtbt7l1zfKc

****
### :computer: Prerrequisitos

-   [Git](https://git-scm.com/downloads) - Sistema de control de versiones
-   [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias
-   [Java 8](https://www.java.com/download/ie_manual.jsp) - Entorno de desarrollo
-   [Intellij Idea](https://www.jetbrains.com/es-es/idea/download/) (Opcional)

****

### :bulb: Construido con

* [Maven](https://maven.apache.org/) - Dependency Management
* [AWS](https://aws.amazon.com/) - Instancia EC2


## :woman: Actores

* **Carol Tatiana Cely Rodriguez** 
