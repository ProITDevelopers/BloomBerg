package proitdevelopers.com.bloomberg.modelo;

public class VideoModel {

    private String img,title,time,fileLink;

    public VideoModel() {
    }

    public VideoModel(String img, String time, String title,String fileLink) {
        this.img = img;
        this.time = time;
        this.title = title;
        this.fileLink = fileLink;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
