# Airbnb Test Framework
This repo contains the Cucumber Java based test framework for SL ABNB assignment.


## Dependencies
    - Java >= 1.8

**Check if Java is installed on your machine**
```bash
  $ java -version
```


  ### Installing on MacOS
The recommended way to install java and maven on Mac is using [homebrew](https://brew.sh/).
  
**Java Installation**
  ```bash
  $ brew info openjdk
  $ brew install openjdk
  ```


## Running the Scenarios
Currently the Scenarios reside in [SearchLocation.feature](https://github.com/CaIin/ABNB-Test-Framework/blob/main/src/test/resources/features/SearchLocation.feature).

### **Running in Local Web Browser**
You can run the scenarios locally using your one of the installed browsers on your machine. 
Please note that the browser you want to use` **must be installed on your machine**.
By default running the ```./mvnw``` test without any arguments will default to running the tests on your local Google Chrome browser installation.

``` bash
$ ./mvnw test [ browser.name=<chrome|firefox|edge|safari|opera> ]
```

*Note*: For **Safari Web Browser** it is mandatory to manually enable the `Develop -> Remote Automation`  feature from the Menu bar before running the tests
(tested on Version 14.1.1 (16611.2.7.1.4)).

