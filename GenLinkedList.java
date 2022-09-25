//Taha Ahmed
//Txa190016
//CS 3345.001 Greg Ozbrin

import java.util.NoSuchElementException;
public class GenLinkedList<G>{
    
    // add a node with an integer to the front of the list 
    private Node<G> head;
    private Node<G> tail;
    int size = 0;
    public void addFront(G n){//if list is empty
        if(head== null){
            head = new Node<G>(n,null);
            tail = head;
        }
        else{
        head = new Node<G>(n,head);//create new node and set its next pointer to old head
        }
        size++;
    }
    // add a node with an integer to the back of the list
       public void addBack(G n){
        if(tail==null){
            head = new Node<G>(n,null);//if list is empty
            tail = head;
        }
        else{
        tail.next = new Node<G>(n,null);//create new node and set its next pointer to null
        tail=tail.next;//set old tail to the new tail node
        }
        size++;
    }
    //remove a node from the front of the list. throw exception if head = null
    public G removeFront(){
        G olddata;
        if(head==null){//empty list
            throw new NoSuchElementException();
     
        }
       if(head==tail){//only one element
           olddata = head.data;
           head=tail=null;
           
       }
       else{
           olddata = head.data;
           head=head.next;//set heads pointer to new head
       }
       size--;
       return olddata;
    }
    //remove a node from the back of the list
    public G removeEnd(){
        G olddata;
        if(head==null){
            throw new NoSuchElementException();
           
        }
        if(head==tail){//if there is only one element 
            olddata = head.data;
            head=tail=null;
            
        }
        else{
            Node<G> current = head;
            while(current.next!=tail) //loop through linkedlist
            current = current.next;
            
            olddata = tail.data;
            tail = current;//set node before tail as the tail
            current.next = null;
            
                
            
        }
        size--;
        return olddata;
    }
    //Formatting print for linked list
    public String toString(){
        StringBuilder sb = new StringBuilder("[ ");
        Node<G> p = head;
        while(p !=null){
            sb.append(p.data + " ");
            p = p.next;
            
        }
       sb.append("]");    
        
        return new String(sb);
    }
    //Node class
    public static class Node<G>{
        
        Node(G d, Node<G> n)
        {
            data = d;
            next = n;
        }
        
        G data;
        Node<G> next;
        
    }
    //receives a position as a parameter, returns the item at this position,
   // provided it is within the current size
    public Node<G> get(int i) {
    	
    	if(i>size-1 || i< 0)
    		throw new ArrayIndexOutOfBoundsException();
    	Node<G> current = head;
    	//G data;
    	
    	for(int x=0;x<i;x++) {//looping through array
    		current = current.next;
    		
    	}
    	
    	return current;
    		
    }
    //different get function  
    //returns data instead of whole node
    public G get2(int i) {
    	
    	if(i>size-1 || i< 0)
    		throw new ArrayIndexOutOfBoundsException();
    	Node<G> current = head;
    	//G data;
    	
    	for(int x=0;x<i;x++) {//looping through array
    		current = current.next;
    		
    	}
    	
    	return current.data;
    		
    }
    //receives a position and item as parameters, sets the element at this
    //position, provided it is within the current size
    public void set(int i, G d) {
    	if(i>size-1 || i< 0)//checking index
    		throw new ArrayIndexOutOfBoundsException();
    	Node<G> current = head;
    	
    	
    	for(int x=0;x<i;x++) {//getting node at I
    		current = current.next;
    		
    	}
    	current.data = d;//setting that nodes data equal to generic data
    }
    public void swap(int i,int k) {
    
    	if(i>size-1 || i< 0 || k>size-1 || k<0)//checking index
    		throw new ArrayIndexOutOfBoundsException();
    	if (i == k)//if they are the same node
            return;
 
        Node<G> previousx=head; //we need the node before x 
        for(int x=0;x<i-1;x++){
        	previousx = previousx.next;
        }
        Node<G>currentx = head;//get the node at x
        for(int x=0;x<i;x++){
            currentx = currentx.next;
        }
 
        Node<G> previousk = head; 
        Node<G> currentk = head;
        for(int x=0;x<k;x++) {//get the node at k
        	currentk = currentk.next;
        }
        for(int x=0;x<k-1;x++) {//get the node before k
        	previousk = previousk.next;
        }
 
        if (previousx == null) // if the node before x is null means its head
        	head = currentk; //set the head equal to node k
        else 
           	previousx.next = currentk; //otherwise swap
 
      
        if (previousk ==null) // if the node before k is null means its head
        	head = currentx;//set the head equal to node i
        else 
        	previousk.next = currentx;// otherwise swap
 
        //remaining swap with the before and after nodes
        Node<G> temp = currentx.next;
        currentx.next = currentk.next;
        currentk.next = temp;
    
    	    	
    	
    }
    //receives an integer as a parameter, and shifts the list forward or
    //backward this number of nodes, provided it is within the current size
    public void shift(int i) {
    	if(i==0)//done shifting
    		return;
    	if(size==0)//if empty we do nothing
    		return;
    	else if(i >0){//we shifting left
    		for(int x=i;x!=0;x--) {
    		Node<G> temp = head;//keep old head
    		Node<G> end;
    		head=head.next;//move head back once
    		end = get(size-2);//get the node before the tail
    		end.next=temp;//set the node after to be the head
    		temp.next=null;//set new tail
    		}
    	}
    	else {//we shift right
    		for(int x=i;x!= 0;x++) {
    		Node<G> temp = head; // keep the old head
    		Node<G> end = get(size-2); //get the node before the tail
    		head=get(size-1); // set new head to be the end
    		head.next = temp; //then point new head to old head 
    		end.next=null;// the node before the tail becomes new tail by setting next to null
    	}
    	}
    		
    	
    }
    //receives a value of the generic type as a parameter and removes all
   // occurrences of this value from the list.
   public void removeMatching(G d) {
	   
	   Node<G> current = head;
	   Node<G> previous = head;
	   for(int x=0;x<size;x++) {
	   if(current.data == d) { //if our head equals the data, remove head
		   head=current.next;
		   current=head;
		   size--;//decrease our size
	   		}
	   }
	   for(int x=0;x<size;x++) { //looping through the linked list starting at next node after head
		   if(current.data == d) { 
			   previous.next = current.next;//delete the node
			   current = current.next;//get next current
			   size--;  //decrease our size
		   }
		   else {//if data is not equal
			   previous = current;//increment previous node
			   current = current.next;//increment current node
		   	}
		   	
	   }
   }
   
   /* receives an index position and number of elements as parameters, and
        removes elements beginning at the index position for the number of 
        elements specified, provided the index position is within the size
        and together with the number of elements does not exceed the size
   */
   public void erase(int i, int num) {
	   if(i>size-1 || i<0)
		   throw new ArrayIndexOutOfBoundsException();
	   if(i+num-1>size || num<=0)
		   throw new ArrayIndexOutOfBoundsException();
	   if(i ==1  && num == size-1)//delete everything but head
	   {
		   head.next = null;
		   size = 1;
		   return;
		   
	   }
	   if(i==0 && num == size-1)//delete everything but the tail
	   {
		   
		   head = tail;
		   head.next = null;
		   size = 1;
		   return;
	   }
	   if(i==0 && num == size)//delete the whole list
	   {
		   head = tail = null;
		   size = 0;
		   return;
	   }
	   Node<G> current = head;
	   Node<G> location = head;
	   
	   if(i==0)
		   current = head; //if i is 0, just set to head
	   else {
	   for(int x=0;x<i-1;x++) // else get node before i since we are also deleting i
		   current = current.next; 
	   }
	   for(int x=0;x<i+num-1;x++) // get the last element to delete
		   location = location.next;
	   if(current == head) // if current is head, we can delete head till element after
		   head = location.next;
	   current.next = location.next; // delete all elements
	   size = size - num; // resize linked list
   }
   /*
    *receives a generic List (a Java List) and an index position as parameters, 
     and copies each value of the passed list into the current list starting
     at the index position, provided the index position does not exceed the size.
     For example, if list has a,b,c and another list having 1,2,3 is inserted at
     position 2, the list becomes a,b,1,2,3,c
    */
	public void insertList(GenLinkedList<G> list, int i){
		if(i<0 || i>size-1)
			throw new ArrayIndexOutOfBoundsException();
		
		if(list.size==0) {
			return;
		}
			
		Node<G> current = head;
		for(int x =0;x<i-1;x++)  //get position before to add properly
		{
			 current = current.next;
		}
		
		for(int x=0;x<list.size;x++)
		{
			Node<G> temp = new Node<G>(list.get2(x), null);//create new nodes with same data to add to original list
			temp.next = current.next;
			current.next = temp;
			current = current.next;
		}
		
	}
    
    public static void main(String[] args) {
		GenLinkedList<Integer> list = new GenLinkedList<>();
        System.out.println("-------add front and add end-----");
		list.addFront(-1); //addfront
        list.addBack(0);
        list.addBack(1);
        list.addBack(2);
        list.addBack(3); //addend
        System.out.println(list);
        System.out.println("--------remove front and remove end--------");
        
        int f = list.removeFront(); //removefront
        int b = list.removeEnd(); //removeend
        System.out.println(list + " with removed ends and front");
        System.out.println("------get and set-----");
        
        System.out.println(list.get2(0) + "  data at index 0 ");
        System.out.println(list.get2(2) + "  data at index 2"); //get
        System.out.println(list.get2(1) + "  data at index 1");
        list.set(0, 10); //set
        System.out.println(list + " index 0 now has data 10 ");
        GenLinkedList<Integer> test = new GenLinkedList<>();
        System.out.println("------swap and shift-----");
        test.addBack(0);
        test.addBack(1);
        test.addBack(2);
        test.addBack(3);
        test.addBack(4);
        test.addBack(5);
        test.addBack(6);
        test.addBack(7);
        System.out.println(test);
        test.swap(2, 5); //swap
        System.out.println(test + " after swapping 2 and 5");
        test.shift(2); //shift
        System.out.println(test + " after shifting 2");
        test.shift(-2); //shift
        System.out.println(test + " after shifting -2");
        System.out.println("------removing matching-----");
        test.addFront(6);
        System.out.println(test);
        test.removeMatching(6);//removeMatching
        System.out.println(test + " after removing 6");
        System.out.println("------erasing-----");
        test.erase(2,5); //erase
        System.out.println(test + " after removing 5 elements starting at index 2");
        GenLinkedList<Integer> temp = new GenLinkedList<>();
        System.out.println("------Inserting another GenLinkedList-----");
        temp.addFront(11);
        temp.addFront(12);
        temp.addFront(13);
        System.out.println(temp);
        test.insertList(temp, 1); //insertList
        System.out.println(test + " after inserting temp list into test list");
        
	}
    
}

