package dynamicTT;





import dynamicTT.Professor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBConnection {

    public Connection conn;
    public static DBConnection dBConnection;

    DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");
    }

    public static DBConnection getDBConnection() throws ClassNotFoundException, SQLException {
        if(dBConnection==null){
            dBConnection=new DBConnection();
            System.out.println("DB Created");
        }
        return dBConnection;
    }
    public Connection getConnection(){
        return conn;
    }
    
    
    public static ResultSet getAllproff() throws ClassNotFoundException, SQLException{
         String sql = "Select * From user";
         Connection conn=DBConnection.getDBConnection().getConnection();
         Statement stm=conn.createStatement();
         ResultSet rst=stm.executeQuery(sql);
       
         return rst;
    } 
    
     public static ResultSet getAllSub() throws ClassNotFoundException, SQLException{
         String sql = "Select * From subject";
         Connection conn=DBConnection.getDBConnection().getConnection();
         Statement stm=conn.createStatement();
         ResultSet rst=stm.executeQuery(sql);
       
         return rst;
    } 
     public static ResultSet getAllRooms() throws ClassNotFoundException, SQLException{
         String sql = "Select * From room";
         Connection conn=DBConnection.getDBConnection().getConnection();
         Statement stm=conn.createStatement();
         ResultSet rst=stm.executeQuery(sql);
       
         return rst;
    } 
      public static String getdept( String dep) throws ClassNotFoundException, SQLException{
         String sql = "Select * From department where dept_id='"+dep+"'";
         Connection conn=DBConnection.getDBConnection().getConnection();
         Statement stm=conn.createStatement();
         ResultSet rst=stm.executeQuery(sql);
          if(rst.next()){
             Object row[] = {rst.getString(1), rst.getString(2)};
             
            return  row[1].toString();
        }else{
            return null;
        }
       
    } 
}
