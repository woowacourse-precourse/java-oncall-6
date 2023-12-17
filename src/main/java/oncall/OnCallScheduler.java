package oncall;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class OnCallScheduler {

	private int month;
	private List<OnCall> weekdayOncallList;
	private List<OnCall> weekendOncallList;

	public OnCallScheduler() {
		month = 1;
		weekdayOncallList = new ArrayList<>();
		weekendOncallList = new ArrayList<>();
	}

	public void setMonth(int month) {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("유효하지 않은 월입니다.");
		}
		this.month = month;
	}

	public void setWeekdayOnCallList(List<OnCall> weekdayOncallList) {
		validateOnCallList(weekdayOncallList, "평일");
		this.weekdayOncallList = weekdayOncallList;
	}

	public void setWeekendOnCallList(List<OnCall> weekendOncallList) {
		validateOnCallList(weekendOncallList, "주말");
		this.weekendOncallList = weekendOncallList;
	}

	private void validateOnCallList(List<OnCall> oncallList, String type) {
		if (oncallList == null || oncallList.isEmpty()) {
			throw new IllegalArgumentException(type + " 응급 근무자 목록을 입력해주세요.");
		}

		for (OnCall oncall : oncallList) {
			if (oncall == null || oncall.getNickname().isEmpty()) {
				throw new IllegalArgumentException(type + "에 대한 유효한 응급 근무자 정보를 입력해주세요.");
			}
		}
	}

	public List<OnCall> generateOncallList() {
		List<OnCall> oncallList = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1); // Calendar는 0부터 시작하는 월을 사용하므로 조정

		for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			DayOfWeek startDayOfWeek = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK));

			List<OnCall> currentOncallList = startDayOfWeek == DayOfWeek.SATURDAY || startDayOfWeek == DayOfWeek.SUNDAY ?
				weekendOncallList : weekdayOncallList;

			OnCall oncall = currentOncallList.get((i - 1) % currentOncallList.size());
			// Adjusting or setting the date for oncall object can be added based on your requirement

			oncallList.add(oncall);
		}

		return oncallList;
	}

	public void setStartDayOfWeek(DayOfWeek startDayOfWeek) {
	}

	public void setWeekdayOncallList(List<String> weekdayOncallList) {
	}

	public void setWeekendOncallList(List<String> weekendOncallList) {
	}
}