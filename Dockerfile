FROM java:8
ADD src/com/baggga/job_test/Main.java .
RUN javac Main.java
CMD ["java", "Main"]