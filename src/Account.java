import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Account implements Printable {
    private String username;
    private String password;
    private Date dateOfCreation;
    private Date expireSubscription;
    private WatchedSeries[] watchedSeries;

    public Account(String username, String password) {
        this.username = username;
        if (isPasswordStrongEnough(password)){
            this.password =password;
        }else {
          this.password=createStrongPassword();
        }
        this.dateOfCreation=Date.valueOf(LocalDate.now());
        this.expireSubscription=Date.valueOf(LocalDate.now().plusYears(Constants.SUBSCRIPTION_FOR_YEAR));
         this.watchedSeries=new WatchedSeries[Constants.NUMBER_OF_SERIES];

    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setExpireSubscription(Date expireSubscription) {
        this.expireSubscription = expireSubscription;
    }
    public Date getExpireSubscription() {
        return expireSubscription;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username =username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password=password;
        this.password=createStrongPassword();

    }

    public WatchedSeries[] getWatchedSeries() {
        return watchedSeries;
    }

    public void setWatchedSeries(WatchedSeries[] started_watching) {
        this.watchedSeries = started_watching;
    }



 private boolean isPasswordStrongEnough(String password){
        int digitsCounter=0,alphabeticCounter=0;
        char [] characterPassword = password.toCharArray();
        for (int i = 0; i < password.length(); i++) {
             if (Character.isDigit(characterPassword[i])){
                 digitsCounter++;
             }else if (Character.isAlphabetic(characterPassword[i])){
                 alphabeticCounter++;
             }
    }if (digitsCounter>0 && alphabeticCounter>0&&password.length()>=Constants.MINIMUM_LENGTH_PASSWORD){
            return true;

    }

      return false;
    }

    public String createStrongPassword(){
        String password="";
        Scanner scanner = new Scanner(System.in);
        while (!isPasswordStrongEnough(password)) {
            System.out.println("you password invalid, please enter again.Remember,your password must be 6 character long and contain at least one digit and one character  ");
            password = scanner.nextLine();
        }
        return password;




    }


    @Override
    public void print() {
        System.out.println("Subscription details:\nDate of creation:" + this.dateOfCreation +"\nExpire subscription: " + this.expireSubscription);
        System.out.println("So far you watched " + howManyWatchedSeries() +" series." +"\nYou finished watching " + howManySeriesUserFinished() + " series.");
        System.out.println("The total episodes you watched is "+ howManyEpisodeWatched());
        System.out.println("---------------------------------------");

    }
    public void addToWatched(Series series, int episodeNumber) {
        if (getFirstNullIndex()!=-1){
           watchedSeries[getFirstNullIndex()]=new WatchedSeries(series,episodeNumber);
            }
        }

    private int getFirstNullIndex(){
        int fullArray=-1;
        for (int i=0;i<watchedSeries.length;i++){
            if (watchedSeries[i]==null){
                return i;
            }
        }
        return fullArray;
    }

    private int howManyWatchedSeries(){
        int counterWatchedSeries=0;
        for (WatchedSeries series:watchedSeries) {
            if (series!=null){
                counterWatchedSeries++;
            }

        }
        return counterWatchedSeries;
    }

    private int howManyEpisodeWatched(){
        int watchedEpisodeCounter=0;
        for (int i = 0; i < howManyWatchedSeries(); i++) {
            WatchedSeries watchedSeries = this.watchedSeries[i];
            watchedEpisodeCounter+=watchedSeries.getLastWatchedEpisode();

        }
             return watchedEpisodeCounter;
    }private int howManySeriesUserFinished(){
        int counterOfFinishedSeries=0;
        for (WatchedSeries watchedSeries:this.watchedSeries) {
            if (watchedSeries!=null){
                int numberOfWatchedEpisodes = watchedSeries.getLastWatchedEpisode();
                if (numberOfWatchedEpisodes==Constants.TOTAL_NUMBER_OF_EPISODES){
                    counterOfFinishedSeries++;
                }
            }

        }

     return counterOfFinishedSeries;
    }public WatchedSeries isSeriesWatched(String seriesName,NetflixManagementSystem managementSystem){
        Series existSeries = managementSystem.checkIfSeriesExist(seriesName);
        if (existSeries!=null){
            for (WatchedSeries watchedSeries: this.watchedSeries) {
                if (watchedSeries!=null){
                    if (watchedSeries.getSeries().equals(existSeries)){
                        return watchedSeries;
                    }
                }


            }
        }

      return null;
    }

}