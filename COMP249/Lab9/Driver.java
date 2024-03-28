public class Driver {
    public static void main(String[] args) {
        MathEncoding math = new MathEncoding();

        MathEncoding.compareResults(MathEncoding.encodeValue(10), MathEncoding.encodeValue(12));

        System.out.println(MathEncoding.computePow(8,7));
        System.out.println(MathEncoding.computePow(8, 9));

        String encode1 = MathEncoding.encodeValue((int)MathEncoding.computePow(8,7));
        String encode2 = MathEncoding.encodeValue((int)MathEncoding.computePow(8, 9));

        System.out.println("Encode 1:"+encode1);
        System.out.println("Encode 2: "+encode2);


        System.out.println(MathEncoding.encodeValue(192837789));
        MathEncoding.compareResults(encode1,encode2);

    }
}