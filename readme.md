# Class Assignment - 2
---------------------------------------------
## Parallel Pipeline using the Build Pipeline Plug-In

Esta parte do Class Assignment teve como objectivo a implementação de uma pipeline no Jenkins utilizando um plug in. Este plug in foi o Build Pipeline, permitindo, sem a utilização de scripts, implementar uma pipeline com os objectivos suprarreferidos.

### **Checkout e clean**

O job referente a esta parte da pipeline é: **Class-Assignment-Checkout-clean**.

A pipeline inicia com o checkout da branch do bitbucket onde o código fonte está guardado. Executa de igual forma a task clean. 

Após esta execução, e de forma a evitar a reutilização do workspace para evitar futuros problemas devido a commits simultâneos, ou outro tipo de alterações a acontecer no Jenkins/Repositório remoto, é utilizado o Copy artifacts, para poder ser passado o código referente a este checkout ao próximo Job do jenkins, neste caso o War.

-- **Figura relativa ao archive dos artefactos** -- 

### **Build e war**

De seguida, é executado outro job da pipeline, que faz o build e gera o ficheiro .war da aplicação. O código utilizado para este ficheiro ser gerado é o provenienete do último job, checkout, e do seu consequente armazenamento do artefactos. 

O nome do job referente a esta parte é: **Class-Assignment-WAR**.

Aqui simplesmente é executada a task .war do gradle, que gera o ficheiro. De seguida todos os ficheiros são armazenados, também como artefactos, para passar ao próximo passo, a geração do Javadoc.

Nas post-build actions, são armazenados os artefactos.
 
### **Javadoc**

A geração do javadoc é feita no Job **Class-Assignment-Javadoc**.

Ao que se assemelha ao Job *War* este job também utiliza os artefactos (que contêm o código) do ultimo Job.

Depois é feita a geração do Javadoc e a sua consequente publicação. após isso é definido quais os jobs a serem executados após a execução deste job relativo ao **War** é utilizado um plug in (Join trigger) que permite fazer a ramificação e utilizar paralelismo para executar os testes (integration, pit mutation e unit).

-- **Figura relativa ao plugi in do join trigger** --

### **Unit Tests**

A proxima task, a ser executada em paralelo com os integration e mutation tests, são os unit tests, no Job: ** Class-Assignment-Unit-Test**.

É feita a cópia dos artefactos (todos os aretfactos gerados até ao javadoc) para o workspace relativo aos unit tests.

Estes tests são executados utilizando o comando gradle *test* e são publicados a cobertura e os resultados dos testes utilizando os respectivos plug ins, tanto do jacoco como do html publish report.

Neste caso não será necessário fazer trigger do próximo passo, uma vez que este trigger é feito a partir do plug in *'Join Trigger'*, configurado no ultimo Job.

-- **imagem referente aos post build actions**

### **Integration tests**

Paralelamente é executada a task integration tests, sendo o seu nome: **Class-Assignment-Integration-Test**

De maneira semelhante ao que acontece nos unit tests, é feita a cópia dos artefactos e é executada a task integrationTest.

### **Mutation tests**

Também em paralelo, é executado o projeto do jenkins chamado **Class-Assignment-PiTest**, que executa os Mutation Tests.

É executada a task pitests do gradle, e de seguida são publicados os seus Pit mutation reports através pit mutatioon plug in, configurada nas post build actions, como mostra a figura em baixo.

-- ** PIT MUTATION TESTS IMAGE** --

### Deployment

De seguida, é feito o deployment para uma instancia de servidor do tomcat. Este processo pode ser acompnhado no projeto **Class-Assignment-System**.

Inicialmente neste projeto são copiados os artefactos necessários, de momento o único necessário será o ficheiro war, sendo este o unico a ser copiado para o workspace.


De seguida é executada uma post build action que é Deploy war to a container, utilizando o Deploy to container Plugin. A configuração deste deployment utiliza as credenciais necessárias do tomcat para fazer o deployment e a localização da instantância do mesmo.

--**Figura deste deployment**

### Smoke test 

Após o sucesso do projeto anterior, é executado o **Class-Assignment-SmokeTest**.

Este consiste num simples curl que permite saber o estado do servidor depois do deployment, se este é acessível.

### Manual test and aproval

Chegando a este projeto, **Class-Assignment-Manual-Test**, significa que todos os passos anteriores foram executados com sucesso.

Então neste ponto é enviado um email para um utilizador a pedir execução de testes para a posterior aceitação.
Nesta configuração, para além do email, é definida que a proxima ação, o envio de uma tag para o repositorio, é feito com uma aprovação do utilizador. 

Para isto foi utilizado o Editable E-mail notification para o envio do email.

Já para a aprovação do utilizador é utilizada a opção post-build **Build Other projects (manual step)** que permite o utilizador executar manualmente a task identificada na pipeline view.

--**Imagem do build other projects**--

### Envio da tag para um repositório

Por ultimo, esta task é apenas executada se o utilizador assim o permitir e chama-se **Class-Assignment-Build-Tag**.

Aqui é utilizado o Git Plugin e é utilizada a post build action git publisher que permite fazer o push de uma tag para o repositório remoto, tal como demonstra a imagem.

-- **imagem**--