package com.ahmadroni.javalogic.service;

import com.ahmadroni.javalogic.model.PalindromeRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class Logic01Service {
    public Map<Character, Integer> soal01(String param){
        param = param.replace(" ","");
        char[] params = param.toUpperCase().toCharArray();
        Arrays.sort(params);

        Map<Character, Integer> result = new HashMap<>();
        for(char item: params){
            Integer value = result.containsKey(item) ?  result.get(item) + 1 : 1;
            result.put(item, value);
        }
        return result;
    }


    public PalindromeRequest soal02(PalindromeRequest param){
        // word01 => Map
        // word02 => Map
        // if jumlah nya sama => PALINDROME
        return null;
    }

    public Map<Integer, String> soal03(int n){
        Map<Integer, String> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if(i % 2 == 0)
                result.put(i,"GENAP");
            else
                result.put(i,"GANJIL");
        }
        return result;
    }
}
