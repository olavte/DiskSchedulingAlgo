package no.hials.sched;

import java.util.*;
import java.util.List;

/**
 * SSTF Disk scheduling algorithm (Shortest Seek Time First) Takes the job which
 * lies closest to the "seeker" Fill in your code in this class!
 * <p>
 * 2016-04-04
 */
public class SSTFScheduling extends DiskSchedulingAlgo {

    private int movement = 0; // Total head movement
    private ArrayList<Integer> takenDisk = new ArrayList<>();
    private int closestNumberDifference;
    private int prevClosestNumber = 0;

    @Override
    public int process(List<Integer> refList) {

        this.movement = 0;
        takenDisk.clear();

        if (refList == null ||  refList.isEmpty()) {
            // No references means "empty sequence" == no movement
            return 0;
        }

        for (Integer integer : refList) {
            if ((integer < 0) || (integer >= super.getCylinderCount()))
                return -1;

            //Get closest number
            int closestNumber = getCylinderCount();
            int closestNumberIndex = 0;
            closestNumberDifference = getCylinderCount();
            //System.out.println(closestNumber);
            //System.out.println(super.getHeadPosition());
            //System.out.println(refList.get(0));


            for(int i = 0; i <= refList.size()-1; i++) {
                if ((isIndexFree(i))) {
                    //System.out.println("current Number: " + refList.get(i));
                    //System.out.println("The closest: " + closestNumber);
                    //System.out.println("The difference: " + closestNumberDifference);
                    if (Math.abs(super.getHeadPosition() - refList.get(i)) < closestNumberDifference) {
                        closestNumber = refList.get(i);
                        closestNumberIndex = i;
                        //System.out.println(closestNumber);
                        closestNumberDifference = Math.abs(super.getHeadPosition() - refList.get(closestNumberIndex));
                    }
                }
            }
            takenDisk.add(closestNumberIndex);
            //System.out.println("Choosen number: " + closestNumber);

            //System.out.println("prevClosest: " + prevClosestNumber);

            super.moveHead(closestNumber);
            /*movement += integer;*/
            this.movement += closestNumberDifference;
            prevClosestNumber = closestNumber;

            //System.out.println("movement: " + movement);
        }

        // TODO - fill in your algorithm here
        // Detect which position to move to in each step and call
        // moveHead()
        // See FCFSScheduling as an example

        return this.movement;
    }

    private boolean isIndexFree(int i) {
        boolean result = true;
        for(Integer j : takenDisk) {
            if(i == j) {
                result = false;
            }
        }
        return result;
    }

}