package ru.rogi.logger;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.rogi.beans.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DBLogger implements EventLogger {

    private static final String SQL_ERROR_STATE_SCHEMA_EXISTS = "X0Y68";
    private static final String SQL_ERROR_STATE_TABLE_EXISTS = "X0Y32";

    private JdbcTemplate jdbcTemplate;
    private String schema;

    public DBLogger(JdbcTemplate jdbcTemplate, String schema) {
        this.jdbcTemplate = jdbcTemplate;
        this.schema = schema.toUpperCase();
    }

    @Override
    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO t_event (id, date, msg) VALUES (?, ?, ?)",
                event.getId(),event.getDate(), event.toString());
        System.out.println("Saved to DB event with id " + event.getId());
    }

    public void init(){
        createDBSchema();
        createTableIfNotExists();
        updateEventAutoId();
    }

    private void updateEventAutoId() {
        int maxId = getMaxId();
    }

    private int getMaxId() {
        Integer count = jdbcTemplate.queryForObject("SELECT MAX(id) FROM t_event", Integer.class);
        return count != null ? count : 0;
    }

    public void destroy(){
        int totalEvents = getTotalEvents();
        System.out.println("Total events in the DB: " + totalEvents);

        List<Event> allEvents = getAllEvents();
        String allEventIds = allEvents.stream()
                .map(Event::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("All DB Event ids: " + allEventIds);

    }

    private List<Event> getAllEvents() {

        List<Event> list = jdbcTemplate.query("SELECT * FROM t_event", (rs, num) -> {
            Integer id = rs.getInt("id");
            Date date = rs.getDate("date");
            String msg = rs.getString("msg");
            return new Event(id, date, msg);
        });

        return list;
    }

    private int getTotalEvents() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT (*) FROM t_event", Integer.class);
        return count != null ? count : 0;
    }

    public void createDBSchema(){
        try {
            jdbcTemplate.update("CREATE SCHEMA " + schema);
        } catch (DataAccessException e){
            Throwable causeException = e.getCause();
            if (causeException instanceof SQLException) {
                SQLException sqlException = (SQLException) causeException;
                if (sqlException.getSQLState().equals(SQL_ERROR_STATE_SCHEMA_EXISTS)) {
                    System.out.println("Schema already exists");
                } else {
                    throw e;
                }
            }
            else {
                throw e;
            }
        }
    }


    public void createTableIfNotExists(){
        try {
            jdbcTemplate.update("CREATE TABLE t_event (" + "id INT NOT NULL PRIMARY KEY,"
                    + "date TIMESTAMP," + "msg VARCHAR(255))");
            System.out.println("Created table t_event");
        } catch (DataAccessException e) {
            Throwable causeException = e.getCause();
            if (causeException instanceof SQLException) {
                SQLException sqlException = (SQLException) causeException;
                if (sqlException.getSQLState().equals(SQL_ERROR_STATE_TABLE_EXISTS)) {
                    System.out.println("Table already exists");
                } else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
    }
}
