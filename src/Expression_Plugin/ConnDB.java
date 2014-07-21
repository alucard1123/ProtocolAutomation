package Expression_Plugin;

/**
 * Created by Edward on 14-1-16.
 */


import java.sql.*;


public class ConnDB {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    public ConnDB(){
        conn = null;
        stmt = null;
        rs = null;
    }
    public void connDB(String instance,String user,String pass) throws Exception{
        linkDB(instance,user,pass);
    }
    private Statement linkDB(String instance,String urname,String psword) throws SQLException, ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@"+instance;
        String username = urname;
        String password = psword;
        conn = DriverManager.getConnection(url,username,password);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        return stmt;
    }
    public ResultSet runSql(String sql) throws SQLException{
        rs =stmt.executeQuery(sql);
        return rs;
    }
    public void cleanAll() throws SQLException{
            rs.close();
        stmt.close();
    }
}
