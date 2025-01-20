import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static Scanner sc = new Scanner(System.in);
  private static List<List<Character>> characterLists = new ArrayList<>();
  private static List<Integer> targetCombinations = new ArrayList<>();
  private static StringBuilder sb = new StringBuilder();

  private static int count = 0;
  private static int curTarget = 0;
  private static boolean isDone = false;

  public static void main(String[] args) {
    initializeProblems();
    for (int i = 0; i < targetCombinations.size(); i++) {
      count = 0;
      curTarget = targetCombinations.get(i);
      isDone = false;
      List<Character> curLi = characterLists.get(i);
      List<Character> printLi = new ArrayList<>();
      boolean[] used = new boolean[curLi.size()];

      for (Character c : curLi) {
        sb.append(c);
      }

      sb.append(" ");
      sb.append(curTarget);
      sb.append(" = ");

      if (getPermutation(curLi.size()) < curTarget) {
        sb.append("No permutation\n");
      }

      Collections.sort(curLi);
      backtracking(curLi, printLi, used);
    }
    System.out.print(sb.toString().trim());
    sc.close();
  }

  private static void initializeProblems() {
    while (sc.hasNext()) {
      String input = sc.next();
      if (input.equals("0"))
        break;

      char[] unsortedChars = input.toCharArray();
      List<Character> charList = new ArrayList<>();
      for (char c : unsortedChars) {
        charList.add(c);
      }
      characterLists.add(charList);
      targetCombinations.add(sc.nextInt());
    }
  }

  private static int getPermutation(int size) {
    int permutation = 1;
    for (int i = 1; i <= size; i++) {
      permutation *= i;
    }
    return permutation;
  }

  /**
   * @param charLi  남아있는 문자를 담는 리스트
   * @param printLi 출력할 문자를 담는 리스트
   * @param used    사용된 index를 확인하는 배열열
   */
  private static void backtracking(List<Character> charLi, List<Character> printLi, boolean[] used) {
    if (printLi.size() == charLi.size()) {
      count++;
      if (curTarget == count) {
        for (char c : printLi) {
          sb.append(c);
        }
        sb.append("\n");
        isDone = true;
      }
      return;
    }

    for (int i = 0; i < charLi.size(); i++) {
      if (used[i] || isDone)
        continue;

      // 현재 위치에서 남은 원소들로 만들 수 있는 순열 개수 계산
      int remainingSize = charLi.size() - printLi.size() - 1;
      int factorial = getPermutation(remainingSize);

      // 목표 위치가 현재 위치보다 크면 가능한 순열 개수만큼 건너뛰기
      if (count + factorial < curTarget) {
        count += factorial;
        continue; // 건너뛰기
      }

      used[i] = true;
      printLi.add(charLi.get(i));
      backtracking(charLi, printLi, used);
      printLi.remove(printLi.size() - 1);
      used[i] = false;
    }
  }

}
