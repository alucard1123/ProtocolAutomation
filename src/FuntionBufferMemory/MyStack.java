package FuntionBufferMemory;

public class MyStack {
	String[] array;//��String���鱣������
	public int s_size;//�����ջ���
	public MyStack(int i)//����һ���������Ĺ�����
	{
		array = new String[i];//���嶯̬���鳤��
		s_size = i;//����������
	}
	public MyStack()//Ĭ�Ϲ�����������50��Ԫ��
	{this(2);}
	public void push(String s,int j){//ѹջ
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
			String str = array[this.s_size-1];//ջ����Ԫ��
			array[this.s_size-1]="";//ȡ��Ԫ�ظ�λ���ÿգ�
			s_size--;//ջ�Ĵ�С��1
			return str;//����ջ��Ԫ��
			
		}
		else {
			System.out.println("This stack is empty!");
			return "";//��ջΪ��ʱ����ʾ�������ؿ�
		}
		
		
	}
	public boolean isEmpty(){//�ж�ջ�Ƿ�Ϊ��
		
		return this.s_size==0;
		
	}
	public String top()//��ջ��ȡֵ����pop()����һ��
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
			return "";//��ջΪ��ʱ����ʾ�������ؿ�
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
