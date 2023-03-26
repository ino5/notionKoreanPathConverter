import java.util.Scanner;

public class Main {
    private static final int FIRST_COMBINATION_WEIGHT = 21 * 28;
    private static final int MIDDLE_COMBINATION_WEIGHT = 28;
    private static final int LAST_COMBINATION_WEIGHT = 1;

    private static final int FIRST_KOREAN_CHARACTER = 0xAC00;
    private static final int LAST_KOREAN_CHARACTER = 0xD7A3;

    private static final int COMBINATION_CHARACTER_OFFSET = FIRST_KOREAN_CHARACTER;
    private static final int FIRST_CHARACTER_OFFSET = 0x1100;
    private static final int MIDDLE_CHARACTER_OFFSET = 0x1161;
    private static final int LAST_CHARACTER_OFFSET = 0x11A7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] strArr = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int strIdx = 0; strIdx < strArr.length; strIdx++) {
            int strItem = (int) strArr[strIdx];
            
            // 한글인지 확인
            int combinationCharacter = 0;
            if (strItem >= FIRST_KOREAN_CHARACTER && strItem <= LAST_KOREAN_CHARACTER) {
                combinationCharacter = strItem;
            } else {
                sb.append((char) strItem);
                continue;
            }
            
            // 각 문자열에 대해 처리
            int combinationIdx = combinationCharacter - COMBINATION_CHARACTER_OFFSET;
            int firstIdx = combinationIdx / FIRST_COMBINATION_WEIGHT;
            int middleIdx = (combinationIdx - (firstIdx * FIRST_COMBINATION_WEIGHT)) / MIDDLE_COMBINATION_WEIGHT;
            int lastIdx = (combinationIdx - (firstIdx * FIRST_COMBINATION_WEIGHT) - (middleIdx * MIDDLE_COMBINATION_WEIGHT)) / LAST_COMBINATION_WEIGHT;
            char firstCh = (char) (firstIdx + FIRST_CHARACTER_OFFSET);
            char middleCh = (char) (middleIdx + MIDDLE_CHARACTER_OFFSET);
            char lastCh = (char) (lastIdx + LAST_CHARACTER_OFFSET);

            sb.append(firstCh).append(middleCh);
            if (lastIdx > 0) {
                sb.append(lastCh);
            }
        }
        
        // 결과 출력
        System.out.println(sb.toString());
    }
}