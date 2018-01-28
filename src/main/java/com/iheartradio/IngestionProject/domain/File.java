package com.iheartradio.IngestionProject.domain;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public class File {

    private FileInfo FILEINFO;

    public File() {
    }

    public FileInfo getFILEINFO() {
        return FILEINFO;
    }

    public void setFILEINFO(FileInfo FILEINFO) {
        this.FILEINFO = FILEINFO;
    }

    @Override
    public String toString() {
        return "File{" +
                "FILEINFO=" + FILEINFO +
                '}';
    }
}
