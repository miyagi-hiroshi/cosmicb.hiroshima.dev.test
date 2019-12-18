package net.supportdoc.helloworld.action;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.*;
import net.supportdoc.helloworld.action.BaseAction;
import net.supportdoc.helloworld.model.DtoModel;
import net.supportdoc.helloworld.model.ExitModel;

public class ListAction extends BaseAction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private DtoModel dtoM;
    private List<DtoModel> dtoList;
    private ExitModel exitM;
   
   
    /** 
     * @return String
     */
    public String create() {

        dtoM = new DtoModel();
        exitM = new ExitModel();


        //Azure for MySQLへ接続
        boolean ret = connectDb();

        if (ret == false) {
            System.exit(0);
        }

        // selectクエリを投げる
        //DATE_FORMAT(in_date,'%m/%d %H:%i')：入室時間をM/d H:m表示
        //TIME_FORMAT(TIMEDIFF(CURRENT_TIMESTAMP(), in_date), '%H:%i')：入室中時間をh:mm表示
        dtoList = selectDetail("select id, company, name, num, DATE_FORMAT(in_date,'%m/%d %H:%i') as in_date, TIME_FORMAT(TIMEDIFF(CURRENT_TIMESTAMP(), in_date), '%H:%i') AS diff, dest, out_date " + 
                                                    "from houmon " +
                                                    "where out_date='2000/01/01 0:0:0' " +
                                                    "order by in_date desc;");

        System.out.println("【ListAction.create】success");
        return "ok";

    }

    
    /** MySQL SELECT発行
     * @param query クエリ
     * @return List<DtoModel> 返り値：DTOList
     */
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
                dtoM.setDiff(rs.getString("diff"));
                dtoList.add(dtoM);
            }

            //デバッグ用：Listの中を表示させる
            for (int i=0; i< dtoList.size(); i++) {
                System.out.print(dtoList.get(i).getId());
                System.out.print(dtoList.get(i).getCompany());
                System.out.print(dtoList.get(i).getName());
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


    public void taishitsu() {

        String idValue = exitM.getIdValue(); System.out.println("ID = " + idValue);
        String dest = exitM.getDest(); System.out.println("dest = " + dest);

        if (idValue == null) {
            System.out.println("ID値の取得に失敗しました。");
            //System.exit(0);            
        }

        boolean ret;
        
        //Azure for MySQLへ接続
        ret = connectDb();

        if (ret == false) {
            System.exit(0);
        }       
        
        ret = updateDetail(idValue, dest);
        if (ret == false) {
            System.out.println("退室処理ができませんでした。");
            System.exit(0);
        } else {
            System.out.println("ID = " + idValue + "の退室処理を行いました。");
        }

    }
    
    /** 
     * @param id ID
     * @param dest 訪問先
     * @return Boolean
     */
    public Boolean updateDetail(String id, String dest) {
    
        String query = "update houmon set dest = ?, out_date= CURRENT_TIMESTAMP() where id = ?;";
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

    
    /** 
     * @return DtoModel
     */
    public DtoModel getDtoM() {
        return dtoM;
    }

    
    /** 
     * @param dtoM
     */
    public void setDtoM(DtoModel dtoM) {
        this.dtoM = dtoM;
    }

    
    /** 
     * @return List<DtoModel>
     */
    public List<DtoModel> getDtoList() {
        return dtoList;
    }

    
    /** 
     * @param dtoList
     */
    public void setDtoList(List<DtoModel> dtoList) {
        this.dtoList = dtoList;
    }

    public ExitModel getExitM() {
        System.out.println("getExitM " + exitM);
        return exitM;
    }

    public void setExitM(ExitModel exitM) {
        System.out.println("setExitM " + exitM);
        this.exitM = exitM;
    }







}

