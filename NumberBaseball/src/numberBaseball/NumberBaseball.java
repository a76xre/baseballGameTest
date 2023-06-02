package numberBaseball;

import utils.Console;
import utils.Randoms;

public class NumberBaseball {
	public static void main(String[] args) {
		
		String Answer = makeNumberList();
		playGame(Answer);
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		String flag = Console.readLine();
		while(!flag.equals("2")) {
			Answer = makeNumberList();
			playGame(Answer);
			System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
			flag = Console.readLine();
		}
		return;
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
		String result = Integer.toString(Answer[0])+Integer.toString(Answer[1])+Integer.toString(Answer[2]);
		return result;
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
		String[] result = ball;
		int i =0;
		while(i<3) {
			result[i]= (strike[i].equals("S")) ? "S" : ball[i] ;
			i++;
		}
		return result;
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
	
	// 게임을 반복 실행하는 메소드.
	public static void playGame(String Answer) {
		String result = "OOO";
		boolean flag = true;
		String userNumList = "";
		while (!result.equals("SSS") && flag) {
			//유저 숫자 입력.
			System.out.print("숫자를 입력하세요. : ");
			userNumList = Console.readLine();
			//숫자를 입력했는지 판별.
			flag = checkIsInt(userNumList);
			
			//베이스볼 게임 시작
			result = baseballGame(userNumList,Answer);
			
			System.out.println(resultToString(result));
		}
		System.out.println("3개의 숫자를 모두 맞추셨습니다! 게임 종료");
		return ;
	}
}
