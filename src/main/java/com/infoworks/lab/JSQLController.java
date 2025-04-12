package com.infoworks.lab;

import com.it.soul.lab.connect.DriverClass;
import com.it.soul.lab.connect.JDBConnection;
import com.it.soul.lab.connect.io.ScriptRunner;
import com.it.soul.lab.sql.QueryExecutor;
import com.it.soul.lab.sql.SQLExecutor;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public final class JSQLController {

    public static void executeScripts(DriverClass driverClass) throws SQLException {
        ScriptRunner runner = new ScriptRunner();
        Connection conn = createConnection(driverClass);
        //
        File file = new File("testDB.sql");
        String[] cmds = runner.commands(runner.createStream(file));
        runner.execute(cmds, conn);
    }

    public static QueryExecutor createExecutor(DriverClass driverClass) throws SQLException {
        Connection conn = createConnection(driverClass);
        //Create a SQL-Executor:
        QueryExecutor executor = new SQLExecutor(conn);
        return executor;
    }

    public static Connection createConnection(DriverClass driverClass) throws SQLException {
        Connection conn;
        if (driverClass == DriverClass.MYSQL){
            conn = new JDBConnection.Builder(driverClass)
                    .host("localhost", "3306")
                    .database("testDB")
                    .credential("root","root@123")
                    .build();
        } else if (driverClass == DriverClass.H2_FILE) {
            conn = new JDBConnection.Builder(driverClass)
                    .database("~/testDB")
                    .credential("sa", "sa")
                    .query(";DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE")
                    .build();
        } else {
            conn = new JDBConnection.Builder(driverClass)
                    .database("testDB")
                    .credential("sa", "")
                    .query(";DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE")
                    .build();
        }
        return conn;
    }

}
