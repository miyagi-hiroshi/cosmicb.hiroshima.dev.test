package net.supportdoc.helloworld.action;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.*;
import net.supportdoc.helloworld.action.BaseAction;
import net.supportdoc.helloworld.model.DtoModel;
import net.supportdoc.helloworld.model.ExitModel;

public class ListAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private DtoModel dtoM;
    private List<DtoModel> dtoList;
    private ExitModel exitM;
    private HashMap<String, String> jsonMap = new HashMap<String, String>();
   
    /** 管理画面在室者検索処理
     * @return String
     */
    public String create() {

        dtoM = new DtoModel();
        exitM = new ExitModel();

        //Azure for MySQLへ接続
        // try {
        //     connection();
        // } catch (InstantiationException e) {
        //     System.out.println("JDBCのドライバロードに失敗しました。");

        // } catch (SQLException e) {
        //     System.out.println("MySQLへの接続に失敗しました。");
        //     return "error";
        // }
        String ret = conDetail();
        if (ret=="error") {
            return "error";
        }

        // 現在入室中のデータを検索する
        //DATE_FORMAT(in_date,'%m/%d %H:%i')：入室時間をM/d H:m表示
        //TIME_FORMAT(TIMEDIFF(CURRENT_TIMESTAMP(), in_date), '%H:%i')：入室中時間をh:mm表示
        dtoList = selectDetail("select id," +
                                 "company," +
                                 "name, " +
                                 "num, " + 
                                 "DATE_FORMAT(in_date, '%m/%d %H:%i') as _in_date, " + 
                                 "TIME_FORMAT(TIMEDIFF(CURRENT_TIMESTAMP(), in_date), '%H:%i') as diff, " +
                                 "dest, " +
                                 "out_date " + 
                                 "from houmon " +
                              "where out_date='2000/01/01 0:0:0' " +
                              "order by in_date desc;");

        System.out.println("【ListAction.create】success");


        return "ok";

    }

    
    /** 検索条件指定の場合の処理
     * @return String
     */
    public String filter() {

        boolean chk_filter = exitM.isChk_filter();
        String minDate = exitM.getMinDate();
        String maxDate = exitM.getMaxDate();

        //Azure for MySQLへ接続
        String ret = conDetail();
        if (ret == "error") {
            return "error";
        }

        if (chk_filter) {
            //入室中のみの表示
            dtoList = selectDetail("select id, " +
                                    "company, " +
                                    "name, " +
                                    "num, " +
                                    "DATE_FORMAT(in_date,'%m/%d %H:%i') as _in_date, " +
                                    "TIME_FORMAT(TIMEDIFF(CURRENT_TIMESTAMP(), in_date), '%H:%i') as diff, " +
                                    "dest, " +
                                    "out_date " + 
                                    "from houmon " +
                                    "where (in_date between '" + minDate + " 0:0:0' AND '" + maxDate + " 23:59:59') " +
                                        "AND out_date='2000/01/01 0:0:0' " +
                                    "order by in_date desc;");

        } else {
            //すべて表示
            dtoList = selectDetail("select id, " +
                                    "company, " +
                                    "name, " +
                                    "num, " +
                                    "DATE_FORMAT(in_date,'%m/%d %H:%i') as _in_date, " +
                                    "CASE WHEN out_date>'2000/1/1 0:0:0' THEN '---' " +
                                        "ELSE TIME_FORMAT(TIMEDIFF(CURRENT_TIMESTAMP(), in_date), '%H:%i') END as diff, " +
                                    "dest, " +
                                    "out_date " + 
                                    "from houmon " +
                                    "where (in_date between '" + minDate + " 0:0:0' AND '" + maxDate + " 23:59:59') " +
                                    "order by in_date desc;");
        }

            return "ok";

    }

    /** 退室ボタンを押した時の処理
     */
    public String taishitsu() {

        String id = exitM.getId(); System.out.println("ID = " + id);
        String dest = exitM.getDest(); System.out.println("dest = " + dest);

        DaoUpdate update = new DaoUpdate();

        String ret;
        
        //Azure for MySQLへ接続する処理
        ret = conDetail();
        if (ret == "error") {
            jsonMap.put("MySQLcon","error");
            setJsonMap(jsonMap);
            return "error";

        } else {
            //okの場合
            jsonMap.put("MySQLcon","ok");
        }

        // try {
        //     connection();
        //     jsonMap.put("MySQLcon","ok");

        // } catch (InstantiationException | SQLException e) {
        //     System.out.println("JDBCのドライバロードに失敗しました。");
        //     //json形式でステータスを記録する
        //     jsonMap.put("MySQLcon","error");
        //     setJsonMap(jsonMap);
        // }
        
        //更新処理
        ret = update.updateEvent(conn, id, dest);
        if (ret == "error") {
            System.out.println("退室処理ができませんでした。");
            jsonMap.put("MySQLupdate","error");
            setJsonMap(jsonMap);
            return "error";

        } else {
            System.out.println("ID = " + id + "の退室処理を行いました。");
            jsonMap.put("MySQLupdate","ok");
            jsonMap.put("MySQLupdateId",id);
            jsonMap.put("update", ret);    //update対象件数
            setJsonMap(jsonMap);
            return "ok";
        }

    }

    
    /** データ削除処理(入室日より3年以上経過が対象。退室処理されていないデータは対象外)
     * @return String
     */
    public String delete() {

        //Azure for MySQLへ接続
        String ret = conDetail();
        if (ret == "error") {
            return "error";
        }

        //Selectして削除対象のデータを取得
        dtoList = selectDetail("select " +
                                    "id, " +
                                    "company, " + 
                                    "name, " +
                                    "num, " +
                                    "DATE_FORMAT(in_date,'%Y/%m/%d %H:%i:%s') as _in_date, " +
                                    "DATE_FORMAT(out_date,'%Y/%m/%d %H:%i:%s') as out_date, " +
                                    "TIME_FORMAT(TIMEDIFF(out_date, in_date), '%H:%i:%s') as diff, " +
                                    "dest " +
                                "from houmon " +
                                "where out_date>'2000/1/1 0:0:0' AND (in_date < (NOW() - INTERVAL 1095 DAY)) "+
                                "order by in_date desc;");

        
        if (dtoList.size() == 0) {
            //削除対象データがない場合
            return "nothing";

        } else {
            //削除対象データがある場合、
            String query;
            try {   
                
                for (int i = 0; i< dtoList.size(); i++) {

                    int id = dtoList.get(i).getId();
                    query = "delete from houmon where id=" + id;
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                    
                }

            } catch (SQLException e) {
                System.out.println("DELETE処理エラー：" + e);
                return "error";
                
            } finally {
                if (conn != null) {
                    try {                    
                        rs.close();
                        ps.close();
                        conn.close();
                        System.out.println("MySQLのクローズ処理。");
                    } catch (SQLException e) {
                        System.out.println("MySQLのクローズ処理に失敗しました。");
                    }
                }
            }

            //deleteLogを作成する
            try {
                FileWriter fw = new FileWriter("C:\\test\\deleteLog.txt", false);
                PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

                //Header
                pw.print("ID");
                pw.print(",");
                pw.print("入室時間");
                pw.print(",");
                pw.print("退室時間");
                pw.print(",");
                pw.print("所属／会社名");
                pw.print(",");
                pw.print("名前");
                pw.print(",");
                pw.print("訪問先");
                pw.println();

                for (int i=0; i<dtoList.size(); i++) {
                    pw.print(dtoList.get(i).getId());
                    pw.print(",");
                    pw.print(dtoList.get(i).get_in_date());
                    pw.print(",");
                    pw.print(dtoList.get(i).getOut_date());
                    pw.print(",");
                    pw.print(dtoList.get(i).getCompany());
                    pw.print(",");
                    pw.print(dtoList.get(i).getName());
                    pw.print(",");
                    pw.print(dtoList.get(i).getDest());
                    pw.println();
                }

                fw.close();
                pw.close();
                System.out.println("deleteLog出力OK");

            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
            return "ok";

        }
    }

    
    /** MySQL SELECT発行し結果をDTOListに収める
     * @param query クエリ
     * @return List<DtoModel> 返り値：DTOList
     */
    public List<DtoModel> selectDetail(String query) {

        dtoList = new ArrayList<DtoModel>();

        // 引数のselectクエリを投げる
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
                dtoM.set_in_date(rs.getString("_in_date"));
                dtoM.setOut_date(rs.getString("out_date"));
                dtoM.setDiff(rs.getString("diff"));

                dtoList.add(dtoM);
            }
            //exitM.setCount(dtoList.size());

            //デバッグ用：Listの中を表示させる
            if (dtoList.size() > 0) {
                for (int i=0; i< dtoList.size(); i++) {
                    System.out.print(dtoList.get(i).getId());
                    System.out.print(dtoList.get(i).getCompany());
                    System.out.print(dtoList.get(i).getName());
                    System.out.println(dtoList.get(i).get_in_date());
                }
            }

        } catch (SQLException e) {
            System.out.println("SELECT処理エラー：" + e);

        } finally {
            if (conn != null) {
                try {
                    rs.close();
                    ps.close();
                    conn.close();
                    System.out.println("MySQLのクローズ処理。");
                } catch (SQLException e) {
                    System.out.println("MySQLのクローズ処理に失敗しました。");
                }
            }
        }

        return dtoList;

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

    
    /** 
     * @return ExitModel
     */
    public ExitModel getExitM() {
        System.out.println("getExitM " + exitM);
        return exitM;
    }

    
    /** 
     * @param exitM
     */
    public void setExitM(ExitModel exitM) {
        System.out.println("setExitM " + exitM);
        this.exitM = exitM;
    }

    
    /** 
     * @return HashMap<String, String>
     */
    public HashMap<String, String> getJsonMap() {
        return jsonMap;
    }

    
    /** 
     * @param jsonMap
     */
    public void setJsonMap(HashMap<String, String> jsonMap) {
        this.jsonMap = jsonMap;
    }


}

