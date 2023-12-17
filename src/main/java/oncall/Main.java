package oncall;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 입력값 확인하고 받기
		int month = InputValidator.validateMonth(getInput("월을 입력하세요 (1-12): ", scanner));
		DayOfWeek startDayOfWeek = InputValidator.validateDayOfWeek(getInput("시작 요일을 입력하세요: ", scanner));
		List<String> weekdayOncallListString = getInputList("평일 응급 근무자를 입력하세요: ", scanner);
		List<String> weekendOncallListString = getInputList("주말 응급 근무자를 입력하세요: ", scanner);

		// 응급 근무자 목록 변환
		List<OnCall> weekdayOncallList = weekdayOncallListString.stream()
			.map(nickname -> new OnCall(nickname, DayOfWeek.MONDAY, false)) // 여기에 적절한 값 입력
			.collect(Collectors.toList());

		List<OnCall> weekendOncallList = weekendOncallListString.stream()
			.map(nickname -> new OnCall(nickname, DayOfWeek.SATURDAY, true)) // 여기에 적절한 값 입력
			.collect(Collectors.toList());

		// 응급 근무자 스케줄러 생성 및 설정
		OnCallScheduler oncallScheduler = new OnCallScheduler();
		oncallScheduler.setMonth(month);
		// 시작 요일 설정 (OnCallScheduler 클래스에 맞게 변경 필요)
		// oncallScheduler.setStartDayOfWeek(startDayOfWeek);
		oncallScheduler.setWeekdayOnCallList(weekdayOncallList);
		oncallScheduler.setWeekendOnCallList(weekendOncallList);

		// 응급 근무자 스케줄 확인 (필요시)
		// oncallScheduler.validate();

		// 응급 근무자 스케줄 출력
		System.out.println("생성된 응급 근무자 스케줄:");
		List<OnCall> generatedList = oncallScheduler.generateOncallList();
		for (OnCall oncall : generatedList) {
			System.out.println(oncall.toString());
		}

		scanner.close();
	}

	private static String getInput(String message, Scanner scanner) {
		System.out.print(message);
		return scanner.nextLine();
	}

	private static List<String> getInputList(String message, Scanner scanner) {
		System.out.print(message);
		return Arrays.asList(scanner.nextLine().split("\\s*,\\s*"));
	}
}

