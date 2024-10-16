package com.example.safesocietyalertsystem.Model;

public class User {

    String Id;
    String Dated;
    String FireSensor;
    String LDR;
    String DoorSensor;
    String HumiditySensor;
    String img;
    String GasSensor;
    String TempSensor;
    String LaserSensor;
    String RFID;
    String RainSensor;
    String Timed;
    String UltrsonicSensor;

    public User(String id, String dated, String fireSensor, String LDR, String doorSensor, String humiditySensor, String img, String gasSensor, String tempSensor, String laserSensor, String RFID, String rainSensor, String timed, String ultrsonicSensor) {
        Id = id;
        Dated = dated;
        FireSensor = fireSensor;
        this.LDR = LDR;
        DoorSensor = doorSensor;
        HumiditySensor = humiditySensor;
        this.img = img;
        GasSensor = gasSensor;
        TempSensor = tempSensor;
        LaserSensor = laserSensor;
        this.RFID = RFID;
        RainSensor = rainSensor;
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

    public String getFireSensor() {
        return FireSensor;
    }

    public void setFireSensor(String fireSensor) {
        FireSensor = fireSensor;
    }

    public String getLDR() {
        return LDR;
    }

    public void setLDR(String LDR) {
        this.LDR = LDR;
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

    public String getGasSensor() {
        return GasSensor;
    }

    public void setGasSensor(String gasSensor) {
        GasSensor = gasSensor;
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

    public String getRainSensor() {
        return RainSensor;
    }

    public void setRainSensor(String rainSensor) {
        RainSensor = rainSensor;
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
