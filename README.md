# **Todo App**

## **Prerequisites**

* Java 8
* Maven 3+
* MySQL 5.7+

## **Configuration**

## **Deployment**

### **Hosts**

* For development, add to `/etc/hosts`
```sh
127.0.0.1   mysql
```

### **Database**

#### **MySQL**

* Run it in docker container
```sh
$ docker-compose -f docker-compose-backing-services.yml up -d
```

### **Docker**

#### **Build**

* In the root directory execute
```sh
$ mvn clean package
$ docker build -t todo-app -f docker/Dockerfile .
```

#### **Run**

* After build the image, execute
```sh
$ docker run --rm --name todo-app --add-host=mysql:${IP} -p 0.0.0.0:8080:8080 todo-app
```

### **Testing**

* Go to [http://127.0.0.1:8080](http://127.0.0.1:8080)
