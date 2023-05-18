import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        String[] allowRimNumber = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};  //  Словапь допускаемых символов

        boolean typeFloat = true, rimVal = false; // Булевые флаги для проверок

        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение (Например '1 + 2'):\n"); String str = in.nextLine(); // Блок для парсинга значений
        str = str.replace(",", ".");

        String[] result = str.split(" ");

        for(String s : allowRimNumber){
            int indexRim1 = result[0].indexOf(s);
            if (indexRim1 != -1) {
                rimVal = true;
                break;
            }
        }

        int indexDot = str.indexOf(".");

        if(indexDot == -1) // Проверка на дробное значение
            typeFloat = false;

        if(result.length > 3)
            throw new Exception("Так как формат метематисеской операции не удовлетворяет заданию - два операнда и один оператор"); // Проверка ввода






        for (String s : result) {

            if (s == null) {
                throw new Exception("Строка не является математической операцией 2\n");
            }

            System.out.print(s + " ");

        }

        if(rimVal){
            if(!rimExpression(result[0], result[1], result[2]))
                throw new Exception("Проверьте, верность введеных данных\n");
        }
        else
            defaultExpression(result[0], result[1], result[2], typeFloat);
    }


    // Обработка арабской СИ
    public static void defaultExpression(String variable1, String operator, String variable2, boolean typeFloat) throws Exception{
        if(typeFloat){
            double varFloat1 = Double.parseDouble(variable1);
            double varFloat2 = Double.parseDouble(variable2);

            if(varFloat1 > 10 || varFloat2 < 1)
                throw new Exception("Используйте числа от 1 до 10\n");

            switch (operator){
                case ("+") -> System.out.print("= " + (varFloat1 + varFloat2) + "\n");
                case ("-") -> System.out.print("= " + (varFloat1 - varFloat2) + "\n");
                case ("*") -> System.out.print("= " + (varFloat1 * varFloat2) + "\n");
                case ("/") -> System.out.print("= " + (varFloat1 / varFloat2) + "\n");
            }
        }
        else {
            int varInt1 = Integer.parseInt(variable1);
            int varInt2 = Integer.parseInt(variable2);

            if(varInt1 > 10 || varInt2 < 1)
                throw new Exception("Используйте числа от 1 до 10\n");

            switch (operator) {
                case ("+") -> System.out.print("= " + (varInt1 + varInt2) + "\n");
                case ("-") -> System.out.print("= " + (varInt1 - varInt2) + "\n");
                case ("*") -> System.out.print("= " + (varInt1 * varInt2) + "\n");
                case ("/") -> System.out.print("= " + (varInt1 / varInt2) + "\n");
            }
        }
    }
    // Обработка римской СИ
    public static boolean rimExpression(String variable1, String operator, String variable2) throws Exception{

        String[] rims = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "L", "C"};
        boolean flag1 = false, flag2 = false;

        for (int i = 0; i < 10; i++){
            if(!variable1.equals(rims[i]))
                flag1 = true;

            if(!variable2.equals(rims[i]))
                flag2 = true;
        }

        if(flag1 || flag2)
            throw new Exception("Используйте числа от 1 до 10");


        int varInt1 = -100;
        int varInt2 = -100;

        for(int i = 0; i < rims.length; i++){

            if(variable1.equals(rims[i])){
                varInt1 = i + 1;
            }

            if(variable2.equals(rims[i])){
                varInt2 = i + 1;
            }
        }

        if(varInt1 == -100 || varInt2 == -100){
            throw new Exception("Используйте числа от 1 до 10\n");
        }

        int response = 0;
        String rimResponse = "";
        int rimDozen;

        if(varInt1 - varInt2 < 0 && operator.equals("-")){
            System.out.print("Вывод ошибки, так как в римской системе нет отрицательных чисел.\n");
            return false;
        }

        switch (operator) {
            case ("+") -> response = varInt1 + varInt2;
            case ("-") -> response = varInt1 - varInt2;
            case ("*") -> response = varInt1 * varInt2;
            case ("/") -> response = varInt1 / varInt2;
        }

        if(response == 100){
            response -= 100;
            rimResponse = rims[11];
        }

        if(100 > response && response >= 90){
            response -= 90;
            rimResponse = rims[9] + rims[11];
        }

        if(90 > response && response >= 50){
            response -= 50;
            rimResponse += rims[10];
        }

        if(50 > response && response >= 40){
            response -= 40;
            rimResponse = rims[9] + rims[10];
        }

        if(40 > response && response >= 11){
            rimDozen = response / 10;
            response -= rimDozen * 10;
            for(int i = 0; i < rimDozen; i++){
                rimResponse = rimResponse + rims[9];
            }
        }

        if(10 >= response && response >= 1){
            rimResponse += rims[response - 1];
        }

        System.out.print("= " + rimResponse + "\n");
        return true;
    }
}