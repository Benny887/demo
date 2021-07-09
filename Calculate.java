public interface Calculate {
    //арабский калькуолятор
    void mathOperate(String write,int num1, int num2);
    int minus(int num1, int num2);
    int plus(int num1, int num2);
    int divide(int num1, int num2);
    int multiple(int num1, int num2);
    int parser1(String scan);
    int parser2(String scan);
    String sign(String scan);
    int getSize(String write);
    boolean chekTen(String write);
    boolean chekCont(int length, String write);

    public boolean checkArabOrRoman (String write) throws Exception; //проверка рим/араб

    //Римский калькулятор
    public String sigR (char a);
    public int mathRoman (char[] f, int length);
    public boolean chekContRom(int length, String write);
    public int math(String sign,int num1, int num2);
    public int romeToArab(char l);
    public String arabToRoman(int arab);
    public boolean checkCorrectRomInput(char[] f, int length, String j);
    public static boolean checkLengthNumRom(char[] f, int length){
        int c = 0;
        for (int i = 0; i < f.length; i++) {
            if ((f[i] == '+') || (f[i] == '-') || (f[i] == '*') || (f[i] == '/')) {
                c += i;
                break;
            }
        }
        if (c >= 5) {
            System.out.println("Недопустимое число");
            return false;
        } else {
            return true;
        }
    };
}
