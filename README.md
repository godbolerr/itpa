
Section completed

1. 80qqb
2. 80rrb
3. 80tta
4. 80gga
5. 80ggc
6. 80g
7. 80c


1. To run the server

mvn spring-boot:run


2. To run the server without running test 

mvn spring-boot:run -Dmaven.test.skip=true


3. To test all the scenarios

mvn test


Issues:

	Standalone war file is not able to load Rule related packages. 
	Summary calculations are still in Java codebase. Need to extract it out into rules.
	Rule package need to be seperated all together and built as seperate deployable.
	
	
	 






