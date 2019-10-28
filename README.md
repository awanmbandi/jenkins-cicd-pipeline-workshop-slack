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

**5b. Select plugins to install**

![Select plugins to install](images/select-plugins-to-install.png)

Select the following plugins to install:
* Folders
* Build Timeout
* Credentials Binding
* Timestamper
* Workspace Cleaner
* Pipeline
* GitHub Branch Source
* Pipeline: GitHub Groovy Libraries
* Pipeline: Stage View
* Git

![Plugins installation](images/plugins-installation.png)

**5c. Create first admin user**

Choose a new password and set an email address for your admin user. Click `Save and Continue`.

![Create first admin user](images/create-first-admin-user.png)

**5d. Skip the instance configuration (for now)**

Click `Save and Finish`.

![Instance configuration](images/instance-configuration.png)

**5e. Start using Jenkins**

You are good to go! 🍻

![Welcome to Jenkins](images/welcome-to-jenkins.png)
