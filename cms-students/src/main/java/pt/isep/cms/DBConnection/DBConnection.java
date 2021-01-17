package pt.isep.cms.DBConnection;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
//import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBConnection extends RemoteServiceServlet {
    public Connection connection = null;
    private String connString = "jdbc:mysql://localhost:3306/CMS_Students";
    private String user = "application";
    private String pass = "app_pw";

    public DBConnection() {
    }

    public Connection getConnection() {
     // todo: environments
        Properties properties = new Properties();
        try {
            properties.load(readConfig());
        } catch (Exception e) {
            try {
                connection = DriverManager.getConnection(connString, user, pass);
                Class.forName ("com.mysql.jdbc.Driver").newInstance ();
                return connection;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return null;
    }

    private InputStream readConfig() {
        String path = new File("").getAbsolutePath() + "/config.properties";

        File file = new File(path);

        if(!file.exists()) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}

