import java.util.Scanner;


public class NetflixManagementSystem {
    private Account[] listOfAccounts;
    private Series[] listOfSeries;

    public NetflixManagementSystem() {
        this.listOfAccounts =new Account[Constants.NUMBER_OF_ACCOUNTS];
        this.listOfSeries =new Series[Constants.NUMBER_OF_SERIES];
    }

    public Account[] getListOfAccounts() {
        return listOfAccounts;
    }

    public void setListOfAccounts(Account[] listOfAccounts) {
        this.listOfAccounts = listOfAccounts;
    }

    public Series[] getListOfSeries() {
        return listOfSeries;
    }

    public void setListOfSeries(Series[] listOfSeries) {
        this.listOfSeries = listOfSeries;
    }

    public void openAccount() {
        int emptyIndexArray = returnEmptyIndexAccount();
        String selectedUsername = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("please enter a username");
            selectedUsername = scanner.nextLine();
            if (isUsernameExist(selectedUsername)!=null) {
                System.out.println("The username you chose already occupied,please choose something else ");
            }

        } while (isUsernameExist(selectedUsername)!=null);
        System.out.println("please enter a password (6 characters long, at least: 1 digit and 1 letter)");
        String password = scanner.nextLine();

        Account newAccount = new Account(selectedUsername, password);
        if (emptyIndexArray != -1) {
            this.listOfAccounts[emptyIndexArray] = newAccount;
        } else {
            this.listOfAccounts = extendArrayOfAccounts(newAccount);

        }
    }


   //if array is full, this function meant to create a bigger one
    private Account[] extendArrayOfAccounts(Account account) {
        Account[] tempArray = new Account[listOfAccounts.length + 1];
        for (int i = 0; i < listOfAccounts.length; i++) {
            tempArray[i] = listOfAccounts[i];
        }
        listOfAccounts = tempArray;
        listOfAccounts[listOfAccounts.length - 1] = account;
        return listOfAccounts;
    }


    private int returnEmptyIndexAccount() {
        int outOfRange = -1;
        if (listOfAccounts != null) {
            for (int i = 0; i < listOfAccounts.length; i++) {
                if (listOfAccounts[i] == null) {
                    return i;
                }
            }
        }
        return outOfRange;

    }

    public Account isPasswordExist(String password) {
        for (Account account : listOfAccounts) {
            if (account != null) {
                if (account.getPassword().equals(password))
                    return account;

            }

        }
        return null;
    }

    public Account isUsernameExist(String username) {
        for (Account account : listOfAccounts) {
            if (account != null) {
                if (username.equals(account.getUsername())) {
                    return account;
                }
            }
        }


        return null;


    }public Series checkIfSeriesExist(String wantedSeries){
        for (Series series:this.listOfSeries) {
            if (series!=null){
                String seriesName=series.getName();
                if (seriesName.equals(wantedSeries)){
                  return series;
                }
            }

        }
        return null;
    }




}



