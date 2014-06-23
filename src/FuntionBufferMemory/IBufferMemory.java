package FuntionBufferMemory;


import java.util.Map;


public interface IBufferMemory {

	public Map<String, MyStack> BufferMemoryMap(Map<String, String> receiveMap,
			Map<String, String> getOutput);

	public Map<String, MyStack> getBufferMemory();

	void setBufferMemory(Map<String, MyStack> bufferMemory);
	
}
