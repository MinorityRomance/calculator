package exception;

public class ResultOutOfBoundException extends Exception{

    private int a;//第一个运算数
    private int b;//第二个运算数

    /**
     * 初始化
     */
    public ResultOutOfBoundException(){}

    /**
     * 创建一个异常实例
     * @param a 第一个运算数
     * @param b 第二个运算数
     */
    public ResultOutOfBoundException(int a ,int b){
        super();
        this.setA(a);
        this.setB(b);
        judge();
    }

    /**
     * 返回第一个运算数
     * @return 第一个运算数
     */
    public int getA() {
        return this.a;
    }

    /**
     * 返回第二个运算数
     * @return 第二个运算数
     */
    public int getB() {
        return this.b;
    }

    /**
     * 改变第一个运算数的值
     * @param a 第一个运算数的值
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * 改变第二个运算数
     * @param b 第二个运算数
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * 判断运算数结果是否溢出
     */
    public String judge()throws ArithmeticException{
        if(this.a + this.b > Double.MAX_VALUE || this.a + this.b < Double.MIN_VALUE
        || this.a - this.b > Double.MAX_VALUE || this.a - this.b < Double.MIN_VALUE
        || this.a * this.b > Double.MAX_VALUE || this.a * this.b < Double.MIN_VALUE
        || this.a / this.b > Double.MAX_VALUE || this.a / this.b < Double.MIN_VALUE) {

            return "结果溢出";
        }
        return "";
    }
}
