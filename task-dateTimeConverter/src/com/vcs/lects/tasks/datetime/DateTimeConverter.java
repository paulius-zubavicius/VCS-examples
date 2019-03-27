package com.vcs.lects.tasks.datetime;

public class DateTimeConverter {

	/**
	 * 
	 * Padavus validžią datą, turetų grąžinti:
	 * 
	 * <pre>
	 *  2018, 08, 01, 12, 15, 10 => Du tukstančiai aštuoniolikti metai, Rugpjūčio pirma, dvylika val. penkiolika min. dešimt sek.
	 * </pre>
	 * 
	 * @param metai
	 *            Metai gali buti tik nuo [2001 ... 2019]
	 * @param menuo
	 *            [1..12]
	 * @param diena
	 *            [1..31]
	 * @param hh
	 *            [0..23]
	 * @param mm
	 *            [0..59]
	 * @param ss
	 *            [0..59]
	 * @param fuzzyTime
	 *            Išvedimo išraiška suapvalinama, jei reikšmė yra
	 *            <b>true</b>:<b><i>Šių metų, Rugpjūčio pati pradžia šiektiek po
	 *            vidurdienio</i></b>
	 * 
	 * @return Žodine išraiška pateiks datą, pvz.:<b> 2018, 08, 01, 12, 15, 10
	 *         </b>pavers į:<b> <i>Du tukstančiai aštuoniolikti metai, Rugpjūčio
	 *         pirma, dvylika val. penkiolika min. dešimt sek.</i> </b>
	 */
	public String getDateTimeHumanReadable(int metai, int menuo, int diena, int hh, int mm, int ss, boolean fuzzyTime) {

		if (fuzzyTime) {
			return new DateTimeConverterFuzzyImpl().convert(metai, menuo, diena, hh);
		}

		return new DateTimeConverterImpl().convert(metai, menuo, diena, hh, mm, ss);
	}
}
