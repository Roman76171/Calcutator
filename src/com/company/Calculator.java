package com.company;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

    private static final Map<Character, Integer> MATH_OPERATOR_PRIORITY;

    static {
        Map<Character, Integer> mathOperatorPriority = new HashMap<>();
        mathOperatorPriority.put('+', 0);
        mathOperatorPriority.put('-', 0);
        mathOperatorPriority.put('*', 1);
        mathOperatorPriority.put('/', 1);
        MATH_OPERATOR_PRIORITY = mathOperatorPriority;
    }

    //Вычисляет переданное выражение
    public static int calculate(ArrayList<MathObject> mathExpression) {
        Stack<MathObject> mathStack = new Stack<>();
        for (MathObject mathObject : mathExpression) {
            if (isCalculate(mathStack, mathObject)) {
                calculateStack(mathStack);
            }
            mathStack.push(mathObject);
        }
        calculateStack(mathStack);
        return Integer.valueOf(mathStack.pop().getMathObject());
    }

    //Проверка можно ли вычислить значение из того что находиться в стеке
    private static boolean isCalculate(final Stack<MathObject> mathStack, final MathObject nextMathObject) {
        if (mathStack.size() <= 3) {
            return false;
        }
        //Номер 1-го с конца элемента
        int lastElement = mathStack.size() - 1;
        //Номер 2-го с конца элемента
        int element2rdFromEnd = lastElement - 1;
        //Номер 1-го с конца элемента
        int element3rdFromEnd = lastElement - 2;
        var iter = mathStack.listIterator(mathStack.size());
        for (int index = lastElement; index >= element3rdFromEnd; index--) {
            var value = iter.previous();
            if ((index == lastElement || index == element3rdFromEnd) && (value.getMathObjectType() != MathObjectType.NUMBER)) {
                return false;
            }
            else if (index == element2rdFromEnd) {
                if (value.getMathObjectType() != MathObjectType.MATH_OPERATOR) {
                    return false;
                }
                if (MATH_OPERATOR_PRIORITY.get(value.getMathObject().charAt(0)) <= MATH_OPERATOR_PRIORITY.get(nextMathObject.getMathObject().charAt(0))) {
                    return false;
                }
            }
        }
        return true;
    }

    //Вычисляет значение из того, что находится в стеке
    private static void  calculateStack(Stack<MathObject> mathStack) {
        do {
            int number2 = Integer.valueOf(mathStack.pop().getMathObject());
            char operator = mathStack.pop().getMathObject().charAt(0);
            int number1 = Integer.valueOf(mathStack.pop().getMathObject());
            int result = 0;
            switch (operator) {
                case '+':
                    result = number1 + number2;
                    break;
                case '-':
                    result = number1 - number2;
                    break;
                case '*':
                    result = number1 * number2;
                    break;
                case '/':
                    result = number1 / number2;
            }
            MathObject mathObject = new MathObject();
            mathObject.set(result);
            mathStack.push(mathObject);
        } while (mathStack.size() > 1);
    }
}