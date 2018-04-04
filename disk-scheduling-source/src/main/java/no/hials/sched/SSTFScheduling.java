package no.hials.sched;

import java.util.*;
import java.util.List;

/**
 * SSTF Disk scheduling algorithm (Shortest Seek Time First) Takes the job which
 * lies closest to the "seeker" Fill in your code in this class!
 *
 * 2016-04-04
 */
public class SSTFScheduling extends DiskSchedulingAlgo {

    private int movement; // Total head movement
    
    @Override
    public int process(List<Integer> refList) {
        
        Collections.sort(refList);
        
        //this.movement = super.getHeadPosition();

        if (refList == null) {
            // No references means "empty sequence" == no movement
            return 0;
        }
        
        int someheadposint = super.getHeadPosition();
        
        for (Integer integer : refList) {
            if ((integer < 0) || (integer > super.getCylinderCount())) 
                return -1;
            
            this.movement = Math.abs(super.getHeadPosition() - refList.get(0));
            super.moveHead(integer);
            /*movement += integer;*/
        }

        // TODO - fill in your algorithm here
        // Detect which position to move to in each step and call
        // moveHead()
        // See FCFSScheduling as an example
        movement = Math.abs(someheadposint - refList.get(refList.get(refList.size()-1)));
        
        return this.movement;
    }

}