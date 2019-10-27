# jenkins-pipeline-workshop

# Pre-requisites

#0 Have a Jenkins server

We can easily have a containerized Jenkins using the following steps:


Get the latest jenkins docker image (see docs here: https://hub.docker.com/r/jenkins/jenkins):

docker pull jenkins/jenkins

Check you docker images:
➜  spring-petclinic git:(master) docker images | grep "jenkins"
jenkins/jenkins        latest        eda4e3b68b8b        6 days ago        553MB

Start the jenkins container (for more info see docs here: https://github.com/jenkinsci/docker/blob/master/README.md):

docker run n -p 9090:8080 -v /Users/valentcb/Projects/jenkins-container-home:/var/jenkins_home jenkins/jenkins

Go to http://localhost:9090

