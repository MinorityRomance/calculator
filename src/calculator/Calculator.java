package calculator;

import exception.ResultOutOfBoundException;

/**
 * 计算器类
 */
public class Calculator {
    private int a;//第一个运算数
    private int b;//第二个运算数
    private int result;//结果

    /**
     * 初始化
     */
    public Calculator(){}

    /**
     * 创建一个计算实例
     * @param a 第一个运算数
     * @param b 第二个运算数
     */
    public Calculator(int a,int b){
        this.setA(a);
        this.setB(b);
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
     * 返回结果
     * @return 结果
     */
    public int getResult() {
        return this.result;
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
     * 改变结果的值
     * @param result 结果
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 计算两数之和
     * @return  两数之和
     */
    public int sum(){
        try {
            this.result = this.a + this.b ;
            throw new ResultOutOfBoundException(a,b);
        }
        finally {
            return result;
        }
    }

    /**
     * 返回两数之差
     * @return 两数之差
     */
    public int sub(){
        try{
        this.result = this.a - this.b;
        throw new ResultOutOfBoundException(a,b);
        }
        finally {
            return this.result ;
        }
    }

    /**
     * 返回两数之商
     * @return 两数之商
     */
    //可能会出现除数为0的异常
    public int div(){
        try {
            this.result = this.a / this.b;
            throw new ResultOutOfBoundException(a,b);
        }
        finally{
            return this.result;
        }
    }

    /**
     * 返回两数之积
     * @return 两数之积
     */
    public int mul(){
        try {
            this.result = this.a * this.b;
            throw new ResultOutOfBoundException(a,b);
        }
        finally {
            return this.result;
        }
   }
}
