package com.example.car_parking;

public class Car_Description {
    public int id;
    public String carnumber;
    public String entrytime,endtime;
    public String slotnumber;
    public String email,date,type,bill;

    public Car_Description(Integer id,String email,String entrytime,String date,String carnumber,String type, String slotnumber){
        this.id=id;
        this.carnumber=carnumber;
        this.entrytime=entrytime;
        this.slotnumber=slotnumber;
        this.email=email;
        this.date=date;
        this.type=type;
    }
    public Car_Description(Integer id,String email,String entrytime,String endtime,String date,String carnumber,String type, String slotnumber,String bill){
        this.id=id;
        this.carnumber=carnumber;
        this.entrytime=entrytime;
        this.endtime=endtime;
        this.slotnumber=slotnumber;
        this.email=email;
        this.date=date;
        this.type=type;
        this.bill=bill;
    }

//    public Car_Description(Integer integer, String carnumber, String entrytime, String slotnumber, String exittime) {
//        this.id=integer;
//        this.carnumber=carnumber;
//        this.entrytime=entrytime;
//        this.slotnumber=slotnumber;
//        this.exittime=exittime;
//
//
//
//    }

    public int getId() {
        return id;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public String getEntrytime() {
        return entrytime;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getBill() {
        return bill;
    }

    public String getSlotnumber() {
        return slotnumber;
    }
}

