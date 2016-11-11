# QA_Deliverable4

## IS2545 - DELIVERABLE 4: Performance Testing

## 1. Summary
   In this deliverable, we conducted performance testing on Conway's Game of Life. VisualVM is used as profiler to determine methods that consume most resources, then we applied pinning test to refactor the methods and ensure that the speed of application without modifying behavior 

## 2. Intial Run and Profiler in VisualVM
   From the screenshots in 2.1 and 2.2, we can see that convertToInt() and runContinous() are CPU intensive. Moreover, we can see from the memory situation that char[] takes up lots of memory. Screenshot in 2.3 suggeset that toString() take up most of the CPU when write into backup file
  
  2.1 CPU situation 
  
   
   ![InitailCPU](https://cloud.githubusercontent.com/assets/16599342/20202780/f1efa9b4-a78f-11e6-8a2e-5b15d401fb0d.png)

   2.2 Memory Situation
   
   ![InitialM](https://cloud.githubusercontent.com/assets/16599342/20202783/f1f074de-a78f-11e6-818f-d0d73f99f508.png)

  
   
   2.3 Using Write Function
   
   ![tostring](https://cloud.githubusercontent.com/assets/16599342/20202786/f1fc4ce6-a78f-11e6-8c74-50ba26199e67.png)
  
   
   
3. Methods modified
   
  3.1 MainPanel.convertToInt() 
  
  Original method: 
 
			 private int convertToInt(int x) {
				   int c = 0;
				   String padding = "0";
				   while (c < _r) {
				       String l = new String("0");
				       padding += l;
				       c++;
				   }
				   String n = padding + String.valueOf(x);
				   int q = Integer.parseInt(n);
				   return q;
			 }
    
 Modified method:

			private int convertToInt(int x) {
			   	if (x < 0) throw new NumberFormatException();
				   return x;
			}
  
  The String concatenation used lots of time and space. Once those codes have been deleted, we can observe a great improvement   on both the time complexity and space complexity, as illustrated in the below screenshots 
  
 ![ConvertC](https://cloud.githubusercontent.com/assets/16599342/20202784/f1f52e2a-a78f-11e6-9ca9-efbe0769ef03.png)
 
 ![ConvertM](https://cloud.githubusercontent.com/assets/16599342/20202785/f1f8f74e-a78f-11e6-8971-9b9db154c387.png)
 

  3.2 MainPanel.runContinuous()
  
  Original method:

				 public void runContinuous() {
					_running = true;
					while (_running) {
					    System.out.println("Running...");
					    int origR = _r;
					    try {
						Thread.sleep(20);
					    } catch (InterruptedException iex) {
					    }
					    for (int j = 0; j < _maxCount; j++) {
						_r += (j % _size) % _maxCount;
						_r += _maxCount;
					    }
					    _r = origR;
					    backup();
					    calculateNextIteration();
					}
				    }
    
 Modified Method:

				 public void runContinuous() {
					_running = true;
					while (_running) {
					    System.out.println("Running...");
					    backup();
					    calculateNextIteration();
					}
				    }

 The try catch method and for loop in the inital method is useless, modifying them increase performance, which can be seen from the below screenshots. RunContinous() now is not the most CPU-intensive methods. However, the Cell<init> method in the
 backup() methods, which is called continously by runContinous() method now takes up most CPU. I was trying to modify the backup() method initially by initiating the _backupCells at the same time with _cell. In this way, we only need to assign the current cell status into backupCell as I think this repetitive initiation in the for loop (_backupCells[j][k] = new Cell();) is unecessary as we can overrite the initial status in the backupCell easily. I thought this should work, however, the clear function sometimes cannot clear the whole status in the board sometimes, for which I don't know the reason
 
  
 ![runc](https://cloud.githubusercontent.com/assets/16599342/20202787/f1fd8e12-a78f-11e6-9e67-3e751e71d601.png)
 
 
 ![runm](https://cloud.githubusercontent.com/assets/16599342/20202788/f1fe1684-a78f-11e6-9c04-053b82f11be8.png)


  3.3 MainPanel.iteracteCell()
  
  This method actually didn't cause so much pain, still I think it can be more clear, and there is a slightly improvement in performance
  
  Original method:
  
      private boolean iterateCell(int x, int y) {
		boolean toReturn = false;
		boolean alive = _cells[x][y].getAlive();
		int numNeighbors = getNumNeighbors(x, y);
		if (alive) {
			if (numNeighbors < 2 || numNeighbors > 3) {
				toReturn = false;
			} else {
				toReturn = true;
			}
		} else {
			if (numNeighbors == 3) {
				toReturn = true;
			} else {
				toReturn = false;
			}
		}
		
    	if(alive) {
            if(numNeighbors >= 2  && numNeighbors <= 3){
               toReturn = true;
            }     
    	} else {
            if( numNeighbors == 3) {
                toReturn  = true;
            }
    	}      
	  return toReturn;
     }

  Modified method:
  
      private boolean iterateCell(int x, int y) {
		boolean toReturn = false;
		boolean alive = _cells[x][y].getAlive();
		int numNeighbors = getNumNeighbors(x, y);
		
		if(alive) {
                    if(numNeighbors >= 2  && numNeighbors <= 3){
                        toReturn = true;
                    }     
    	        } else {
                  if( numNeighbors == 3) {
                     toReturn  = true;
                  }
    	       }      
		return toReturn;
	}
  
 ![iteratec](https://cloud.githubusercontent.com/assets/16599342/20202782/f1f00fc6-a78f-11e6-8fb5-014c4e78d6b9.png)
 
 
 ![iteratem](https://cloud.githubusercontent.com/assets/16599342/20202781/f1efdace-a78f-11e6-8f62-80f5aea1f96d.png)
 

  3.4 Cell.toString() MainPanel.toString()
  
  I modified the toString() method in both Cell class and MainPanel, although the main performance issue is attributed to toString() in Cell class, which has large amount of string concantenation. However, if the size of board is extremely large, the toString() method in MainPanel will also influence the performance. I use StringBuilder instead of String in concatenating board status consider the factor that the time complexity of using StringBuilder is O(N) while String is O(N^2). 


  3.4.1 Cell.toString 
  
  Original method:
  
  public String toString() {

		String toReturn = new String("");
		String currentState = getText();
		for (int j = 0; j <_maxSize; j++) {
			toReturn += currentState;
		}
		if (toReturn.substring(0,1).equals("X")) {
			return toReturn.substring(0,1);
		} else {
			return ".";
	    }
		
		String currentState = getText();
		if (currentState.equals("X")){
			return currentState;
		} else {
			return ".";
		}
	}
	
 Modified method:
  
  public String toString() {
		
		String currentState = getText();
		if (currentState.equals("X")){
			return currentState;
		} else {
			return ".";
		}
	}
   
  3.4.2 MainPanel.toString 
  
  Original Method :
  
  public String toString() {

		// Loop through all of the cells, and
		// if they are alive, add an "X" to
		// the String, if dead, a ".".

		String toWrite = "";
		
		for (int j = 0; j < _size; j++) {
			for(int k = 0; k < _size; k++) {
				if (_cells[j][k].getAlive()) {
					toWrite += _cells[j][k].toString();
				} else {
					toWrite += _cells[j][k].toString();
				}

			}
		    toWrite += "\n";
			
		}
		return toWrite;
		
	}

  Modified Method :
  
     public String toString() {
		
		StringBuilder toWrite = new StringBuilder();
		for (int j = 0; j < _size; j++) {
			for(int k = 0; k < _size; k++) {
			    toWrite.append(_cells[j][k].toString());
			}
			toWrite.append("\n");
		} 
		return toWrite.toString();
	}

## 4. Some words on testing
4.1 Test IterateCell() method

I write this method to test the modified iterateCell method which take the current status of cell and its neighbor numbers   as input directly and return the new status
  
   public boolean iterateCellFlag(boolean alive, int numNeighbors) {
    	boolean toReturn = false; 
    	if(alive) {
            if(numNeighbors >= 2  && numNeighbors <= 3){
               toReturn = true;
            }     
    	} else {
            if( numNeighbors == 3) {
                toReturn  = true;
            }
    	}      
    	return toReturn;        
    }
    
  4.2 Test RunContinous() method
Apart from doing manual test on RunContinous to ensure that the output is the same after modifiying the backup method,  I write a method to test whether size, _maxCount will impact _r because the codes I deleted is mainly about the manipulation on size, _r and maxCount. If _r will not be influenced by the size and _maxCount, meaning deleteing them should not influence the results. I write a method called runContinuousFlag() in MainPanel Class which takes size and _maxCount as arguements and returns _r. 
    
   
   public int runContinuousFlag(int size, int _maxCount) { 	   
		System.out.println("Running...");
		int origR = _r;
		try {
			Thread.sleep(1);
		} catch (InterruptedException iex) { }
		for (int j=0; j < _maxCount; j++) {
			_r += (j % _size) % _maxCount;
			_r += _maxCount;
		}

		_r = origR;

		return _r;
	}
    
  


