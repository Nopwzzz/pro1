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
	//����ϸ��״̬
	public boolean isAlive() 
	{
		return isAlive;
	}
	//ϸ����һ״̬�仯
	public void prepareNextGeneration(List<Cell> neighbourCells) 
	{
		this.isNextGenerationAlive = isNextGenerationAlive(neighbourCells);
	}
	//ϸ����ǰ״̬�仯
	public void gotoNextGeneration() 
	{
		this.isAlive = this.isNextGenerationAlive;
	}
    //�ж���һ״̬�������
	public boolean isNextGenerationAlive(List<Cell> neighbourCells)
	{
		int aliveNeighbourCellCount = countAliveCells(neighbourCells);
		//��ϸ����ǰ״̬Ϊ��ʱ
		if(this.isAlive) 
		{
			if (aliveNeighbourCellCount != 2 && aliveNeighbourCellCount != 3) //��ϸ����Χ�Ļ�ϸ����Ŀ��Ϊ2��3ʱϸ������
			{
				return false;
			} 
			else                                                              //����ϸ����Ȼ��� 
			{
				return true;
			}
		} 
		else 
		{
			if (aliveNeighbourCellCount == 3)                                 //��ǰϸ������ʱ�������ϸ����Χϸ����Ϊ3ʱϸ��װ������ 
			{
				return true;
			}
		}
		return false;
	}

	private int countAliveCells(List<Cell> neighbourCells)                     //��ǰϸ����Χ��ϸ������
	{
		int count = 0;
		for (Cell cell: neighbourCells)                                        //������ǰ��Χϸ�� 
		{
			if (cell.isAlive()) 
			{
				count++;
			}
		}
		return count;
	}
}
