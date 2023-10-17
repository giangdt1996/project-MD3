package ra.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase<T> implements Serializable{
    public static final String USER_PATH ="/Users/shmily/Desktop/ProjectMd3-JV03/src/ra/util/users.txt";
    public static final String MOVIE_PATH ="/Users/shmily/Desktop/ProjectMd3-JV03/src/ra/util/movies.txt";
    public static final String TICKET_PATH ="/Users/shmily/Desktop/ProjectMd3-JV03/src/ra/util/tickets.txt";
    public  void writeToFile(List<T> o, String path){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos= new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(oos != null){
                    oos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try{
                if(fos != null){
                    fos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public  List<T> readFromFile(String path){
        FileInputStream fis  = null;
        ObjectInputStream ois =null;
        List<T> o = new ArrayList<>();
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            o = (List<T>) ois.readObject();
        }catch (EOFException xx){

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(ois != null){
                    ois.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try{
                if(fis != null){
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return o;
    }
}
