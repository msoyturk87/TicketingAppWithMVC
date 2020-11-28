package com.cybertek.enums;

public enum Status {
    // to keep in DB we use long way to create it means we can keep it as Male etc
    OPEN("Open"),IN_PROGRESS("In Progress"),UAT_TEST("UAT Testing"),COMPLETE("COmpleted");

    private final String value;

    // it is access modifier default private so no need to add it
    Status(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }

}
