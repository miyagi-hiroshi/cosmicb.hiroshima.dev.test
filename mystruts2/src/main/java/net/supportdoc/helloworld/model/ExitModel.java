package net.supportdoc.helloworld.model;

public class ExitModel {

    private String id, dest;
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
        return chk_filter;
    }

    public void setChk_filter(boolean chk_filter) {
        this.chk_filter = chk_filter;
    }

    


}
