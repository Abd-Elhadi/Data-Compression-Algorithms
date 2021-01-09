package LZ77Algorithm;

import java.util.ArrayList;
import java.util.Scanner;

class TagLZ77{
    int pos,len;
    char nxt;
    public TagLZ77(int _pos,int _len,char _nxt){
        pos=_pos;
        len=_len;
        nxt=_nxt;
    }
}
public class LZ77 {
    ArrayList<TagLZ77>tags=new ArrayList<TagLZ77>();
    public void Start(String txt){
        tags.clear();
        Compress(txt);
        PrintTags();
        Decompression();
    }

    public void Compress(String original){
        String finish="",cur="";
        for (int i=0;i<original.length();++i){
            cur+=original.charAt(i);
            int idx=finish.lastIndexOf(cur);
            if(idx==-1||i==original.length()-1){
                if(cur.length()==1)
                    tags.add(new TagLZ77(0,0,cur.charAt(0)));
                else{
                    char back=cur.charAt(cur.length()-1);
                    tags.add(new TagLZ77(finish.length()-finish.lastIndexOf(cur.substring(0,cur.length()-1)),cur.length()-1,back));
                }
                finish+=cur;
                cur="";
            }
        }
    }
    public void PrintTags(){
        for (int i=0;i<tags.size();i++){
            System.out.println("<"+tags.get(i).pos+","+tags.get(i).len+","+"\""+tags.get(i).nxt+"\">");
        }
    }
    public void Decompression(){
        String original="";
        for (int i=0;i<tags.size();i++){
            original+=original.substring(original.length()-tags.get(i).pos,original.length()-tags.get(i).pos+tags.get(i).len)+tags.get(i).nxt;
        }
        System.out.println(original);
    }
    
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        LZ77 lz77 = new LZ77();
        while(true){
            System.out.print("Enter string: ");
            String str = scan.next();
            
            
            lz77.Start(str);
            System.out.println("1-To Continue\n2-Exit");
            int choice = scan.nextInt();
            if (choice == 1) continue;
            else if (choice == 2) break;
            else {
            	System.out.println("Invalid input input");
            	break;
            }
        }
    }
}
