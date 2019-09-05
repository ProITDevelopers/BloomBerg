package proitdevelopers.com.bloomberg;

public class VideoModel {

    private String img,title,time;

    public VideoModel() {
    }

    public VideoModel(String img, String time, String title) {
        this.img = img;
        this.time = time;
        this.title = title;
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
}
