package ru.rogi.logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.rogi.beans.Event;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


@Component
public class CacheFileLogger extends FileEventLogger{

    @Value("5")
    private int cashSize;

    private List<Event> cache;


    public CacheFileLogger() {
    }

    public CacheFileLogger(String fileName) {
        super(fileName);
    }

    public CacheFileLogger(String fileName, int cashSize) {
        super(fileName);
        this.cashSize = cashSize;
        this.cache = new ArrayList<>();
    }

    @PostConstruct
    public void initCache(){
        this.cache = new ArrayList<>(cashSize);
    }


    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cashSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    public void writeEventsFromCache(){
        /*for (Event e: cache){
            super.logEvent(e);
        }*/
        cache.forEach(super::logEvent);
    }

    @PreDestroy
    public void destroy(){
        if (!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
