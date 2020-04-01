package ru.rogi.logger;

import ru.rogi.beans.Event;
import java.util.Collection;


//@Component
public class CombinedEventLogger implements EventLogger{

    //@Resource(name = "combinedEventLogger")
    private Collection<EventLogger> loggers;

    public CombinedEventLogger() {
    }

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    //@Override
    public void logEvent(Event event) {
        for (EventLogger logger: loggers){
            logger.logEvent(event);
        }
    }
}
