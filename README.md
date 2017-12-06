
Section completed

1. 80qqb
2. 80rrb
3. 80tta
4. 80gga
5. 80ggc
6. 80g
7. 80c - Premium Aggregation - Need to discuss, Break into smaller chunks based on type of head
8. 80gg
9. 80e
10. 80ccd1b
11. 80ccc
12. 80CCD1 - Testing is not complete
13. 

1. To run the server

mvn spring-boot:run -Dserver.port=8888


2. To run the server without running test 

mvn spring-boot:run -Dmaven.test.skip=true -Dserver.port=8888


3. To test all the scenarios

mvn test


4. To access the functionality, use POST with the following URL

	http://localhost:8080/itpa/benefits
	
5. Sample JSON for input can be found in src/test/resources folder.



Issues:

	a.Standalone war file is not able to load Rule related packages. 
	b.Summary calculations are still in Java codebase. Need to extract it out into rules.
	c.Rule package need to be seperated all together and built as seperate deployable.
	d. hraAvailed -> is it boolean or String, [ FALSE or false ]
	
	
	 
TODO:
	
	
	1. Work with logging
	2. Fractions in amount
	3. Rounding
	4. Config Service
	5. KJAR implementation in Drools
	6. Equals,hashcode and toString for each domain object.
	7. Add Unit Tests to Cover all the data scenarios for all sections. 
	8. Write rules with unit tests for the sections remaining 
	9. Handle NPE in rules
	10. 80CCD
	
	
	
	





