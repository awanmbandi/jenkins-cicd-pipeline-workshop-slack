pipeline {
    agent any

    environment {
        MAVEN_INSTALLATION = 'maven-installation'
        MAVEN_SETTINGS_CONFIG = 'maven-settings.xml'
    }

    stages {
        stage('Init') {
            steps {
                script {
                    stagesLib = load "stages.groovy"
                    slackNotifier = load "slack-notifier.groovy"
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    stagesLib.buildApplication()
                    slackNotifier.sendSlackNotificationForRegularStage()
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    stagesLib.runAllTests()
                    slackNotifier.sendSlackNotificationForRegularStage()
                }
            }
        }

        stage('Deploy') {
            input {
                message 'Do you want to deploy?'
                submitterParameter 'responder'
            }
            steps {
                script {
                    stagesLib.deployApplication()
                    slackNotifier.sendSlackNotificationForApprovedStage(responder)
                }
            }
        }
    }

    post {
        success {
            script {
                slackNotifier.sendSlackNotificationForSuccessfulBuild()
            }
        }
        failure {
            script {
                slackNotifier.sendSlackNotificationForFailedBuild()
            }
        }
        aborted {
            script {
                slackNotifier.sendSlackNotificationForAbortedBuild()
            }
        }
    }
}
