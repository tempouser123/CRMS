package persistance;

import services.SimpleList;

import java.io.*;

public class ObjectStore {
    public static <T extends Serializable> void save(String file, SimpleList<T> list) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeInt(list.size());
            for (int i = 0; i < list.size(); i++) {
                out.writeObject(list.get(i));
            }
            out.close();
        } catch (Exception e) {
            System.out.println("save fail");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> void load(String file, SimpleList<T> list) {
        list.all().clear();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            int n = in.readInt();
            for (int i = 0; i < n; i++) {
                T t = (T) in.readObject();
                list.add(t);
            }
            in.close();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println("load fail");
        }
    }
}