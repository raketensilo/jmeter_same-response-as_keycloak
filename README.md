# JMeter_same-response-as_keycloak
## Using JMeter to assert actual against expected Json responses
#### applied on the Admin API of a Keycloak authentication server

***
*This repository in a series of similar repositories for API testing intended for a specific workflow that compares expected against actual API responses*

### (I) The problem

The following are typical workflows for API development and testing

* **Regression testing of an existing API or backend**
* **Development of a new API**
* **Integration testing at API-level**

For more details about the problem please go to repository [`restassured_same-response-as_keycloak`](https://github.com/raketensilo/restassured_same-response-as_keycloak)

### (II) A solution attempt with JMeter

Using
* `JMeter` - popular and well-known REST-API client with a GUI and often uses for performance testing
* testing it on the `Keycloak Admin API` of a Keycloak Authentication Server that runs inside a `Docker` container

Versions used (other versions may differ)
* JMeter 5.0
* Keycloak 4.3.0.Final

### (III) How to get started
#### 1. Download this repository
* Use git or your browser to download this repository to your platform

#### 2. Download and install JMeter
* Download the app `Apache JMeter 5.0` or higher from [https://jmeter.apache.org/](https://jmeter.apache.org/download_jmeter.cgi)
* Install JMeter on your system

#### 3. Import JAR and start JMeter
1. Copy the JAR-file `jar/jmeter-sameresponseas-0.1.jar` from this repository to the to directory where you have installed JMeter with destination `apache-jmeter-5.0/lib/`
1. Run JMeter either via `apache-jmeter-5.0/bin/jmeter.bat` or `apache-jmeter-5.0/bin/jmeter.sh` depending on your platform

#### 4. Open the `keycloakRealmsRolesUsers` JMeter project
1. Inside JMeter, go to `File > Open` and select `jmx/keycloakRealmsRolesUsers.jmx` and open it
1. Click on `kc1.properties` and edit the `basePath` to a folder on your system that will be used by JMeter as workspace

#### 5. Use Docker to start 2 Keycloak server
* make sure you have `docker` installed
* go into the `docker` subfolder of this repository and run `docker-compose up` to start 2 docker containers each containing a keycloak server

### (IV) Usage instructions
#### 1. Test example
![](./_images/ListOfRoles/1-request.png)

![](./_images/ListOfRoles/2-responseAssertion.png)

![](./_images/ListOfRoles/4-responseAssertionResult.png)
#### 2. Run group of tests against kc1 (Keycloak 1) to record expected responses
![](./_images/test-run/kc1a.png)

![](./_images/test-run/kc1b.png)
#### 3. Re-run group of tests against kc2 to compare actual and expected response
![](./_images/test-run/kc2a.png)

![](./_images/test-run/kc2b.png)
