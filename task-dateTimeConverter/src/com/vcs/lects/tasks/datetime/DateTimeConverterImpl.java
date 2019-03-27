package com.vcs.lects.tasks.datetime;

public class DateTimeConverterImpl {

	private static final String[] MEN = { "Sausio", "Vasario", "Kovo", "Balnadžio", "Gegužės", "Birželio", "Liepos",
			"Rugpjūčio", "Rugsėjo", "Spalio", "Lapkričio", "Gruodžio" };

	public String convert(int metai, int menuo, int diena, int hh, int mm, int ss) {

		NumToString numToStr = new NumToString();

		StringBuilder str = new StringBuilder();
		str.append("Du tūkstančiai ");
		str.append(numToStr.getAsString(metai - 2000, false));
		str.append(" metų, ");
		str.append(MEN[menuo - 1]);
		str.append(" mėnesio, ");
		str.append(numToStr.getAsString(diena, true));
		str.append(" diena, ");
		str.append(numToStr.getAsString(hh, true));
		str.append(" ");
		str.append(convertTimeInWord(hh, "valanda", "valandų", "valandos"));
		str.append(", ");
		str.append(numToStr.getAsString(mm, true));
		str.append(" ");
		str.append(convertTimeInWord(mm, "minutė", "minučių", "minutės"));
		str.append(", ");
		str.append(numToStr.getAsString(ss, true));
		str.append(" ");
		str.append(convertTimeInWord(mm, "sekundė", "sekundžių", "sekundės"));
		str.append(" ");

		return str.toString();
	}

	private String convertTimeInWord(int tm, String singular, String pluralX0, String plural1X) {

		// 0, 10..20, 30, 40, 50
		if (tm == 0 || tm >= 10 && tm <= 20 || tm == 30 || tm == 40 || tm == 50) {
			return pluralX0;
		}

		// 1, 21, 31, 41, 51
		if (tm % 10 > 0) {
			return singular;
		}

		// 2..9; 22..29, 32..39, 42..49, 52..59
		return plural1X;
	}
}
