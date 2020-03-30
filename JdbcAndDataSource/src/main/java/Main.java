import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("beans.xml");
        DataSource dataSource = context.getBean(DataSource.class);
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.execute("CREATE TABLE Customer(id INTEGER, name VARCHAR(100))");
        template.execute((ConnectionCallback<Object>) con -> {
            System.out.println(con.getMetaData().getURL());
            System.out.println(con.getMetaData().getUserName());
            return null;
        });
        template.update("INSERT INTO Customer VALUES (1, 'Billy')");
        Boolean ok = template.execute((StatementCallback<Boolean>) stmt -> {
            stmt.execute("INSERT INTO Customer VALUES(2, 'Jack')");
            return Boolean.TRUE;
        });
        System.out.println("ok = " + ok);
        template.execute(con ->
                con.prepareStatement("INSERT INTO Customer VALUES(?, ?)"),(PreparedStatementCallback<Object>) ps -> {
            ps.setInt(1, 3);
            ps.setString(2, "Alex");
            ps.execute();
            return null;
        });

        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(template);
        Map<String, Object> params = new HashMap<>();
        params.put("id", 4);
        params.put("name", "Nike");
        namedTemplate.update("INSERT INTO Customer VALUES(:id, :name)", params);
        template.query("SELECT * FROM Customer", rs -> {
            System.out.println(rs.getString("name"));
        });

        Integer count = template.queryForObject("SELECT COUNT (*) FROM Customer", Integer.class);
        System.out.println("Count: " + count);

        Map<String, Object> customerMap = template.queryForMap("SELECT * FROM Customer WHERE id =1");
        System.out.println(customerMap.get("name"));






    }
}
