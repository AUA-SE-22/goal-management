FROM eclipse-temurin:17-jdk
RUN mkdir -p "/opt/app"
COPY goal-management/webapps/goalManagement.jar /opt/app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "opt/app/goalManagement.jar"]