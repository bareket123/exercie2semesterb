import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static NetflixManagementSystem managementSystem;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        init();
        while (true) {
            printMainMenu();
            try{
                userChoice = scanner.nextInt();
            }catch (InputMismatchException inputMismatchException){

                System.out.println("you must enter a number 1 or 2");
            }

            //check to see if the input valid,if not ask for another output, place it in userChoice
            userChoice=invalidChoice(userChoice,Constants.FIRST_OPTION_IN_MENU,Constants.LAST_OPTION_IN_MAIN_MENU);
            switch (userChoice) {
                case Constants.OPEN_ACCOUNT:
                    managementSystem.openAccount();
                    break;
                case Constants.SING_IN:
                        Account existAccount=signToExistAccount2();
                        optionsAfterLogIn(existAccount);
                    break;


            }


        }
    }

    public static void init(){
        managementSystem=new NetflixManagementSystem();
        //gameOfThrones
        Episode episode1GameOfThrones = new Episode("winter is coming","the opening episode",new MyDate(5,12,2012));
        Episode episode2GameOfThrones = new Episode("The Kings road","episode 2",new MyDate(6,12,2012));
        Episode episode3GameOfThrones = new Episode("Lord Snow","episode 3",new MyDate(7,12,2012));
        Episode[] gameOfThronesEpisodes ={episode1GameOfThrones,episode2GameOfThrones,episode3GameOfThrones};
        Series gameOfThrones =new Series("Game Of Thrones",gameOfThronesEpisodes);

        //Killing Eve
        Episode episode1KillingEve  = new Episode("Nice Face", "the opening episode", new MyDate(18,4,2018));
        Episode episode2KillingEve = new Episode("I'll Deal with Him Later", "Episode 2 ", new MyDate(19,4,2018));
        Episode episode3KillingEve  = new Episode("Don't I Know You?", "Episode 3 ", new MyDate(20,4,2018));
        Episode[] listOfKillingEveEpisodes={episode1KillingEve,episode2KillingEve,episode3KillingEve};
        Series killingEve = new Series("Killing Eve" , listOfKillingEveEpisodes);

        // Vikings
        Episode episode1Vikings  = new Episode("Rites of Passage", "the opening episode", new MyDate(19,4,2013));
        Episode episode2Vikings = new Episode("Wrath of the Northmen", "Episode 2 ", new MyDate(20,4,2013));
        Episode episode3Vikings = new Episode("Dispossessed", "Episode 3", new MyDate(21,4,2013));
        Episode [] vikingsEpisodes = {episode1Vikings , episode2Vikings ,episode3Vikings};
        Series vikings = new Series("Vikings" , vikingsEpisodes);
        Series [] listOfSeries={killingEve,gameOfThrones,vikings};
        managementSystem.setListOfSeries(listOfSeries);

    }

    public static void printMainMenu() {
        System.out.println("Welcome to Netflix:");
        System.out.println("Please choose one of the following options:");
        System.out.println("1.Create new account \n2.Sign in to exist account");


    }
        public static int invalidChoice(int useChoice, int firstOptionInMenu,int lastOptionInMenu) {
        Scanner scanner=new Scanner(System.in);
            while (useChoice > lastOptionInMenu || useChoice<firstOptionInMenu) {
                System.out.println("you enter invalid value, please try again");
                useChoice = scanner.nextInt();

            }
            return useChoice;
        }



    public static void PrintMenuAfterLogIn() {
        System.out.println("please chose one of the following option:");
        System.out.println("1.show list of all the series\n2.View the list of series you started watching\n3.View subscription information");
        System.out.println("4.choose series you like to watch\n5.sign out");

    }

    public static void optionsAfterLogIn(Account account){
        int optionFromMenu=0;
        Scanner scanner=new Scanner(System.in);
        System.out.println("You connected successfully!! what would you like to do?");

        do {
            PrintMenuAfterLogIn();
             optionFromMenu=scanner.nextInt();
             optionFromMenu=invalidChoice(optionFromMenu,Constants.FIRST_OPTION_IN_MENU,Constants.LAST_OPTION_IN_USER_MENU);
            switch (optionFromMenu) {
                case Constants.VIEW_ALL_SERIES:
                    Series[] allSeriesInNetflix = managementSystem.getListOfSeries();
                    System.out.println("Here you can view all the series:\n");
                    for (int i = 0; i < allSeriesInNetflix.length; i++) {
                        if (allSeriesInNetflix[i] != null) {
                            System.out.println(i + "." + allSeriesInNetflix[i].getName());
                            System.out.println();
                        }
                    }
                    break;
                case Constants.VIEW_USER_SERIES:
                      int counterUnwatchedSeries=0;
                    if (account != null) {
                        for (WatchedSeries watchedSeries : account.getWatchedSeries()) {
                            if (watchedSeries != null) {
                                watchedSeries.print();
                            }else{
                                counterUnwatchedSeries++;
                            }


                        }


                    }if (counterUnwatchedSeries== account.getWatchedSeries().length)
                    System.out.println("you haven't started watching any series");
                    System.out.println("//////////////////////////////////////");


                    break;
                case Constants.VIEW_USER_INFORMATION:
                    account.print();
                       break;
                case Constants.CHOOSE_SERIES_TO_WATCH:
                    System.out.println("what would you like to watch (write the name of the series with Capital letters)?");
                    scanner.nextLine();
                    String seriesToWatch=scanner.nextLine();
                    Series wantedSeries =managementSystem.checkIfSeriesExist(seriesToWatch);
                    if (wantedSeries!=null){
                        //if the series the function(isSeriesWatched)return isn't null-then show the episodes,else- series isn't exist-print message
                        if (account.isSeriesWatched(seriesToWatch,managementSystem)!=null){
                            WatchedSeries watchedSeries=account.isSeriesWatched(seriesToWatch,managementSystem);
                           int lastWatchedEpisode =watchedSeries.getLastWatchedEpisode();
                            System.out.println("Here is the last watched episode in the series you wanted:");
                           watchedSeries.getSeries().getListOfEpisodes()[lastWatchedEpisode-1].print();
                        }else {
                            System.out.println("we found the series you liked to watch, here you can view all the exist episodes:");
                            System.out.println();
                            for (Episode episode:wantedSeries.getListOfEpisodes()) {
                                if (episode!=null)
                                    episode.print();

                            }
                            episodeToWatched(wantedSeries,account);


                        }

                    }else
                        System.out.println("We sorry, we don't own the series you want to watched");

                    System.out.println("*************************************");
                    break;
                case 5:
                    break;
                default:
                    optionFromMenu=invalidChoice(optionFromMenu,Constants.FIRST_OPTION_IN_MENU,Constants.LAST_OPTION_IN_USER_MENU);

            }

                }while (optionFromMenu<Constants.LAST_OPTION_IN_USER_MENU);



        }public static Account signToExistAccount2() {
        String username = "", userPassword = "";
        Scanner scanner = new Scanner(System.in);
        Account existAccount=null;
        do {
            System.out.println("please enter your username and your password");
            username=scanner.nextLine();
            userPassword = scanner.nextLine();
            try {
                if (managementSystem.isUsernameExist(username) !=null && managementSystem.isPasswordExist(userPassword)!=null){
                    if (managementSystem.isUsernameExist(username).equals(managementSystem.isPasswordExist(userPassword))  ){
                        existAccount=managementSystem.isUsernameExist(username);
                    }
                } else{
                    System.out.println("we couldn't locate you in our system, please try again");

                }



            }catch (NullPointerException nullPointerException){
                   nullPointerException.printStackTrace();
            }

        }while ((managementSystem.isUsernameExist(username)==null) || (managementSystem.isPasswordExist(userPassword)==null));

        return existAccount;

    }public static void episodeToWatched(Series wantedSeries,Account account){
        Scanner scanner=new Scanner(System.in);
        try {
            System.out.println("which episode you would like to watch? (enter the number of the episode)");
            int wantedEpisodeNumber=scanner.nextInt();
            if (wantedEpisodeNumber<=Constants.TOTAL_NUMBER_OF_EPISODES && wantedEpisodeNumber>0){
                wantedSeries.getListOfEpisodes()[wantedEpisodeNumber-1].addAccountWhoWatched(account);
                account.addToWatched(wantedSeries,wantedEpisodeNumber);
                System.out.println("we add the series to your list of watched series including the last episode you watched, you can view it by pressing option 2 in the menu");
            }else
                System.out.println("The episode you choose to view isn't exist");


        }catch (InputMismatchException inputMismatchException){
            System.out.println("you need to enter episode number 1-3 not a word ");
        }
        }


    }




