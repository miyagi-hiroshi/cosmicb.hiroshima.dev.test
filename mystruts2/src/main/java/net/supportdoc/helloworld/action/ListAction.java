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
        //DTOList = new ArrayList<DtoModel>();
        //query = "select * from houmon where out_date='2000/01/01 0:0:0' order by in_date asc";

        // Azure for MySQLへ接続
        boolean ret = connectDb();


        if (ret == false) {
            System.exit(0);
        }

        // selectクエリを投げる
        dtoList = selectDetail("select * from houmon where out_date='2000/01/01 0:0:0' order by in_date asc;");


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

