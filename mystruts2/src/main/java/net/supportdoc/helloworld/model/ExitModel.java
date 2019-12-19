package net.supportdoc.helloworld.model;

public class ExitModel {

    private String id, dest,minDate, maxDate;
    private int count;
    private boolean chk_filter;

    public String getId() {
        System.out.println("getIdValue " + id);
        return id;
    }

    public void setId(String id) {
        System.out.println("setIdValue " + id);
        this.id = id;
    }

    public String getDest() {
        System.out.println("getDest " + dest);
        return dest;
    }

    public void setDest(String dest) {
        System.out.println("setDest " + dest);
        this.dest = dest;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChk_filter() {
        System.out.println("isChk_filter " + chk_filter);
        return chk_filter;
    }

    public void setChk_filter(boolean chk_filter) {
        System.out.println("setChk_filter " + chk_filter);
        this.chk_filter = chk_filter;
    }

    public String getMinDate() {
        System.out.println("getMinDate " + minDate);
        return minDate;
    }

    public void setMinDate(String minDate) {
        System.out.println("setMinDate " + minDate);
        this.minDate = minDate;
    }

    public String getMaxDate() {
        System.out.println("getMaxDate " + maxDate);
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        System.out.println("setMaxDate " + maxDate);
        this.maxDate = maxDate;
    }

    


}
