public class Series implements Printable {

    private String name;
    private Episode [] listOfEpisodes;



    public Series(String name,Episode[]listOfEpisodes) {
        this.name = name;
        this.listOfEpisodes = listOfEpisodes;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Episode[] getListOfEpisodes() {
        return listOfEpisodes;
    }

    public void setListOfEpisodes(Episode[] listOfEpisodes) {
        this.listOfEpisodes = listOfEpisodes;
    }

    @Override
    public void print() {
        System.out.println("name: " + this.name);
        for (Episode episode:listOfEpisodes) {
              if (episode!=null)
                  episode.print();
        }


    }
}

