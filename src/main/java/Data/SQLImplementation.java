package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*@author  Jacob Egon Bach Meyer, 204418
 * @version 1.0
 * @since   2022-06-23*/

public class SQLImplementation implements EkgDAO, PatientDAO {

    @Override
    public void save(List<EkgData> ekgData) {
        Connection conn = DatabaseConnector.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO ekgDATA(CPR, voltage, time) VALUES (?,?,?)");
            for (int i = 0; i < ekgData.size(); i++) {
                preparedStatement.setString(1,ekgData.get(i).getID());
                preparedStatement.setInt(2,ekgData.get(i).getVoltage());
                preparedStatement.setTimestamp(3,ekgData.get(i).getTime());
                preparedStatement.addBatch();
            }

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
            preparedStatement2.setString(1,patientDTO.getCPR());
            preparedStatement2.setString(2,patientDTO.getFirstName());
            preparedStatement2.setString(3,patientDTO.getLastName());
            preparedStatement2.addBatch();
            preparedStatement2.executeBatch();
            //Kan gemme samme data flere gange, men gemmer dem en af gangen

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    @Override
//    public List<EkgDTO> load(Timestamp time) {
//        List<EkgDTO> data = new ArrayList<>();
//        Connection connection = DatabaseConnector.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ekgDATA WHERE time > ? ");
//            preparedStatement.setTimestamp(1,time);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                EkgDTO ekgDTO = new EkgDTO();
//                ekgDTO.setVoltage(resultSet.getDouble("voltage"));
//                ekgDTO.setTime(resultSet.getTimestamp("time"));
//                data.add(ekgDTO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
}
