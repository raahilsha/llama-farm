How to Compile and Run our Code
===============================

Prerequisites:

* Install [Java EE SDK 7+](http://www.oracle.com/technetwork/java/javaee/downloads/index.html)
* Add Java to your PATH
* Install [R](http://www.r-project.org/)
* Add R to your PATH

Customizing Parameters:

* Open \llama-farm\code\LlamaFarm\src\Parameters.java
* Read the comments, customize parameters, and save file

Compiling Code:

* Navigate to the \llama-farm\code\LlamaFarm directory
* Run the following command: javac -classpath . -d bin/ src/*.java

Running Code:

* Navigate to the \llama-farm\code\LlamaFarm\bin directory
* Run the following command: java LlamaFarm

Creating Graphs:

* Navigate to the \llama-farm\code\LlamaFarm\bin directory
* (On Windows): Run the MakeGraphs.bat file as Administrator
* (Linux + OS X): Run the MakeGraphs.sh file after correcting permissions
