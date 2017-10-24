package lifegame3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

public class Lifegame 
{
	private static final int ROW_SIZE = 20;               
	private static final int COLUMN_SIZE = 20;
	private static final int SLEEP_MSEC = 100;
	private CellGrid cellGrid;
	private LifegameView view;
	private ScheduledExecutorService scheduler;
    //初始化地图
	public Lifegame() 
	{
		cellGrid = new CellGrid(ROW_SIZE, COLUMN_SIZE);
		view = new LifegameView(this);
	}

	public void start() 
	{
		Runnable gotoNextGenerationTask = new Runnable()   //创建线程
		{
			@Override
			public void run() 
			{
				cellGrid.prepareNextGeneration();
				cellGrid.gotoNextGeneration();
				view.update();
			}
		};
		scheduler = Executors.newSingleThreadScheduledExecutor();     
		scheduler.scheduleAtFixedRate(gotoNextGenerationTask, 0, SLEEP_MSEC,TimeUnit.MILLISECONDS); //按指定频率间隔执行某个任务
	}

	public void stop() 
	{
		scheduler.shutdown();
		scheduler = null;
		
	}

	public void reset() 
	{
		stop();
		cellGrid.reset();
		start();
	}

	public CellGrid getCellGrid() 
	{
		return cellGrid;
	}
	
	public static void main(String[] args) 
	{
	
	  Lifegame game = new Lifegame();
		game.start();
		
	}
}