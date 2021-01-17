# 2.3 Code Quality and Integration Tests

### "CheckStyle" Plugin

O Checkstyle plugin realiza verificações de qualidade nos ficheiros de origem Java do projeto usando Checkstyle e gera relatórios dessas verificações.
Para este projeto, decidimos usar o plugin checkstyle das ferramentas "puppycrawl".
#### Como usar?
Para integrar este plugin no projeto GWT foi necessário introduzir no ficheiro build.gradle:


1. Introduzir o plugins do checkstyle ID 


   > id "checkstyle"
    
2. Adicionar a dependência do checkstyle na secção


   > compile 'com.puppycrawl.tools:checkstyle:8.27'
    
3. Adicionar umas configurações relativas ao checkstyle
     
   > checkstyle {
    
   >     configFile = rootProject.file('config/checkstyle/checkstyle.xml') //root do config file do checkstyle
        
   >     toolVersion = '8.27' //versão usada
        
   >     checkstyleTest.enabled = true // permite que faça análise às classes de teste
        
   >     showViolations = false //caso seja necessário mostrar validação, mudar para true
        
   >     sourceSets = [] // fonte se necessário
   > }
   
   > //executa o checkstyle nos arquivos de origem Java de produção
   
   > checkstyleMain {
   
   >     source ='src/main/java'
   
   > }
   
   > //executa o checkstyle nos arquivos de origem Java de teste
   
   > checkstyleTest {
   
   >     source ='src/test/java'
   
   > }


4. Adicionar a task do Checkstyle
   
   > tasks.withType(Checkstyle) {
    
   >     reports {
        
   >         xml.enabled true //permite gerar relatório xml
            
   >         html.enabled true //permite gerar relatório HTML
            
   >         html.stylesheet resources.text.fromFile('config/xsl/checkstyle-simple-check-style.xsl') //root do stylesheet do checkstyle
            
   >     }
        
   >     // retirado de https://bit.ly/34Hj1gT
        
   >     def maxWarnings = 7500 //máx de avisos que permite
        
   >     doLast {
        
   >         reports.all { report ->
            
   >             def outputFile = report.destination //destino do relatório
                
   >             //verificar numero de avisos ("erros")
                
   >             if (outputFile.exists()) {
                
   >                 def count = outputFile.text.count("<error ")
                    
   >                 if (count > maxWarnings) {
                    
   >                     throw new GradleException("[Threshold=$maxWarnings] There were $count checkstyle warnings! Check $outputFile")
                        
   >                 }
                    
   >             }
                
   >         }
            
   >     }
        
   > }
    

    
### "FindBugs" Plugin

O Findbugs plugin realiza também verificações de qualidade nos ficheiros de origem Java do projeto usando FindBugs e gera relatórios dessas verificações.
Para este projeto, decidimos usar o plugin checkstyle das ferramentas "puppycrawl".
#### Como usar?
Para integrar este plugin no projeto GWT foi necessário introduzir no ficheiro build.gradle:


1. Introduzir o plugins do findbugs ID 


   > id "findbugs"
    
2. Adicionar umas configurações relativas ao findbugs

   > findbugs {
    
   >     ignoreFailures = true // permite que a build continue se houver avisos
        
   >     toolVersion = '3.0.1' //versão usada
        
   >     excludeFilter = file("config/findbugs/excludeFilter.xml") //caminho do ficheiro que específica os bugs a serem excluídos do relatório
    
   >     effort = "max" //nível de esforço de análise. max->aumenta a precisão e encontra mais bugs, demorando mais tempo de execução e mais consumo de memória
        
   >     reportLevel  = "low" //específica o limite de confiança/prioridade para relatar problemas. low ->a confiança não é usada para filtrar bugs
        
   >     sourceSets = [] // fonte se necessário para a tarefa check e build
   
   > }


4. Adicionar a task do FindBugs

   > tasks.withType(FindBugs) {
    
   >     reports {
        
   >         xml.enabled false //permite gerar relatório xml se tiver true
            
   >         html.enabled true //permite gerar relatório HTML, apenas um (ou xml ou html) pode estar enabled pois entra em conflito
            
   >         html.stylesheet resources.text.fromFile('config/xsl/checkstyle-simple-find-bugs.xsl') //root do stylesheet do findbugs
            
   >     }
        
   > }
    
    
# Pipeline
Adicionar a imagem do formato final da pipeline e justificar os motivos para a atualização
temos de definir como vai ser o trigger da pipeline. falou-se em ser Master trigger automatico, develop trigger manual
# Disponibilidação das aplicações em containers
Excplicar que para o ambiente de desenvolvimento foi usado o docker compose para facilitar a a orquestração dos containers
(?) e que em prod vamos usar o K8S

# Tasks do gradle
Para facilitar o desenvolvimento da aplicação e tendo em conta que havia a necessidade de colocar as creds da DB na classe java e que o URL da BD muda quando estamos no Docker (porque os containers estão numa network interna do docker) foram criadas algumas tasks novas no gradle para subir estes containers


