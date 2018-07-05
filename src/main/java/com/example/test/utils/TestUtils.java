package com.example.test.utils;

public class TestUtils {

    public static void main(String[] args) {
        int[] number = {3, 55, 33, 2};
        int temp = 0;
        for (int i = 0; i < number.length - 1; i++) {
            for (int j = 0; j < number.length - i - 1; j++) {
                if (number[j + 1] < number[j]) {
                    temp = number[j];
                    number[j] = number[j + 1];
                    number[j + 1] = temp;
                }
            }
        }
        for (int x : number) {
            System.out.println(x);
        }
    }

    public void bug(){
        System.out.println("这是bug分支！！！");
        System.out.println("这是在feature分支新添加的内容");
    }


}
