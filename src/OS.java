import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class OS {

    private static ArrayList<Integer> tracks;
    private static ArrayList<Integer> trackPath;
    private static int tracksNum;

    static  {
        tracksNum = 0;
        tracks = new ArrayList<>();

        readTrackList();
    }

    private static void readTrackList() {
        try {
            InputStream is = new FileInputStream("TrackList");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String[] list = reader.readLine().split(" ");
            for (String item :
                    list) {
                tracks.add(Integer.parseInt(item));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FCFS(int thisTrack){
        tracksNum = 0;
        trackPath = new ArrayList<>();
        trackPath.add(thisTrack);
        trackPath.addAll(tracks);
        for (int i = 1; i < trackPath.size(); i++) {
            tracksNum += Math.abs(trackPath.get(i) - trackPath.get(i-1));
        }

    }

    public static void SSTF(int thisTrack){
        tracksNum = 0;
        trackPath = new ArrayList<>();
        ArrayList<Integer> afterTracks = new ArrayList<>();
        ArrayList<Integer> beforeTracks = new ArrayList<>();

        for (int track :
                tracks) {
            if (track < thisTrack)
                beforeTracks.add(track);
            else
                afterTracks.add(track);
        }
        beforeTracks.sort(Comparator.reverseOrder());
        afterTracks.sort(Comparator.naturalOrder());
        trackPath.add(thisTrack);

        while(!(beforeTracks.isEmpty() && afterTracks.isEmpty())){
            if(beforeTracks.isEmpty()){
                trackPath.addAll(afterTracks);
                afterTracks.clear();
            } else if(afterTracks.isEmpty()){
                trackPath.addAll(beforeTracks);
                beforeTracks.clear();
            } else {
                if(Math.abs(beforeTracks.get(0)-thisTrack) >= Math.abs(afterTracks.get(0)-thisTrack) ){
                    trackPath.add(thisTrack = afterTracks.remove(0));

                }else if(Math.abs(beforeTracks.get(0)-thisTrack) < Math.abs(afterTracks.get(0)-thisTrack)){
                    trackPath.add(thisTrack = beforeTracks.remove(0));
                }
            }
        }

        for (int i = 1; i < trackPath.size(); i++) {
            tracksNum += Math.abs(trackPath.get(i) - trackPath.get(i-1));
        }

    }

    public static void SCAN(int thisTrack,int flag){
        tracksNum = 0;
        trackPath = new ArrayList<>();
        ArrayList<Integer> beforeTracks = new ArrayList<>();
        ArrayList<Integer> afterTracks = new ArrayList<>();
        for (int track :
                tracks) {
            if (track < thisTrack)
                beforeTracks.add(track);
            else
                afterTracks.add(track);
        }
        beforeTracks.sort(Comparator.reverseOrder());
        afterTracks.sort(Comparator.naturalOrder());
        trackPath.add(thisTrack);
        if(flag == 0){
            trackPath.addAll(beforeTracks);
            trackPath.addAll(afterTracks);
        }else if (flag == 1){
            trackPath.addAll(afterTracks);
            trackPath.addAll(beforeTracks);
        }

        for (int i = 1; i < trackPath.size(); i++) {
            tracksNum += Math.abs(trackPath.get(i) - trackPath.get(i-1));
        }

    }

    public static String MessageToString(){
        return getString(trackPath);
    }

    public static String TracksToString() {
        return getString(tracks);
    }

    public static int getTracksNum(){
        return tracksNum;
    }

    private static String getString(ArrayList<Integer> trackPath) {
        StringBuilder trackString = new StringBuilder();
        for (int i = 0; i < trackPath.size(); i++) {
            trackString.append(trackPath.get(i));
            if(i != trackPath.size()-1)
                trackString.append(" -> ");
        }
        return trackString.toString();
    }


}
