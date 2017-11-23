
TODO:


1. Write unit test include amount with fractions.
2. Address rounding issue.
3. Need input JSON for 80D
4. dependentRelation and relationshipCode in 80DD
4. DependentRelation not taken into account 80DDB -> amount recovered where to use 
5. 80CCC - Pension fund investment constant is missing. .
6. 80CCD1 - Which head/key expense should belong to ?
7. 80CCD2 - Which head/key expense should belong to ?
8. 80CCD1B - Need clarity
9. 80D - Confusion in terms of Insurance or EXPENSE, policyFor ?
10.80DD - Why relationship code exists in the decsion table.
11.80G1 - donationCode - changed to schemeCode







Notes:

1. Atomicity and Independence
2. Take advantage of Inference
3. Ordering of rules should be treated as exception 
4. Creation of Agenda, Addition or deletion of rules from the agenda based on criterion
5. 


<?xml version="1.0" encoding="UTF-8"?>
<kmodule xmlns="http://jboss.org/kie/6.0.0/kmodule">

Original Rules and knowledge base

        
    <kbase name="ItpaDataKb" default="true" packages="com.work.itpa.rules, com.work.itpa.rules.drl">
        
        <!--  Total 17 rules  -->
        
        
        <!-- Rule 80 d -->
        
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80d.drt"
                      row="3" col="4"/>
        
        <!-- Rule 80 d1 -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80d1.drt"
                      row="51" col="4"/>
                      
        <!-- Rule 80 d2 -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80d2.drt"
                      row="66" col="4"/>
                      
                      
        <!-- Rule 80 U -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80u.drt"
                      row="76" col="4"/>
                      
        <!-- Rule 80 DD-->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80dd.drt"
                      row="83" col="4"/>
                      
        <!-- Rule 80 DDB -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80ddb.drt"
                      row="100" col="4"/>
                      
        <!-- Rule 80 E -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80e.drt"
                      row="128" col="4"/>
                      
        <!-- Rule 80 EE -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80ee.drt"
                      row="140" col="4"/>
                      
        <!-- Rule 80 G -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80g.drt"
                      row="148" col="4"/>
                      
        <!-- Rule 80 G1 -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80g1.drt"
                      row="180" col="4"/>
                     
  
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80gg.drt"
                      row="193" col="4"/>
                      
        <!-- Rule 80 GGA -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80gga.drt"
                      row="200" col="4"/>
                      
                      
       <!-- Rule 80 GGC -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80ggc.drt"
                      row="204" col="4"/>
                      
        <!-- Rule 80 QQB -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80qqb.drt"
                      row="208" col="4"/>
                      
        <!-- Rule 80 RRB  -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80rrb.drt"
                      row="212" col="4"/>
                      
        <!-- Rule 80 TTA  -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80tta.drt"
                      row="216" col="4"/>
                      
        <ksession name="ItpaDataKs"/>
    </kbase>     
    
    <kbase name="FindDroolsSessionDecisionTableKb" default="false" packages="com.work.findrools.rules.decisionTable">
        <ksession name="FindDroolsSessionDecisionTable"/>
    </kbase>    
    
    
</kmodule>



      <!-- Rule 80 GGC -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80ggc.drt"
                      row="78" col="2"/>
                      
                      
       <!-- Rule 80 QQB -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80qqb.drt"
                      row="85" col="2"/>
                      
        <!-- Rule 80 RRB  -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80rrb.drt"
                      row="89" col="2"/>
                      
        <!-- Rule 80 TTA  -->
                      
        <ruleTemplate dtable="com/work/itpa/rules/itpa.xls"
                      template="com/work/itpa/rules/itpa_80tta.drt"
                      row="93" col="2"/>         
                      
                      



3. Donation type master values are missing - SCIENTIFIC, POLITICAL, ....

