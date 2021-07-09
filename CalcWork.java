import java.sql.Struct;

public class CalcWork implements Calculate{

//Арабский калькулятор
    @Override
    public int minus(int num1, int num2) {
        return num1 - num2;
    }

    @Override
    public int plus(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public int divide(int num1, int num2) {
        return num1 / num2;
    }

    @Override
    public int multiple(int num1, int num2) {
        return num1 * num2;
    }

    @Override
    public int parser1(String write) {
        try {
            String cutNum1 = write.substring(0, 2);
            return Integer.parseInt(cutNum1);
        } catch (NumberFormatException e) {
            String cutNum1 = write.substring(0, 1);
            return Integer.parseInt(cutNum1);
        }
    }


    @Override
    public int parser2(String write) {
        try {
            if (chekTen(write)) {
                String cutNum2 = write.substring(3, 5);
                return Math.abs(Integer.parseInt(cutNum2));
            } else {
                String cutNum2 = write.substring(2, 4);
                return Math.abs(Integer.parseInt(cutNum2));
            }
        } catch (Exception e){
            if (chekTen(write)) {
                String cutNum2 = write.substring(3, 4);
                return Math.abs(Integer.parseInt(cutNum2));
            } else {
                String cutNum2 = write.substring(2, 3);
                return Math.abs(Integer.parseInt(cutNum2));
            }
            }
        }

    @Override
    public boolean chekTen(String write) {
        try{
            String cutNum1 = write.substring(0, 2);
            int num = Integer.parseInt(cutNum1);
            return num == 10;
        } catch (NumberFormatException e){
            return false;
        }
    }

    @Override
    public int getSize(String write) {
        return write.length();
    }

    @Override
    public boolean chekCont(int length, String write){
        int ind=0;
        String r = "0123456789/+-*";
        for (int i = 0; i < length; i++){
            String d = write.substring(i, i+1);
            Boolean t = r.contains(d);
            if (t){
                ind += 1;
            } else {
                return false;
            }
        }
        return ind <= 5;
    }


    @Override
    public String sign(String write) {
        String sign = write.substring(2, 3);
        if (sign.equals("+") || sign.equals("-") || sign.equals("/") ||sign.equals("*") ) {
            return sign;
        } else {
            sign = write.substring(1, 2);
            if (sign.equals("+") || sign.equals("-") || sign.equals("/") ||sign.equals("*") ) {
                return write.substring(1, 2);
            }
            return "#";
       }
    }



    @Override
    public void mathOperate(String sign,int num1, int num2) {
        switch (sign){
            case "-":
                System.out.println(minus(num1,num2));
                break;
            case "+":
                System.out.println(plus(num1,num2));
                break;
            case "*":
                System.out.println(multiple(num1,num2));
                break;
            case "/":
                System.out.println(divide(num1,num2));
                break;
            default:
                System.out.println("Некорректная математическая операция.");
                break;
        }
    }

    //проверка рим/араб
    @Override
    public boolean checkArabOrRoman(String write) throws Exception {
        int ind=0;
        String arab = "0123456789";
        String rom = "IVX";
        try {
            for (int i = 0; i < 1; i++) {
                String d = write.substring(i, i + 1);
                Boolean ar = arab.contains(d);
                Boolean rm = rom.contains(d);
                if (ar) {
                    ind = 1;
                } else {
                    if (rm) {
                        ind = 2;
                    }
                }
            }
            if (ind == 1) {
                return true;
            } else if (ind == 2) {
                return false;
            } else {
                throw new Exception("Некорректный ввод.");
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод.");
        }
        return false;
    }

    //Римский калькулятор


    @Override
    public String sigR(char a) {
        switch (a){
            case '-':
                return "-";
            case '+':
                return "+";
            case '*':
                return "*";
            case '/':
                return "/";
            default:
                System.out.println("Некорректная математическая операция.");
                return null;
        }
    }



    @Override
    public int mathRoman(char[] f, int length) {
        int c = 0;
        int first = 0;
        int second = 0;
        for (int i = 0; i < f.length; i++){
            if ((f[i] == '+') || (f[i] == '-') || (f[i] == '*') || (f[i] == '/' )){
                c += i;
                break;
            }
        }
        for (int i = 0; i < c; i++) {
            if (c == 2){
                if (romeToArab(f[i]) < romeToArab(f[i+1])){
                    first = romeToArab(f[i+1]) - romeToArab(f[i]);
                    break;
                } else {
                    first =  romeToArab(f[i]) + romeToArab(f[i+1]);
                    break;
                }
            } else {
                first += romeToArab(f[i]);
            }
        }
        if ((length - c) == 3){
            if (romeToArab(f[c + 1]) < romeToArab(f[c + 2])) {
                second = romeToArab(f[c + 2]) - romeToArab(f[c + 1]);
            } else {
                second = romeToArab(f[c + 2]) + romeToArab(f[c + 1]);
            }
        } else
            for(int i = c + 1; i < length; i++ ){
                second += romeToArab(f[i]);
            }
        String b = sigR(f[c]);
        return math(b, first, second);
    }

    @Override
    public boolean chekContRom(int length, String write) {
        int ind=0;
        String r = "IVX/+-*";
        for (int i = 0; i < length; i++){
            String d = write.substring(i, i+1);
            Boolean t = r.contains(d);
            if (t){
                ind += 1;
            } else {
                return false;
            }
        }
        return ind <= 9;
    }

    @Override
    public int math(String sign, int num1, int num2) {
        switch (sign){
            case "-":
                return minus(num1,num2);
            case "+":
                return plus(num1,num2);
            case "*":
                return multiple(num1,num2);
            case "/":
                return divide(num1,num2);
            default:
                System.out.println("Некорректная математическая операция.");
                return 0;
        }
    }

    @Override
    public int romeToArab(char l) {
        switch (l) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            default:
                return 0;
        }
    }

    @Override
    public boolean checkCorrectRomInput(char[] f, int length, String j) {
        int c = 0;
        String q = "I II III IV V VI VII VIII IX X";
        for (int i = 0; i < f.length; i++) {
            if ((f[i] == '+') || (f[i] == '-') || (f[i] == '*') || (f[i] == '/')) {
                c += i;
                break;
            }
        }
        String d = j.substring(0, c);
        Boolean t = q.contains(d);
        String b = j.substring(c+1,length);
        Boolean l = q.contains(b);
        if (t && l){
            return true;
        } else {
            System.out.println("Некорректная римская цифра.");
            return false;
        }
    }

    @Override
    public String arabToRoman(int arab) {
        switch (arab) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            case 11:
                return "XI";
            case 12:
                return "XII";
            case 13:
                return "XIII";
            case 14:
                return "XIV";
            case 15:
                return "XV";
            case 16:
                return "XVI";
            case 17:
                return "XVII";
            case 18:
                return "XVIII";
            case 19:
                return "XIX";
            case 20:
                return "XX";
            case 21:
                return "XXI";
            case 22:
                return "XXII";
            case 23:
                return "XXIII";
            case 24:
                return "XXIV";
            case 25:
                return "XXV";
            case 26:
                return "XXVI";
            case 27:
                return "XXVII";
            case 28:
                return "XXVIII";
            case 29:
                return "XXIX";
            case 30:
                return "XXX";
            case 31:
                return "XXXI";
            case 32:
                return "XXXII";
            case 33:
                return "XXXIII";
            case 34:
                return "XXXIV";
            case 35:
                return "XXXV";
            case 36:
                return "XXXVI";
            case 37:
                return "XXXVII";
            case 38:
                return "XXXVIII";
            case 39:
                return "XXXIX";
            case 40:
                return "XL";
            case 41:
                return "XLI";
            case 42:
                return "XLII";
            case 43:
                return "XLIII";
            case 44:
                return "XLIV";
            case 45:
                return "XLV";
            case 46:
                return "XLVI";
            case 47:
                return "XLVII";
            case 48:
                return "XLVIII";
            case 49:
                return "XLIX";
            case 50:
                return "L";
            case 51:
                return "LI";
            case 52:
                return "LII";
            case 53:
                return "LIII";
            case 54:
                return "LIV";
            case 55:
                return "LV";
            case 56:
                return "LVI";
            case 57:
                return "LVII";
            case 58:
                return "LVIII";
            case 59:
                return "LIX";
            case 60:
                return "LX";
            case 61:
                return "LXI";
            case 62:
                return "LXII";
            case 63:
                return "LXIII";
            case 64:
                return "LXIV";
            case 65:
                return "LXV";
            case 66:
                return "LXVI";
            case 67:
                return "LXVII";
            case 68:
                return "LXVIII";
            case 69:
                return "LXIX";
            case 70:
                return "LXX";
            case 71:
                return "LXXI";
            case 72:
                return "LXXII";
            case 73:
                return "LXXIII";
            case 74:
                return "LXXIV";
            case 75:
                return "LXXV";
            case 76:
                return "LXXVI";
            case 77:
                return "LXXVII";
            case 78:
                return "LXXVIII";
            case 79:
                return "LXXIX";
            case 80:
                return "LXXX";
            case 81:
                return "LXXXI";
            case 82:
                return "LXXXII";
            case 83:
                return "LXXXIII";
            case 84:
                return "LXXXIV";
            case 85:
                return "LXXXV";
            case 86:
                return "LXXXVI";
            case 87:
                return "LXXXVII";
            case 88:
                return "LXXXVIII";
            case 89:
                return "LXXXIX";
            case 90:
                return "XC";
            case 91:
                return "XCI";
            case 92:
                return "XCII";
            case 93:
                return "XCIII";
            case 94:
                return "XCIV";
            case 95:
                return "XCV";
            case 96:
                return "XCVI";
            case 97:
                return "XCVII";
            case 98:
                return "XCVIII";
            case 99:
                return "XCIX";
            case 100:
                return "C";
            default:
                return "Не верно";
        }
    }




}
