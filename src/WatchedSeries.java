

public class WatchedSeries implements Printable {

    private Series series;
    private int lastWatchedEpisode;


    public WatchedSeries(Series series, int lastWatchedEpisode) {
        this.series = series;
        this.lastWatchedEpisode=lastWatchedEpisode;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public int getLastWatchedEpisode() {
      return this.lastWatchedEpisode;
    }

    public void setLastWatchedEpisode(int lastWatchedEpisode) {
        if (this.lastWatchedEpisode <Constants.TOTAL_NUMBER_OF_EPISODES && this.lastWatchedEpisode >0){
            System.out.println("invalid number of last episode, the series contain only" + Constants.TOTAL_NUMBER_OF_EPISODES+" please select again");
            this.lastWatchedEpisode = lastWatchedEpisode;
        }else
            this.lastWatchedEpisode=1;
    }


    @Override
    public void print() {
        System.out.println( "series name:" + this.series.getName());
        System.out.println("last watched episode information:");
        this.series.getListOfEpisodes()[lastWatchedEpisode-1].print();
        }



}
