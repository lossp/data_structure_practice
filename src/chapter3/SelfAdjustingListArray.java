package chapter3;

import java.util.Arrays;

/**
 * a.写出自调整表(self-adjusting list)的数组实现。在自调整表中，所有的插入都在前端进行。
 * 自调整表添加一个find操作，当一个元素被find访问时候，它就被移动到表的前端而并不会改变其余的项的相对顺序
 *
 * tip: 数组为自动扩容数组，预留一个null位置
 */
public class SelfAdjustingListArray {
    private String[] array;
    private int INIT_CAPACITY = 10;
    private int size;
    private int pointer;


    public SelfAdjustingListArray() {
        array = new String[INIT_CAPACITY];
        size = INIT_CAPACITY;
        pointer = size - 1;
    }

    public void push(String item) {
        if (pointer == 0) { grow(); }
        array[pointer--] = item;
    }

    private void grow() {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        array = Arrays.copyOf(array, newCapacity);

        for (int i = 0; i < oldCapacity; i++) {
            // 从最后一位开始，整理位置，将数组移动到新数组后面部分，
            array[newCapacity - 1 - i] = array[oldCapacity - 1 - i];
            array[oldCapacity - 1 - i] = null;
        }
        // 移动完毕之后，将数字添加至尾部
        size = newCapacity;
        // 更新指针位置
        pointer = newCapacity - oldCapacity;

    }


    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

    public boolean find(String item) {
        boolean isFound = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(item)) {
                isFound = true;
                swap(pointer + 1, i);
                break;
            }
        }
        return isFound;
    }

    public int getPointer() { return pointer; }

    private void swap(int positionA, int positionB) {
        String tempA = array[positionA];
        array[positionA] = array[positionB];
        array[positionB] = tempA;
    }

    public static void main(String[] args) {
        SelfAdjustingListArray selfAdjustingListArray = new SelfAdjustingListArray();
        selfAdjustingListArray.push("0");
        selfAdjustingListArray.push("1");
        selfAdjustingListArray.push("2");
        selfAdjustingListArray.push("3");
        selfAdjustingListArray.push("4");
        selfAdjustingListArray.push("5");
        selfAdjustingListArray.push("6");
        selfAdjustingListArray.push("7");
        selfAdjustingListArray.push("8");
        selfAdjustingListArray.push("9");
        selfAdjustingListArray.push("10");
        selfAdjustingListArray.push("11");
        selfAdjustingListArray.push("12");
        selfAdjustingListArray.push("13");
        selfAdjustingListArray.find("10");
        selfAdjustingListArray.printArray();
    }
}
