# ODSOFT - Projeto Final

## Modelo de Git Branching
De forma a facilitar a organização no desenvolvimento do projeto o grupo definiu o seguinte modelo de *branching* que vai ser utilizado.
A branch **master** será a branch principal e aquela onde estará a versão mais estável do projeto. 
O desenvolvimento irá ocorrer na branch **develop**, sendo feitas derivações desta em branches **feature** que serão incluídas na branch develop assim que aquele desenvolvimento esteja concluído.
Na eventualidade de ser identificado um problema que requeira uma correção rápida da versão em produção (branch master) será feita uma derivação da branch desta versão para uma branch **hotfix**. Concluída a resolução do problema que motivou a correção, a mesma é enviada para as branches master e develop.
![Git Branching Model](./images/Branching_Model.jpg)

## Pipeline
### Esquema da Pipeline
(Imagem ilustrativa do esquema da pipeline)

### Separação dos ambientes de desenvolvimento e produção
Em ambientes corporativos onde existe o real objetivo de disponibilizar software para clientes finais existe de facto a necessidade de ter uma pipeline especifica para cada ambiente, no entanto o grupo entende que esse é um cenário demasiado complexo para o projeto a ser desenvolvido e por isso propõe a seguinte solução: O ficheiro jenkins fica na pasta cms-students (à semelhança do que acontece com os ficheiros equivalentes para o segundo class assignment). No servidor Jenkins existirão dois *jobs*, um para o ambiente de desenvolvimento e um para o ambiente de produção. Os *jobs* fazem checkout do *Jenkins File* da branch correspondente. Desta forma é garantido que o ficheiro que define os passos da pipeline está coerente com o código que está a ser trabalhado.

#### Adaptação da pipeline mediante as necessidades
O ficheiro Jenkins conta ainda ainda com a possibilidade de desativar a execução de alguns stages no ambiente de dev. Desta forma evita-se que passos mais demorados ocorram quando o objetivo da execução for testar outro algum passo.

## Organização das configurações das aplicações
De modo a manter uma total sintonia no grupo face às configurações das diferentes aplicações, o grupo decidiu criar um ficheiro de [configurações](./configurations.md) para manter uma lista atualizada de todas as configurações necessárias para executar o projeto, desde as configurações básicas do Jenkins até às portas onde as aplicações finais estarão à escuta aguardando pedidos.