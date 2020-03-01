package healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;

import static healthycoderapp.BMICalculator.*;
import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {


    @Test
    void should_ReturnTrue_When_DietRecommended() {
        //given
        double weight=89.0;
        double height=1.87;
        //when
        boolean recommended=BMICalculator.isDietRecommended(weight,height);
        //then
        assertTrue(recommended);
    }
    @Test
    void should_ReturnFalse_When_DietRecommended() {
        //given
        double weight=50.0;
        double height=0.0;
        //when
        Executable executable=()-> BMICalculator.isDietRecommended(weight,height);
        //then
        assertThrows(ArithmeticException.class,executable);
    }
@Test
void should_ThrowArithmeticException_When_HeiightZero(){

    double weight=89.0;
    double height=0.0;
    Executable executable =() -> BMICalculator.isDietRecommended(weight,height);
    assertThrows(ArithmeticException.class,executable);
}
    @Test
    void should_Return_CoderWithWorstBMI(){
        //given
        List<Coder> coders=new ArrayList<>();
        coders.add(new Coder(1.8,60.0));
        coders.add(new Coder(1.82,98.0));
        coders.add(new Coder(1.82,64.7));
        //when
        Coder coderWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertAll(
                () -> assertEquals(1.82,coderWorstBMI.getHeight()),
                () -> assertEquals(98.0,coderWorstBMI.getWeight())
        );
    }

    @Test
    void should_Return_Null_CoderWithWorstBMI(){
        //given
        List<Coder> coders=new ArrayList<>();

        //when
        Coder coderWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertNull(coderWorstBMI);
    }


    @Test
    void should_Return_BMIscores(){
        //given
        List<Coder> coders=new ArrayList<>();
        coders.add(new Coder(1.80,60.0));
        coders.add(new Coder(1.82,98.0));
        coders.add(new Coder(1.82,64.7));
        double expected[]={18.52,29.59,19.53};
        //when
        double result_Array[]=getBMIScores(coders);

        //then
        assertArrayEquals(expected,result_Array);

    }


}
