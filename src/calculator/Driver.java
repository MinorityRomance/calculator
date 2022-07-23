package calculator;

/**
 * 测试并封装代码
 */
public class Driver {
    public static void main(String[]args){
        Helper.helper("( 1+ 3 *    2)+1");
        Helper.helper("( 1+(2 +( 1 + 2 ) ) )");
        Helper.helper("a");
    }
}
