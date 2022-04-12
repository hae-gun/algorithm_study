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
    public static void answer(Object[] solutions, Object[] answers){
        boolean isOk = true;
        if(solutions.length == 0){
            if(answers.length != 0){
                isOk = false;
            }
        }
        for(int i=0; i<solutions.length; i++){
            if(!solutions[i].equals(answers[i])){
                isOk = false;
                break;
            }
        }
        answer(isOk);
    }
    public static void answer(Object solution, Object answer){
        answer(solution.equals(answer));
    }
    public static void answer(long solution, long answer){
        answer(solution == answer);
    }
    public static void answer(String solution, String answer){
        answer(solution.equals(answer));
    }
}
