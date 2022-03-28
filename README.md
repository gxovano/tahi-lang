# tahi-lang
## Passos para compilar e executar
Utiliza-se o Apache Ant para buildar e pode-se utilizá-lo para executar. 

Diretivas disponíveis:
* ant run : executa, caso exista, o jar (Main.jar) em build/jar com o argumento assets/ex1.tahi
* ant runDev : compila as classes, builda o jar e executa-o com o argumento assets/ex1.tahi
* ant build : somente compila as classes
* ant jar : compila as classes e builda o jar

Pastas do projeto:
* assets : pasta com os fontes em .tahi ;
* src : fontes do projeto ;
* build : após executar o Ant, será criada a pasta build ;
     *  build/classes : classes compiladas ;
     *  build/jar : jar do projeto


