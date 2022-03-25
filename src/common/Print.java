package common;

public class Print {
    public static void answer(boolean answer){
        if(answer){
            System.out.println("정답입니다.");
        }else{
            System.out.println("오답입니다.");
        }
    }
    public static void answer(int[] solution, int[] answer){
        boolean collect = true;
        for(int i=0; i<answer.length; i++){
            if(solution[i] != answer[i]){
                collect = false;
                break;
            }
        }
        answer(collect);
    }
    public static void answer(Object solution, Object answer){
        answer(solution.equals(answer));
    }
    public static void answer(String solution, String answer){
        answer(solution.equals(answer));
    }
}
