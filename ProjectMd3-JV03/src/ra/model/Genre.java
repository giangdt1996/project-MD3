package ra.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Genre implements Serializable {
    private List<Genre> genre= new ArrayList<>();
    private int id;
    private String nameGenre;

    public Genre() {
    }

    public Genre(int id, String nameGenre) {
        this.id = id;
        this.nameGenre = nameGenre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }
    public void Display() {
        System.out.println("ID: " + id + "||" +  "Genre: " + nameGenre);
    }
}
