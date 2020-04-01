package ru.rogi.logger;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.rogi.beans.Event;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Component
public class FileEventLogger implements EventLogger {

    private File file;

    @Value("test.txt")
    private String fileName;

    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
        this.file =  new File(fileName);
    }
    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() throws IOException{
        file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException(
                    "Can't write to file " + fileName);
        } else if (!file.exists()) {
            file.createNewFile();
        }
    }
}
