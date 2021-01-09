# Configurações
Neste ficheiro estão descritas as configurações das aplicações e serviços utilizados no desenvolvimento do Projeto Final de ODSOFT.

## Portas das aplicações
| Aplicação    | Porta |
|--------------|-------|
| Jenkins      | 8090  |
| Tomcat Local | 8091  |

## Configuração de credenciais do Jenkins
Para configurar o envio de emails do Jenkins, é utilizada uma conta de Gmail

| ID              | Username                         | Password |
|-----------------|----------------------------------|----------|
| bitbucket-creds | (email de cada dev)              | •••••••• |
| n/a             | notificationsystemisep@gmail.com | •••••••• |

## Configurar o Jenkins
### GIT
Para configurar o GIT, deve ser feita a seguinte configuração na página **Configurar Sistema** dentro de **Gerir o Jenkins** na secção **GIT**
O valores a ser colocados variam consoante o propósito. O dados apresentados são fictícios
![](./images/config_git.png)

De seguida deve aceder à página **Configuração Global** e na secção **GIT** configurar o acesso ao git na máquina local
![](./images/config_git_2.png)

### JDK
Para configurar o JDK, deve ser feita a seguinte configuração na página **Configurar Sistema** dentro de **Configuração Global** na secção **JDK**
O valores a ser colocados variam consoante o sistema.
![](./images/config_jdk.png)

### Gradle
Para configurar o Gradle, deve ser feita a seguinte configuração na página **Configurar Sistema** dentro de **Configuração Global** na secção **Gradle**
O valores a ser colocados variam consoante o sistema.
![](./images/config_gradle.png)

### Email
Para configurar o envio de email, deve ser feita a seguinte configuração na página **Configurar Sistema** dentro de **Gerir o Jenkins** na secção **Extended E-mail Notification**

| Serviço      | URL                 | Porta | SSL   |
|--------------|---------------------|-------|-------|
| SMTP         | smtp.gmail.com      | 465   | Yes   |

![](./images/config_email.png)