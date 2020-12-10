# Report

## Implementação da pipeline via plugin vs via jenkins file

A implementação utilizando plug-ins pode ser visualmente mais apelativa e intuitiva, no entanto a grande dependência de plug-ins gera alguns problemas neste tipo de implementações, que inicialmente exige um maior tempo na preparação do ambiente para a execução. No entanto após todo este processo esta abordagem torna-se menos penosa nomeadamente para utilizadores que não tenham conhecimento de linguagens de programação, nomeadamente Groovy.

Inicialmente, também será necessário maior disponibilidade de memória (física) alocada para o armazenamento destes mesmos plug-ins.

## Importação da pipeline para outra máquina

Relativamente às pipelines utilizando os plug-ins, para fazer o seu set up numa máquina diferente à originalmente usada para a implementação da pipeline, sem a utilização de um plug-in específico, esta torna-se muito mais complexa e demorada.

A primeira razão é devido à necessidade de instalação de todos os plug-ins necessários para a execução deste tipo de pipelines. Para além deste facto, estes plug-ins requerem uma depêndencia externa que não é ideal, por exemplo caso o plug-in em questão não esteja disponível ou uma versão desatualizada é disponibilizada podendo ter um efeito prejudicial na implementação da pipeline. 
Para além de que nem todas as tarefas são possíveis de serem concluidas através de plug-ins estando assim dependentes de fatores externos.

Em segundo lugar, devido às diferentes configurações ao longo de todos os plug-ins utilizados durante a criação da pipeline, estas podem dificultar na implementação, como por exemplo, a troca de credenciais, para fazer checkout de um determinado repositório remoto. Apesar destes cuidados também serem necessários nas scripted pipelines, são de mais fácil e intuitiva resolução.

Já relativamente a scripted pipelines, a importação da mesma é feita através de um JenkinsFile, que permite uma migração mais rápida entre duas máquinas diferentes, não tendo que haver um set up tão complicado como no caso das pipelines utilizando plug-ins.

## Comparação de performance e tempos de execução

## Tempos de execução

| Build Pipeline Sequencial | Build Pipeline Paralelo | Scripted Pipeline Sequencial | Scripted Pipeline Paralelo |
|:-------------------------:|:-----------------------:|:----------------------------:|:--------------------------:|
|          624.26 s         |         463.71 s        |              600 s           |            437,6 s         |


### Sequencial VS Paralelo
O facto de executar várias tasks ao mesmo tempo ajuda na melhoria dos tempos de execução da pipeline. Nestes testes atigiu-se aproximadamente 25% de ganho em tempo nas pipelines paralelas face às sequenciais. No entanto este processo exige que existam mais runners disponiveis para que as tarefas possam ser paralelizadas o que obriga a que exista uma máquina (ou cluster) com maior poder computacional e pode atrasar outras execução. Além disto os logs apresentados estão misturados o que pode dificultar o debug.

![exemplo de logs na scripted pipeline paralela](images/Parallel_Example_Build.png)

### Plugin VS Scripted
Neste ponto as scripted pipelines assumem uma ligeria vantagem face às pipelines construidas com plugins correspondentes (aprox. 5%), principalmente devido ao facto destas ultimas basearem-se em pequenos jobs executado um após o outro enquanto as primeiras formam apenas um job.

## Conclusões
As pipelines feitas com base em plugin são uteis para projetos mais pequenos e proof-of-concepts (POC) no entanto as scripted monstram-se muito mais versateis. Quanto ao balanceamento sequencial/paralelo este deve ser estudado mediante o caso pois demasiado paralelismo numa pipeline pode por em risco a execução de outras no mesmo servidor/cluster.