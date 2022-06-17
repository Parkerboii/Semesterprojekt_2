
/*package Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Resultset {
    public static <Int> void Resultset(String[] args) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        Resultset resultSet = null;

        String dbUrl = "jdbc:mysql://mysql-db.caprover.diplomportal.dk/s204814?" +
                "user=s204814&password=IORTlOFEarsqvyYfAfBAN";
        String user = "root";
        String password = "password";

        try {
            connection = DatabaseConnector.getConnection(dbUrl, user, password);
            statement = connection.createStatement();
            String sql = "SELECT CPR, voltage, time FROM ekgDATA";
            ResultSet resultSet1et = statement.executeQuery(sql);

            while (((ResultSet) resultSet).next()) {
                String CRP = ((ResultSet) resultSet).getString("Patient CPR");
                String voltage = ((ResultSet) resultSet).getString("Patient voltage");
                String time = ((ResultSet) resultSet).getString("Patient time");

                System.out.println(CRP + " " + voltage + " " + time);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            DatabaseConnector.closeResultSet(resultSet);
            DatabaseConnector.closeStatement(statement);
            DatabaseConnector.closeConnection(resultSet);
        }*/
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

class Get_Result_Set{
    public static void main(String[] args) {
        Connection db_con = null;
        PreparedStatement db_Statement = null;
        ResultSet result_set = null;
        try {
            String db_path = "jdbc:mysql://mysql-db.caprover.diplomportal.dk/s204814?" +
                    "user=s204814&password=IORTlOFEarsqvyYfAfBAN";
            db_con = DriverManager.getConnection(db_path);
            System.out.println("The Data Based Connection is established.");

            db_Statement = db_con.prepareStatement("select * from ekgDATA", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            result_set = db_Statement.executeQuery();

            int Row_Count = 0;
            System.out.println("Display all the records in ResultSet object");
            System.out.println("CPR, voltage, time");
            while (result_set.next()) {
                System.out.println(result_set.getString("CPR")+"\t\t"+result_set.getInt("voltage")+"\t\t"+result_set.getTimestamp("time"));
                // Row count will get the length of result set
                Row_Count++;
            }
            System.out.println("Total number of rows in ResultSet object = "+Row_Count);

        }
        catch (SQLException e) {
            throw new Error("Problem", e);
        }
        finally {
            try {
                if (db_con != null) {
                    db_con.close();
                }
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}