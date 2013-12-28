package robotsimulator.robot;

import java.util.ArrayList;

import robotsimulator.Simulator;
import robotsimulator.worldobject.Block;

public class Robot implements Runnable
{
	private Simulator sim;
	private Block b;
	private volatile Thread robotThread;
	private int delay = 50;
	private boolean driveDist, rotateDist = false;
	private int angTotal, angGoal = 0;
	
	//Forward = f, Backwards = b
	//Left = l, Right = r
	//Strafe Left = h, Strafe Right = i (Hook and slIce)
	//Stop = s
	private char status = 's';
	
	private ArrayList<SonarSensor> sonars = new ArrayList<SonarSensor>();
	
	public Robot(Simulator s)
	{
		sim = s;
		int centerX = 100;
		int centerY = 100;
		
		//Degrees, 0 is direct north, counterclockwise
		int angle = 0;

		b = new Block(20, 30, centerX, centerY, angle, sim);
		
		int sonarLen = 750;

		//THESE SHOULD BE ADDED IN CLOCKWISE STARTING FROM FRONT-LEFT
		sonars.add(new SonarSensor(sim, "Front-Left", getX0(), getY0(), sonarLen, 315, 25));
		sonars.add(new SonarSensor(sim, "Front", getCenterFrontX(), getCenterFrontY(), sonarLen, 0, 25));
		sonars.add(new SonarSensor(sim, "Front-Right", getX1(), getY1(), sonarLen, 45, 25));
		sonars.add(new SonarSensor(sim, "Right", getCenterRightX(), getCenterRightY(), sonarLen, 90, 25));
		sonars.add(new SonarSensor(sim, "Rear", getCenterRearX(), getCenterRearY(), sonarLen, 180, 25));
		sonars.add(new SonarSensor(sim, "Left", getCenterLeftX(), getCenterLeftY(), sonarLen, 270, 25));
	}

	public Block getBlock() 
	{
		return b;
	}
	
	public ArrayList<SonarSensor> getSonarSensors() 
	{
		return sonars;
	}
	
	public SonarSensor getSonarSensor(int num) 
	{
		return sonars.get(num);
	}
	
	public double getCenterX() 
	{
		return b.getCenterX();
	}
	
	public double getCenterY() 
	{
		return b.getCenterY();
	}
	
	public double getX0()
	{
		return b.getX0();
	}
	
	public double getY0()
	{
		return b.getY0();
	}
	
	public double getX1()
	{
		return b.getX1();
	}
	
	public double getY1()
	{
		return b.getY1();
	}
	
	public double getX2()
	{
		return b.getX2();
	}
	
	public double getY2()
	{
		return b.getY2();
	}
	
	public double getX3()
	{
		return b.getX3();
	}
	
	public double getY3()
	{
		return b.getY3();
	}
	
	public double getCenterFrontX()
	{
		return (getX0() + getX1()) / 2;
	}
	
	public double getCenterFrontY()
	{
		return (getY0() + getY1()) / 2;
	}
	
	public double getCenterLeftX()
	{
		return (getX0() + getX2()) / 2;
	}
	
	public double getCenterLeftY()
	{
		return (getY0() + getY2()) / 2;
	}
	
	public double getCenterRightX()
	{
		return (getX1() + getX3()) / 2;
	}
	
	public double getCenterRightY()
	{
		return (getY1() + getY3()) / 2;
	}
	
	public double getCenterRearX()
	{
		return (getX2() + getX3()) / 2;
	}
	
	public double getCenterRearY()
	{
		return (getY2() + getY3()) / 2;
	}
	
	public char getStatus()
	{
		return status;
	}

	public void drive(char d)
	{
		if(robotThread == null)
		{
			status = d;
			robotThread = new Thread(this);
			robotThread.start();
		}
	}
	
	public void turn(char d)
	{
		if(robotThread == null)
		{
			status = d;
			robotThread = new Thread(this);
			robotThread.start();
		}
	}
	
	public void turn(int angle)
	{
		if(robotThread == null && angle != 0)
		{
			if(angle < 0)
			{
				status = 'l';
			}
			else if(angle > 0)
			{
				status = 'r';
			}
			
			rotateDist = true;
			angGoal = angle;
			angTotal = 0;
			robotThread = new Thread(this);
			robotThread.start();
		}
	}
	
	public void stop()
	{
		status = 's';
		robotThread = null;
	}

	public void run() 
	{
		Thread me = Thread.currentThread();
		while(robotThread == me)
		{
			long beforeTime, timeDiff, sleep;
	        beforeTime = System.currentTimeMillis();
	        
	        double oldX, oldY, oldA;
			
			switch(status)
			{
				case 'f':
					oldX = b.getCenterX();
					oldY = b.getCenterY();
					b.translate(1);
					for(SonarSensor s : sonars)
					{
						s.translate(b.getCenterX() - oldX, b.getCenterY() - oldY);
					}
				break;
				case 'b':
					oldX = b.getCenterX();
					oldY = b.getCenterY();
					b.translate(-1);
					for(SonarSensor s : sonars)
					{
						s.translate(b.getCenterX() - oldX, b.getCenterY() - oldY);
					}
				break;
				case 'l':
					oldA = b.getDegAngle();
					b.rotate(-1);
					for(SonarSensor s : sonars)
					{
						s.rotate(b.getDegAngle() - oldA);
					}
					if(rotateDist)
					{
						double curAngle = b.getDegAngle();
						if(curAngle > oldA)
						{
							angTotal += curAngle - 360 - oldA;
						}
						else
						{
							angTotal += curAngle - oldA;
						}

						if(angGoal >= angTotal)
						{
							rotateDist = false;
							angTotal = 0;
							angGoal = 0;
							stop();
						}
					}
				break;
				case 'r':
					oldA = b.getDegAngle();
					b.rotate(1);
					for(SonarSensor s : sonars)
					{
						s.rotate(b.getDegAngle() - oldA);					
					}
					if(rotateDist)
					{
						double curAngle = b.getDegAngle();
						if(curAngle < oldA)
						{
							angTotal += curAngle + 360 - oldA;
						}
						else
						{
							angTotal += curAngle - oldA;
						}

						if(angGoal <= angTotal)
						{
							rotateDist = false;
							angTotal = 0;
							angGoal = 0;
							stop();
						}
					}
				break;
			}
			
			timeDiff = System.currentTimeMillis() - beforeTime;
	        sleep = delay - timeDiff;
	        
	        if(sleep == 0) sleep = 2;

	        try 
			{
				Thread.sleep(sleep);
			} 
			catch (InterruptedException e) 
			{
			}
			
            beforeTime = System.currentTimeMillis();
		}
	}

	public double getAngle() 
	{
		return b.getDegAngle();
	}
}
