# Report

## Implementação da pipeline via plugin vs via jenkins file

De certa forma, a implementação utilizando plug-ins pode ser visualmente mais apelativa e intuitiva, no entanto a grande dependência de plug-ins gera alguns problemas neste tipo de implementações, inicialmente exige uma maior pesquisa para familiarização com os mesmos, ao contrário do que acontece com a scripted pipeline.

Inicialmente, também será necessário maior disponibilidade de memória (física) alocada para o armazenamento destes mesmos plug-ins.

## Importação da pipeline para outra máquina

Relativamente às pipelines utilizando os plug-ins do jenkins, para fazer o seu set up numa máquina diferente à originalmente usada para a implementação da pipeline, sem a utilização de um plug-in específico, esta torna-se muito mais complexa e demorada.

A primeira razão é devido à necessidade de instalação de todos os plug-ins necessários para a execução deste tipo de pipelines. Para além deste facto, estes plug-ins requerem uma depêndencia externa que não é ideal, por exemplo caso o plug-in em questão não esteja disponível ou uma versão desatualizada é disponibilizada podendo ter um efeito prejudicial na implementação da pipeline. 
Para além de que nem todas as tarefas são possíveis de serem concluidas através de plug-ins estando assim dependentes de fatores externos.

Em segundo lugar, devido a várias variáveis de configuração ao longo de todos os plug-ins utilizados durante a criação da pipeline, estas podem dificultar na implementação, como por exemplo, a troca de credenciais, para fazer checkout de um determinado repositório remoto. Apesar destes cuidados também serem necessários nas scripted pipelines, são de mais fácil e intuitiva resolução.

Já relativamente a scripted pipelines, a importação da mesma é feita através de um JenkinsFile, que permite uma migração mais rápida entre duas máquinas diferentes, não tendo que haver um set up tão complicado como no caso das pipelines utilizando plug-ins.

## Comparação de performance e tempos de execução

### Sequencial VS Paralelo
falar sobre a necessidade de um runner para rodar os varios jobs
falar dos outputs misturados

### Plugin VS Scripted