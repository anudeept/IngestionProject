package com.iheartradio.IngestionProject.domain;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public class Language {
    /*  <AMWKEY>111-SAMPLE-ABC</AMWKEY>
        <COPYRIGHTYEAR>1984</COPYRIGHTYEAR>
        <PUBLISHER>(P) 1984 Epic Records, a division of Sony Music Entertainment</PUBLISHER>
        <EXPLICITLYRICS>false</EXPLICITLYRICS>
        <TITLE>Scuttle Buttin'</TITLE>
        <DURATION>112</DURATION>
        <COPYRIGHT>(P) 1984 Epic Records, a division of Sony Music Entertainment</COPYRIGHT>
        <TRACKNUMBER>1</TRACKNUMBER>
        <ARTIST>Stevie Ray Vaughan &amp; Double Trouble</ARTIST>*/

    private String AMWKEY;
    private String COPYRIGHTYEAR;
    private String PUBLISHER;
    private String EXPLICITLYRICS;
    private String TITLE;
    private int DURATION;
    private String COPYRIGHT;
    private int TRACKNUMBER;
    private String ARTIST;

    public String getAMWKEY() {
        return AMWKEY;
    }

    public void setAMWKEY(String AMWKEY) {
        this.AMWKEY = AMWKEY;
    }

    public String getCOPYRIGHTYEAR() {
        return COPYRIGHTYEAR;
    }

    public void setCOPYRIGHTYEAR(String COPYRIGHTYEAR) {
        this.COPYRIGHTYEAR = COPYRIGHTYEAR;
    }

    public String getPUBLISHER() {
        return PUBLISHER;
    }

    public void setPUBLISHER(String PUBLISHER) {
        this.PUBLISHER = PUBLISHER;
    }

    public String getEXPLICITLYRICS() {
        return EXPLICITLYRICS;
    }

    public void setEXPLICITLYRICS(String EXPLICITLYRICS) {
        this.EXPLICITLYRICS = EXPLICITLYRICS;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getDURATION() {
        return DURATION;
    }

    public void setDURATION(int DURATION) {
        this.DURATION = DURATION;
    }

    public String getCOPYRIGHT() {
        return COPYRIGHT;
    }

    public void setCOPYRIGHT(String COPYRIGHT) {
        this.COPYRIGHT = COPYRIGHT;
    }

    public int getTRACKNUMBER() {
        return TRACKNUMBER;
    }

    public void setTRACKNUMBER(int TRACKNUMBER) {
        this.TRACKNUMBER = TRACKNUMBER;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public void setARTIST(String ARTIST) {
        this.ARTIST = ARTIST;
    }

    @Override
    public String toString() {
        return "Language{" +
                "AMWKEY='" + AMWKEY + '\'' +
                ", COPYRIGHTYEAR='" + COPYRIGHTYEAR + '\'' +
                ", PUBLISHER='" + PUBLISHER + '\'' +
                ", EXPLICITLYRICS='" + EXPLICITLYRICS + '\'' +
                ", TITLE='" + TITLE + '\'' +
                ", DURATION=" + DURATION +
                ", COPYRIGHT='" + COPYRIGHT + '\'' +
                ", TRACKNUMBER=" + TRACKNUMBER +
                ", ARTIST='" + ARTIST + '\'' +
                '}';
    }
}
