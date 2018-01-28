package com.iheartradio.IngestionProject.domain;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public class ProductInfo {
    private Language LANGUAGE;

    public ProductInfo() {
    }

    public Language getLANGUAGE() {
        return LANGUAGE;
    }

    public void setLANGUAGE(Language LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "LANGUAGE=" + LANGUAGE +
                '}';
    }
}
