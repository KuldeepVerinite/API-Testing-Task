package Method;

import io.restassured.response.Response;

import java.sql.*;

public class DatabaseConnectivity {
   static PostService ps = new PostService();

    public static void main(String[] args) {
        // JDBC connection parameters for H2 in-memory database
        String dbUrl = "jdbc:h2:mem:testdb"; // in-memory database
        String dbUser = "in";
        String dbPassword = "";

//        ps.getCreatedBooking();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Step 1: Create table bookings
            createBookingsTable(connection);

            // Step 2: Insert data from JSON example
            insertBookingData(connection, "Jim", "Brown", 111.00, true, "2018-01-01", "2019-01-01", "Breakfast");

            // Step 3: Query and display data
            queryAndDisplayBookings(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createBookingsTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS bookings (" +
                "booking_id INT AUTO_INCREMENT PRIMARY KEY," +
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

    private static void insertBookingData(Connection connection, String firstname, String lastname, double totalprice,
                                          boolean depositpaid, String checkinDate, String checkoutDate, String additionalneeds) throws SQLException {
        String insertSQL = "INSERT INTO bookings (firstname, lastname, totalprice, depositpaid, checkin_date, checkout_date, additionalneeds) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        preparedStatement.setString(1, firstname);
        preparedStatement.setString(2, lastname);
        preparedStatement.setDouble(3, totalprice);
        preparedStatement.setBoolean(4, depositpaid);
        preparedStatement.setDate(5, Date.valueOf(checkinDate));
        preparedStatement.setDate(6, Date.valueOf(checkoutDate));
        preparedStatement.setString(7, additionalneeds);
        int rowsInserted = preparedStatement.executeUpdate();
        System.out.println(rowsInserted + " row(s) inserted.");
    }

    private static void queryAndDisplayBookings(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM bookings";
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
}
