
pipeline {
    agent any
	tools{
		maven 'maven'	
		}
    stages {
        stage('Build') {
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
                // Example: sh 'mvn clean install -DskipTests'
            }
            post
            { 
				sucess{
					junit '**/target/surefire-reports/TEST-*.xml'
					archiveArtifacts 'target/*.jar'
				}
			}
        }

        stage('Deploy to qa') {
            steps {
                echo 'Deploy to Qa environment'
                // Example: sh './scripts/deploy.sh dev'
            }
        }

        stage('Run Regression Test Cases on QA') {
            steps {
                echo 'Run regression test cases on QA'
                git 'https://github.com/i-am-khillan/uiseleniumframework.git'
                bat "maven clean test -Dsurefire.suiteXmlFiles=/src/test/resources/testrunner/sanity_chrome.xml"
            }
        }

        stage('Publish Allure reports') {
            steps {
				script{
					allure([
						includeProperties:false,
						jdk:'',
						properties:[],
						reportBuildPolicy:'ALWAYS',
						results:[[path:'/allure-results']]
					])
				}
            }
        }

        stage('Publish Extent Report') {
            steps {
                	publishHTML([allowMissing: false,
                	alwaysLinkToLastBuild: false,
                	keepAll: true,
                	reportDir: 'reports',
                	reportFiles: 'TestExecutionReport.html',
                	reportName: 'HTML Regression Extert Report',
                	reportTitles: ''
                	])
            }
        }
          stage('Deploy to stg') {
            steps {
                echo 'Deploy to stg environment'
                // Example: sh './scripts/deploy.sh dev'
            }
        }
        
        stage('Run Regression Test Cases on QA') {
            steps {
                echo 'Run regression test cases on QA'
                git 'https://github.com/i-am-khillan/uiseleniumframework.git'
                bat "maven clean test -Dsurefire.suiteXmlFiles=/src/test/resources/testrunner/sanity_chrome.xml"
            }
        }
         stage('Run Regression Test Cases on QA') {
            steps {
                echo 'Run regression test cases on QA'
                git 'https://github.com/i-am-khillan/uiseleniumframework.git'
                bat "maven clean test -Dsurefire.suiteXmlFiles=/src/test/resources/testrunner/sanity_chrome.xml"
            }
        }

        stage('Publish Allure reports') {
            steps {
				script{
					allure([
						includeProperties:false,
						jdk:'',
						properties:[],
						reportBuildPolicy:'ALWAYS',
						results:[[path:'/allure-results']]
					])
				}
            }
        }
          stage('Publish Extent Report') {
            steps {
                	publishHTML([allowMissing: false,
                	alwaysLinkToLastBuild: false,
                	keepAll: true,
                	reportDir: 'reports',
                	reportFiles: 'TestExecutionReport.html',
                	reportName: 'HTML Regression Extert Report',
                	reportTitles: ''
                	])
            }
        }
     }
}
