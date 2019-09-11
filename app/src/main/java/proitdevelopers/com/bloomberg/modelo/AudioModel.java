package proitdevelopers.com.bloomberg.modelo;

public class AudioModel {

    private String title,time,fileLink;

    public AudioModel() {
    }

    public AudioModel(String time, String title, String fileLink) {
        this.time = time;
        this.title = title;
        this.fileLink = fileLink;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
}
