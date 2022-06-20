package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLImplementation implements EkgDAO {
    @Override
    public void save(EkgData ekgDTO) {
        Connection conn = DatabaseConnector.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO ekgDATA(CPR, voltage, time) VALUES (?,?,?)");
            //preparedStatement.setString(1,CPRnumber);
            preparedStatement.setDouble(2,ekgDTO.getVoltage());
            preparedStatement.setTimestamp(3,ekgDTO.getTime());
            preparedStatement.addBatch();

//            preparedStatement.setString(1,ekgDTO.getCPR());
//            preparedStatement.setString(2,ekgDTO.getFirstname());
//            preparedStatement.setString(3,ekgDTO.getLastname());
//            PreparedStatement preparedStatement2 = conn.prepareStatement("INSERT INTO patient(CPR, Firstname, Lastname) VALUES (?,?,?,)");

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EkgDTO> load(Timestamp time) {
        List<EkgDTO> data = new ArrayList<>();
        Connection connection = DatabaseConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ekgDATA WHERE time > ? ");
            preparedStatement.setTimestamp(1,time);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                EkgDTO ekgDTO = new EkgDTO();
                ekgDTO.setVoltage(resultSet.getInt("voltage"));
                ekgDTO.setTime(resultSet.getTimestamp("time"));
                data.add(ekgDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
