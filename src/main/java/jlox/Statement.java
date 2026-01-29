package jlox;

import java.util.List;

abstract class Statement {
  interface Visitor<R> {
    R visitExpressionStatement(Expression statement);
    R visitPrintStatement(Print statement);
  }

  static class Expression extends Statement {
    Expression(Expression expression) {
    this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitExpressionStatement(this);
    }

    final Expression expression;
  }

  static class Print extends Statement {
    Print(Expression expression) {
    this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitPrintStatement(this);
    }

    final Expression expression;
  }


  abstract <R> R accept(Visitor<R> visitor);
}
