/*     This is a program to count the number of inversions in a permutation of integers.  
 *     Idea: modified MergeSort O(n log n) in complexity.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Inversion
{
    	public static void main (String args[]){
	        Scanner s;
	        int L = 100000;
		int nums[] = new int[L];
	        try {
			s = new Scanner(new File("/Users/zhengnih/Downloads/IntegerArray.txt")).useDelimiter("\\n");
			
			int i=0;
			while(s.hasNext()){
				nums[i] = Integer.parseInt(s.next().trim());
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  // create new input Scanner
	
	        long rslt = countInversions(nums);
	        System.out.println(rslt);
	}  


	public static long countInversions(int nums[]){  
		int mid = nums.length/2, k;
             	long countLeft, countRight, countMerge;
            
		if (nums.length <= 1) 
			return 0;

		int left[] = new int[mid];
		int right[] = new int[nums.length - mid];
		
		for (k = 0; k < mid; k++)
			left[k] = nums[k];
		for (k = 0; k < nums.length - mid; k++)
			right[k] = nums[mid+k];

		countLeft = countInversions (left);
		countRight = countInversions (right);

             	int result[] = new int[nums.length];
             	countMerge = mergeAndCount (left, right, result);
  
             	for (k = 0; k < nums.length; k++)
                 	nums[k] = result[k];

             	return (countLeft + countRight + countMerge);  
        }  
         
        public static long mergeAndCount (int left[], int right[], int result[]){
            	int a = 0, b = 0, i, k=0;
            	long count = 0;

            	while ( ( a < left.length) && (b < right.length) ){
			if ( left[a] <= right[b] )
				result [k] = left[a++];
			else{
				result [k] = right[b++];
				count += left.length - a;
			}
			k++;
                }

		if ( a == left.length )
			for ( i = b; i < right.length; i++)
		   		result [k++] = right[i];
		else
			for ( i = a; i < left.length; i++)
		   	result [k++] = left[i];
		
		return count;
        } 

}
