import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Class3 {

    private static Class2 class2 = new Class2();
    private Logger logger = LoggerFactory.getLogger(Class3.class);

    public static void main(String[] args){
        Class3 class3 = new Class3();
        try {
            class3.genAnotherEx();
        } catch (CustomException e) {
            class3.logger.error("failed!", e);
        }
    }


    private void genAnotherEx() throws CustomException {
        try {
            class2.callEx();
        } catch (ArithmeticException e) {
            throw new CustomException("another ex is created as resp to first ex", e);
        }
    }
}
