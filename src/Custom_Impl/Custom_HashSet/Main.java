package Custom_Impl.Custom_HashSet;

public class Main {
    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.print();
        myHashSet.add(20);      // set = [1, 2]
        myHashSet.print();
        System.out.println(myHashSet.contains(1)); // return True
        myHashSet.contains(3); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // return True
        myHashSet.remove(3);   // set = [1]
        myHashSet.print();
        System.out.println(myHashSet.contains(3)); // return False, (already removed)*/
    }

}
