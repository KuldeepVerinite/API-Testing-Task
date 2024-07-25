package Method;

import org.testng.annotations.Test;

import java.sql.SQLException;

public class DBRunClass {

    DBConnect dbc = new DBConnect();

    @Test(priority = 1, invocationCount = 5)
    public void createBooking() throws SQLException {
        dbc.convertRespIntoDatabase();
    }

    @Test
    public void UpdateBooking(){
        dbc.updateBooking("Virat","Kohli",2065);
    }

    @Test
    public void deleteBooking(){
        dbc.DeleteBooking(2074);
    }
}
