package lifegame3;

import java.util.List;
public class Cell 
{
	private boolean isAlive = false;
	private boolean isNextGenerationAlive = false;
	
	public Cell(boolean isAlive) 
	{
		this.isAlive = isAlive;
	}
	//返回细胞状态
	public boolean isAlive() 
	{
		return isAlive;
	}
	//细胞下一状态变化
	public void prepareNextGeneration(List<Cell> neighbourCells) 
	{
		this.isNextGenerationAlive = isNextGenerationAlive(neighbourCells);
	}
	//细胞当前状态变化
	public void gotoNextGeneration() 
	{
		this.isAlive = this.isNextGenerationAlive;
	}
    //判断下一状态生命情况
	public boolean isNextGenerationAlive(List<Cell> neighbourCells)
	{
		int aliveNeighbourCellCount = countAliveCells(neighbourCells);
		//当细胞当前状态为生时
		if(this.isAlive) 
		{
			if (aliveNeighbourCellCount != 2 && aliveNeighbourCellCount != 3) //当细胞周围的活细胞数目不为2或3时细胞死亡
			{
				return false;
			} 
			else                                                              //否则细胞仍然存活 
			{
				return true;
			}
		} 
		else 
		{
			if (aliveNeighbourCellCount == 3)                                 //当前细胞死亡时，如果此细胞周围细胞数为3时细胞装换成生 
			{
				return true;
			}
		}
		return false;
	}

	private int countAliveCells(List<Cell> neighbourCells)                     //当前细胞周围活细胞个数
	{
		int count = 0;
		for (Cell cell: neighbourCells)                                        //遍历当前周围细胞 
		{
			if (cell.isAlive()) 
			{
				count++;
			}
		}
		return count;
	}
}
