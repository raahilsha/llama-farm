How to Compile and Run our Code
===============================

Prerequisites:

* Install [Java EE SDK 7+](http://www.oracle.com/technetwork/java/javaee/downloads/index.html)
* Add Java to your PATH
* Install [R](http://www.r-project.org/)
* Add R to your PATH
* (Optional) Install [Eclipse EE](http://eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/lunasr2) to make things easier

Customizing Parameters:

* Open \llama-farm\code\LlamaFarm\src\Parameters.java
* Read the comments, customize parameters, and save file

Compiling Code (Without Eclipse):

* Navigate to the \llama-farm\code\LlamaFarm directory
* Run the following command: javac -classpath . -d bin/ src/*.java

Running Code (Without Eclipse):

* Navigate to the \llama-farm\code\LlamaFarm\bin directory
* Run the following command: java LlamaFarm

Creating Graphs (Without Eclipse):

* Navigate to the \llama-farm\code\LlamaFarm\bin directory
* (On Windows): Run the MakeGraphs.bat file as Administrator
* (Linux + OS X): Run the MakeGraphs.sh file after correcting permissions
* Images will appear in the current directory

Compiling and Running Code (With Eclipse):

* Open Eclipse
* Set the working directory to \llama-farm\code
* Click the green run button at the top bar
* Code will automatically compile and run

Creating Graphs (With Eclipse):

* Navigate to the \llama-farm\code\LlamaFarm\ directory
* (On Windows): Run the MakeGraphs.bat file as Administrator
* (Linux + OS X): Run the MakeGraphs.sh file after correcting permissions
* Images will appear in the current directory
