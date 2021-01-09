package ArithmeticCodingFloat;

import java.util.HashMap;

public class ArithmeticCoding {
    public static HashMap<Character, Double> rangeLow  = new HashMap<Character, Double>();
    public static HashMap<Character, Double> rangeHigh = new HashMap<Character, Double>();
    public static char lastChar = ' ';
    
    public static Double compress(String s){
        Double Lower = 0.0 , Upper = 1.0 , originLower;
        for(int i=0 ; i<s.length() ; ++i){
            originLower = Lower;
            Lower = originLower + (Upper-originLower) * rangeLow.get(s.charAt(i));
            Upper = originLower + (Upper-originLower) * rangeHigh.get(s.charAt(i));
        }
        return nearest(Lower, Upper);
    }
    
    public static String decompress(Double code, int numSym){
        double Lower = 0.0 , Upper = 1.0 , newCode = code ,originLower;
        String s="";
        for(int i=0 ; i<numSym ; ++i){
            for (Character c : rangeLow.keySet()) {
                if(newCode>rangeLow.get(c) && newCode<rangeHigh.get(c)){
                    s+=c;
                    originLower = Lower;
                    Lower = originLower + (Upper-originLower) * rangeLow.get(c);
                    Upper = originLower + (Upper-originLower) * rangeHigh.get(c);
                    newCode = (code - Lower)/(Upper - Lower);
                    break;
                }
            }
        }
        return s;
    }
    
    public static Double nearest(Double low , Double high){
        Double num = 0.0;
        Double i = -1.0;
        while(!(num >low && num < high)){
            num+=Math.pow(2, i);
            if(num > high){
                num-=Math.pow(2, i);
            }
            --i;
        }
        return num;
    }
    
    public static void addRange(Character c,Double range){
        if(rangeLow.size() == 0){
            rangeLow.put(c,0.0);
            rangeHigh.put(c,range);
        }
        else{
            rangeLow.put(c, rangeHigh.get(lastChar));
            rangeHigh.put(c, rangeHigh.get(lastChar) + range);
        }
        lastChar = c;
    }
    
    public static void main(String[] args) {
        
        rangeLow.put('a',0.0);
        rangeHigh.put('a',0.8);
        rangeLow.put('b',0.8);
        rangeHigh.put('b',0.82 );
        rangeLow.put('c',0.82);
        rangeHigh.put('c',1.0);
        System.out.println(compress("acba"));
        System.out.println(decompress(compress("acba") , 4));
        
    }
}