import java.util.Scanner;

public class Calculator extends CalcWork{
    public static void main(String[] args) {
        Calculate calc = new CalcWork();
        Scanner scanner = new Scanner(System.in);
        try {
        String write = scanner.nextLine();
        if (calc.checkArabOrRoman(write)) {
            String insSign = calc.sign(write);
            if (calc.chekCont(calc.getSize(write), write)) {
                int a = calc.parser1(write);
                int b = calc.parser2(write);
                if ((a >= 1 && a <= 10) && (b >= 1 && b <= 10)) {
                    calc.mathOperate(insSign, a, b);

                } else {
                    System.out.println("Используйте только целые числа от 1 до 10.");
                }
            } else {
                System.out.println("Некорректный ввод.Введите только целые арабские или только римские цифры от 1 до 10.");
            }
        } else {
            int length = write.length();
            char[] roman = write.toCharArray();
            if (calc.chekContRom(length,write) && Calculate.checkLengthNumRom(roman,length) && calc.checkCorrectRomInput(roman,length,write)) {
                System.out.println(calc.arabToRoman(calc.mathRoman(roman, length)));
            } else {
                System.out.println("Некорректный ввод.Введите только римские или только целые арабске цифры от 1 до 10.");
            }

        }
        } catch (Exception e) {
            System.out.println("Некорректный ввод.Программа завершена.");
        }

        scanner.close();
    }
}
