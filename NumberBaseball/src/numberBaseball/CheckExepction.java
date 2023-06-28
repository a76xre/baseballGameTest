package numberBaseball;

public class CheckExepction {
    private CheckExepction(){
    }
    // 사용자 입력값이 숫자인지 확인하는 메소드.
    private static boolean checkIsInt(String num) {
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
    private static boolean checkDuplicateNum(String num){
        String[] temp = num.split("");
        if (temp[0].equals(temp[1]) || temp[1].equals(temp[2]) || temp[0].equals(temp[2])){
            System.out.println("중복되지 않는 숫자를 입력해주세요.");
            return false;
        }
        return true;
    }

    //3자리 수 판별.
    private static boolean checkIsLength3(String num){
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
}
