import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student{
	int id;
	String name;
	double cgpa;
	
	Student(int id, String name, double cgpa) {
		this.id = id;
		this.name = name;
		this.cgpa = cgpa;
	}
	
	String getName() {
		return name;
	}
	
	int getID() {
		return id;
	}
	
	double getCGPA() {
		return cgpa;
	}

}

class Priorities {
	List<Student> getStudents(List<String> events) {
		List<Student> retList = new ArrayList<Student>();
		for(String str : events) {
			String[] strArr = str.split(" ");
			if (strArr[0].equals("ENTER")) {
				Student std = new Student(Integer.parseInt(strArr[3]), strArr[1], Double.parseDouble(strArr[2]));
				retList.add(std);
			}
			else if (strArr[0].equals("SERVED")) {
				if (retList.size() != 0) {
					sortMyList(retList);
					retList.remove(0);
				}
			}
		}
		return retList;
	}

		
	private void sortMyList(List<Student> list) {
		int ptr = 1;
		int list_size = list.size();
		while(ptr < list_size) {
			int ptr2 = 0;
			while(ptr2 < ptr) {
				if (myCompare(list.get(ptr), list.get(ptr2)) == 1) {
					Student std = list.get(ptr2);
					list.set(ptr2, list.get(ptr));
					list.set(ptr, std);
				}
				ptr2++;
			}
			ptr++;
		}
	}

	public int myCompare(Student std1, Student std2) {
		if (std1.getCGPA()>std2.getCGPA()) {
			return 1;
		}
		else if (std1.getCGPA() < std2.getCGPA()){
			return -1;
		}
		else {
			if (std1.getName().compareTo(std2.getName()) < 0) {
				return 1;
			}
			else if (std1.getName().compareTo(std2.getName()) > 0) {
				return -1;
			}
			else {
				if (std1.getID() < std2.getID()) {
					return 1;
				}
				else if (std1.getID() > std2.getID()) {
					return -1;
				}
			}
		}
		return 0;
	}
	
}

public class PriorityQueue {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}