# Airbnb Test Framework
This repo contains the Cucumber Java based test framework for SL ABNB assignment.


## Dependencies

The following dependencies need to be installed on your local machine in order to run the tests:
  1. Java 1.8 or higher.
  2. Maven 3 or higer
  3. Chrome Web Browser (any reasonably recent version should be ok)

  ### Installing Dependencies on MacOS
The recommended way to install java and maven on Mac is using [homebrew](https://brew.sh/).
  
**Java Installation**
  ```bash
  $ brew info openjdk
  $ brew install openjdk

  ```
**Maven Installation**
```bash
$ brew install maven
```

## Running the Scenarios
### **Running in Local Web Browser**
You can run the scenarios locally using your one of the installed browsers on your machine. Please note that the browser you send to `mvn test` **must be installed on your machine**. By default running the `mvn test` without any arguments will default to running the tests on your local chrome browser installation.

``` bash
$ mvn test [ browser.name=<chrome|firefox|edge|safari|opera> ]
```

*Note*: For **Safari Web Browser** it is mandatory to manually enable the `Develop -> Remote Automation`  feature from the Menu bar before running the tests
(tested on Version 14.1.1 (16611.2.7.1.4)).

