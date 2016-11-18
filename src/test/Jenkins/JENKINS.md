Docker image for this demo
==========================
Based on Docker images provided for
  - the [workflow-aggregator-plugin](https://github.com/jenkinsci/workflow-aggregator-plugin)
  - the tutorial [Putting Jenkins in a Docker Container](https://engineering.riotgames.com/news/putting-jenkins-docker-container)

Steps to get it up and running
------------------------------
Execute

    docker build -t thebutler .

Afterwards run the freshly created image with

    docker run -p 8080:8080 --name=jenkins-master thebutler

Jenkins will start up, then open [http://localhost:8080]() in a browser to step through the configuration.
To get at the hash to unlock Jenkins run

    docker exec jenkins-master cat /var/jenkins_home/secrets/initialAdminPassword.

Click the *Install suggested plugins* button, this will ensure that most of the plugins needed to run
the Build Pipeline for this project will be installed.
After creating an administrative account proceed and install the [Pipeline Maven Integration Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Maven+Plugin)

Apache Maven 3.3.9 is part of the image, located at `/usr/local/maven`.
OpenJDK is available as well, under `/usr/lib/jvm`.

To stop and (re-)start the image execute `docker stop jenkins-master` and `docker start jenkins-master`, respectively.

Build Pipeline for this project
-------------------------------
TODO