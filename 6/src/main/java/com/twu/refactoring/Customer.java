package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private String name;
	private int regularMovieAdds = 2;
	private double childrenMovieAdds = 1.5;

	private int regularMovieMinimum = 2;
	private int childrenMovieMinimum = 3;

	private double weightsForRegular = 1.5;
	private double weightsForChildren = 1.5;
	private int weightsForNewRelease = 3;


	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			double movieAmount = 0;
			Rental each = rentals.next();

			// determine amounts for each line
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				movieAmount += regularMovieAdds;
				if (each.getDaysRented() > regularMovieMinimum)
					movieAmount += (each.getDaysRented() - regularMovieMinimum) * weightsForRegular;
				break;
			case Movie.NEW_RELEASE:
				movieAmount += each.getDaysRented() * weightsForNewRelease;
				break;
			case Movie.CHILDRENS:
				movieAmount += childrenMovieAdds;
				if (each.getDaysRented() > childrenMovieMinimum)
					movieAmount += (each.getDaysRented() - childrenMovieMinimum) * weightsForChildren;
				break;

			}

			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& each.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(movieAmount) + "\n";
			totalAmount += movieAmount;

		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

}
