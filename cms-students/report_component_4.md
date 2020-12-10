# 1. Report [Compenent 4]
@JoãoSantos

## Scripted Jenkins File [Parallel Build]  

### First Test (Hello World)
Testar algumas funcionalidades retiradas das aulas teoricas. 

![CodeTestHelloWord](../images/Report_Component_4/CodeTestHelloWord.png)
![JenkinsTestHelloWorld](../images/Report_Component_4/JenkinsTestHelloWorld.png)

### Pipeline Design

Foi usada o paralelismo no Pipeline recorente aos testes 'Units, Integration e Mutation', essa opção de grupo, deveu-se a facto de que o tanto o 'War file' e o 'Javadoc' devem ser executados de forma sequencial. Para o o 'Deploy do Tomcat' e o 'Smoke test' optou-se por ser após paralelismo devido à importancia destes testes passarem antes de verificar que se encontra disponivel através do 'curl' para o 'Manual Approve' através de uma notificação previa via email ('Email notification').

![PipelineDesign](../images/Parallel_Pipeline.jpg)

### Stage View

![StageView](../images/Report_Component_3/0_pipeline.png)

### Start
Esta é a primeira etapa on apenas é apresentada umas variaveis e ambiente do sistema em questão [env.BUILD_ID, env.JENKINS_URL, env.BUILD_NUMBER, env.WORKSPACE].

![Start](../images/Report_Component_4/Start.png)

### Repository Checkout
Nesta etapa é feito um 'checkout' a partir do repositorio remoto do git (bitbucket.org/1160929/odsoft-m1b_g1.git) do 'branch master' com a minha credencial ID.

![Checkout](../images/Report_Component_4/Checkout.png)

### War File
Esta etapa é utilizado um gradle task ('gradle clean build -g gradle-user-home'). Também é utilizado o 'archiveArtifacts' parar gerar o 'war file' para um caminho previamente escolhido.

![War File](../images/Report_Component_4/Wat_File.png)

### Javadoc
Nesta, o objectivo é gerar o 'javadoc' da aplicação. Através de uma 'gradle task' este mesmo objectivo é cumprido com sucesso.


### Unit Tests

Nesta etapa, através de um gradle taks ('gradle test -g gradle-user-home') e outro ('gradle jacocoTestReport -g gradle-user-home'), é possivel executar e publicar os testes unitarios implementados anteriormente.


### Integration 

Com semelhança do anterior, este teste é executado em paralelo, visto que não contêm nenhuma dependencia. A task gradle 'integrationTest' é executada e de seguida o 'gradle jacocoIntegrationReport' para publicar o relatorio dos testes.


### Mutation Test 

Este é o ultimo teste executado atraves do paralelismo. Utilizando o uma taks 'gradle pitest -g gradle-user-home'.

### Deploy to TomCat

![TomCat](../images/Report_Component_4/Tomcat_server_config.png)

![TomCat](../images/Report_Component_4/Tomcat_start.png)

### Smoke Test

curl --write-out "%{http_code}" --silent --output /dev/null ' + cmsHttp
Este codigo é executado para testar a conexão para ver se o servidor (TomCat) esta 'up' e a correr, para de seguida ser testado manualmente pelo utilizador. 


### Email notification for manual test

![Email_1](../images/Report_Component_4/E-mail_Notification.png)

![Email_2](../images/Report_Component_4/Email.png)

### Manual Approve

![Approve](../images/Report_Component_4/Manual_Approve.png)

### FeedBack

![Tags](../images/Report_Component_4/Tags.png)
