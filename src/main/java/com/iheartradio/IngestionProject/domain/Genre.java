package com.iheartradio.IngestionProject.domain;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public class Genre {

    private String GENRENAME;

    public Genre() {
    }

    public String getGENRENAME() {
        return GENRENAME;
    }

    public void setGENRENAME(String GENRENAME) {
        this.GENRENAME = GENRENAME;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "GENRENAME='" + GENRENAME + '\'' +
                '}';
    }
}
