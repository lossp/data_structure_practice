package chapter3;


import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 编写只用一个数组而实现两个栈的例程。这些例程不应该声明溢出，除非数组中的每个单元都被使用
 *
 * tip:
 *  1.定义两个指针，topStackPosition和bottomStackPosition，两个指针分别初始化为起点和终点，同时指向数组中间，这样一来数组使用率比较高，同时判断数组充满时候方便
 *  2.不声明溢出，则数组填充满的时候自动扩容，扩容直接从ArrayList中方法学，oldCapacity >> 1，bit每一位向右移动一位，相当于变为原来一半大小，这种效率高，因为直接操作bit
 */
public class OneArrayTwoStacks {
    private String[] array;
    private int size;
    private int topStackPosition;
    private int bottomStackPosition;

    public OneArrayTwoStacks(int size) {
        array = new String[size];
        this.size = size;
        topStackPosition = -1;
        bottomStackPosition = size;
    }

    public void pushToFirst(String item) {
        // 以当前数组的4分之一大小扩容，需要将数组进行复制
        if (topStackPosition == bottomStackPosition - 1) {
            grow();
        }
        array[++topStackPosition] = item;
    }

    public String popFromFirst() {
        if (topStackPosition == -1) throw new IndexOutOfBoundsException();
        String item = array[topStackPosition];
        array[topStackPosition] = null;
        topStackPosition = topStackPosition - 1;
        return item;
    }

    public void pushToSecond(String item) {
        if (topStackPosition + 1 == bottomStackPosition) grow();
        array[--bottomStackPosition] = item;
    }

    public String popFromSecond() {
        if (bottomStackPosition == size) throw new IndexOutOfBoundsException();
        String item = array[bottomStackPosition];
        array[bottomStackPosition] = null;
        bottomStackPosition = bottomStackPosition + 1;
        return item;
    }

    public int getFirstSize() {
        return topStackPosition + 1;
    }

    public int getSecondSize() {
        return bottomStackPosition - 1;
    }

    public void printArray() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i != array.length - 1) {
                result.append(",");
            }
        }
        System.out.println(result.toString());
    }

    private void grow() {
        array = Arrays.copyOf(array, newCapacity());
        int diff = array.length - size;
        // 需要将第二个栈相应的后移diff位
        for (int i = 0; i < size - bottomStackPosition; i++) {
            array[size + diff - 1 - i] = array[size - 1 - i];
            array[size - 1 - i] = null;
        }
        // 重置size大小
        size = array.length;
        // 重置bottomStackPosition指针位置
        bottomStackPosition += diff;
    }

    private int newCapacity() {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return newCapacity;
    }

    public static void main(String[] args) {
        OneArrayTwoStacks oneArrayTwoStacks = new OneArrayTwoStacks(10);
        oneArrayTwoStacks.pushToFirst("第一个");
        oneArrayTwoStacks.pushToFirst("第2个");
        oneArrayTwoStacks.pushToFirst("第3个");
        oneArrayTwoStacks.pushToFirst("第4个");
        oneArrayTwoStacks.pushToSecond("最后一个");
        oneArrayTwoStacks.pushToSecond("最后二个");
        oneArrayTwoStacks.pushToSecond("最后3个");
        oneArrayTwoStacks.pushToSecond("最后4个");
        oneArrayTwoStacks.pushToSecond("最后5个");
        oneArrayTwoStacks.pushToSecond("最后6个");
        oneArrayTwoStacks.pushToSecond("最后7个");
        oneArrayTwoStacks.pushToSecond("最后8个");
        oneArrayTwoStacks.pushToFirst("第rist 5个");

        oneArrayTwoStacks.printArray();
    }
}
