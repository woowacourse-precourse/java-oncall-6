package oncall;

import java.time.DayOfWeek;

public class OnCall {

	private final String nickname;
	private final DayOfWeek dayOfWeek;
	private final boolean isHoliday;

	public OnCall(String nickname, DayOfWeek dayOfWeek, boolean isHoliday) {
		this.nickname = nickname;
		this.dayOfWeek = dayOfWeek;
		this.isHoliday = isHoliday;
	}

	public String getNickname() {
		return nickname;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public boolean isHoliday() {
		return isHoliday;
	}

	@Override
	public String toString() {
		return nickname + "(" + dayOfWeek + (isHoliday ? "(휴일)" : "") + ")";
	}
}

