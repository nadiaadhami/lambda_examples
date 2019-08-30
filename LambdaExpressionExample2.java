package nadiatests;

// https://www.javatpoint.com/java-lambda-expressions

interface Sayable{
    public String say(String name);
}
interface IsOdd{
    public boolean modula(int i);
}

public class LambdaExpressionExample2{
    public static void main(String[] args) {

        // Lambda expression with single parameter.
        Sayable s1=(name)->{
            return "Hello, "+name;
        };
        System.out.println(s1.say("Jonathan"));

        // You can omit function parentheses
        Sayable s2= name ->{
            return "Hello, "+name;
        };
        System.out.println(s2.say("Jeremy"));

        IsOdd isOdd = (i) -> {return (i % 2 == 0 ? true : false);};
        System.out.println(isOdd.modula(5));
        System.out.println(isOdd.modula(4));
    }
}