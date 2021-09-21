package Vedantu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Vedantu {

	private static final Integer BOARDING_HOUSE = 4;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String input = sc.nextLine();

		String[] inArr = input.split(" ");

		int numOfStudents = Integer.parseInt(inArr[1]);

		int houseCapacity = numOfStudents / BOARDING_HOUSE;

		Map<String, List<Integer>> classFoodToStudents = new LinkedHashMap<>();

		classFoodToStudents.put("NA", new ArrayList<>());

		processInput(sc, houseCapacity, classFoodToStudents);

		Set<Map.Entry<String, List<Integer>>> entrySet = classFoodToStudents.entrySet();
		List<Integer> na = null;
		for (Entry<String, List<Integer>> entry : entrySet) {
			if (entry.getKey().equals("NA"))
				na = (List<Integer>) entry.getValue();
			else {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}

		}
		System.out.println("NA : " + na);

	}

	private static void processInput(Scanner sc, int houseCapacity, Map<String, List<Integer>> classFoodToStudents) {
		while (sc.hasNext()) {
			String regStudent = sc.nextLine();
			if (regStudent.equals("fin")) {
				break;
			}
			String studentDetails[] = regStudent.split(" ");
			Student s = new Student(Integer.parseInt(studentDetails[1]), studentDetails[2], studentDetails[3]);
			if (!s.isValidRollNumber()) {
				throw new IllegalArgumentException("Roll Number is more than 4 digit");
			}

			String key = s.getClassName() + s.getFoodPrefernce();

			int houseCuurentStrength = classFoodToStudents.get(key) != null ? classFoodToStudents.get(key).size() : 0;

			if (houseCuurentStrength >= houseCapacity) {
				classFoodToStudents.computeIfAbsent("NA", l -> new ArrayList())
						.add(Integer.parseInt(studentDetails[1]));
				continue;
			}
			if (!classFoodToStudents.containsKey(key) && classFoodToStudents.size() > BOARDING_HOUSE) {

				throw new IllegalArgumentException("Invalid Input");

			}
			classFoodToStudents.computeIfAbsent(key, l -> new ArrayList()).add(Integer.parseInt(studentDetails[1]));

		}
	}

	static class Student {

		private Integer rollNumber;
		private String className;
		private String foodPrefernce;

		public Student() {

		}

		public Student(Integer rollNumber, String className, String foodPrefernce) {
			super();
			this.rollNumber = rollNumber;
			this.className = className;
			this.foodPrefernce = foodPrefernce;
		}

		public Integer getRollNumber() {
			return rollNumber;
		}

		public void setRollNumber(Integer rollNumber) {
			this.rollNumber = rollNumber;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getFoodPrefernce() {
			return foodPrefernce;
		}

		public void setFoodPrefernce(String foodPrefernce) {
			this.foodPrefernce = foodPrefernce;
		}

		private boolean isValidRollNumber() {
			return String.valueOf(this.rollNumber).length() <= 4;

		}

	}

}
