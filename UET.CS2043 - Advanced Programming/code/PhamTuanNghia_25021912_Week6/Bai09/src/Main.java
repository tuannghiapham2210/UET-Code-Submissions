interface AudioPlayable {
    void playAudio(String file);
}

interface VideoPlayable {
    void playVideo(String file);
}

class AudioPlayer implements AudioPlayable{ //the only purpose of this class is playing audio
    public void playAudio(String file){
        System.out.println("playing audio from file: " + file);
    }
}

class VideoPlayer implements VideoPlayable{ //the only purpose of this class is playing video
    public void playVideo(String file){
        System.out.println("playing video from file: " + file);
    }
}

class MediaPlayer { //the only purpose of this class is provide an object to hold an audio player and a video player
    AudioPlayable audioPlayer;
    VideoPlayable videoPlayer; //depends on abstractions not concretions

    //constructor injection
    public MediaPlayer(AudioPlayable audioPlayer, VideoPlayable videoPlayer){
        this.audioPlayer = audioPlayer;
        this.videoPlayer= videoPlayer;
    }

    public void playAudio(String file){
        audioPlayer.playAudio(file);
    }

    public void playVideo(String file){
        videoPlayer.playVideo(file);
    }
}

public class Main{
    public static void main(String[] args){
        //creating concrete videoPlayer and audioPlayer
        AudioPlayer audioPlayer = new AudioPlayer();
        VideoPlayer videoPlayer = new VideoPlayer();
        
        //creating a media player
        MediaPlayer mediaPlayer = new MediaPlayer(audioPlayer, videoPlayer);

        mediaPlayer.playAudio("AmThamBenEm.mp3");
        mediaPlayer.playVideo("Avatar3.mp4");
    }   
}