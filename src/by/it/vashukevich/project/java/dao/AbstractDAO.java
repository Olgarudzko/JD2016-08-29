package by.it.vashukevich.project.java.dao;

import by.it.vashukevich.project.java.connection.ConnectionCreater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AbstractDAO {
    protected int executeUpdate(String sql) {
        int result = -1;
        try (Connection connection = ConnectionCreater.getConnection();
             Statement statement = connection.createStatement()) {
            result = statement.executeUpdate(sql);
            if (sql.trim().toUpperCase().startsWith("INSERT")) {
                ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
                if (resultSet.next()) result = resultSet.getInt(1);
            }
        } catch (Exception e) {
            //тут нужно логгирование SQLException(e);
        }
        //System.out.println(result+":"+sql); //проверить SQL можно снимая комментарий с этой строки
        return result;
    }
}
