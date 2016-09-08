package com.wmz.test;

import java.util.ArrayList;

/**
 * Created by wmz on 20/5/16.
 */
public class Quick {
    public int sort(ArrayList<ArrayList<Integer>> list,int arr[], int low, int high) {
        int sum = 0;
        int l = low;
        int h = high;
        int povit = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= povit)
                h--;
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                ArrayList<Integer> list1 = new ArrayList<Integer>();
                list1.add(h);
                list1.add(l);
                list.add(list1);
                l++;
            }

            while (l < h && arr[l] <= povit)
                l++;

            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                ArrayList<Integer> list2 = new ArrayList<Integer>();
                list2.add(h);
                list2.add(l);
                list.add(list2);
                h--;
            }
        }
        print(arr);
        System.out.print("l=" + (l + 1) + "h=" + (h + 1) + "povit=" + povit + "\n");
        System.out.println("list="+list.toString());
        if (l > low) sort(list,arr, low, l - 1);
        if (h < high) sort(list,arr, l + 1, high);

        return sum;
    }

    private void print(int[] arr) {
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+",");
        }
        System.out.println("------------");
    }

    public int add(int a ,int b){
        return a+b;
    }
}
