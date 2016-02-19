# quartz-test

In order tu make this work you have to make sure the database is created on mysql which is the db I prepared this project for, then execute the script inside resources and later you can do

gradle -DDEBUG=true|false bootRun 

the app will be executed

A couple of things to note is that this project shows how to deploy quartz in a cluster environment and besides that some of the classes are annotated as: @ConditionalOnProperty(name = "quartz.enabled") with this spring will know if these classes will be injected or not.


