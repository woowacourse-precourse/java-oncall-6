package oncall;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.Month;



public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the month (1-12) and start day of the week (Sun, Mon, Tue, Wed, Thu, Fri, Sat) for emergency work assignment: ");
        Month month = readMonth(scanner);
        DayOfWeek startDayOfWeek = readDayOfWeek(scanner);

        List<String> weekdayOncallList = readOncallList(scanner, "weekday");
        List<String> weekendOncallList = readOncallList(scanner, "weekend");

        validateInput(month, startDayOfWeek, weekdayOncallList, weekendOncallList);

        OnCallScheduler oncallScheduler = new OnCallScheduler();
        oncallScheduler.setMonth(month.getValue());
        oncallScheduler.setStartDayOfWeek(startDayOfWeek);
        oncallScheduler.setWeekdayOncallList(weekdayOncallList);
        oncallScheduler.setWeekendOncallList(weekendOncallList);

        System.out.println("Emergency Worker Schedule:");
        System.out.println(oncallScheduler.generateOncallList());

        scanner.close();
    }

    private static Month readMonth(Scanner scanner) {
        int input = scanner.nextInt();
        if (input < 1 || input > 12) {
            System.out.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            return null;
        }
        return Month.of(input);
    }

    private static DayOfWeek readDayOfWeek(Scanner scanner) {
        String input = scanner.next();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(input.toUpperCase());
        if (dayOfWeek == null) {
            System.out.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            return null;
        }
        return dayOfWeek;
    }

    private static List<String> readOncallList(Scanner scanner, String type) {
        System.out.print("Enter employee nicknames in order of " + type + " emergency shifts (type 'exit' to stop): ");
        List<String> oncallList = new ArrayList<>();
        while (true) {
            String nickname = scanner.next();
            if (nickname.equals("exit")) {
                break;
            }
            oncallList.add(nickname);
        }
        return oncallList;
    }

    private static void validateInput(Month month, DayOfWeek startDayOfWeek, List<String> weekdayOncallList, List<String> weekendOncallList) {
        if (month == null || startDayOfWeek == null || weekdayOncallList.isEmpty() || weekendOncallList.isEmpty()) {
            System.out.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            System.exit(0);
        }

        // 평일 비상 근무 순서 검증
        if (weekdayOncallList.size() < 7) {
            System.out.println("[ERROR] 평일 비상 근무 순서는 7명 이상이어야 합니다. 다시 입력해 주세요.");
            System.exit(0);
        }

        // 휴일 비상 근무 순서 검증
        if (weekendOncallList.size() < 3) {
            System.out.println("[ERROR] 휴일 비상 근무 순서는 3명 이상이어야 합니다. 다시 입력해 주세요.");
            System.exit(0);
        }
    }
}