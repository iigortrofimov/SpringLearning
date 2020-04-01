package ru.rogi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.rogi.config.AppConfig;
import ru.rogi.config.LoggerConfig;
import ru.rogi.event.EventType;
import ru.rogi.logger.EventLogger;

import javax.annotation.Resource;
import java.util.Map;


@Service
public class App {

    @Autowired
    private Client client;

    @Resource(name = "defaultLogger")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public App() {
    }

    public static void main(String[] args) {
/*        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        App app = (App) context.getBean("app");

        Event event = (Event) context.getBean("event");
        app.logEvent(EventType.INFO, event, "Event 1");

        event = (Event) context.getBean("event");
        app.logEvent(EventType.INFO, event, "Event 2");

        event = (Event) context.getBean("event");
        app.logEvent(EventType.ERROR, event, "Event 3");

        event = (Event) context.getBean("event");
        app.logEvent(EventType.ERROR, event, "Event 4");

        event = (Event) context.getBean("event");
        app.logEvent(null, event, "Event null");

        System.getProperty("java.class.path");

        context.close(); */


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AppConfig.class, LoggerConfig.class);
        context.scan("ru.rogi");
        context.refresh();

        App app = (App) context.getBean("app");

        Client client = context.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = context.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = context.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = context.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        context.close();
    }

    private void logEvent(EventType type, Event event, String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        EventLogger logger =loggers.get(type);
        if (logger == null){
            logger = defaultLogger;
        }
        event.setMsg(message);
        logger.logEvent(event);
    }
}
