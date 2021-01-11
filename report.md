# 2.3 Code Quality and Integration Tests

"CheckStyle" Plugin

O Checkstyle plugin realiza verificações de qualidade nos ficheiros de origem Java do projeto usando Checkstyle e gera relatórios dessas verificações.
Para este projeto, decidimos usar o plugin checkstyle das ferramentas "puppycrawl".
#### Como usar?
Para integrar este plugin no projeto GWT foi necessário introduzir no ficheiro build.gradle:


1. Introduzir o plugins do checkstyle ID 

    id "checkstyle"
    
2. Adicionar a dependência do checkstyle na secção


    compile 'com.puppycrawl.tools:checkstyle:8.27'
    
3. Adicionar umas configurações relativas ao checkstyle

    checkstyle {
        configFile = rootProject.file('config/checkstyle/checkstyle.xml') //root do config file do checkstyle
        toolVersion = '8.27' //versão usada
        checkstyleTest.enabled = true // permite que faça análise às classes de teste
        showViolations = false //caso seja necessário mostrar validação, mudar para true
        sourceSets = [] // fonte se necessário
    }


4. Adicionar a task do Checkstyle

    tasks.withType(Checkstyle) {
        reports {
            xml.enabled true //permite gerar relatório xml
            html.enabled true //permite gerar relatório HTML
            html.stylesheet resources.text.fromFile('config/xsl/checkstyle-simple-check-style.xsl') //root do stylesheet do checkstyle
        }
        // retirado de https://bit.ly/34Hj1gT
        def maxWarnings = 7500 //máx de avisos que permite
        doLast {
            reports.all { report ->
                def outputFile = report.destination //destino do relatório
                //verificar numero de avisos ("erros")
                if (outputFile.exists()) {
                    def count = outputFile.text.count("<error ")
                    if (count > maxWarnings) {
                        throw new GradleException("[Threshold=$maxWarnings] There were $count checkstyle warnings! Check $outputFile")
                    }
                }
            }
        }
    }



