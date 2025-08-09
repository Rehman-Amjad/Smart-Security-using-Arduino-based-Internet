package com.example.safesocietyalertsystem.Model;

public class User {

    String Id;
    String Dated;
    String WindowSensor;
    String DoorSensor;
    String HumiditySensor;
    String img;
    String TempSensor;
    String LaserSensor;
    String RFID;
    String Timed;
    String UltrsonicSensor;

    public User(String id, String dated, String windowSensor, String doorSensor, String humiditySensor, String img, String tempSensor, String laserSensor, String RFID, String timed, String ultrsonicSensor) {
        Id = id;
        Dated = dated;
        WindowSensor = windowSensor;
        DoorSensor = doorSensor;
        HumiditySensor = humiditySensor;
        this.img = img;
        TempSensor = tempSensor;
        LaserSensor = laserSensor;
        this.RFID = RFID;
        Timed = timed;
        UltrsonicSensor = ultrsonicSensor;
    }


    public User() {

    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDated() {
        return Dated;
    }

    public void setDated(String dated) {
        Dated = dated;
    }

    public String getWindowSensor() {
        return WindowSensor;
    }

    public void setWindowSensor(String windowSensor) {
        WindowSensor = windowSensor;
    }

    public String getDoorSensor() {
        return DoorSensor;
    }

    public void setDoorSensor(String doorSensor) {
        DoorSensor = doorSensor;
    }

    public String getHumiditySensor() {
        return HumiditySensor;
    }

    public void setHumiditySensor(String humiditySensor) {
        HumiditySensor = humiditySensor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTempSensor() {
        return TempSensor;
    }

    public void setTempSensor(String tempSensor) {
        TempSensor = tempSensor;
    }

    public String getLaserSensor() {
        return LaserSensor;
    }

    public void setLaserSensor(String laserSensor) {
        LaserSensor = laserSensor;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getTimed() {
        return Timed;
    }

    public void setTimed(String timed) {
        Timed = timed;
    }

    public String getUltrsonicSensor() {
        return UltrsonicSensor;
    }

    public void setUltrsonicSensor(String ultrsonicSensor) {
        UltrsonicSensor = ultrsonicSensor;
    }
}
