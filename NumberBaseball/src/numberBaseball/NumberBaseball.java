package numberBaseball;

import numberBaseball.CheckExepction;
import numberBaseball.CheckStrike;
import utils.Console;
import utils.Randoms;

public class NumberBaseball {
	public static void main(String[] args) {

		String Answer = makeNumberList();
		System.out.println(Answer);
		playGame(Answer);
		String userAnwser = checkNewGame();
		while(!userAnwser.equals("2")) {
			Answer = makeNumberList();
			System.out.println(Answer);
			playGame(Answer);
			userAnwser = checkNewGame();
		}
	}

	//새 게임 진행여부 판단.
	public static String checkNewGame(){
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		String userAnswer = Console.readLine();
		while (!userAnswer.equals("1") && !userAnswer.equals("2")){
			System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
			userAnswer = Console.readLine();
		}
		return userAnswer;
	}

	// 정답(숫자3개)를 만들어내는 메소드.
	public static String makeNumberList() {
		int[] Answer = new int[3];
		Answer[0] = Randoms.pickNumberInRange(0, 9);
		Answer[1] = Randoms.pickNumberInRange(0, 9);
		Answer[2] = Randoms.pickNumberInRange(0, 9);
		while (Answer[0] == Answer[1] || Answer[1] == Answer[2] || Answer[0] == Answer[2]) {
			Answer[1] = Randoms.pickNumberInRange(0, 9);
			Answer[2] = Randoms.pickNumberInRange(0, 9);
		}
		return Integer.toString(Answer[0])+ Answer[1] + Answer[2];
	}
	
	// 게임을 실행하는 메소드.
	public static String baseballGame(String userNum, String answer) {
		String[] strikeTemp = {"","",""},ballTemp = {"","",""},userNumList = userNum.split(""),answerList = answer.split("");
		for (int i=0 ; i < answerList.length ; i++) {
			strikeTemp[i] = CheckStrike.checkStrike(answerList[i],userNumList[i]);
			ballTemp[i] = CheckStrike.checkStrike(answer,userNumList[i]);
		}
		String[] result = CheckStrike.checkBS(strikeTemp,ballTemp);
		String result2 = result[0]+result[1]+result[2];
		return result2;
	}



	// 게임을 반복 실행하는 메소드.
	public static void playGame(String Answer) {
		String result = "OOO";
		boolean flag;
		String userNumList;
		while (!result.equals("SSS")) {
			//유저 숫자 입력.
			System.out.print("숫자를 입력하세요. : ");
			userNumList = Console.readLine();

			//예외 확인.
			flag = CheckExepction.checkException(userNumList);

			//베이스볼 게임 시작
			result = flag ? baseballGame(userNumList,Answer) : "OOO";

			System.out.println(CheckStrike.resultToString(result));
		}
		System.out.println("3개의 숫자를 모두 맞추셨습니다! 게임 종료");
	}
}
