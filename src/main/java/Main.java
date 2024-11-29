import list.MyList;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<>();
        for (int i = 0; i < 10; i++) {
            myList.add(i);
        }
        System.out.println(myList);
        while (!myList.isEmpty()) {
            myList.remove(0);
        }
        System.out.println(myList);

    }
}
