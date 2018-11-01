FROM java:8
ADD src/main/java/com/baggga/job_test .
RUN javac Main.java
CMD ["java", "Main"]