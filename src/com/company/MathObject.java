package com.company;

import java.util.ArrayList;

enum MathObjectType {
    NUMBER,
    MATH_OPERATOR,
    NONE
}

//Определяет число и арифметический знак входящий в мат. выражение
public class MathObject {

    public static final ArrayList<Character> AVAILABLE_OPERATORS;

    static {
        ArrayList<Character> availableOperators = new ArrayList();
        availableOperators.add('+');
        availableOperators.add('-');
        availableOperators.add('*');
        availableOperators.add('/');
        //availableOperators.add('^');
        AVAILABLE_OPERATORS = availableOperators;
    }

    private char operator;
    private int number;
    private MathObjectType type;

    MathObject() {
        operator = '=';
        number = 0;
        type = MathObjectType.NONE;
    }

    MathObject(final MathObject mathObject) {
        this.operator = mathObject.operator;
        this.number = mathObject.number;
        this.type = mathObject.type;
    }

    public void set(final int number) {
        this.number = number;
        type = MathObjectType.NUMBER;
    }

    public void set(final char mathOperator) throws Exception{
        if (AVAILABLE_OPERATORS.contains(mathOperator)) {
            operator = mathOperator;
            type = MathObjectType.MATH_OPERATOR;
        }
        else {
            throw new Exception("Не допустимый математический оператор!");
        }
    }

    public String getMathObject() {
        switch (type) {
            case NUMBER: return String.valueOf(number);
            case MATH_OPERATOR: return  String.valueOf(operator);
            default: return "";
        }
    }

    public MathObjectType getMathObjectType() {
        return type;
    }
}
