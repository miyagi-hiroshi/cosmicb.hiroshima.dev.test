package net.supportdoc.helloworld.model;

public class ExitModel {

    private String idValue, dest;

    public String getIdValue() {
        System.out.println("getIdValue " + idValue);
        return idValue;
    }

    public void setIdValue(String idValue) {
        System.out.println("setIdValue " + idValue);
        this.idValue = idValue;
    }

    public String getDest() {
        System.out.println("getDest " + dest);
        return dest;
    }

    public void setDest(String dest) {
        System.out.println("setDest " + dest);
        this.dest = dest;
    }



}
