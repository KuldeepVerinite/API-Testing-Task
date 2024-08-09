package Method;

import org.testng.annotations.Test;

import java.sql.SQLException;

public class DBRunClass {

    DBConnect dbc = new DBConnect();

    @Test(priority = 1, invocationCount = 5)
    public void createBooking() throws SQLException {
        dbc.convertRespIntoDatabase();
    }

    @Test(priority = 2)
    public void updBooking(){
        dbc.updateBooking("Virat","Kohli",2065);
    }

    @Test(priority = 3)
    public void deleteBooking(){
        dbc.deleteBooking(2074);
    }
}