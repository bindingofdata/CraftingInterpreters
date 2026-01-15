package jlox;

public class AstPrinter implements Expression.Visitor<String> {
    String print(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expression.Binary expression) {
        return parenthesize(expression.operator.lexeme, expression.left, expression.right);
    }

    @Override
    public String visitGroupingExpr(Expression.Grouping expression) {
        return parenthesize("group", expression.expression);
    }

    @Override
    public String visitLiteralExpr(Expression.Literal expression) {
        if (expression.value == null) return "nil";
        return expression.value.toString();
    } 

    @Override
    public String visitUnaryExpr(Expression.Unary expression) {
        return parenthesize(expression.operator.lexeme, expression.right);
    }

    private String parenthesize(String name, Expression... exprs)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expression expression : exprs) {
            builder.append(" ");
            builder.append(expression.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
}
