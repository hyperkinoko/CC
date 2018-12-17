package section_5;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

enum Constellation {
	//列挙子
	Aries("牡羊座", MonthDay.of(3, 21)),
	Taurus("牡牛座", MonthDay.of(4, 20)),
	Gemini("双子座", MonthDay.of(5, 21)),
	Cencer("蟹座", MonthDay.of(6, 22)),
	Leo("獅子座", MonthDay.of(7, 23)),
	Virgo("乙女座", MonthDay.of(8, 23)),
	Libra("天秤座",MonthDay.of(9, 23)),
	Scorpio("蠍座", MonthDay.of(10, 24)),
	Sagittarius("射手座",MonthDay.of(11, 23)),
	Capricorn("山羊座", MonthDay.of(12, 22)),
	Aquarius("水瓶座",MonthDay.of(1, 20)),
	Pisces("魚座", MonthDay.of(2, 19));

	private final String name;
	private final MonthDay from;

	private Constellation(String name, MonthDay from) {
		this.name = name;
		this.from = from;
	}

	public String getName() {
		return this.name;
	}

	public MonthDay getFromDate() {
		return this.from;
	}

	public MonthDay getToDate() {
		return MonthDay.of(this.next().getFromDate().getMonthValue(), this.next().getFromDate().getDayOfMonth() -1);
	}

	public Constellation next() {
		switch (this) {
		case Aries:
			return Taurus;
		case Taurus:
			return Gemini;
		case Gemini:
			return Cencer;
		case Cencer:
			return Leo;
		case Leo:
			return Virgo;
		case Virgo:
			return Libra;
		case Libra:
			return Scorpio;
		case Scorpio:
			return Sagittarius;
		case Sagittarius:
			return Capricorn;
		case Capricorn:
			return Aquarius;
		case Aquarius:
			return Pisces;
		case Pisces:
			return Aries;
		default:
			throw new IllegalStateException();
		}
	}

	public Constellation prev() {
		switch (this) {
		case Aries:
			return Pisces;
		case Taurus:
			return Aries;
		case Gemini:
			return Taurus;
		case Cencer:
			return Gemini;
		case Leo:
			return Cencer;
		case Virgo:
			return Leo;
		case Libra:
			return Virgo;
		case Scorpio:
			return Libra;
		case Sagittarius:
			return Scorpio;
		case Capricorn:
			return Sagittarius;
		case Aquarius:
			return Capricorn;
		case Pisces:
			return Aquarius;
		default:
			throw new IllegalStateException();
		}
	}

	public static Constellation getConstellationOf(int index) {
		switch (index) {
		case 1:
			return Aries;
		case 2:
			return Taurus;
		case 3:
			return Gemini;
		case 4:
			return Cencer;
		case 5:
			return Leo;
		case 6:
			return Virgo;
		case 7:
			return Libra;
		case 8:
			return Scorpio;
		case 9:
			return Sagittarius;
		case 10:
			return Capricorn;
		case 11:
			return Aquarius;
		case 12:
			return Pisces;
		default:
			throw new IllegalStateException();
		}
	}

	public static Constellation getType(MonthDay birthday) {
		Constellation[] constellations = Constellation.values();
		for(Constellation constellation : constellations) {
			if(constellation == Capricorn) {
				continue;
			}
			if(birthday.isAfter(constellation.prev().getToDate()) && birthday.isBefore(constellation.next().getFromDate())) {
				return constellation;
			}
		}
		return Capricorn;
	}

	public String getExplain() {
		return this.toString() + " : " + this.name + "\n" + this.getFromDate().format(DateTimeFormatter.ofPattern("M/d")) + "〜" + this.getToDate().format(DateTimeFormatter.ofPattern("M/d"));
	}
}
