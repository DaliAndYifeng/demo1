package com.example.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo1.mapper.dyhTestMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo1.dao.dyhTest;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@RestController()
public class DyhtestController {
    @Resource
    private com.example.demo1.mapper.dyhTestMapper dyhTestMapper;


    @RequestMapping("run")
    public int run(Integer param) {
        int i = 0;
//        try {
//            //获取对应文件
//            File name = new File("C:\\Users\\donyi001\\Desktop\\新建文件夹\\20220228.txt");
//            //防止读取文件内容时乱码
//            InputStreamReader read = new InputStreamReader(new FileInputStream(name), "UTF-8");
//            BufferedReader bf = new BufferedReader(read);
//            String str;
//            // 按行读取字符串
//            while (bf.readLine() != null) {
//                str = bf.readLine();
//                String str1 = str.substring(0, str.indexOf("{"));
//                String json = str.substring(str1.length(), str.length());
//                i++;
//                System.out.println(json+i);
//            }
//            bf.close();
//            read.close();

        File file = new File("C:\\Users\\donyi001\\Desktop\\新建文件夹\\20220228.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                String str1 = tempString.substring(0, tempString.indexOf("{"));
                String json = tempString.substring(str1.length(), tempString.length());
                dyhTest dyhTest = JSONObject.parseObject(json, dyhTest.class);
                dyhTestMapper.insert(dyhTest);
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }


    public int[] arrayRankTransform(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = clone.length, idx = 0;
        for (int i : clone) {
            if (!map.containsKey(i)) {
                map.put(i, idx++);
            }
        }
        int[] ints = new int[n];
        for (int i = 0; i < arr.length; i++) {
            ints[i] = map.get(arr[i]);
        }
        return ints;
    }


    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 1;
        int indx = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[indx - 1]) {
                continue;
            }
            nums[indx] = nums[i];
            count++;
            indx++;
        }
        return count;

    }

    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 0 || nums.length == 1) {
            return null;
        }
        int[] result = new int[]{};
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = 0; i1 < nums.length; i1++) {
                if (target == nums[i] + nums[i1]) {
                    if (i1 == i) {
                        continue;
                    } else if (i > i1) {
                        return new int[]{i1, i};
                    } else {
                        return new int[]{i, i1};
                    }

                }
            }
        }
        return result;
    }

    public String largestOddNumber(String num) {
        if (num.isEmpty()) {
            return "";
        }
        char[] chars = num.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            char element = chars[i];
            int integer = (int) element;
            if (integer % 2 == 0) {
                continue;
            } else {
                return num.substring(0, i);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String s = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        System.out.println(s);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        if (strs.length == 1){
            return strs[0];
        }
        String result = "";
        for (int i = 0; i < strs.length; i++) {
            result = result(result.toCharArray(),strs[i].toCharArray());
            if (result.isEmpty()){
                break;
            }
        }
        return result;
    }

    public static String result(char[] strs1, char[] strs2) {
        String value = String.valueOf(strs1);
        int length = Math.min(strs1.length, strs2.length);
        if (strs1.length == 0){
            value += String.valueOf(strs2);
        }else{
            for (int i = 0; i < length; i++) {
                 if (strs1[i] != strs2[i]){
                   return value.substring(0,i);
                }
            }
            if (strs2.length <= strs1.length){
                return String.valueOf(strs2);
            }
        }

        return value;
    }


}
