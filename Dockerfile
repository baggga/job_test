FROM java:8
EXPOSE 8080
ADD /target/job_test.jar job_test.jar
ENTRYPOINT ["java","-jar","job_test.jar"]