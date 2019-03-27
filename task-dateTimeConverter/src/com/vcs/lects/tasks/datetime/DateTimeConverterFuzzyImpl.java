package com.vcs.lects.tasks.datetime;

import java.time.LocalDate;

public class DateTimeConverterFuzzyImpl {

	private static final int ALMOST_MIDLE = 16;

	public String convert(int metai, int menuo, int diena, int hh) {

		String yStr = convertMetai(metai);

		String mStr = convertMenuoDiena(menuo, diena);

		String tStr = convertTime(hh);

		return yStr + ", " + mStr + ", " + tStr;
	}

	/**
	 * <ul>
	 * <li>pavakarys [18..20)</li>
	 * <li>vakaras [20..22)</li>
	 * <li>velus vakaras [22..0)</li>
	 * <li>naktis [0..3)</li>
	 * <li>paryciai [3..5)</li>
	 * <li>ankstus rytas [5..7)</li>
	 * <li>rytas [7..9)</li>
	 * <li>velyvas rytas [9..10)</li>
	 * <li>diena [10..12)</li>
	 * <li>apie pietus [12..14)</li>
	 * <li>popiete [14..15)</li>
	 * <li>vakaro planu metas [15..18)</li>
	 * </ul>
	 */
	private String convertTime(int hh) {
		switch (hh) {
		case 0:
		case 1:
		case 2:
			return "naktis";
		case 3:
		case 4:
			return "paryčiai";
		case 5:
		case 6:
			return "ankstus rytas";
		case 7:
		case 8:
			return "rytas";
		case 9:
			return "vėlyvas rytas";
		case 10:
		case 11:
			return "diena";
		case 12:
		case 13:
			return "apie pietus";
		case 14:
			return "popietė";
		case 15:
		case 16:
		case 17:
			return "vakaro planų metas";
		case 18:
		case 19:
			return "pavakarys";
		case 20:
		case 21:
			return "vakaras";
		case 22:
		case 23:
			return "vėlus vakaras";

		}
		return "???";
	}

	/**
	 * ziemos [pacioje] pradzioje /[mazdaug] viduryje / [pacioje] pabaigoje
	 * 
	 * @param menuo
	 * @param diena
	 * @return
	 */
	private String convertMenuoDiena(int menuo, int diena) {

		// Z pacioje pradzioje
		// Z pradzioje
		// Z pimoje puseje
		// Z antroje puseje
		// Z pabaigoje
		// Z pacioje pabaigoje

		switch (menuo) {
		case 12:
			return fuzzyMonth("žiemos", 1, diena);
		case 1:
		case 2:
			return fuzzyMonth("žiemos", menuo + 1, diena);
		case 3:
		case 4:
		case 5:
			return fuzzyMonth("pavasario", menuo - 2, diena);
		case 6:
		case 7:
		case 8:
			return fuzzyMonth("vasaros", menuo - 5, diena);
		case 9:
		case 10:
		case 11:
			return fuzzyMonth("rudens", menuo - 8, diena);
		}

		return "???";

	}

	private String fuzzyMonth(String seasonName, int monthOfTheSeason, int day) {

		switch (monthOfTheSeason) {
		case 1:
			return seasonName + (day < ALMOST_MIDLE ? " pačioje " : " ") + "pradžioje";
		case 2:
			return seasonName + (day < ALMOST_MIDLE ? " pirmoje " : " antroje ") + "pusėje";
		case 3:
			return seasonName + (day < ALMOST_MIDLE ? " pačioje " : " ") + "pabaigoje";
		}

		return "???";
	}

	/**
	 * <ul>
	 * <li>8,7,6,5,4,3,2 metai atgal</li>
	 * <li>praitais metais</li>
	 * <li>siu metu</li>
	 * <li>kitais metais</li>
	 * <li>po 2,3,4... metu</li>
	 * </ul>
	 */
	private String convertMetai(int metai) {
		LocalDate ld = LocalDate.now();

		int diff = ld.getYear() - metai;
		if (diff == 0) {
			return "Šių metų";
		}

		if (diff == 1) {
			return "Praitais metais";
		}

		if (diff == -1) {
			return "Kitamet";
		}

		if (diff > 2) {
			return "Prieš " + num_2_9(diff, false) + (diff > 9 ? " metų" : " metus");
		}

		if (diff < 2) {
			return "Po " + num_2_9(diff, true) + " metų";
		}

		return "???";
	}

	private String num_2_9(int num, boolean future) {
		switch (num) {
		case 2:
			return "dvej" + endingUS_U(future);
		case 3:
			return "trej" + endingUS_U(future);
		case 4:
			return "ketveri" + endingUS_U(future);
		case 5:
			return "penkeri" + endingUS_U(future);
		case 6:
			return "šešiari" + endingUS_U(future);
		case 7:
			return "septyneri" + endingUS_U(future);
		case 8:
			return "aštuoneri" + endingUS_U(future);
		case 9:
			return "devyneri" + endingUS_U(future);
		case 10:
			return "dešimt";

		case 11:
			return "vienuolika";

		case 12:
			return "dvylika";

		case 13:
			return "trylika";

		case 14:
			return "keturolika";

		case 15:
			return "penkiolika";

		case 16:
			return "šešiolika";

		case 17:
			return "septyniolika";

		case 18:
			return "aštuoniolika";

		case 19:
			return "devyniolika";
		}

		return "???";
	}

	private String endingUS_U(boolean future) {
		return (future ? "us" : "ų");
	}

}
