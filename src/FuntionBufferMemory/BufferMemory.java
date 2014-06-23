package FuntionBufferMemory;


import java.util.Map;
import java.util.Set;



public class BufferMemory implements IBufferMemory{
	private Map<String, MyStack> BufferMemory;
	public Map<String, MyStack> getBufferMemory() {
		return BufferMemory;
	}

	@SuppressWarnings("null")
	public String[] getStackpop(String Key)
	{
		String[] value=null;
		MyStack stack = new MyStack();	
		if(BufferMemory.containsKey(Key))
		{
		stack = BufferMemory.get(Key);
		value[0]=stack.pop();
		value[1]= stack.pop();
		return value;
		}
		else {
			return null;
		}
	}
		

	
	public void setBufferMemory(Map<String, MyStack> bufferMemory) {
		BufferMemory = bufferMemory;
	}
@Override
	public Map<String, MyStack> BufferMemoryMap(Map<String, String> receiveMap,
			Map<String, String> getOutput) 
		{
		// TODO Auto-generated method stub

		//Set<String> receivekeySet = receiveMap.keySet();
		Set<String> getOutkeySet = getOutput.keySet();		
		
		for(String key:getOutkeySet)
		{

			MyStack stack = new MyStack();		

			if(receiveMap.containsKey(key))
			{
				stack.push(getOutput.get(key),0);//将XML压入栈
				stack.push(receiveMap.get(key),1);//将实际接收到的消息压栈
			}
			
			if (BufferMemory.containsKey(key)) {
				BufferMemory.remove(key);
				
			}
			BufferMemory.put(key, stack);		
		}
		

		return BufferMemory;
	}
}


	
	


