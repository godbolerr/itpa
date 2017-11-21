# itpa
This application was generated using JHipster 4.6.2, you can find documentation and help at [https://jhipster.github.io/documentation-archive/v4.6.2](https://jhipster.github.io/documentation-archive/v4.6.2).

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with JHipster][] page of the documentation for more information.

This application is configured for Service Discovery and Configuration with . On launch, it will refuse to start if it is not able to connect to .

## Development

To start your application in the dev profile, simply run:

    ./mvnw


For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

### Using angular-cli


For example, the following command:

    ng generate component my-component

will generate few files:

    create src/main/webapp/app/my-component/my-component.component.html
    create src/main/webapp/app/my-component/my-component.component.ts
    update src/main/webapp/app/app.module.ts

## Building for production

To optimize the itpa application for production, run:

    ./mvnw -Pprod clean package

To ensure everything worked, run:

    java -jar target/*.war


Refer to [Using JHipster in production][] for more details.

## Testing

To launch your application's tests, run:

    ./mvnw clean test

For more information, refer to the [Running tests page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.
For example, to start a no database in a docker container, run:

    docker-compose -f src/main/docker/no.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/no.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw package -Pprod docker:build

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[JHipster Homepage and latest documentation]: https://jhipster.github.io
[JHipster 4.6.2 archive]: https://jhipster.github.io/documentation-archive/v4.6.2
[Doing microservices with JHipster]: https://jhipster.github.io/documentation-archive/v4.6.2/microservices-architecture/
[Using JHipster in development]: https://jhipster.github.io/documentation-archive/v4.6.2/development/
[Using Docker and Docker-Compose]: https://jhipster.github.io/documentation-archive/v4.6.2/docker-compose
[Using JHipster in production]: https://jhipster.github.io/documentation-archive/v4.6.2/production/
[Running tests page]: https://jhipster.github.io/documentation-archive/v4.6.2/running-tests/
[Setting up Continuous Integration]: https://jhipster.github.io/documentation-archive/v4.6.2/setting-up-ci/




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


1. 80 GGA Only one row is required

2. 80GGC Only 1 row is required

3. Donation type master values are missing - SCIENTIFIC, POLITICAL, ....

