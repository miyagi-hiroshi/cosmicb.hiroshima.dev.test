package net.supportdoc.helloworld.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.*;

import net.supportdoc.helloworld.action.BaseAction;
import net.supportdoc.helloworld.model.DtoModel;

public class ListAction extends BaseAction {

    private DtoModel dto;
    private List<DtoModel> DTOList = new ArrayList<DtoModel>();


    public ListAction() {



    }

    public String inRoomSelect() {

        String query;
        boolean ret;
        PreparedStatement ps=null;
        ResultSet rs=null;
        query = "select * from houmon where out_date='2000/01/01 0:0:0' order by in_date asc";

        // Azure for MySQLへ接続
        ret = connectDb();
        if (ret == false) {
            System.exit(0);
        }

        // selectクエリを投げる
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            // ResulktSetからDTOListを作る
            while (rs.next()) {
                dto = new DtoModel();
                dto.setId(rs.getInt("id"));
                dto.setCompany(rs.getString("company"));
                dto.setName(rs.getString("name"));
                dto.setNum(rs.getInt("num"));
                dto.setDest(rs.getString("dest"));
                dto.setIn_date(rs.getString("in_date"));
                dto.setOut_date(rs.getString("out_date"));
                DTOList.add(dto);
            }

            for (int i=0; i< DTOList.size(); i++) {
                System.out.print(DTOList.get(i).getId() + "\t");
                System.out.print(DTOList.get(i).getCompany() + "\t");
                System.out.print(DTOList.get(i).getName() + "\t");
                System.out.println(DTOList.get(i).getIn_date());
            }



        } catch (SQLException e) {
            //System.out.println("SELECT処理エラー：" + e);

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

        return "ok";

    }

    public DtoModel getDto() {
        return dto;
    }

    public void setDto(DtoModel dto) {
        this.dto = dto;
    }

    public List<DtoModel> getDTOList() {
        return DTOList;
    }

    public void setDTOList(List<DtoModel> dTOList) {
        DTOList = dTOList;
    }




}

