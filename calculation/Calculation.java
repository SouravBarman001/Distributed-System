package calculation;

public class Calculation {

    public static String Calculation(int number){
        String str;
        if(number%2==0) {
            str = "Even";
        }
        else{
            str="Odd";
        }

        return str;
    }
}