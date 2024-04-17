package com.fullcycle.admin.catalogo.infrastructure.configuration.properties.amqp;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class QueueProperties implements InitializingBean {

    public static final Logger log = org.slf4j.LoggerFactory.getLogger(QueueProperties.class);

    private String exchange;

    private String routingKey;

    private String queue;

    public QueueProperties() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "QueueProperties{" +
               "exchange='" + exchange + '\'' +
               ", routingKey='" + routingKey + '\'' +
               ", queue='" + queue + '\'' +
               '}';
    }

    public String exchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String routingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String queue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}
