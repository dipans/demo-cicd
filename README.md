docker run -d -p 49001:8080 8081:8081 -v $PWD/jenkins:/var/jenkins_home:z -t jenkins/jenkins