package services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleList<T> implements Serializable {
    private final List<T> data = new ArrayList<>();

    public void add(T t) {
        data.add(t);
    }

    public int size() {
        return data.size();
    }

    public T get(int i) {
        return data.get(i);
    }

    public void set(int i, T t) {
        data.set(i, t);
    }

    public void removeAt(int i) {
        data.remove(i);
    }

    public List<T> all() {
        return data;
    }
}