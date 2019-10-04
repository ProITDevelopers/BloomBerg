package proitdevelopers.com.bloomberg.audio_video;

public interface CallBacks {

    void callbackObserver(Object obj);

    public interface playerCallBack {
        void onItemClickOnItem(Integer albumId);

        void onPlayingEnd();
    }
}
