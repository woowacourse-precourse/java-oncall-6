package oncall;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class InputValidator {

	public static int validateMonth(String input) {
		try {
			int month = Integer.parseInt(input);
			if (month < 1 || month > 12) {
				throw new IllegalArgumentException("유효하지 않은 월입니다.");
			}
			return month;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("월을 숫자로 입력해 주세요.");
		}
	}


	public static DayOfWeek validateDayOfWeek(String input) {
		try {
			return DayOfWeek.valueOf(input.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("요일을 입력해 주세요.");
		}
	}

	public static List<String> validateOncallList(String input) {
		List<String> oncallList = Arrays.asList(input.split(","));
		if (oncallList.isEmpty()) {
			throw new IllegalArgumentException("비상 근무 순서를 입력해 주세요.");
		}
		for (String nickname : oncallList) {
			if (nickname.isEmpty()) {
				throw new IllegalArgumentException("닉네임을 입력해 주세요.");
			}
		}
		return oncallList;
	}
}

