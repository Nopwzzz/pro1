package lifegame3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CellGrid 
{
	private int rowSize = 10;
	private int columnSize = 10;

	private Cell[][] cellArray;

	public CellGrid(int rowSize, int columnSize) 
	{
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		createCellGrid();
	}
	
	public Cell getCell(int row, int column)       //获取当前细胞 
	{
		return cellArray[row][column];
	}
	
	private void createCellGrid() 
	{
		Random random = new Random();              //产生随机变量
		cellArray = new Cell[getRowSize()][getColumnSize()];
		for (int row = 0; row < getRowSize(); row++)    //初始化地图
		{
			for (int column = 0; column < getColumnSize(); column++)    //随机产生活细胞
			{
				boolean isAlive = random.nextBoolean();   
				cellArray[row][column] = new Cell(isAlive);
			}
		}
	}
    //准备下一状态
	public void prepareNextGeneration()           
	{
		for (int row = 0; row < getRowSize(); row++) 
		{
			for (int column = 0; column < getColumnSize(); column++) 
			{
				getCell(row, column).prepareNextGeneration(getNeightbourCells(row, column));
			}
		}
	}
    //进行到下一状态
	public void gotoNextGeneration() 
	{
		for (int row = 0; row < getRowSize(); row++) 
		{
			for (int column = 0; column < getColumnSize(); column++) 
			{
				getCell(row, column).gotoNextGeneration();
			}
		}
	}
    //收集当前细胞周围的细胞状态
	private List<Cell> getNeightbourCells(int cellRow, int cellColumn) 
	{
		List<Cell> neighbourCells = new ArrayList<Cell>();
		for (int row = cellRow - 1; row <= cellRow + 1; row++) 
		{
			for (int column = cellColumn - 1; column <= cellColumn + 1; column++) 
			{
				if (isInCellGrid(row, column)&& !isSameGridPosition(cellRow, cellColumn, row, column))   //当前细胞与界外细胞不能算入周围细胞
				{
					neighbourCells.add(getCell(row, column));
				}
			}
		}
		return neighbourCells;
	}
    //判断是否为当前细胞
	private boolean isSameGridPosition(int cellRow, int cellColumn, int row, int column)
	{
		return row == cellRow && column == cellColumn;
	}
    //判断是否为界内细胞
	private boolean isInCellGrid(int row, int column)
	{
		return row >= 0 && row < getRowSize() && column >=0 && column < getColumnSize();
	}
	//重置
	public void reset()
	{
		createCellGrid();
	}
    
	public int getRowSize() {
		return rowSize;
	}

	public int getColumnSize() {
		return columnSize;
	}
}