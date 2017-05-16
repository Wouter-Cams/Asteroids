package asteroids.model.program.statements;

import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class IfStatement extends Statement{
	
	public IfStatement(Expression<Boolean> condition, 
			Statement ifStatement, Statement elseStatement, SourceLocation sourcelocation) {
		super(sourcelocation);
		this.condition = condition;
		this.ifStatement = ifStatement;
		this.elseStatement = elseStatement;
	}
	
	private Expression<Boolean> condition;
	private Statement ifStatement;
	private Statement elseStatement;
	
	public void execute(){
		if (condition.evaluate() == true)
			ifStatement.execute();
		else if (elseStatement != null)
			elseStatement.execute();
		return;
	}
}