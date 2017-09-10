package powerdialer;

import org.testng.annotations.Test;

import java.sql.*;

public class RunSQLQuery {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String userName = "GBWebPhoneTest";
        String password = "yt~k$tCW8%Gj";
        String url = "jdbc:sqlserver://172.21.7.225\\\\corporate;DatabaseName=GBWebPhoneTest;portNumber=1438";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }

    public static void updateRecord(Connection con) throws SQLException {
        String query = "UPDATE GBWebPhoneTest.dbo.pd_5009_3 SET phone_number_1 = '94636'," +
                " caller_id_1 = null, call_from_time_1 = null, call_till_time_1 = null," +
                " timezone_offset_1 = null, call_from_time = null, call_till_time = null," +
                " timezone_offset = null, start_date = null, answer_date = null," +
                " complete_date = null, called_number = null, telephony_result_code = null," +
                " user_result_code = null, rc_sequence = null, user_id = null, " +
                "callback_number = null, callback_date = null, retry_number = null, " +
                "retry_date = null, retry_total = null, retry_total_macro = null, ad_card_id = null," +
                " is_fetched = null, caller_id_4 = null, phone_number_4 = null," +
                " call_from_time_4 = null, call_till_time_4 = null, timezone_offset_4 = null" +
                " WHERE id = 124;";

        Statement  stmt = con.createStatement();
        stmt.execute(query);
    }

    @Test
    public void runSQLQuery() throws SQLException, ClassNotFoundException {
        updateRecord(getConnection());
    }


}