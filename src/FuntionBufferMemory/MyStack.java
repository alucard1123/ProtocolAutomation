package FuntionBufferMemory;

public class MyStack {
	String[] array;//用String数组保存数据
	public int s_size;//定义堆栈宽度
	public MyStack(int i)//定义一个带参数的构造器
	{
		array = new String[i];//定义动态数组长度
		s_size = i;//定义数组宽度
	}
	public MyStack()//默认构造器可容纳50个元素
	{this(2);}
	public void push(String s,int j){//压栈
		System.out.println("this.s_size::"+this.s_size);
		if(j<this.s_size)
		{
			array[j]=s;
			//this.s_size++;
		}
	}
	public String pop()
	{
		if(this.s_size!=0)
		{
			String str = array[this.s_size-1];//栈顶的元素
			array[this.s_size-1]="";//取完元素该位置置空；
			s_size--;//栈的大小减1
			return str;//返回栈顶元素
			
		}
		else {
			System.out.println("This stack is empty!");
			return "";//当栈为空时，提示，并返回空
		}
		
		
	}
	public boolean isEmpty(){//判断栈是否为空
		
		return this.s_size==0;
		
	}
	public String top()//从栈顶取值，和pop()方法一样
	{
		if(!isEmpty())
		{
			String str=array[this.s_size-1];
			array[this.s_size-1]="";
			this.s_size--;
			return str;
			
			
		}
		else {
			System.out.println("This stack is empty!");
			return "";//当栈为空时，提示，并返回空
		}
		
	}
	public void printAll()
	{
		
		if(!isEmpty())
		{
			System.out.print("Stack################################");
			for(int i=this.s_size-1;i>=0;i--)
			{
				
				System.out.print("i::"+i+",array[i]::"+array[i]+";");
			}
		}
	}
	
}
