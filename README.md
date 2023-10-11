# RelaxTask

This project is a sample Cucumber Java UI and API Test Implementation

### Prerequisites

1. Open JDK 17 (make sure Java class path is set)
2. IntelliJ 
3. IntelliJ Plugin for
    - Cucumber for Java
    - Gherkin

### Installing

1. Open JDK 17: https://openjdk.org/projects/jdk/17/
2. IntelliJ Community Edition:https://www.jetbrains.com/idea/download/#section=windows
3. Open IntelliJ and Go: File/Settings/Plugins and write Cucumber for Java and click install.
   Repeat the same for Gherkin.


## Cloning the repository


```bash
git clone https://github.com/alperoguzkan/RelaxTask.git
```


## WebDrivers

Webdrivers are already located in the project itself. And in the code it refers to relevant user's directory. So you don't need to change the directory. 
Only thing you need to make sure of is that, your browser versions should be compatible with driver versions used:


## Running the tests

There are 2 ways of running test scenarios. 

1)You can directly trigger the feature file without having a report.
- Go to ```src\main\java\Features\1- Login Fail without credentials.feature``` 
feature file and right click, then click  ```Run '1- Login Fail without credentials'```


2)Or you can run with the Runner class- this will creates a report under target file named ```cucumber-report.html```
- Go to ```src\main\java\Runners\Runner``` 
feature file and right click, then click  ```Run 'Runner'```

There is an example report file named ```cucumber-report_example.html``` under target directory.

## config.properties file

This file is created for the control of some high level parameters:

In this file, you can change ```defaultBrowser``` parameter to ```firefox``` or ```edge``` to run in these browsers, by default feature file runs in chrome driver.
