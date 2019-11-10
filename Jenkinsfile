import hudson.tasks.test.AbstractTestResultAction

pipeline {
    agent any

    environment {
        MAVEN_INSTALLATION = 'maven-installation'
        MAVEN_SETTINGS_CONFIG = 'maven-settings.xml'
    }

    stages {
        stage('Build') {
            steps {
                withMaven(maven: MAVEN_INSTALLATION, mavenSettingsConfig: MAVEN_SETTINGS_CONFIG) {
                    sh 'mvn -DskipTests clean package'
                }
                script {
                    statusComment = "[${env.JOB_NAME}] <${env.BUILD_URL}|#${env.BUILD_NUMBER}> ${env.STAGE_NAME} stage completed succesfully for ${env.GIT_BRANCH}"
                    slackSend color: '#0000ff', message: statusComment
                }
            }
        }

        stage('Test') {
            steps {
                withMaven(maven: MAVEN_INSTALLATION, mavenSettingsConfig: MAVEN_SETTINGS_CONFIG) {
                    sh 'mvn test'
                }
                script {
                    statusComment = "[${env.JOB_NAME}] <${env.BUILD_URL}|#${env.BUILD_NUMBER}> ${env.STAGE_NAME} stage completed succesfully for ${env.GIT_BRANCH}"
                    slackSend color: '#0000ff', message: statusComment
                }
            }
        }

        stage('Deploy') {
            input{
                message 'Do you want to deploy?'
                submitterParameter 'responder'
            }
            steps {
                echo "Deploying application..."
                script {
                    statusComment = "[${env.JOB_NAME}] <${env.BUILD_URL}|#${env.BUILD_NUMBER}> ${env.STAGE_NAME} stage was approved by ${responder} for ${env.GIT_BRANCH}"
                    slackSend color: '#0000ff', message: statusComment
                }
            }
        }
    }

    post {
        success {
            script {
                statusComment = "[${env.JOB_NAME}] <${env.BUILD_URL}|#${env.BUILD_NUMBER}> completed succesfully for ${env.GIT_BRANCH} :tada:"
                slackSend color: 'good', message: statusComment
            }
        }
        failure {
            script {
                statusComment = getTestResultsMessage()
                slackSend color: 'danger', message: statusComment
            }
        }
        aborted {
            script {
                statusComment = "[${env.JOB_NAME}] <${env.BUILD_URL}|#${env.BUILD_NUMBER}> for ${env.GIT_BRANCH} was aborted by ${getBuildUser()}"
                slackSend message: statusComment
            }
        }
    }

}

String getTestResultsMessage() {
    AbstractTestResultAction testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
    if (testResultAction != null) {
        def total = testResultAction.totalCount
        def failed = testResultAction.failCount
        def skipped = testResultAction.skipCount
        return "[${env.JOB_NAME}] <${env.BUILD_URL}|#${env.BUILD_NUMBER}> had test failures for ${env.GIT_BRANCH}.\n  Total: ${total}, Failed: ${failed}, Skipped: ${skipped}"
    } else {
        return "[${env.JOB_NAME}] <${env.BUILD_URL}|#${env.BUILD_NUMBER}> failed for ${env.GIT_BRANCH}"
    }
}

String getBuildUser() {
    return currentBuild.rawBuild.getCause(Cause.UserIdCause).getUserId()
}
