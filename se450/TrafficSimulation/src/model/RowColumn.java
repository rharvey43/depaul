package model;

public final class RowColumn
{
	final private int row;
	final private int column;

	public RowColumn(int nRow, int nColumn)
	{
		row    = nRow;
		column = nColumn;
	}
	
	public final int getRow()
	{
		return row;
	}
	
	public final int getColumn()
	{
		return column;
	}
}
      