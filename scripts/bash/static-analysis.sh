#!/bin/sh

mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent verify sonar:sonar \
    -Dsonar.projectKey=dorefactor_todo-app \
    -Dsonar.host.url=https://sonarcloud.io \
    -Dsonar.organization=dorefactor \
    -Dsonar.login=$SONAR_TOKEN \
    -Dsonar.coverage.jacoco.xmlReportPaths=target/
