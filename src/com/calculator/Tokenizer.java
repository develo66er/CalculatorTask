package com.calculator;

public class Tokenizer {
	private static String digit = "0123456789";
	private static String rome = "IVX";
	private static String operation = "+-*/";
	private static String expression = "";
	private static int index;

	public static String getExpression() {
		return expression;
	}

	public static void setExpression(String expression) {
		Tokenizer.expression = expression;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		Tokenizer.index = index;
	}

	public static Token nextToken() throws Throwable {
		if (isEmpty()) {
			throw new IllegalArgumentException("wrong expression");
		}
		Token token = new Token();
		char firstSymbol = expression.charAt(index);
		if (isDigit(firstSymbol) && firstSymbol != '0') {
			int start = index;
			skipDigits();
			if (isEmpty() || isSpace(expression.charAt(index))) {

				String beforeSpace = expression.substring(start, index);

				if ((index - start) > 2 || (index - start) == 2 && !beforeSpace.equals("10"))
					throw new Throwable("wrong expression");

				skipSpaces();

				token.setType(TokenTypes.DIGIT);
				token.setValue(beforeSpace);
				return token;
			} else {
				skipSpaces();
				throw new Throwable("wrong expression");

			}

		} else if (rome.indexOf(expression.charAt(index)) != -1) {
			int start = index;
			skipRome();
			if (isEmpty() || isSpace(expression.charAt(index))) {
				String beforeSpace = expression.substring(start, index);
				if (!beforeSpace.matches("(I{1,3})|(I{0,1}V)|(VI{1,3})|(I{0,1}X)")) {
					index++;
					skipSpaces();
					throw new Throwable("wrong expression");
				}
				skipSpaces();
				token.setType(TokenTypes.ROME);
				token.setValue(beforeSpace);
				return token;
			} else {
				index++;
				skipSpaces();
				throw new Throwable("wrong expression");

			}

		} else if (operation.indexOf(expression.charAt(index)) != -1) {

			String operation = String.valueOf(expression.charAt(index));
			index++;
			skipSpaces();
			token.setType(TokenTypes.OPERATION);
			token.setValue(operation);
			return token;

		} else
			index++;
		skipSpaces();
		throw new Throwable("wrong expression");

	}

	private static void skipDigits() {
		while (!isEmpty() && isDigit(expression.charAt(index))) {
			index++;
		}
	}

	private static void skipRome() {
		while (!isEmpty() && isRome(expression.charAt(index))) {
			index++;
		}
	}

	private static void skipSpaces() {
		while (!isEmpty() && isSpace(expression.charAt(index))) {
			index++;
		}
	}

	private static boolean isSpace(char s) {
		return s == ' ';
	}

	private static boolean isDigit(char s) {
		return digit.indexOf(s) != -1;
	}

	private static boolean isRome(char s) {
		return rome.indexOf(s) != -1;
	}

	public static boolean isEmpty() {
		return index > expression.length() - 1;
	}
}
