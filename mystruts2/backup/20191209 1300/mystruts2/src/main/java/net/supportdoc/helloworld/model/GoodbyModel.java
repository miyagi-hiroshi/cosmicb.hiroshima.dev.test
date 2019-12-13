package net.supportdoc.helloworld.model;
import java.util.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class GoodbyModel {

    private String report;

    private String sysDate;
    private ArrayList<String> dbData;
    
    public GoodbyModel(){

        Calendar c = Calendar.getInstance();
        //カレンダーフォーマット
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        sysDate = sdf.format(c.getTime());

        //report="Strutsのない世界よさようなら";
        // dbData = new ArrayList <String>();
        // dbData.add("2019/12/06 09:00:00,退出済,みやぎ");
        // dbData.add("2019/12/06 09:05:00,入室中,おか");
        // dbData.add("2019/12/06 09:11:00,入室中,はたがみ");
        // dbData.add("2019/12/06 09:20:00,退出済,うちだ");
        // dbData.add("2019/12/06 13:00:00,入室中,しらはた");
        // dbData.add("2019/12/06 15:00:00,入室中,しらはた");
        // dbData.add("2019/12/06 18:00:00,入室中,しらはた");

        
    }

    public String getReport() {
        System.out.println("【GoodbyModel:report】" + report);
        return report;
    }

    public ArrayList<String> getDbdata(){
        return dbData;
    }

    public void setDbdata(ArrayList<String> dbData){
        this.dbData = dbData;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
}