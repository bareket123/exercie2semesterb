public class Episode implements Printable {

    private String name;
    private String overView;
    private MyDate dateOfAiring;
    private Account [] usersWatchedTheEpisode;


    public Episode(String name, String overView, MyDate dateOfAiring) {
        this.name = name;
        this.overView = overView;
        this.dateOfAiring = dateOfAiring;
        this.usersWatchedTheEpisode =new Account[Constants.NUMBER_OF_ACCOUNTS];
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public MyDate getDateOfAiring() {
        return dateOfAiring;
    }

    public void setDateOfAiring(MyDate myMyDateOfAiring) {
        this.dateOfAiring = myMyDateOfAiring;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account[] getUsersWatchedTheEpisode() {
        return usersWatchedTheEpisode;
    }

    public void setUsersWatchedTheEpisode(Account[] usersWatchedTheEpisode) {
        this.usersWatchedTheEpisode = usersWatchedTheEpisode;
    }

    @Override
    public void print() {
        System.out.println("name: " +this.name);
        System.out.println("overview:" + this.overView);
        System.out.println("date of airing:");
        this.dateOfAiring.print();
    }

    private int returnEmptyIndexAccount() {
        int outOfRange = -1;
        if (this.usersWatchedTheEpisode != null) {
            for (int i = 0; i < usersWatchedTheEpisode.length; i++) {
                if (usersWatchedTheEpisode[i] == null) {
                    return i;
                }
            }
        }
        System.out.println("array is null");
        return outOfRange;

    }
    public void addAccountWhoWatched(Account account){
        int emptyIndex=returnEmptyIndexAccount();
        if (emptyIndex!=-1){
            this.usersWatchedTheEpisode[emptyIndex]=account;

        }else {
            increaseArrayOfAccounts(account);

        }


    } private Account [] increaseArrayOfAccounts(Account account){
        Account [] largerArrayAccount=new Account[this.usersWatchedTheEpisode.length+1];
        for (int i = 0; i < usersWatchedTheEpisode.length; i++) {
            usersWatchedTheEpisode[i]=largerArrayAccount[i];
        }
        usersWatchedTheEpisode =largerArrayAccount;

        usersWatchedTheEpisode[usersWatchedTheEpisode.length-1]=account;
        return usersWatchedTheEpisode;
    }
}

