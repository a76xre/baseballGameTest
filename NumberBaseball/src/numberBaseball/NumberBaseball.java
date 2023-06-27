package numberBaseball;

import utils.Console;
import utils.Randoms;

public class NumberBaseball {
	public static void main(String[] args) {
		
		String Answer = makeNumberList();
		System.out.println(Answer);
		playGame(Answer);
		String userAnser = checkNewGame();
		while(!userAnser.equals("2")) {
			Answer = makeNumberList();
			System.out.println(Answer);
			playGame(Answer);
			userAnser = checkNewGame();
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
			strikeTemp[i] = checkStrike(answerList[i],userNumList[i]);
			ballTemp[i] = checkStrike(answer,userNumList[i]);
		}
		String[] result = checkBS(strikeTemp,ballTemp);
		String result2 = result[0]+result[1]+result[2];
		return result2;
	}
	
	// 볼 스트라이크 아웃을 판단하는 메소드.
	public static String checkStrike(String answer, String userNum) {
		if(answer.equals(userNum)) {
			return "S";
		}
		if(answer.contains(userNum)) {
			return "B";
		}
		return "O";
	}
	
	// 각 자리별로 스트라이크 아웃을 확인해서 스트링배열로 만들어주는 메소드.
	public static String[] checkBS(String[] strike, String[] ball) {
		int i =0;
		while(i<3) {
			ball[i]= (strike[i].equals("S")) ? "S" : ball[i] ;
			i++;
		}
		return ball;
	}
	
	// 게임결과를 판단하는 메소드.
	public static String resultToString(String result) {
		String resultString ="";
		int strikeCount = result.length() - result.replace("S", "").length();
		int ballCount = result.length() - result.replace("B", "").length();
		resultString += (ballCount>0) ? ballCount + "볼" : "";
		resultString += (strikeCount>0) ? strikeCount + "스트라이크" : "";
		resultString += (ballCount==0 && strikeCount==0) ? "낫싱" : "";
		return resultString;
	}
	
	// 사용자 입력값이 숫자인지 확인하는 메소드.
	public static boolean checkIsInt(String num) {
		try {
			Integer.parseInt(num);
		} catch(Exception e) {
			//e.printStackTrace();
			System.out.println("숫자를 입력해주세요.");
			return false;
		}
		return true;
	}

	//중복숫자 확인.
	public static boolean checkDuplicateNum(String num){
		String[] temp = num.split("");
		if (temp[0].equals(temp[1]) || temp[1].equals(temp[2]) || temp[0].equals(temp[2])){
			System.out.println("중복되지 않는 숫자를 입력해주세요.");
			return false;
		}
		return true;
	}

	//3자리 수 판별.
	public static boolean checkIsLength3(String num){
		if(!(num.length() ==3)){
			System.out.println("3자리 수를 입력해주세요.");
			return false;
		}
		return checkDuplicateNum(num);
	}

	//예외 처리
	public static boolean checkException(String num){
		//숫자를 입력했는지 판별.
		if (!checkIsInt(num)) {
			return false;
		}
		//자리수를 판별.
		return checkIsLength3(num);
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
			flag = checkException(userNumList);

			//베이스볼 게임 시작
			result = flag ? baseballGame(userNumList,Answer) : "OOO";

			System.out.println(resultToString(result));
		}
		System.out.println("3개의 숫자를 모두 맞추셨습니다! 게임 종료");
	}
}
