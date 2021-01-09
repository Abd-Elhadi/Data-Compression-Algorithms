package LZ78Algorithm;


import java.util.ArrayList;
import java.util.Scanner;

import LZ77Algorithm.LZ77;

class TagLZ78{
    int idxInDec;
    char nxt;
    public TagLZ78(int _idxInDec,char _nxt){
        idxInDec=_idxInDec;
        nxt=_nxt;
    }
}
public class LZ78 {
    ArrayList<TagLZ78> tags=new ArrayList<TagLZ78>();
    ArrayList<String> Dictionary=new ArrayList<String>();
    public void Start(String txt){
        tags.clear();
        Dictionary.clear();
        Dictionary.add("");
        Compress(txt);
        PrintDictionary();
        PrintTags();
        Decompression();
    }
    public void Compress(String original){
        String cur="";
        for (int i=0;i<original.length();i++){
            cur+=original.charAt(i);
            int idx=Dictionary.indexOf(cur);
            if(idx==-1||i==original.length()-1){
                tags.add(new TagLZ78(Dictionary.indexOf(cur.substring(0,cur.length()-1)),cur.charAt(cur.length()-1)));
                if(idx==-1)
                    Dictionary.add(cur);
                cur="";
            }
        }
    }
    public void PrintDictionary(){
        for (int i=0;i<Dictionary.size();i++){
            System.out.println(i+" | \""+Dictionary.get(i)+"\"");
        }
    }
    public void PrintTags(){
        for (int i=0;i<tags.size();i++){
            System.out.println("<"+tags.get(i).idxInDec+","+"\""+tags.get(i).nxt+"\""+">");
        }
    }
    public void Decompression(){
        Dictionary.clear();
        Dictionary.add("");
        String original="";
        for(int i=0;i<tags.size();i++){
            String tmp=Dictionary.get(tags.get(i).idxInDec)+tags.get(i).nxt;
            original+=tmp;
            Dictionary.add(tmp);
        }
        System.out.println(original);
    }
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        LZ78 lz78 = new LZ78();
        while(true){
            System.out.print("Enter string: ");
            String str = scan.next();
            
            
            lz78.Start(str);
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

