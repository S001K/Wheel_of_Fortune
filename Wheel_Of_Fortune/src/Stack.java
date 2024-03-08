
public class Stack {
	private int top;
	private Object[] elements;
	
	Stack (int capacity){
		elements = new Object[capacity];
		top=-1;
	}
	
	public void push(Object data) {
		top++;
		elements[top] = data;
		}
	
	public Object pop()
	{
	Object data = elements[top];
	elements[top] = null;
	top --;
	return data;
	}
	
	Object peek()
	{
	Object data = elements[top];
	return data;
	}
	
	public int size()
	{
	return top+1;
	}
	
	public boolean isFull()
	{
	if (elements.length == (top + 1))
	return true;
	else
	return false;
	}
	
	public boolean isEmpty()
	{
	if (top == -1)
	return true;
	else
	return false;
	}
	
	public int getTop() {
		return top;
	}
	
	
}
