import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

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

        if(!result[1].equals("+") && !result[1].equals("-")  &&  !result[1].equals("*")  &&  !result[1].equals("/")){
            System.out.print("Строка не является математической операцией. Используйте: '+', '-', '*', '/' операторы. \n");
            return;
        } // Проверка ввода

        for (String s : result) {

            if (s == null) {
                System.out.print("Строка не является математической операцией 2\n");
                return;
            }

            System.out.print(s + " ");

        }

        if(rimVal){
            if(!rimExpression(result[0], result[1], result[2]))
                System.out.print("Проверьте, верность введеных данных\n");
        }
        else
            defaultExpression(result[0], result[1], result[2], typeFloat);
    }


    // Обработка арабской СИ
    public static void defaultExpression(String variable1, String operator, String variable2, boolean typeFloat){
        if(typeFloat){
            double varFloat1 = Double.parseDouble(variable1);
            double varFloat2 = Double.parseDouble(variable2);

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

            switch (operator) {
                case ("+") -> System.out.print("= " + (varInt1 + varInt2) + "\n");
                case ("-") -> System.out.print("= " + (varInt1 - varInt2) + "\n");
                case ("*") -> System.out.print("= " + (varInt1 * varInt2) + "\n");
                case ("/") -> System.out.print("= " + (varInt1 / varInt2) + "\n");
            }
        }
    }
    // Обработка римской СИ
    public static boolean rimExpression(String variable1, String operator, String variable2){
        String[] rims = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "L", "C"};

        int varInt1 = 1;
        int varInt2 = 1;

        for(int i = 0; i < rims.length; i++){

            if(variable1.equals(rims[i])){
                varInt1 += i;
            }

            if(variable2.equals(rims[i])){
                varInt2 += i;
            }
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