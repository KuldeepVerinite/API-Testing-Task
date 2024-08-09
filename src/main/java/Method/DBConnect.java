package Method;

import io.restassured.response.Response;

import java.sql.*;

public class DBConnect {

    static Service postservice = new Service();

    int booking_id;
    String firstname,lastname,checkin,checkout,additionalneeds,firstname1,lastname1;
    double totalprice;
    boolean depositpaid;



//    public static void main(String[] args) {
//        // JDBC connection parameters for H2 in-memory database
//        String dbUrl = "jdbc:mysql://localhost:3306/db1"; // in-memory database
//        String dbUser = "root";
//        String dbPassword = "Verinite@123";
//
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
//            // Step 1: Create table bookings
//            createBookingsTable(connection);
//
//            // Step 2: Insert data from JSON example
//            insertBookingData(connection, 123,"Jim", "Brown", 111.00, true, "2018-01-01", "2019-01-01", "Breakfast");
//
//            // Step 3: Query and display data
//            queryAndDisplayBookings(connection);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private static void createBookingsTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS bookings1 (" +
                "booking_id INT," +
                "firstname VARCHAR(255)," +
                "lastname VARCHAR(255)," +
                "totalprice DECIMAL(10, 2)," +
                "depositpaid BOOLEAN," +
                "checkin_date DATE," +
                "checkout_date DATE," +
                "additionalneeds VARCHAR(255)" +
                ")";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableSQL);
        System.out.println("Bookings table created successfully.");
    }

    private static void insertBookingData(Connection connection, int booking_id,String firstname, String lastname, double totalprice,
                                          boolean depositpaid, String checkinDate, String checkoutDate, String additionalneeds) throws SQLException {
        String insertSQL = "INSERT INTO bookings1 (booking_id, firstname, lastname, totalprice, depositpaid, checkin_date, checkout_date, additionalneeds) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        preparedStatement.setInt(1,booking_id);
        preparedStatement.setString(2, firstname);
        preparedStatement.setString(3, lastname);
        preparedStatement.setDouble(4, totalprice);
        preparedStatement.setBoolean(5, depositpaid);
        preparedStatement.setDate(6, Date.valueOf(checkinDate));
        preparedStatement.setDate(7, Date.valueOf(checkoutDate));
        preparedStatement.setString(8, additionalneeds);
        int rowsInserted = preparedStatement.executeUpdate();
        System.out.println(rowsInserted + " row(s) inserted.");
    }

    private static void queryAndDisplayBookings(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM bookings1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);
        while (resultSet.next()) {
            int bookingId = resultSet.getInt("booking_id");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            double totalprice = resultSet.getDouble("totalprice");
            boolean depositpaid = resultSet.getBoolean("depositpaid");
            Date checkinDate = resultSet.getDate("checkin_date");
            Date checkoutDate = resultSet.getDate("checkout_date");
            String additionalneeds = resultSet.getString("additionalneeds");

            System.out.println("Booking ID: " + bookingId +
                    ", Name: " + firstname + " " + lastname +
                    ", Total Price: " + totalprice +
                    ", Deposit Paid: " + depositpaid +
                    ", Check-in Date: " + checkinDate +
                    ", Checkout Date: " + checkoutDate +
                    ", Additional Needs: " + additionalneeds);
        }
    }

    private static void updateBookingData(Connection connection, int bookingId, String firstname, String lastname, double totalprice,
                                          boolean depositpaid, String checkinDate, String checkoutDate, String additionalneeds) throws SQLException {
        String updateSQL = "UPDATE bookings1 " +
                "SET firstname = ?, lastname = ?, totalprice = ?, depositpaid = ?, checkin_date = ?, checkout_date = ?, additionalneeds = ? " +
                "WHERE booking_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        preparedStatement.setString(1, firstname);
        preparedStatement.setString(2, lastname);
        preparedStatement.setDouble(3, totalprice);
        preparedStatement.setBoolean(4, depositpaid);
        preparedStatement.setDate(5, Date.valueOf(checkinDate));
        preparedStatement.setDate(6, Date.valueOf(checkoutDate));
        preparedStatement.setString(7, additionalneeds);
        preparedStatement.setInt(8, bookingId);

        int rowsUpdated = preparedStatement.executeUpdate();
        System.out.println(rowsUpdated + " row(s) updated.");
    }

    private static void deleteBookingFromDb(Connection connection, int bookingId) throws SQLException {
        String deleteSQL = "DELETE FROM bookings1 WHERE booking_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, bookingId);

        int rowsDeleted = preparedStatement.executeUpdate();
        System.out.println(rowsDeleted + " row(s) deleted.");
    }

    public void createTable() throws SQLException {

        String dbUrl = "jdbc:mysql://localhost:3306/db1"; // in-memory database
        String dbUser = "root";
        String dbPassword = "Verinite@123";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Step 1: Create table
            createBookingsTable(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void convertRespIntoDatabase() throws SQLException {

        String dbUrl = "jdbc:mysql://localhost:3306/db1"; // in-memory database
        String dbUser = "root";
        String dbPassword = "Verinite@123";


        Response resp = postservice.createBooking("Rohit", "Sharma");
        booking_id = postservice.getBookingId(resp);
        firstname = postservice.getFirstOrLastnameOrAdditionalneeds(resp,"firstname");
        lastname = postservice.getFirstOrLastnameOrAdditionalneeds(resp, "lastname");
        totalprice = postservice.getPrice(resp, "totalprice");
        depositpaid = postservice.getDepositpaid(resp, "depositpaid");
        checkin = postservice.getCheckInOrOut(resp, "checkin");
        checkout = postservice.getCheckInOrOut(resp, "checkout");
        additionalneeds = postservice.getFirstOrLastnameOrAdditionalneeds(resp, "additionalneeds");


        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

            // Step 1: Insert data from JSON example
            insertBookingData(connection, booking_id, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);

            // Step 2: Query and display data
            queryAndDisplayBookings(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateBooking(String firstname,String lastname,int id) {

        String dbUrl = "jdbc:mysql://localhost:3306/db1"; // in-memory database
        String dbUser = "root";
        String dbPassword = "Verinite@123";

        Response resp = postservice.updateBooking(firstname, lastname, id);
        firstname1 = postservice.getFirstOrLastnameOrAdditionalneedsForPutAndGet(resp,"firstname");
        lastname1 = postservice.getFirstOrLastnameOrAdditionalneedsForPutAndGet(resp, "lastname");
        totalprice = postservice.getPriceForPutAndGet(resp, "totalprice");
        depositpaid = postservice.getDepositpaidForPutAndGet(resp, "depositpaid");
        checkin = postservice.getCheckInOrOutForPutAndGet(resp, "checkin");
        checkout = postservice.getCheckInOrOutForPutAndGet(resp, "checkout");
        additionalneeds = postservice.getFirstOrLastnameOrAdditionalneedsForPutAndGet(resp, "additionalneeds");


        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

            // Step 1: Update data
            updateBookingData(connection, id, firstname1, lastname1, totalprice, depositpaid, checkin, checkout, additionalneeds);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(int id){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "Verinite@123");
            deleteBookingFromDb(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
