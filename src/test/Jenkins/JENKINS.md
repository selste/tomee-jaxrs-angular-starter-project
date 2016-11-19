Docker image for this demo
==========================
Based on Docker images provided for
  - the [workflow-aggregator-plugin](https://github.com/jenkinsci/workflow-aggregator-plugin)
  - the tutorial [Putting Jenkins in a Docker Container](https://engineering.riotgames.com/news/putting-jenkins-docker-container)

Information on how to set up Oracle's JDK gleaned from [How to install Oracle Java 8 in Debian via Repository](http://www.webupd8.org/2014/03/how-to-install-oracle-java-8-in-debian.html) 

Steps to get it up and running
------------------------------
Execute

    docker build -t thebutler .

Afterwards run the freshly created image with

    docker run -p 8080:8080 --name=jenkins-master thebutler

Jenkins will start up, then open [http://localhost:8080]() in a browser to step through the configuration.
To get at the hash to unlock Jenkins run

    docker exec jenkins-master cat /var/jenkins_home/secrets/initialAdminPassword

Click the *Install suggested plugins* button, this will ensure that most of the plugins needed to run
the Build Pipeline for this project will be installed.
After creating an administrative account proceed and install the [Pipeline Maven Integration Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Maven+Plugin)


Apache Maven 3.3.9 is part of the image, located at `/usr/local/maven`.
OpenJDK is available as well, under `/usr/lib/jvm`.

To stop and (re-)start the image execute `docker stop jenkins-master` and `docker start jenkins-master`, respectively.

Build Pipeline for this project
-------------------------------
The provided Jenkinsfile requires modifications to the _Global Tool Configuration_ in Jenkins.
  - Add a JDK entry named _Java 8 SE_, set JAVA_HOME to _/usr/lib/jvm/java-8-oracle_
  - Add a Maven installation named _Maven 3.3.9_, set MAVEN_HOME to _/usr/local/maven

Create a new Pipeline Job - let's call it _tomee-rest-angular_.

Copy the contents of _Jenkinsfile_ into the Job (when using _Pipeline script_ definition).
You can also select _Pipeline script from SCM_, choose _Git_ as Provider, enter the URL to this repository
and set the correct branch to checkout, then enter the relative path to the Jenkinsfile (_src/test/Jenkins/Jenkinsfile_).