# itpa


TODO:

1. Build Base class to create test data
2. Add java.util.time instead of Date
3. Build rules for collections
4. Expand result object

Types of Rules;

1. Validation
2. Calculation
3. Filteration



5. Test parallel invocation of rules

KieServices ks = KieServices.Factory.get();
KieBaseConfiguration kieBaseConf = ks.newKieBaseConfiguration();
kieBaseConf.setOption(MultithreadEvaluationOption.YES);
KieBase kieBase = kieContainer.newKieBase(kieBaseConf);

drools.multithreadEvaluation = true // system property


5. Use of ReactiveList for notifications on modified fields.

<groupId>org.kie</groupId>
<artifactId>kie-maven-plugin</artifactId>
<extensions>true</extensions>
<configuration>
    <instrument-enabled>true</instrument-enabled> 
</configuration>

6. Usage of Rule Units [ Experimental feature ]

7. accumulate function

Long(...) from accumulate(..., sum($p.getLongWeight()))

8. Update session to inform changes to the object

Person me = new Person("me", 40);
FactHandle meHandle = ksession.insert( me );

me.setAge(41);
me.setAddress("California Avenue");
ksession.update( meHandle, me, "age", "address" );


9. Usage of OOPath 



Student( $grade: /plan/exams/grades{ result > ../averageResult } )
Student( $grade: ?/plan/exams{ course == "Big Data" }/grades )

rule "Find all grades for Big Data exam" when
    Student( $grade: /plan/exams{course == "Big Data"}/grades )
then /* RHS */ end

10. Usage of queries

11. Usage of kiebases in one another.

12. agendaeventlistener in kiesession thru kmodule.xml

13. Defining a KieModule programmatically

14. severity of rules and functions for duplicate

// sets the severity of rule updates
drools.kbuilder.severity.duplicateRule = <INFO|WARNING|ERROR>
// sets the severity of function updates
drools.kbuilder.severity.duplicateFunction = <INFO|WARNING|ERROR>


15. Globals and channels.



16. Stateful and stateless. - keywords related to rules.


17. Negative salience for rules which we want to fire in the end.

salience -50

rule "Print balance for AccountPeriod"
        salience -50
    when
        ap : AccountPeriod()
        acc : Account()
    then
        System.out.println( acc.accountNo + " : " + acc.balance );
end


18. Use of ruleflow-group to sequence the execution of rules.






