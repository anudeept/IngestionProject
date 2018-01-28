package com.iheartradio.IngestionProject.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
@Document(collection = "Tracks")
public class Track {

    private ProductInfo PRODUCTINFO;
    private List<Genre> GENRES;
    private List<File> FILES;



    public ProductInfo getPRODUCTINFO() {
        return PRODUCTINFO;
    }

    public void setPRODUCTINFO(ProductInfo PRODUCTINFO) {
        this.PRODUCTINFO = PRODUCTINFO;
    }

    public List<Genre> getGENRES() {
        return GENRES;
    }

    public void setGENRES(List<Genre> GENRES) {
        this.GENRES = GENRES;
    }

    public List<File> getFILES() {
        return FILES;
    }

    public void setFILES(List<File> FILES) {
        this.FILES = FILES;
    }

    @Override
    public String toString() {
        return "Track{" +
                "PRODUCTINFO=" + PRODUCTINFO +
                ", GENRES=" + GENRES +
                ", FILES=" + FILES +
                '}';
    }
}
