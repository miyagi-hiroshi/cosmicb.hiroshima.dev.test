package net.supportdoc.helloworld.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

import net.supportdoc.helloworld.action.BaseAction;
import net.supportdoc.helloworld.model.DtoModel;

public class ListAction extends BaseAction {

    private DtoModel dtoM;
    private List<DtoModel> dtoList;


    public ListAction() {

    }

    public String create() {

        dtoM = new DtoModel();

        // Azure for MySQLへ接続
        boolean ret = connectDb();


        if (ret == false) {
            System.exit(0);
        }

        // selectクエリを投げる
        dtoList = selectDetail("select id, company, name, num, in_date, TIMESTAMPDIFF(HOUR, in_date, CURRENT_TIMESTAMP()) AS diff, dest, out_date " + 
                                                    "from houmon where out_date='2000/01/01 0:0:0' order by in_date desc;");


        return "ok";

    }

    public List<DtoModel> selectDetail(String query) {

        dtoList = new ArrayList<DtoModel>();

        // selectクエリを投げる
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            // ResultSetからDTOListを作る
            while (rs.next()) {
                dtoM = new DtoModel();
                dtoM.setId(rs.getInt("id"));
                dtoM.setCompany(rs.getString("company"));
                dtoM.setName(rs.getString("name"));
                dtoM.setNum(rs.getInt("num"));
                dtoM.setDest(rs.getString("dest"));
                dtoM.setIn_date(rs.getString("in_date"));
                dtoM.setOut_date(rs.getString("out_date"));
                dtoM.setDiff(rs.getInt("diff"));
                dtoList.add(dtoM);
            }

            //デバッグ用：Listの中を表示させる
            for (int i=0; i< dtoList.size(); i++) {
                System.out.print(dtoList.get(i).getId() + "\t");
                System.out.print(dtoList.get(i).getCompany() + "\t");
                System.out.print(dtoList.get(i).getName() + "\t");
                System.out.println(dtoList.get(i).getIn_date());
            }

        } catch (SQLException e) {
            System.out.println("SELECT処理エラー：" + e);

        } finally {
            if (conn != null) {
                try {
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("MySQLのクローズ処理に失敗しました。");
                }
            }
        }

        return dtoList;


    }

    public Boolean updateDetail(String id, String dest) {
    
        String query = "update houmon set dest = ?, out_time= CURRENT_TIMESTAMP() where id=?;";
        //insertクエリを投げる
        try {

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, dest);  //訪問先
            ps.setString(2, id);    //idまたは入室時間どちらか

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

    public DtoModel getDtoM() {
        return dtoM;
    }

    public void setDtoM(DtoModel dtoM) {
        this.dtoM = dtoM;
    }

    public List<DtoModel> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<DtoModel> dtoList) {
        this.dtoList = dtoList;
    }






}

