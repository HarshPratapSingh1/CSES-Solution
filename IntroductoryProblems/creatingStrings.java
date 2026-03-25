package cses.IntroductoryProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class creatingStrings {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder s = new StringBuilder(scan.next());
        List<String> ans = new ArrayList<>();
        solve(s, 0,ans);

        TreeSet<String> set = new TreeSet<>();

        for(String st : ans) set.add(st);
        System.out.println(set.size());
        for(String st : set) System.out.println(st);
    }
    public static void solve(StringBuilder s , int idx ,List<String> ans){
        if(idx == s.length()) {
            ans.add(s.toString());
            return;
        }

        for(int i = idx ; i < s.length() ; i++){
            swap(s,i,idx);
            solve(s,idx+1,ans);
            swap(s,idx,i);
        }
    }
    public static void swap(StringBuilder s, int i, int j){
        char c = s.charAt(i);
        s.setCharAt(i, s.charAt(j));
        s.setCharAt(j, c);
    }
}