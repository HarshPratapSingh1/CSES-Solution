package cses;
import java.io.*;
import java.util.*;

class DLLNode {
 int data;
 DLLNode next;
 DLLNode prev;

 DLLNode(int val) {
     data = val;
     next = null;
     prev = null;
 }
}
public class Josephus_Queries {
	


//***********************


//{ Driver Code Starts
//Initial Template for Java




 public static void push(DLLNode tail, int new_data) {
     DLLNode newNode = new DLLNode(new_data);
     newNode.next = null;
     newNode.prev = tail;

     if (tail != null) {
         tail.next = newNode;
     }
 }

 public static void printList(DLLNode head) {
     if (head == null) {
         return;
     }

     while (head != null) {
         System.out.print(head.data + " ");
         head = head.next;
     }
     System.out.println();
 }

 public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     int t = Integer.parseInt(br.readLine().trim());

     while (t-- > 0) {
         String[] arr = br.readLine().trim().split(" ");
         int k = Integer.parseInt(br.readLine().trim());

         DLLNode head = new DLLNode(Integer.parseInt(arr[0]));
         DLLNode tail = head;

         for (int i = 1; i < arr.length; i++) {
             push(tail, Integer.parseInt(arr[i]));
             tail = tail.next;
         }

         Solution obj = new Solution();
         DLLNode sorted_head = obj.sortAKSortedDLL(head, k);
         printList(sorted_head);
     }
 }
}

//} Driver Code Ends


//User function Template for Java
class Solution {
 public DLLNode sortAKSortedDLL(DLLNode head, int k) {
     DLLNode temp=head;
     int cnt=0;
     while(temp!=null){
         cnt++;
         temp=temp.next;
     }
     int i=0;
     int arr[]=new int[cnt];
     temp=head;
      while(temp!=null){
        arr[i++]=temp.data;
         temp=temp.next;
     }
     
     Arrays.sort(arr);
     // System.out.println(Arrays.toString(arr));
     
     temp=head;
     i=0;
     while(temp!=null){
         temp.data=arr[i++];
         temp=temp.next;
     }
     return head;
 }
}