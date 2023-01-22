package Custom_Impl.Custom_HashSet;

/*705. Design HashSet

Design a HashSet without using any built-in hash table libraries.
Implement MyHashSet class:
    void add(key) Inserts the value key into the HashSet.
    bool contains(key) Returns whether the value key exists in the HashSet or not.
    void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
Example 1:
Input
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
Output
[null, null, null, true, false, null, true, null, false]
Explanation
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // return True
myHashSet.contains(3); // return False, (not found)
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // return True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // return False, (already removed)*/

import java.util.Arrays;

public class MyHashSet {

    /*Maximum possible value to be stored = 1000000
    Considering each bit of a number reprsents a number
    Number of bits in one number(Numbers each number in the array can represent) = 32
    Total numbers required = 1000000/32 = 31250
    For example:
    number 0 can be reprsented by idx = 0, bit = 0 i.e. num[0] = 00000000000000000000000000000001
    number 37 can be represented by idx = 1(37/32), bit = 5(37%32) ie. num[1] = 00000000000000000000000000100000*/

    int[] set;

    public MyHashSet() {
        this.set = new int[1000000 / 32];
    }
    // set the bit if that element is present
    public void add(int key) {
        set[getIdx(key)] |= getMask(key);
    }

    // unset the bit if a key is not present
    public void remove(int key) {
        set[getIdx(key)] &= ~getMask(key);
    }

    //check if bit corresponding to the key is set or not
    public boolean contains(int key) {
        return (set[getIdx(key)] & getMask(key)) != 0;
    }

    // idx of num[] to which this key belongs
    // for key = 37, it will give 1
    private int getIdx(int key) {
        return key / 32;
    }

    // get mask representing the bit inside num[idx] that corresponds to given key.
    // for key = 37, it will give 00000000000000000000000000100000
    private int getMask(int key) {
        key %= 32;
        return 1<<(key);
    }

    public void print() {
        StringBuilder sb = new StringBuilder("[");
        for (int n : set) {
            if (n != 0) {
                for (int i=0; i < 31; i++) {
                    if ((n & (1<<i)) != 0) sb.append(i).append(", ");
                }
            }
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append("]");
        System.out.println(sb.toString());
    }
}
