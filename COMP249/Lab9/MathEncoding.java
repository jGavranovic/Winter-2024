public class MathEncoding {
    public static double computePow(int num1, int num2){
        if (num2 == 1) return num1;

        else {
            //System.out.println("Result: "+ (num1 * computePow(num1, num2-1)));
            double ans = num1 * computePow(num1, num2-1);
            //System.out.println(ans);
            return ans;
        }
    }
    public static char[] convertToArray(int n){
        //NOT RECURSIVE :(
        String num = n+"";
        char[] chars = new char[num.length()];

        for (int i=0; i<chars.length;i++){
            chars[i]=num.charAt(i);
        }
        return chars;
        
    }

    public static String encodeValue(int n){
        if (n<10){
            if (n<7) return "%";
            else return "#";
        }
        else {
            return encodeValue(n/10)+ ((n%10 < 7)?"%":("#")) ;
        }
    }

    public static boolean compareResults(String n1, String n2){
        if (n1.equals(n2)){
            System.out.println("both are the same");
            return true;
        } else {
            System.out.println("both are different");
            return false;
        }
    }
}
