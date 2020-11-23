package com.cybertek.enums;

public enum Gender {

    // to keep in DB we use long way to create it means we can keep it as Male etc
    MALE("Male"),FEMALE("Female");

    private final String value;

    // it is access modifier default private so no need to add it
    Gender(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }

}
