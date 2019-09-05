package proitdevelopers.com.bloomberg;

public class AudioModel {

    private String title,time;

    public AudioModel() {
    }

    public AudioModel(String time, String title) {
        this.time = time;
        this.title = title;
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
