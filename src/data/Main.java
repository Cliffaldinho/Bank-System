package data;

public class Main {

	public static void main(String[] args) {
	
		int amountOfRatingsReceived = 1;
		System.out.println(amountOfRatingsReceived);
		
		double ratings = (double) amountOfRatingsReceived;
		System.out.println(ratings);
		
		int amountUsers = 5;
		double users = amountUsers;
		System.out.println(users);
		
		double votePercentage = ratings/users;
		System.out.println(votePercentage);
		

	}

}
