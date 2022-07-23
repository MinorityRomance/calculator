package exception;

/**
 * 计算式异常
 */
public class ExpressionException extends Exception {
    private String expresion;//计算式

    /**
     * 创建一个异常默认值
     */
    public ExpressionException(){}

    /**
     * 创建了一个异常实例
     * @param expresion 计算式
     */
    public ExpressionException(String expresion){
        this.setExpresion(expresion);
    }

    /**
     * 返回计算式
     * @return 计算式
     */
    public String getExpresion() {
        return this.expresion;
    }

    /**
     * 改变计算式
     * @param expresion 计算式
     */
    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
}
