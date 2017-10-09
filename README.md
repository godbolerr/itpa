
Import data from csv files

mongoimport --db itpadoctest --collection decisiondata --upsert --type csv --headerline --file <CSVFile>

80d0_dataset.csv
80d1_dataset.csv
80d2_dataset.csv
80ddb_dataset.csv
80dd_dataset.csv
80ee_dataset.csv
80e_dataset.csv
80g1_dataset.csv
80g2_dataset.csv
80gga_dataset.csv
80ggc_dataset.csv
80gg_dataset.csv
80g_dataset.csv
80qqb_dataset.csv
80rrb_dataset.csv
80tta_dataset.csv
80u_dataset.csv

{
	"id":"template1",
		"status":"active",
	"commaSeperatedFields":"section,residentStatus,assesseeType,relationShipCode,deductionType,minAge,maxAge,maxDeduction",
	"assessmentYear" : 2017,
	"ruleText":"template header
section
residentStatus
assesseeType
relationShipCode
deductionType
minAge
maxAge
maxDeduction

package com.work.itpa.rules;

import java.util.List;
import java.math.BigDecimal;

template 'ATDRules'

rule 'ATD_@{row.rowNumber}'
    when
        $fp : FinPerson( assesseeType == '@{assesseeType}' ,residentStatus == '@{residentStatus}')
        $fpPerson :  Person   (	age  >= @{minAge} , age  <= @{maxAge} ,	relationShipCode == '@{relationShipCode}'  ) from $fp.allPersons 
        $result : FinPersonResult( )
    then
    	Deduction $deduction = new Deduction(BigDecimal.valueOf(@{maxDeduction}),'@{deductionType}','@{section}','@{relationShipCode}  Age: ' + $fpPerson.age  );
    	insert($deduction);
end
end template"
}