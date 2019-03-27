package com.vcs.lects.tasks.datetime;

public class NumToString {

	/**
	 * @param sk
	 *            0 iki 99
	 * @param ending
	 *            true - mot. vien., false - vyr. daug.
	 * @return skaičius žodžiais
	 */
	public String getAsString(int sk, boolean ending) {

		// 0..9
		// 10 20 30
		// 11... 19
		// 21... 29; 31...39...

		if (sk <= 9) {
			return from_0_9(sk, ending);
		}

		if (isItRounded(sk)) {
			return fromRounded(sk, ending, false);
		}

		if (sk >= 11 && sk <= 19) {
			return from_11_19(sk, ending);
		}

		return from_X1_X9(sk, ending);
	}

	private String from_X1_X9(int sk, boolean ending) {

		int minor = sk % 10;
		int major = sk - minor;
		return fromRounded(major, ending, true) + " " + from_0_9(minor, ending);
	}

	private String from_11_19(int sk, boolean ending) {
		switch (sk) {

		case 11:
			return "vienuolikt" + endingAU(ending);
		case 12:
			return "dvylikt" + endingAU(ending);
		case 13:
			return "trylikt" + endingAU(ending);
		case 14:
			return "keturiolikt" + endingAU(ending);
		case 15:
			return "peniolikt" + endingAU(ending);
		case 16:
			return "šešiolikt" + endingAU(ending);
		case 17:
			return "septyniolikt" + endingAU(ending);
		case 18:
			return "aštuoniolikt" + endingAU(ending);
		case 19:
			return "devyniolikt" + endingAU(ending);
		}

		return "???";
	}

	private String fromRounded(int sk, boolean ending, boolean shortedEnding) {
		switch (sk) {
		case 10:
			return "dešimt" + ending_AU(shortedEnding, ending);
		case 20:
			return "dvidešimt" + ending_AU(shortedEnding, ending);
		case 30:
			return "trisdešimt" + ending_AU(shortedEnding, ending);
		case 40:
			return "keturiasdešimt" + ending_AU(shortedEnding, ending);
		case 50:
			return "penkiasdešimt" + ending_AU(shortedEnding, ending);
		case 60:
			return "šešiasdešimt" + ending_AU(shortedEnding, ending);
		case 70:
			return "septyniasdešimt" + ending_AU(shortedEnding, ending);
		case 80:
			return "aštuonesdiašimt" + ending_AU(shortedEnding, ending);
		case 90:
			return "devyniasdešimt" + ending_AU(shortedEnding, ending);
		}

		return "???";
	}

	private String from_0_9(int sk, boolean ending) {

		switch (sk) {
		case 0:
			return "nulis";
		case 1:
			return "pirm" + endingAU(ending);
		case 2:
			return "antr" + endingAU(ending);
		case 3:
			return "treči" + endingAU(ending);
		case 4:
			return "ketvirt" + endingAU(ending);
		case 5:
			return "penkt" + endingAU(ending);
		case 6:
			return "šešt" + endingAU(ending);
		case 7:
			return "septint" + endingAU(ending);
		case 8:
			return "aštunt" + endingAU(ending);
		case 9:
			return "devint" + endingAU(ending);
		}

		return "???";
	}

	private boolean isItRounded(int sk) {
		return sk % 10 == 0;
	}

	private String endingAU(boolean ending) {
		return ending ? "a" : "ų";
	}

	private String ending_AU(boolean shortedEnding, boolean ending) {
		if (shortedEnding) {
			return "";
		}
		return endingAU(ending);
	}

}
