package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.print("Выражение: ");
        ArrayList<MathObject> mathExpression = new ArrayList();
        try {
            int code = 0;
            int index = 0;
            while ((code = System.in.read()) != 10) {
                if (code >= '0' && code <= '9') {
                    int number = Integer.valueOf(String.valueOf((char) code));
                    mathExpression.add(new MathObject());
                    mathExpression.get(index).set(number);
                    index++;
                }
                else if (MathObject.AVAILABLE_OPERATORS.contains((char) code)) {
                    mathExpression.add(new MathObject());
                    mathExpression.get(index).set((char) code);
                    index++;
                }
                else {
                    System.out.println("В выражении встречен не известный символ: \'" + (char) code + "\'!");
                    return;
                }
            }
        }
        catch (Exception exception) {
            System.out.println("Не удалось произвести ввод! Попробуйте еще раз!\nОшибка: " + exception.getMessage());
        }
        System.out.println("Результат: " + Calculator.calculate(mathExpression));
    }
}
