package com.bee.sample.ch1.sort;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 冒泡排序
 *
 * @author jiangcan on 2019/10/16
 */
public class BubbleSort {

    private static Integer[] arry = {428, 510, 616, 504, 15, 22, 822, 731, 924, 941, 390, 426, 372, 434, 583, 533,
            58, 602, 127, 994, 299, 168, 387, 395, 114, 421, 671, 352, 690, 422, 598, 864, 409, 111, 102, 606, 534,
            674, 569, 8, 545, 853, 166, 618, 929, 606, 953, 400, 645, 425, 408, 444, 773, 854, 926, 641, 163, 629,
            816, 338, 264, 806, 153, 351, 27, 661, 951, 70, 814, 907, 539, 986, 79, 640, 327, 177, 351, 74, 882, 111,
            983, 312, 92, 146, 932, 990, 16, 900, 757, 72, 134, 215, 475, 760, 538, 747, 494, 582, 440, 365, 668, 585,
            177, 533, 142, 156, 441, 673, 849, 643, 606, 167, 663, 736, 935, 696, 774, 593, 586, 718, 436, 902, 770,
            13, 469, 222, 765, 65, 458, 38, 824, 348, 902, 42, 258, 590, 378, 829, 190, 469, 992, 133, 10, 992, 792,
            853, 915, 770, 8, 722, 247, 711, 767, 745, 702, 511, 630, 280, 617, 922, 922, 856, 285, 122, 285, 443, 894,
            40, 649, 77, 286, 656, 188, 481, 429, 661, 413, 440, 191, 210, 374, 821, 139, 480, 770, 639, 338, 629, 374,
            563, 161, 459, 640, 558, 575, 206, 207, 781, 783, 483, 395, 712, 923, 306, 870, 528, 933, 320, 9, 206, 298,
            97, 5, 78, 362, 650, 921, 66, 9, 504, 4, 528, 200, 88, 579, 745, 411, 6, 219, 589, 115, 923, 696, 275, 756,
            925, 766, 800, 517, 614, 548, 779, 596, 959, 368, 317, 147, 306, 834, 893, 429, 185, 56, 659, 119, 435, 401,
            827, 212, 177, 250, 672, 813, 920, 216, 653, 800, 67, 33, 7, 636, 688, 204, 725, 262, 907, 118, 113, 898,
            169, 996, 993, 85, 93, 715, 127, 465, 306, 440, 780, 104, 566, 264, 258, 421, 356, 417, 295, 266, 173, 456,
            962, 692, 362, 487, 6, 307, 612, 456, 192, 659, 174, 445, 886, 525, 525, 135, 930, 553, 546, 331, 845, 391,
            95, 325, 765, 49, 10, 103, 861, 875, 531, 973, 773, 309, 906, 539, 365, 729, 933, 208, 539, 293, 415, 160,
            590, 624, 21, 866, 750, 656, 892, 671, 867, 628, 50, 844, 718, 46, 853, 441, 58, 182, 381, 380, 757, 322,
            787, 282, 721, 292, 329, 515, 648, 68, 830, 425, 963, 899, 47, 86, 99, 831, 673, 840, 520, 415, 335, 648,
            663, 840, 496, 147, 391, 55, 288, 468, 173, 131, 535, 926, 158, 521, 590, 474, 123, 118, 528, 604, 220,
            313, 47, 919, 661, 502, 15, 498, 78, 192, 319, 776, 493, 458, 192, 382, 548, 410, 437, 194, 6, 99, 859, 153,
            376, 917, 519, 247, 767, 151, 811, 357, 45, 370, 863, 134, 544, 130, 174, 480, 802, 833, 153, 629, 577, 871,
            453, 621, 739, 281, 590, 410, 740, 334, 157, 961, 511, 101, 601, 522, 487, 81, 772, 545, 501, 978, 461, 535,
            833, 829, 370, 958, 122, 36, 274, 999, 30, 540, 968, 500, 721, 297, 821, 943, 767, 588, 940, 567, 651, 473,
            421};

    public static void main(String[] args) {
//        bubbleSort();
//        selectionSort();
//        insertSort();
        insertSort2();
    }


    /**
     * 获取一个随机List
     */
    private void randomList() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 500; i++) {
            list.add(new Random().nextInt(1000));
        }
        System.out.println(list);
    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort() {
        long startTime = System.currentTimeMillis();
        if (arry == null || arry.length == 0) {
            return;
        }
        for (int i = 0; i < arry.length; i++) {
            for (int j = 0; j < arry.length - 1 - i; j++) {
                if (arry[j] > arry[j + 1]) {
                    int temp = arry[j + 1];
                    arry[j + 1] = arry[j];
                    arry[j] = temp;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("冒泡排序时间:" + (endTime - startTime));
        System.out.println("冒泡排序结果：" + JSONObject.toJSONString(arry));
    }

    /**
     * 选择排序
     */
    private static void selectionSort() {
        long startTime = System.currentTimeMillis();
        if (arry == null || arry.length == 0) {
            return;
        }
        for (int i = 0; i < arry.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arry.length; j++) {
                if (arry[j] < arry[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arry[minIndex];
            arry[minIndex] = arry[i];
            arry[i] = temp;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("选择排序时间:" + (endTime - startTime));
        System.out.println("选择排序结果：" + JSONObject.toJSONString(arry));
    }

    /**
     * 插入排序
     */
    private static void insertSort() {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < arry.length; i++) {
            int insert = arry[i];
            int j = i - 1;
            while (j >= 0 && insert < arry[j]) {
                arry[j + 1] = arry[j];
                j--;
            }
            arry[j + 1] = insert;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("插入排序时间:" + (endTime - startTime));
        System.out.println("插入排序结果：" + JSONObject.toJSONString(arry));
    }

    /**
     * 插入排序
     */
    private static void insertSort2() {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < arry.length; i++) {
            int insert = arry[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (insert < arry[j]) {
                    arry[j + 1] = arry[j];
                } else {
                    break;
                }
            }
            arry[j + 1] = insert;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("插入排序2时间:" + (endTime - startTime));
        System.out.println("插入排序2结果：" + JSONObject.toJSONString(arry));
    }
}
