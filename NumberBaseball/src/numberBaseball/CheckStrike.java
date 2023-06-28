package numberBaseball;

public class CheckStrike {
    private CheckStrike(){

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
}
