import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
public class Main {

	

	public static void main(String[] args) {
		String selected_country="";
		Character temp=' ';
		Character temp2=' ';
		Stack S1= Loader.loadCountries("countries.txt");
		Stack S2= new Stack(30);
		Stack S2_temp = new Stack(30);
		Stack HST= Loader.loadHighScoreTable("HighScoreTable.txt");
		int step=1;
		int score=0;
		int counterSameLetters=0;
		int howManyLetters=0;
		boolean letterFound=false;
		boolean isGameOver=false;
	
		// Fill S2
		String alphabet="ZYXWVUTSRQPONMLKJIHGFEDCBA";
		for (Character ch : alphabet.toCharArray()) {
			S2.push(ch);		
		}
		
		System.out.println("Welcome to the Wheel of Fortune!");
		
		// Country selection
		Random rand = new Random();
		int x = rand.nextInt(60);
		x++;
		System.out.println("Random number: "+x);
		
		while(!(S1.getTop()==x-2)) {
			selected_country=(String) S1.pop();
		}
				
		Queue Q1 = new Queue(10017);
		Queue Q1_temp = new Queue(10017);
		Queue Q2 = new Queue(10017);
		Queue Q2_temp = new Queue(10017);


		for (int i = 0;i<selected_country.length()-1;i++){

			Q1.enqueue(selected_country.charAt(i));

			if(selected_country.charAt(i)==' ')
				Q2.enqueue(' ');
			else
				Q2.enqueue('-');
		}
		int gamecounter=0;

		//Main game loop		
		while(true){
						
			System.out.print("Word: ");		

			while(!Q2.isEmpty()) {
				temp=(char)Q2.dequeue();
				System.out.print(temp);
				Q2_temp.enqueue(temp);	
			}

			while(!Q2_temp.isEmpty()){
				Q2.enqueue(Q2_temp.dequeue());
			}
		
			System.out.print("    Step: "+step+"  Score: "+score+"         ");	
			step++;
			
			// Select letter

			int l = rand.nextInt(26-gamecounter);
			if(gamecounter<25) {
				gamecounter++; 
			}
			Character letter=' ';
			
			// Remove the letter  

			if(!S2.isEmpty()) {
			
				for(int i=0;i<l;i++){	
					S2_temp.push(S2.pop());
				}
				
				letter=(char)S2.pop();
				
				while(!S2_temp.isEmpty()){
					S2.push(S2_temp.pop());
				}
			}

			// Print remaining letters

			while(!S2.isEmpty()){
				System.out.print(S2.peek());
				S2_temp.push(S2.pop());
			}

			while(!S2_temp.isEmpty()){
				S2.push(S2_temp.pop());
			}

			if(!isGameOver)
			System.out.println("    Guess: "+letter);

			// Letter check
			while(!Q1.isEmpty()){

				temp=(char)Q1.dequeue();
				temp2=(char)Q2.dequeue();
				
				if(temp.toString().equalsIgnoreCase(letter.toString())){
					Q1_temp.enqueue(temp);
					Q2_temp.enqueue(temp);
					howManyLetters++;
					letterFound=true;
				}
				else{
					Q1_temp.enqueue(temp);
					Q2_temp.enqueue(temp2);
				}
			}

			while(!Q1_temp.isEmpty()){
				Q1.enqueue(Q1_temp.dequeue());
			}
			while(!Q2_temp.isEmpty()){
				Q2.enqueue(Q2_temp.dequeue());
			}
		
			// Wheel

			int wheel=0;
			int wheelOption = rand.nextInt(8);
			if(howManyLetters==0)
			howManyLetters++;

			if(wheelOption==0)
			wheel=0;
			else if(wheelOption==1)
			wheel=10*howManyLetters;
			else if(wheelOption==2)
			wheel=50*howManyLetters;
			else if(wheelOption==3)
			wheel=100*howManyLetters;
			else if(wheelOption==4)
			wheel=250*howManyLetters;
			else if(wheelOption==5)
			wheel=500*howManyLetters;
			else if(wheelOption==6)
			wheel=1000*howManyLetters;
			else if(wheelOption==7)
			wheel=2;
			
			if(!isGameOver) {
			
				System.out.print("\nWheel: ");
				if(wheel==0)
				{
					System.out.println("You got bankrupt! :( ");
					score=0;
				}
				else if(wheel==2)
				{
					System.out.println("You got double money!! ");
					if(letterFound)
						score*=2;
				}
				else{
					System.out.println(wheel);
					if(letterFound)
					score+=wheel;
					
				}
				letterFound=false;
				howManyLetters=0;
			}
			
			// Game over 
			String Stemp1="";
			String Stemp2="";
			Stack HSTnew= new Stack(100);
			
			if(isGameOver) {
				System.out.println("\n\n-------------------------------------------------------------------------------- ");
				System.out.println("Game Over!   You won: "+score+"$ \n");
				 
				while(!HST.isEmpty()) {
					HSTnew.push(HST.pop());
				}
							
				System.out.println("You  "+score);
				
				// Write High Score Table to a file and print
				
				 try {
					 FileWriter writer = new FileWriter("HighScoreTable.txt");
					 
					 while(HSTnew.size()>1) {
						 Stemp1=(String) HSTnew.pop();
						 Stemp2=(String) HSTnew.pop();
					
						 System.out.println(Stemp1+"  "+Stemp2);	
						 writer.write(Stemp1+" "+Stemp2+"\n");
					 
					 }
					 writer.close();
					 }		 
				     catch (IOException e) {
				      System.out.println("File not found.");
				      e.printStackTrace();
				    }
				break;
			}
			
			System.out.println("---------------------------------------------------------------------------------");
			
			// Game end check 
			
			while(!Q1.isEmpty()){

				temp=(char)Q1.dequeue();
				temp2=(char)Q2.dequeue();

				if(temp.toString().equalsIgnoreCase(temp2.toString())){
					counterSameLetters++;
				}
				Q1_temp.enqueue(temp);
				Q2_temp.enqueue(temp2);
			}
			

			while(!Q1_temp.isEmpty()){
				Q1.enqueue(Q1_temp.dequeue());
			}
			while(!Q2_temp.isEmpty()){
				Q2.enqueue(Q2_temp.dequeue());
			}
			
			System.out.println("Counter same letter: "+counterSameLetters);
			
			if (counterSameLetters==Q1.size()){
				isGameOver=true;
			}
			counterSameLetters=0;

		}
	}
	
		
}
