public class Student{
	private String studentID;
	private String name;
	private int quiz1;
	private int quiz2;
	private int midterm;
	private int finalexam;
	private double avg;
	private String grade;
	
	public Student ()
	{
	}
	
	public Student (String id, String n, int q1, int q2, int m, int f)
	{
		
	}
	public Student (String id, String n, int q1, int q2, int m, int f, double a, String letterGrade)
	{
		
	}
	
	public void set(String id, String n, int q1, int q2, int m, int f, double a, String g)
	{
	}
	
	public void calcAvg()
	{
	}
	
	public void calcGrade()
	{
	}
	
	public String getID()
	{
		return null;
	}
	
	public String getName()
	{
		return null;
	}
	
	public int getQuiz1()
	{
		return 0;
	}
	
	public int getQuiz2()
	{
		return 0;
	}
	
	public int getMidterm()
	{
		return 0;
	}
	
	public int getFinalexam()
	{
		return finalexam;
	}
	
	
	public double getAvg()
	{
		calcAvg();
		return 0.0;
	}
	
	public String getGrade()
	{
		calcGrade();
		return "na";
	}
	
}

