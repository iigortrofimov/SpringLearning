package ru.rogi.logger;

import org.springframework.stereotype.Component;
import ru.rogi.beans.Event;


@Component
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.toString() );
    }
}
