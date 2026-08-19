package auditoriski.auditoriska3;

import java.util.ArrayList;
import java.util.Random;

class Box<T> {

    private ArrayList<T> items;

    public Box() {
        this.items = new ArrayList<>();
    }

    public void add(T item){
        items.add(item);
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public T drawItem(){
        if (isEmpty()) return null;

        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }
}

public class BoxTest{
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.add("Dexter");
        stringBox.add("Seinfeld");
        stringBox.add("Barney");
        stringBox.add("Sheldon");
        stringBox.add("Costanza");
        stringBox.add("Hank");
        System.out.println(stringBox.drawItem());
        Box<Integer> intBox = new Box<>();
        intBox.add(23);
        intBox.add(15);
        intBox.add(19);
        intBox.add(3);
        intBox.add(92);
        System.out.println(intBox.drawItem());
    }

}