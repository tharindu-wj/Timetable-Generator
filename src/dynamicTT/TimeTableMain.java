package dynamicTT;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TimeTableMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
            try {
                // TODO Auto-generated method stub
                
                //Program starts................
                System.out.println("Start.......");
                Initialization initialize=new Initialization();
                initialize.readInput();
            } catch (SQLException ex) {
                Logger.getLogger(TimeTableMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TimeTableMain.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}
