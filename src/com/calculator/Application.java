package com.calculator;

public class Application {
	private static boolean isRome;
	private static boolean isArabic;
	public static void main(String[] args) {
		baseTest();
		systemCheckTest();
		romeNegativeTest();
		wrongExpressionNegativeTest();
	}
    public static void baseTest() {
    	expression("1 + 2");
		expression("2 - 3");
		expression("2 * 3");
		expression("6 / 2");
		expression("I + I");
		expression("IV - II");
		expression("II *  III");
		expression("VI *  VIII");
		expression("VI / III");

    }
    public static void systemCheckTest() {
    	expression("1 + II");
		expression("II - 3");
    }
    public static void romeNegativeTest() {
    	expression("I - II");
    }
    public static void wrongExpressionNegativeTest() {
    	expression("I");
    	expression("I g I");
    	expression("");
    	expression("1");
    	expression("1 + 2 + 3");

    }
	public static void expression(String expression) {
		Tokenizer.setExpression(expression);
		Tokenizer.setIndex(0);
		isRome = false;
		isArabic = false;
		int value = -1;
		try {
			int op1 = var(Tokenizer.nextToken());
			String op  = operation(Tokenizer.nextToken());
			int op2 = var(Tokenizer.nextToken());
			if(!Tokenizer.isEmpty()) {
				throw new IllegalArgumentException("wrong expression");
			}
			switch (op) {
			case "+":
				value = op1+op2;
				break;
			case "-":
				value = op1-op2;
				break;
			case "*":
				value = op1*op2;
				break;
			case "/":
				value = op1/op2;
				break;
			}
			if(isRome && value<0) {
				throw new IllegalArgumentException("Rome numeral could not be negative number");
			}
			if(isRome) {
				System.out.println("result : "+RomanNumerals.toRoman(value));

			}else {
				System.out.println("result : "+value);
			}

		} catch (Throwable e) {
			System.out.println(e.getMessage() + " : "+expression);

		}

	}

	public static int var(Token token) {
		if(digit(token)) {
			if(isRome) {
				throw new IllegalArgumentException("both arguments should be in Roman");
			}
			isArabic = true;
			return Integer.valueOf(token.getValue()).intValue();
		}else if(rome(token)){
			if(isArabic) {
				throw new IllegalArgumentException("both arguments should be in Arabic");
			}
			isRome = true;
			return RomanNumerals.fromRoman(token.getValue());
		}else {
			throw new IllegalArgumentException("Illegal argument: number is expected");
		}
	}

    public static String operation(Token token) {
    	if(token.getType().equals(TokenTypes.OPERATION)) {
    		return token.getValue();
    	}else {
    		throw new IllegalArgumentException("Illegal argument: arithmethic operation +,-,*,/ is expected");
    	}
    }
	public static  boolean digit(Token token) {
		return token.getType().equals(TokenTypes.DIGIT);
	}

	public static boolean rome(Token token) {

		return token.getType().equals(TokenTypes.ROME);
	}
}

