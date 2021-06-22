# Airbnb Test Framework

This repo contains the Cucumber Java based test framework for SL ABNB assignment.

## Dependencies

## Java >= 1.8

### Check if Java is installed on your machine

```bash
  $ java -version
```
Note: If the version on your machine is greater than 1.8.0 you can skip to running the scenarios.

### MacOS

The recommended way to install java and maven on Mac is using [homebrew](https://brew.sh/).


  ```bash
  $ brew info openjdk
  $ brew install openjdk
  ```

### Windows

Please follow the steps in [here](https://stackoverflow.com/a/52531093).

### Ubuntu


```bash
$ sudo apt-get install openjdk-11-jdk
```

## Running the Scenarios

Currently, the Test Scenarios reside
in [SearchLocation.feature](https://github.com/CaIin/ABNB-Test-Framework/blob/main/src/test/resources/features/SearchLocation.feature) file.

### Running in Local Web Browser

You can run the scenarios locally using your one of the installed browsers on your machine. Please note that the browser
you want to use **must be installed on your machine**. By default running the ```./mvnw``` test without any arguments
will default to running the tests on your local Google Chrome browser installation.


``` bash
$ ./mvnw test [ browser.name=<chrome|firefox|edge|safari|opera> ]
```

**Safari Web Browser**


For Selenium WebDriver to be able to control the Safary Web Browser it is mandatory to manually enable the `Develop -> Remote Automation`  feature from the Safari Menu bar before running the tests. **Warning**: Runs on safari are unstable at the moment.


### Running tests in Docker (Selenium Grid in Docker)
Please note that in order to be able to run the tests in Docker locally on your machine you need to have Docker installed. 


**Start the Docker Selenium Grid**


```bash
$ docker-compose up -d
```

**Run the tests using the Docker Selenium Grid**


```bash
$ ./mvnw test -Dbrowser.remote=true [-Dbrowser.name=<chrome|firefox>]
```


### Generating Test Report
After running the tests, you can generate the test report (allure) by running 
```bash
$ ./mvnw allure:serve
```
After issuing the Command the test report should be opened in your default Web Browser
