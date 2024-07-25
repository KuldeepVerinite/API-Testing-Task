package Method;

import io.restassured.response.Response;

import java.sql.*;

public class DBConnect {

    static PostService ps = new PostService();

    int booking_id;
    String firstname,lastname,checkin,checkout,additionalneeds;
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

    private static void deleteBooking(Connection connection, int bookingId) throws SQLException {
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


        Response resp = ps.createBooking("Rohit", "Sharma");
        booking_id = ps.getCreatedBookingId(resp);
        firstname = ps.getCreatedBooking1(resp);
        lastname = ps.getCreatedBooking2(resp, "lastname");
        totalprice = ps.getCreatedBooking3(resp, "totalprice");
        depositpaid = ps.getCreatedBooking4(resp, "depositpaid");
        checkin = ps.getCreatedBooking5(resp, "checkin");
        checkout = ps.getCreatedBooking5(resp, "checkout");
        additionalneeds = ps.getCreatedBooking2(resp, "additionalneeds");


        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Step 1: Create table
//              createBookingsTable(connection);

            // Step 2: Insert data from JSON example
            insertBookingData(connection, booking_id, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);

            // Step 3: Query and display data
            queryAndDisplayBookings(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateBooking(String firstname,String lastname,int id) {
        PutService pus = new PutService();

        String dbUrl = "jdbc:mysql://localhost:3306/db1"; // in-memory database
        String dbUser = "root";
        String dbPassword = "Verinite@123";

        Response resp = pus.updateBooking(firstname, lastname, id);
//            booking_id=pus.getCreatedBookingId(resp);
        firstname = pus.getCreatedBooking1(resp);
        lastname = pus.getCreatedBooking2(resp, "lastname");
        totalprice = pus.getCreatedBooking3(resp, "totalprice");
        depositpaid = pus.getCreatedBooking4(resp, "depositpaid");
        checkin = pus.getCreatedBooking5(resp, "checkin");
        checkout = pus.getCreatedBooking5(resp, "checkout");
        additionalneeds = pus.getCreatedBooking2(resp, "additionalneeds");


        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Step 1: Create table
//              createBookingsTable(connection);

            // Step 2: Insert data from JSON example
            updateBookingData(connection, id, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteBooking(int id){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "Verinite@123");
            deleteBooking(connection, id); // Replace 123 with the actual bookingId you want to delete
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
