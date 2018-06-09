FROM java:8-jre

LABEL maintainers="Jorge Alvarez <alvarez.jeap@gmail.com>"

WORKDIR /opt/apps/todo-app

COPY ./target/todo-app.jar ./todo-app.jar

ENV PORT 8080

EXPOSE $PORT

RUN useradd -m todo-app

USER todo-app

CMD ["java", "-jar", "-Xms128m", "-Xmx128m", "/opt/apps/todo-app/todo-app.jar"]
