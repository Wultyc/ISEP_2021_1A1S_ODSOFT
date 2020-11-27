# 1. Report [Compenent 4]
@JoãoSantos

## Scripted Jenkins File [Parallel Build]  

### First Test (Hello World)
![CodeTestHelloWord](../images/Report_Component_4/CodeTestHelloWord.png)
![JenkinsTestHelloWorld](../images/Report_Component_4/JenkinsTestHelloWorld.png)

### Pipeline Design

(Workflow Start)
-> Checkout
-> Build
	-> War File
	-> Javadoc
-> Tests 
	-> Unit Tests
	-> Integration Tests
	-> Mutation Tests
	-> Smoke Test
-> Manual Test
-> Feedback
(Workflow End) 
