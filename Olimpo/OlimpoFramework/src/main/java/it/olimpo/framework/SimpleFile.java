package it.olimpo.framework;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleFile implements Iterable<String> {

    private String[] lists;
    private int counter = 0;

    public SimpleFile () {
        lists = new String[100];
    }

    public SimpleFile (String row_one) {
        lists = new String[100];
        lists[0] = row_one;
    }

    public int size() {
        return counter;
    }

    public void add (String s) {
        if (counter == lists.length - 1) {
            String[] l2 = new String[lists.length * 2];

            for (int i = 0; i < lists.length; i++) {
                l2[i] = lists[i];
            }
            lists = l2;
        }
        lists[counter++] = s;
    }

    public String get (int i) {
        if (i >= lists.length || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        return lists[i];
    }

    public int[] getIntArray (String s) {
        return Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    class SFIterator implements Iterator<String> {
        int current = 0;
        @Override
        public boolean hasNext() {
            return current < counter;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return lists[current++];
        }

        @Override
        public void remove() {
        }
    }
    @Override
    public Iterator<String> iterator() {
        return new SFIterator();
    }
}
