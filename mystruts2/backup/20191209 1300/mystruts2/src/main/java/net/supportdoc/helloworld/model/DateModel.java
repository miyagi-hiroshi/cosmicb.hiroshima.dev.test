package net.supportdoc.helloworld.model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateModel{

    private String date;

    public DateModel(){

        Calendar c = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        date = sdf.format(c.getTime());

        System.out.println("【DateModel】 " + date);
    }

    public String getDate(){
        System.out.println("【DateModel】日時 "+ date + "をゲットしました。");
        return date;
    }

    public void setDate(String date){
        System.out.println("【DateModel】日時 "+ date + "がセットされました。");
        this.date = date;
    }


}
