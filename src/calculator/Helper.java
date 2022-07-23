package calculator;

import exception.ExpressionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 完成计算式的拆分计算
 */
public class Helper {
    public static void helper(String expression){
        expression = expression.replaceAll(" ","");//去掉表达式中包含的空格
        try{
            if(judge(expression)){
                throw new ExpressionException(expression);
            }
            else{
                calculate(expression);
            }
        }catch (ExpressionException e){
            System.out.println("表达式错误");
        }
    }


    //（1 + 2 * （3 - 5）） * （2 - 1）
    // ( ( (    ) ) )
    /**
     * 找到最后或者最里面的括号位置并提取出括号里的内容
     * @param expression 计算式
     * @return 返回括号之间的内容
     */
    public static String withParentheses (String expression) {
        int lastLeftIndex = expression.lastIndexOf('(');
        int rightParenthesis = expression.indexOf(")",lastLeftIndex);

        //括号之间的内容
        String content = expression.substring(lastLeftIndex+1,rightParenthesis);
        return content;
    }

    /**
     * 进行括号内的计算
     * @param content 括号里的内容
     * @return 把字符串形的计算结果返回
     */
    public static String withoutParentheses(String content){
        Calculator calculator = new Calculator();
        int result = 0;

        //把所有运算符换成 “”
        String add = content.replaceAll("[+]","");
        String sub = add.replaceAll("[-]","");
        String mul = sub.replaceAll("[*]","");
        String div = mul.replaceAll("[/]","");

        //计算原字符串与后来的长度差，得出总计有多少运算符，即在此()内的运算次数
        int calculateTimes = content.length() - div.length();

        for(int i = 0 ; i < calculateTimes ; i++ ){
            int symbol = 0 ;//运算符的位置
            String temp = null;//记录下每次运算的字符串
            int before = 0;//运算符之前的数字
            int after = 0;//运算费之后的数字

            if(content.contains("*") || content.contains("/")){
                if (content.contains("*")){
                    symbol = content.indexOf("*");
                    temp = content.substring(symbol-1,symbol+2);

                    //把得到的字符串转int进行运算
                    before = Integer.parseInt(content.substring(symbol-1,symbol));
                    after = Integer.parseInt(content.substring(symbol+1,symbol+2));


                    calculator.setA(before);
                    calculator.setB(after);
                    result = calculator.mul();
                    String result1 = Integer.toString(result);

                    //最后再把原来用来计算的那个子串换成转换成字符串后的结果
                    content = content.replace(temp,result1);

                }//后面几个几乎是复制粘贴内容就不注释了(本来想写个方法的，但懒得改了，直接复制用了)
                else{
                    symbol = content.indexOf("/");
                    temp = content.substring(symbol-1,symbol+2);

                    //把得到的字符串转int进行运算
                    before = Integer.parseInt(content.substring(symbol-1,symbol));
                    after = Integer.parseInt(content.substring(symbol+1,symbol+2));

                    calculator.setA(before);
                    calculator.setB(after);

                    result = calculator.div();
                    String result1 = Integer.toString(result);

                    //最后再把结果换进字符串
                    content = content.replace(temp,result1);
                }

            }else{
                if(content.contains("+")){
                    symbol = content.indexOf("+");
                    temp = content.substring(symbol-1,symbol+2);

                    //把得到的字符串转int进行运算
                    before = Integer.parseInt(content.substring(symbol-1,symbol));
                    after = Integer.parseInt(content.substring(symbol+1,symbol+2));

                    calculator.setA(before);
                    calculator.setB(after);
                    result = calculator.sum();
                    String result1 = Integer.toString(result);

                    //最后再把结果换进字符串
                    content = content.replace(temp,result1);

                }else{
                    symbol = content.indexOf("-");
                    temp = content.substring(symbol-1,symbol+2);

                    //把得到的字符串转int进行运算
                    before = Integer.parseInt(content.substring(symbol-1,symbol));
                    after = Integer.parseInt(content.substring(symbol+1,symbol+2));

                    calculator.setA(before);
                    calculator.setB(after);
                    result = calculator.mul();
                    String result1 = Integer.toString(result);

                    //最后再把结果换进字符串
                    content = content.replace(temp,result1);
                }
            }
        }
        return content;
    }

    /**
     * 汇总计算
     * @param expression 计算式
     */
    public static void calculate(String expression){
        String result = null;//计算过后括号内的内容

        int leftParentheses = 0;//左括号位置

        int rightParenthesis = 0;//右括号位置
        String content = null;//原括号内的内容

        String change = expression.replaceAll("[(]","");

        //计算出括号的数量＋1,即需要调用几次withoutParentheses()方法
        //((( )))此种形式多执行一次也不影响结果输出
        int calculateTimes = expression.length() - change.length();

        for(int i = 0 ; i < calculateTimes + 1 ; i++){
            //有括号的话
            if(expression.contains("(")) {
                content = withoutParentheses(withParentheses(expression));
                //算出最里面或最后面括号的位置
                leftParentheses = expression.lastIndexOf('(');
                rightParenthesis = expression.indexOf(")", leftParentheses);
                result = expression.substring(leftParentheses, rightParenthesis + 1);

                //进行替换
                expression = expression.replace(result, content);
            }
            else {
                expression = withoutParentheses(expression);
            }
        }
        System.out.println(expression);
    }


    /**
     * 判断计算式中是否有数字
     * @param expression 计算式
     * @return 如果有数字返回true，没有就返回false
     */
    public static boolean hasDigit(String expression) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(expression);

        if (m.matches()){
            flag = true;
        }
        return flag;
    }

    /**
     * 判断计算式是否正确
     * @param expresion 计算式
     * @return 判断计算式是否正确
     */
    public static boolean judge(String expresion){
        String temp = departNotNumber(expresion);
        //如果计算式中没有数字并且没有运算号就是错误的
        if ( !(hasDigit(expresion)
                &&(temp.contains("+")
                ||temp.contains("-")
                ||temp.contains("*")
                ||temp.contains("/") )))
        {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 分离计算式中非字符串部分
     * @param expresion 计算式
     * @return 返回计算式中非数字的字符串
     */
    public static String departNotNumber(String expresion){
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(expresion);

        while (matcher.find()) {

            return matcher.group(0);
        }
        return "";
    }
}
