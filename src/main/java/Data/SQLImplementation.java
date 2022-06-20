package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLImplementation implements EkgDAO, PatientDAO {
    @Override
    public void save(EkgData ekgDTO) {
        Connection conn = DatabaseConnector.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO ekgDATA(CPR, voltage, time) VALUES (?,?,?)");
            preparedStatement.setString(1,ekgDTO.getID());
            preparedStatement.setInt(2,ekgDTO.getVoltage());
            preparedStatement.setTimestamp(3,ekgDTO.getTime());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void save(PatientData patientDTO) {
        Connection conn = DatabaseConnector.getConnection();
        try {
            PreparedStatement preparedStatement2 = conn.prepareStatement("INSERT INTO patient(CPR, Firstname, Lastname) VALUES (?,?,?)");
            preparedStatement2.setString(1,patientDTO.getCPR()); //TODO: Få den til at virke
            preparedStatement2.setString(2,patientDTO.getFirstName());
            preparedStatement2.setString(3,patientDTO.getLastName());
            preparedStatement2.addBatch();
            preparedStatement2.executeBatch();

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
