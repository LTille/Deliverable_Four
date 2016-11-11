# QA_Deliverable4

IS2545 - DELIVERABLE 4: Performance Testing

1. Summary

 

2. Intial Run and Profiler in VisualVM
    
   2.1 CPU situation 
   ![InitailCPU](https://cloud.githubusercontent.com/assets/16599342/20202780/f1efa9b4-a78f-11e6-8a2e-5b15d401fb0d.png)

   2.2 Memory Situation
   ![InitialM](https://cloud.githubusercontent.com/assets/16599342/20202783/f1f074de-a78f-11e6-818f-d0d73f99f508.png)

   From the above screenshots, we can see that convertToInt() and runContinous take up most of the CPU. Moreover
   
   2.3 Using Write Function
   ![tostring](https://cloud.githubusercontent.com/assets/16599342/20202786/f1fc4ce6-a78f-11e6-8c74-50ba26199e67.png)
  
    We can see that toString() take up most of the CPU when write into backup file
   
3. Methods modified
   
  3.1 MainPanel.convertToInt() 
 ![ConvertC](https://cloud.githubusercontent.com/assets/16599342/20202784/f1f52e2a-a78f-11e6-9ca9-efbe0769ef03.png)
 ![ConvertM](https://cloud.githubusercontent.com/assets/16599342/20202785/f1f8f74e-a78f-11e6-8971-9b9db154c387.png)
 
 

  3.2 MainPanel.runContinuous()
 ![runc](https://cloud.githubusercontent.com/assets/16599342/20202787/f1fd8e12-a78f-11e6-9e67-3e751e71d601.png)
 ![runm](https://cloud.githubusercontent.com/assets/16599342/20202788/f1fe1684-a78f-11e6-9c04-053b82f11be8.png)

 The MainPanel.runContinuous() method also using CPU intensivly, covering 38% of total CPU usage time, therefore need to be refactored.

  3.3 MainPanel.iteracteCell()
 ![iteratec](https://cloud.githubusercontent.com/assets/16599342/20202782/f1f00fc6-a78f-11e6-8fb5-014c4e78d6b9.png)
 ![iteratem](https://cloud.githubusercontent.com/assets/16599342/20202781/f1efdace-a78f-11e6-8f62-80f5aea1f96d.png)

  3.4 Cell.toString() MainPanel.toString()
  


