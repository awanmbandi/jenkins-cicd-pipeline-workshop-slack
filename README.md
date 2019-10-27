# jenkins-pipeline-workshop

## Pre-requisites

### Have a Jenkins server

We can easily have a containerized Jenkins using the following steps:

**1. Get the latest jenkins docker image** (see docs [here](https://hub.docker.com/r/jenkins/jenkins))
```
$ docker pull jenkins/jenkins
```

**2. Check your docker images**
```
$ docker images | grep "jenkins"
jenkins/jenkins        latest        eda4e3b68b8b        6 days ago        553MB
```

**3. Start the jenkins container** (see docs [here](https://github.com/jenkinsci/docker/blob/master/README.md))
```
$ docker run n -p 9090:8080 -v <absolute_path_to_jenkins_home_directory):/var/jenkins_home jenkins/jenkins
```

**4. Go to http://localhost:9090**

**5. Configure Jenkins**

**5a. Unlock Jenkins**

```
$ cat <absolute_path_to_jenkins_home_directory)/secrets/initialAdminPassword
```

Put the password here:
![Unlock Jenkins](images/unlock-jenkins.png)

