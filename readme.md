# ODSOFT - Projeto Final

## Modelo de Git Branching
De forma a facilitar a organização no desenvolvimento do projeto o grupo definiu o seguinte modelo de *branching* que vai ser utilizado.
A branch **master** será a branch principal e aquela onde estará a versão mais estável do projeto. 
O desenvolvimento irá ocorrer na branch **develop**, sendo feitas derivações desta em branches **feature** que serão incluídas na branch develop assim que aquele desenvolvimento esteja concluído.
Na eventualidade de ser identificado um problema que requeira uma correção rápida da versão em produção (branch master) será feita uma derivação da branch desta versão para uma branch **hotfix**. Concluída a resolução do problema que motivou a correção, a mesma é enviada para as branches master e develop.
![Git Branching Model](./images/Branching_Model.jpg)

## Pipeline
(nota: o deploy para o ambiente dev e prod vai ser feito com base no mesmo jenkins file mas na branch correspondente. para agilizar a execução do job vai ser utilizado variaveis de ambiente para definir se o stage é executado ou não)