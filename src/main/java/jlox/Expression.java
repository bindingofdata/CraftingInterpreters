package jlox;

import java.util.List;

abstract class Expression {
  interface Visitor<R> {
    R visitBinaryExpr(Binary expression);
    R visitGroupingExpr(Grouping expression);
    R visitLiteralExpr(Literal expression);
    R visitUnaryExpr(Unary expression);
  }

  static class Binary extends Expression {
    Binary(Expression left, Token operator, Expression right) {
    this.left = left;
    this.operator = operator;
    this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBinaryExpr(this);
    }

    final Expression left;
    final Token operator;
    final Expression right;
  }

  static class Grouping extends Expression {
    Grouping(Expression expression) {
    this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitGroupingExpr(this);
    }

    final Expression expression;
  }

  static class Literal extends Expression {
    Literal(Object value) {
    this.value = value;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitLiteralExpr(this);
    }

    final Object value;
  }

  static class Unary extends Expression {
    Unary(Token operator, Expression right) {
    this.operator = operator;
    this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitUnaryExpr(this);
    }

    final Token operator;
    final Expression right;
  }


  abstract <R> R accept(Visitor<R> visitor);
}