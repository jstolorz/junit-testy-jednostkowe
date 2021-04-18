package org.bluesoft.unittestjava;

public class Test {
    public static void main(String[] args) {
        testCalcAdd();
    }

    private static void testCalcAdd(){
        Calculator calc = new Calculator();
        int result = calc.add(2,2);

        if(result != 4){
            throw new IllegalStateException("Wrong result, 2 + 2 is not 4");
        }else{
            System.out.println("OK");
        }
    }
}
