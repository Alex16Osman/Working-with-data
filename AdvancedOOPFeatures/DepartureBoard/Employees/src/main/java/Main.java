import java.util.List;

public class Main {

    private static final String STAFF_TXT = "C:\\Users\\user\\Desktop\\СЭШКИНА\\Programmer\\Working-with-data" +
    "\\AdvancedOOPFeatures\\DepartureBoard\\Employees\\data\\staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        return staff.stream()
        .filter((e) -> e.getWorkStart().getYear() == year)
        .max((e1, e2) ->
            e1.getSalary().compareTo(e2.getSalary()))
        .get();
    }
}