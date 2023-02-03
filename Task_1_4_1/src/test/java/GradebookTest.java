import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import org.example.Gradebook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



/**
 * Gradebook test.
 */

public class GradebookTest {

    @Test
    void removeSemestrTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 5);
        obj.addSemestr(3);
        obj.removeSemestr(2);
        Gradebook obj1 = new Gradebook("1");
        obj1.addSemestr(1);
        obj1.addSemestr(3);
        Assertions.assertEquals(obj1.getTable(), obj.getTable());
    }

    @Test
    void removeDisciplineTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 5);
        obj.addDiscipline(1, "Algebra", 3);
        obj.addDiscipline(1, "Osi", 2);
        obj.removeDiscipline(1, "Algebra");
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 3);
        obj.addSemestr(3);
        Gradebook obj1 = new Gradebook("2");
        obj1.addSemestr(1);
        obj1.addDiscipline(1, "OOP", 5);
        obj1.addDiscipline(1, "Osi", 2);
        obj1.addSemestr(2);
        obj1.addDiscipline(2, "OOP", 3);
        obj1.addSemestr(3);
        Assertions.assertEquals(obj1.getTable(), obj.getTable());
    }

    @Test
    void getSemestersTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 5);
        obj.addSemestr(3);
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        ans.add(2);
        ans.add(3);
        List<Integer> s = obj.getSemesters();
        Assertions.assertEquals(s, ans);
    }

    @Test
    void getAllDisciplinesTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 4);
        obj.addDiscipline(1, "Algebra", 3);
        obj.addDiscipline(1, "Osi", 5);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 3);
        obj.addSemestr(3);
        obj.addDiscipline(3, "Physical",2);
        obj.addDiscipline(3, "Imperative", 5);
        List<String> ans = new ArrayList<>();
        ans.add("OOP");
        ans.add("Algebra");
        ans.add("Osi");
        ans.add("Physical");
        ans.add("Imperative");
        List<String> s = obj.getAllDisciplines();
        Assertions.assertEquals(s, ans);
    }

    @Test
    void getDisciplinesTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 3);
        obj.addDiscipline(1, "Algebra", 4);
        obj.addDiscipline(1, "Osi", 4);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 3);
        obj.addSemestr(3);
        obj.addDiscipline(3, "Physical", 3);
        obj.addDiscipline(3, "Imperative", 3);
        List<String> s = obj.getDisciplines(3);
        List<String> ans = new ArrayList<>();
        ans.add("Physical");
        ans.add("Imperative");
        Assertions.assertEquals(s, ans);
    }

    @Test
    void getGradeTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 3);
        obj.addDiscipline(1, "Algebra", 4);
        obj.addDiscipline(1, "Osi", 5);
        Assertions.assertEquals(obj.getGrade(1, "Osi"), 5);
    }

    @Test
    void getLastGradeTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 3);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 5);
        obj.addSemestr(3);
        obj.addDiscipline(3, "OOP", 4);
        Assertions.assertEquals(obj.getLastGrade("OOP"), 4);
    }

    @Test
    void getAverageGradeTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 3);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 4);
        obj.addSemestr(3);
        obj.addDiscipline(3, "OOP", 4);
        List<Integer> temp = new ArrayList<>();
        temp.add(3);
        temp.add(4);
        temp.add(4);
        OptionalDouble average = temp.stream().mapToDouble(a -> a).average();
        double ans;
        if (average.isPresent()) {
            ans = average.getAsDouble();
        } else {
            ans = 0.0;
        }
        Assertions.assertEquals(obj.getAverageGrade("OOP"), ans);
    }

    @Test
    void qualificationWorkTest() {
        Gradebook obj = new Gradebook("3");
        obj.setQualificationWork(4);
        Assertions.assertEquals(obj.getQualificationWork(), 4);
    }

    @Test
    void getOverallAverageTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 3);
        obj.addDiscipline(1, "Algebra", 4);
        obj.addDiscipline(1, "Osi", 5);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 5);
        obj.addDiscipline(2, "Algebra", 3);
        obj.addDiscipline(2, "Osi", 3);
        List<Integer> temp = new ArrayList<>();
        temp.add(3);
        temp.add(4);
        temp.add(5);
        temp.add(5);
        temp.add(3);
        temp.add(3);
        OptionalDouble average = temp.stream().mapToDouble(a -> a).average();
        double ans;
        if (average.isPresent()) {
            ans = average.getAsDouble();
        } else {
            ans = 0.0;
        }
         Assertions.assertEquals(obj.getOverallAverage(), ans);
    }

    @Test
    void getPossibleOfRedDiplomaTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 5);
        obj.addDiscipline(1, "Algebra", 5);
        obj.addDiscipline(1, "Osi", 5);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 5);
        obj.addDiscipline(2, "Algebra", 3);
        obj.addDiscipline(2, "Osi", 3);
        obj.setQualificationWork(5);
        Assertions.assertFalse(obj.getPossibleOfRedDiploma());

        Gradebook obj1 = new Gradebook("5");
        obj1.addSemestr(1);
        obj1.addDiscipline(1, "OOP", 3);
        obj1.addDiscipline(1, "Algebra", 4);
        obj1.addDiscipline(1, "Osi", 5);
        obj1.addSemestr(2);
        obj1.addDiscipline(2, "OOP", 5);
        obj1.addDiscipline(2, "Algebra", 5);
        obj1.addDiscipline(2, "Osi", 5);
        obj1.setQualificationWork(3);
        Assertions.assertFalse(obj1.getPossibleOfRedDiploma());

        Gradebook obj2 = new Gradebook("1");
        obj2.addSemestr(1);
        obj2.addDiscipline(1, "OOP", 3);
        obj2.addDiscipline(1, "Algebra", 4);
        obj2.addDiscipline(1, "Osi", 5);
        obj2.addSemestr(2);
        obj2.addDiscipline(2, "OOP", 5);
        obj2.addDiscipline(2, "Algebra", 5);
        obj2.addDiscipline(2, "Osi", 5);
        obj2.setQualificationWork(5);
        Assertions.assertTrue(obj2.getPossibleOfRedDiploma());
    }

    @Test
    void getPossibleScholarshipTest() {
        Gradebook obj = new Gradebook("1");
        obj.addSemestr(1);
        obj.addDiscipline(1, "OOP", 5);
        obj.addDiscipline(1, "Algebra", 5);
        obj.addDiscipline(1, "Osi", 4);
        obj.addSemestr(2);
        obj.addDiscipline(2, "OOP", 4);
        obj.addDiscipline(2, "Algebra", 5);
        obj.addDiscipline(2, "Osi", 3);
        obj.setQualificationWork(5);
        Assertions.assertFalse(obj.getPossibleScholarship(2));
        Assertions.assertTrue(obj.getPossibleScholarship(1));
    }
}
