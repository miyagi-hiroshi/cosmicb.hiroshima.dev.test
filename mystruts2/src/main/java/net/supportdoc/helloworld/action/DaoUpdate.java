package net.supportdoc.helloworld.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoUpdate {


    
    /** MySQL update処理
     * @param conn connecion
     * @param id id
     * @param dest 訪問先
     * @return boolean true:update成功、false:update失敗
     */
    public boolean updateEvent(Connection conn, String id, String dest) {

        String query = "update houmon set dest = ?, out_date= CURRENT_TIMESTAMP() where id = ?;";

        try {

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, dest);  //訪問先
            ps.setString(2, id);    //id

            System.out.println(ps);
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.out.println("Update処理エラー：" + e);
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("MySQLのクローズ処理に失敗しました。");
                }
            }
        }

        return true;


    }

}