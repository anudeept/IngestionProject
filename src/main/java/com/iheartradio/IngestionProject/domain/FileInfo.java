package com.iheartradio.IngestionProject.domain;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public class FileInfo {

    private String ACCESSSTRING;

    public String getACCESSSTRING() {
        return ACCESSSTRING;
    }

    public void setACCESSSTRING(String ACCESSSTRING) {
        this.ACCESSSTRING = ACCESSSTRING;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "ACCESSSTRING='" + ACCESSSTRING + '\'' +
                '}';
    }
}
