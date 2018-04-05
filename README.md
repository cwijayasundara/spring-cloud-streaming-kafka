This is my attempt to understand and built a working sample to publish and consuming messages off Kafka 1.0.0 using
Spring cloud streaming kafka api.

Start Zookeper:
    go to /usr/local/Cellar/zookeeper/3.4.10/bin then zkServer start
Kafka Commands:

    kafka-server-start /usr/local/etc/kafka/server.properties --override property=
    
How to execute the app

http://localhost:8080/tweet?message=I love Scala