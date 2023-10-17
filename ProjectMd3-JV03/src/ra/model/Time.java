package ra.model;

import java.io.Serializable;

public class Time implements Serializable {
 private int id;
  private String time;

    public Time() {
    }

    public Time(int id, String time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        time = time;
    }
    @Override
    public String toString() {
        return "Time: " +
                "id:" + id + "| " +  "time:" + time
                ;
    }
}

