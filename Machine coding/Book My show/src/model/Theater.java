package model;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private List<Show> showList;
    private String name;
    private int capacity;

    public Theater( int capacity, String name ) {
        this.showList = new ArrayList<>();
        this.capacity = capacity;
        this.name = name;
    }

    //------------------< GETTERS & SETTERS >------------------------

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity( int capacity ) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<Show> getShowList() {
        return showList;
    }

    public void setShowList( List<Show> showList ) {
        this.showList = showList;
    }
}
