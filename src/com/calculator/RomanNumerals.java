package com.calculator;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public enum RomanNumerals {
	M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40), X(10), IX(9), V(5), IV(4), I(1);

	private int value;
	private static final Set<RomanNumerals> SET = Collections.unmodifiableSet(EnumSet.allOf(RomanNumerals.class));

	RomanNumerals(int value) {
		this.value = value;
	}

	public static int fromRoman(String content) {
		String values[] = content.split("");
		int prev = RomanNumerals.valueOf(values[0]).value;

		if (values.length == 1) {
			return prev;
		}
		int sum = 0;
		for (int i = 1; i < values.length; i++) {
			int next = RomanNumerals.valueOf(values[i]).value;
			if (next > prev) {
				sum -= prev;
			} else {
				sum += prev;
			}
			prev = next;
		}
		sum += RomanNumerals.valueOf(values[values.length - 1]).value;
		return sum;
	}

	public static RomanNumerals getLargest(long value) {
		return SET.stream().filter(numeral -> value >= numeral.value).findFirst().orElse(I);
	}
	public static String toRoman(long n) {
	    return LongStream.iterate(n, l -> l - RomanNumerals.getLargest(l).value)
	      .limit(RomanNumerals.values().length)
	      .filter(l -> l > 0)
	      .mapToObj(RomanNumerals::getLargest)
	      .map(String::valueOf)
	      .collect(Collectors.joining())
	    ;
	  }

}
