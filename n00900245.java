//This program uses the huffman tree model to compress data
//Camron Conway

import java.io.*;
import java.util.*;



class n00900245
{
   public static void main(String[] args) throws FileNotFoundException
   {
      int counterA = 0;
      int counterB = 0;
      int counterC = 0;
      int counterD = 0;
      int counterE = 0;
      int counterF = 0;
      int counterG = 0;
   
      //read file from command line and put into string
      File file = new File(args[0]);
      Scanner s = new Scanner(file);
      String tester = "";
      while(s.hasNext())
      {
         tester = tester + s.next();
      }
      
      
         
      //Read file, catch characters A-G and count characters
      char[] tokens = new char[tester.length()];
      int index = 0;
      for(int i = 0; i < tester.length(); i++)
      {
         char p = tester.charAt(i);
         switch(p){
            case 'A':
               tokens[index] = p;
               counterA++;
               index++;
               break;
            case 'B':
               tokens[index] = p;
               counterB++;
               index++;
               break;
            case 'C':
               tokens[index] = p;
               counterC++;
               index++;
               break;
            case 'D':
               tokens[index] = p;
               counterD++;
               index++;
               break;
            case 'E':
               tokens[index] = p;
               counterE++;
               index++;
               break;
            case 'F':
               tokens[index] = p;
               counterF++;
               index++;
               break;
            case 'G':
               tokens[index] = p;
               counterG++;
               index++;
               break;
            default:
               break;
         }//switch
      }//for
      
      
      
      //Initialize Priority Queue
      leafPQ myLeafs = new leafPQ();
      
      //Create leafs
      leaf leafA = new leaf('A', counterA);
      leaf leafB = new leaf('B', counterB);
      leaf leafC = new leaf('C', counterC);
      leaf leafD = new leaf('D', counterD);
      leaf leafE = new leaf('E', counterE);
      leaf leafF = new leaf('F', counterF);
      leaf leafG = new leaf('G', counterG);
      
      //Add to PQ
      myLeafs.addLeaf(leafA);
      myLeafs.addLeaf(leafB);
      myLeafs.addLeaf(leafC);
      myLeafs.addLeaf(leafD);
      myLeafs.addLeaf(leafE);
      myLeafs.addLeaf(leafF);
      myLeafs.addLeaf(leafG);
      
      
      
     
      //Create Tree
      hTree myTree = new hTree(myLeafs);
      
      //Create encoded String
      String encodedFile = encoder(myTree, myLeafs.getFirst(), tokens, counterA, counterB, counterC, counterD, counterE, counterF, counterG);
      
      
      //Menu
      String menuInput = "";
      
      Scanner sc = new Scanner(System.in);
      int exitCode = 0;
      
      while(exitCode == 0)
      {
         System.out.println("What would you like to do?");
         System.out.println(" a: Display Tree |b: Display Table |c: Display Encoded File |d: Display deCoded File |e: Exit");
         menuInput = sc.nextLine();
      
         switch(menuInput)
         {
            case "a":
            //Display Tree
               System.out.println();
               myTree.displayTree(myLeafs.getFirst());
               break;
            case "b":
            //Display Table
               printTable(myTree, myLeafs.getFirst(), counterA, counterB, counterC, counterD, counterE, counterF, counterG);
               break;
            case "c":
             //Display encoded String
               System.out.println("----------------------Encode------------------------------");
               printCode(encodedFile);
               break;
            case "d":
            //Display Decoded String
               System.out.println("\n---------Decoded------------------------------");
               decoder(myTree, myLeafs.getFirst(), encodedFile);
               break;
            case "e":
            //Exit
               System.out.println("Exitting");
               exitCode = 1;
               break;
            default:
               System.out.println("Input not valid");
            
               
         
         }//switch
      }//while
      
   }//main
   
   public static void printCode(String codedString)
   {
      //print code in format
      int j = 0;
      int k = 0;
      for(int i = 0; i < codedString.length(); i++)
      {
         if(j == 8)
         {
            System.out.print(" ");
            j = 0;
            k++;
         }
         if(k == 3)
         {
            System.out.println();
            k = 0;
         }
         System.out.print(codedString.charAt(i));
         j++;
      }//for
   System.out.println();
   }//printCode
   
   public static String encoder(hTree myTree, leaf root, char[] tokens, int cA, int cB, int cC, int cD, int cE, int cF, int cG)
   {
      leaf leafA = myTree.findLeaf(root, cA, 'A');
      leaf leafB = myTree.findLeaf(root, cB, 'B');
      leaf leafC = myTree.findLeaf(root, cC, 'C');
      leaf leafD = myTree.findLeaf(root, cD, 'D');
      leaf leafE = myTree.findLeaf(root, cE, 'E');
      leaf leafF = myTree.findLeaf(root, cF, 'F');
      leaf leafG = myTree.findLeaf(root, cG, 'G');
      
      
      String binCode = "";
      //Create code string
      for(int i = 0; i < tokens.length; i++)
      {
         switch(tokens[i])
         {
            case 'A':
               binCode= binCode + myTree.getCode(leafA);
               break;
            case 'B':
               binCode= binCode + myTree.getCode(leafB);
               break;
            case 'C':
               binCode= binCode + myTree.getCode(leafC);
               break;
            case 'D':
               binCode= binCode + myTree.getCode(leafD);
               break;
            case 'E':
               binCode= binCode + myTree.getCode(leafE);
               break;
            case 'F':
               binCode= binCode + myTree.getCode(leafF);
               break;
            case 'G':
               binCode= binCode + myTree.getCode(leafG);
               break;
         }//switch
      }//for
      
      return binCode;
      
   }//encoder
   
   
   public static void decoder(hTree nTree, leaf root, String code)
   {
      leaf temp = root;
      int index = 0;
      while(index < code.length()+1)
      {
         //check to see if at bottom
         if(temp.leftChild == null && temp.rightChild == null)//if at bottom
         {
            System.out.print(temp.n);
            temp = root;
         }
         else //if not at bottom
         {
            if(index < code.length())
            {
               if(code.charAt(index) == '0')
               {
                  temp = temp.leftChild;
                  index++;
               }
               else
               {
                  temp = temp.rightChild;
                  index++;
               }
            }
            else
               index++;
         }
      }//while
      System.out.println();
   }//decoder
   
   
   public static void printTable(hTree myTree, leaf root, int cA, int cB, int cC, int cD, int cE, int cF, int cG )
   {
      //find the leafs
      leaf leafA = myTree.findLeaf(root, cA, 'A');
      leaf leafB = myTree.findLeaf(root, cB, 'B');
      leaf leafC = myTree.findLeaf(root, cC, 'C');
      leaf leafD = myTree.findLeaf(root, cD, 'D');
      leaf leafE = myTree.findLeaf(root, cE, 'E');
      leaf leafF = myTree.findLeaf(root, cF, 'F');
      leaf leafG = myTree.findLeaf(root, cG, 'G');
   
      //Create table
      System.out.println(
         "---------------------TABLE---------------------------------------------");
      System.out.println("| Char | Freq | Data |  Code  |");
      System.out.format("|   %s  | %2d   | %3d  | %6s | \n", leafA.n, leafA.data, leafA.data*8, myTree.getCode(leafA));
      System.out.format("|   %s  | %2d   | %3d  | %6s | \n", leafB.n, leafB.data, leafB.data*8, myTree.getCode(leafB));
      System.out.format("|   %s  | %2d   | %3d  | %6s | \n", leafC.n, leafC.data, leafC.data*8, myTree.getCode(leafC));
      System.out.format("|   %s  | %2d   | %3d  | %6s | \n", leafD.n, leafD.data, leafD.data*8, myTree.getCode(leafD));
      System.out.format("|   %s  | %2d   | %3d  | %6s | \n", leafE.n, leafE.data, leafE.data*8, myTree.getCode(leafE));
      System.out.format("|   %s  | %2d   | %3d  | %6s | \n", leafF.n, leafF.data, leafF.data*8, myTree.getCode(leafF));
      System.out.format("|   %s  | %2d   | %3d  | %6s | \n", leafG.n, leafG.data, leafG.data*8, myTree.getCode(leafG));
      System.out.println(
         "-----------------------------------------------------------------------");
   
   }//printTable
}//noo900245

class leaf
{
   char n;
   int data;
   
   //for the priority  queue
   leaf next;
   leaf previous;
   //for the tree system
   leaf leftChild;
   leaf rightChild;
   leaf parent;
   String parentType;
   
   public leaf(char name, int value)
   {
      n = name;
      data = value;
      next = null;
      previous = null;
      leftChild = null;
      rightChild = null;
   }//constructor
   
   public void displayLeaf()
   {
      System.out.print(""+n+data+"");
   }
   
}//leaf
   
class leafPQ
{
   private leaf first;
   private leaf previous;

   public leafPQ()
   {
      first = null;
   }//constructor

   public void addLeaf(leaf nLeaf)
   {
      if(first == null)//initialize
      {
         first = nLeaf;
      }
      else
      {
         first.previous = nLeaf;
         nLeaf.next = first;
         first = nLeaf;
      }
   }//addLeaf

   public leaf findMin()
   {
      leaf min = first;
      leaf current = first;
      
      while(current.next != null)
      {
         current = current.next;
         if(current.data < min.data)
            min = current;
      }//while
      
      
      return min;
   }//findMin
   
   public leaf removeMin()
   {
      leaf temp = findMin();
      
      if(temp.previous != null)
         temp.previous.next = temp.next;
      if(temp.next != null)
         temp.next.previous = temp.previous;
      if(temp == first)
      {
         first = temp.next;
      }
   
      return temp;
   }//removeMin
   
   public void printLeafs()
   {
      leaf temp = first;
      while(temp != null)
      {
         temp.displayLeaf();
         temp = temp.next;
      }//while
   }//printLeafs
   
   public leaf getFirst()
   {
      return first;
   }

}//leafPQ

class hTree
{
   leafPQ leafCollection;
   public hTree(leafPQ nPQ)
   {
      leafCollection = nPQ;
      
      while(leafCollection.getFirst().next != null)
      {
         makeBranch();
      }
   }
   
   public void makeBranch()
   {
      leaf branch1 = leafCollection.removeMin();
      leaf branch2 = leafCollection.removeMin();
   
      leaf root = new leaf('-', branch1.data+branch2.data);
      //decide left and right children of root
      if(branch1.data < branch2.data)
      {
         //branch 1 is left child
         root.leftChild = branch1;
         branch1.parent = root;
         branch1.parentType = "0";
         //branch 2 is right child
         root.rightChild = branch2;
         branch2.parent = root;
         branch2.parentType = "1";
      }//if
      else
      {
         root.leftChild = branch2;
         branch2.parent = root;
         branch2.parentType = "0";
         root.rightChild = branch1;
         branch1.parent = root;
         branch1.parentType = "1";
      }//else
      
      leafCollection.addLeaf(root);
   }//makeBranch 
   
   public void displayTree(leaf root)
   {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 64;
      boolean isRowEmpty = false;
      System.out.println(
         "----------TREE-----------------------------------------------------------------------------------------------------------------------------");
      
   
      while(isRowEmpty == false)
      {
         Stack localStack = new Stack();
         isRowEmpty = true;
         
         for(int j = 0; j<nBlanks; j++)
            System.out.print(' ');
         
         while(globalStack.isEmpty()==false)
         {
            leaf temp = (leaf)globalStack.pop();
            if(temp != null)
            {
               temp.displayLeaf();
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);
               if(temp.leftChild != null || temp.rightChild != null)
                  isRowEmpty = false;
            }
            else
            {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
            }
            for(int j=0; j<nBlanks*2-2;j++)
            {
               System.out.print(' ');
            }
         }//while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
         {
            globalStack.push(localStack.pop());
         }//while localstack is not empty
      }//while isRowEmpty false
      System.out.println(
         "-------------------------------------------------------------------------------------------------------------------------------------------");
   
   }//displayTree
   
   public leaf findLeaf(leaf root, int key, char name)
   {
      leaf current;
      if(root.leftChild != null && root.rightChild != null)
      {
         if((current = findLeaf(root.leftChild, key, name)) == null)
            current = findLeaf(root.rightChild, key, name);
      }
      else
      {
         if((root.data == key) && (root.n == name))
            current = root;
         else
            current = null;
      }
      return current;
   
   }//findLeaf
   
   public String getCode(leaf nLeaf)
   {
      String code = "";
      leaf current = nLeaf;
      while(current.parent != null)
      {
         code = code + current.parentType;
         current = current.parent;
      }//while
      code = new StringBuilder(code).reverse().toString();
      return code;
   }//getCode
   
}//hTree

