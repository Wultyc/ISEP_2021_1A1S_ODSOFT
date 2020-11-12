# Aplicação da Calculadora
Cada componente esteve encarregado de adicionar os operadores pedidos (factorial, third, double e exponential).
 
imagem calculadora
 
Foram feitos testes unitários para testar cada operador.
 
No Goal 3, responsável pela geração dos gráficos e do javadoc, há 3 maneiras diferentes de efetuar a mesma ação. As outras formas de chegar ao mesmo resultado estão comentadas no código, tanto no ficheiro RenderPlantUmlTask.groovy como no build.gradle.
Na forma não comentada, é feita uma cópia dos ficheiros para os caminhos.
 
![calculator javadoc](https://bitbucket.org/1160929/odsoft-m1b_g1/raw/4687cab146931e9bd9a227d5386ff7e517424937/images/calculator-javadoc.PNG)
 

# Aplicação CMS
 
## Analysis/Design
 
Foram efetuados os passos para a geração do diagrama pedido, conforme o exercício anterior.
Cada componente tem o seu diagrama de sequência. Cada diagrama de sequência representa uma função diferente e foi elaborado em consideração da respectiva funcionalidade.
Após essa implementação é testado o programa localmente usando o comando -gradle gwtRun, que permite correr e fazer build do projeto.
Após este teste é configurado o jenkins para que o projeto possa ser executado com base no mesmo utilizando desta vez o repositório remoto.
No Jenkins é gerado é publicado o ficheiro war e o javadoc. Estes ficheiros foram gerados e publicados segundo a definição na configuração do workspace utilizado.
 
![](https://bitbucket.org/1160929/odsoft-m1b_g1/raw/4687cab146931e9bd9a227d5386ff7e517424937/images/jenkins_pub.PNG)
 
## Development/Testing
 
Foi adicionada uma funcionalidade ao projeto cuja função será semelhante à previamente existente. Esta implementação foi feita pelo grupo em conjunto. 
De igual forma à track anterior foi testado inicialmente localmente e após esses testes o build da aplicação foi feito no jenkins.
Também no jenkins, e com auxílio de alguns plug ins (jacoco, publish html reports, publish junit test reports) foram gerados e publicados gráficos relativos aos testes efetuados automáticamente.

![](https://bitbucket.org/1160929/odsoft-m1b_g1/raw/4687cab146931e9bd9a227d5386ff7e517424937/images/warehouse-cms4.PNG)
![](https://bitbucket.org/1160929/odsoft-m1b_g1/raw/4687cab146931e9bd9a227d5386ff7e517424937/images/cms4-testes.PNG)
 
## Countinuous integration
 
Os testes foram feitos à semelhança da track anterior. Quando aos testes de mutação, estes não pouderam ser implementados visto que o plug in não estava disponivel para instalação.
Os testes unitários foram feitos com base nos anteirores.
![](https://bitbucket.org/1160929/odsoft-m1b_g1/raw/4687cab146931e9bd9a227d5386ff7e517424937/images/war-javadoc-4.PNG)

## Issues
Cada uma das tasks foi devidamente comentada nos issues presentes neste repositório.

## Problemas encontrados
Foram sentidas algumas dificulades na execução das tarefas que foram sendo superadas ao longo do tempo. Algumas tarefas foram tioveram a sua execução prejudicada por problemas tecnicos nos repositórios de plugins do jenkins

## Elementos do grupo
Nuno Dinis, Ana Rita Rodrigues, Jorge Azevedo, João Santos