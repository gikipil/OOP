package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


/**
 * Gradebook class.
 */

public class Gradebook {
    /**
     * all grades.
     */

    List<Integer> grades = new ArrayList<>();

    /**
     * user ID.
     */

    String id;

    /**
     *discipline and appropriate assessment of term.
     */

    HashMap<Integer, HashMap<String, Integer>> discipline = new HashMap<>();

    /**
     * Qualification work.
     */

    int qualificationWork = 0;

    /**
     * Constructor.
     *
     * @param id user id.
     */

    public Gradebook(String id) {
        this.id = id;
    }

    /**
     * A method that add a semester.
     */

    public void addSemestr(Integer term) {
        discipline.put(term, new HashMap<>());
    }

    /**
     * A method that remove a semester.
     */

    public void removeSemestr(Integer term) {
        discipline.remove(term);
    }

    /**
     * The method of adding discipline.
     */

    public void addDiscipline(Integer term, String sub, Integer grade) {
        discipline.get(term).put(sub, grade);
    }

    /**
     * The method of removing discipline.
     */

    public void removeDiscipline(Integer term, String sub) {
        discipline.get(term).remove(sub);
    }

    /**
     * Find out what semesters are available.
     *
     * @return array of semesters.
     */

    public ArrayList<Integer> getSemesters() {
        return new ArrayList<>(discipline.keySet());
    }

    /**
     * get all disciple.
     */

    public List<String> getAllDisciplines() {
        List<String> ans = this.getSemesters().stream().flatMap(a -> getDisciplines(a).stream()).distinct().collect(Collectors.toList());
        return ans;
    }

    /**
     * Find out what disciplines are available in term.
     *
     * @return array of disciplines.
     */

    public ArrayList<String> getDisciplines(Integer term) {
        return new ArrayList<>(discipline.get(term).keySet());
    }

    /**
     * Find out the assessment of the discipline.
     */

    public Integer getGrade(Integer term, String sub) {
        return discipline.get(term).get(sub);
    }

    /**
     * Find out the latest grade in the discipline.
     */

    public Integer getLastGrade(String sub) {
        Integer lastTerm = 0;
        lastTerm = this.getSemesters().stream().sorted().reduce((a, b) -> b).orElse(null);
        return discipline.get(lastTerm).get(sub);
    }

    /**
     * Find out the average score in the discipline.
     */

    public Double getAverageGrade(String sub) {
        OptionalDouble average = this.getSemesters().stream().map(a -> getGrade(a,sub)).
                dropWhile(a -> Objects.equals(a, null)).mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }



    /**
     * A setter for qualifying work.
     */

    public void setQualificationWork(int grade) {
        this.qualificationWork = grade;
    }

    /**
     * A getter for qualifying work.
     */

    public int getQualificationWork() {
        return qualificationWork;
    }

    /**
     * overall average score.
     */

    public Double getOverallAverage() {
        OptionalDouble average = this.getSemesters().stream().flatMap(a -> getAllDisciplines().
                stream().map(b -> getGrade(a, b)).dropWhile(c -> Objects.equals(c, null))).
                mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    /**
     * criteria 1 for a red diploma.
     */

    private boolean critOne() {
        float fiveCount = 0;
        float allCount = 0;
        for (String sub : this.getAllDisciplines()) {
            if (this.getLastGrade(sub) == 5) {
                fiveCount++;
                allCount++;
            } else {
                allCount++;
            }
        }
        return (fiveCount / allCount) >= 0.75;
    }

    /**
     * criteria 2 for a red diploma.
     */

    private boolean critTwo() {
        boolean mark = true;
        for (String sub : this.getAllDisciplines()) {
            if (this.getLastGrade(sub) <= 3) {
                mark = false;
            }
        }
        return mark;
    }

    /**
     * criteria 3 for a red diploma.
     */

    private boolean critThree() {
        return this.getQualificationWork() == 5;
    }

    /**
     * checking the possibility of obtaining a diploma with honors.
     */

    public boolean getPossibleOfRedDiploma() {
        return critOne() && critTwo() && critThree();
    }

    /**
     * checking the possibility of obtaining a scholarship.
     */

    public boolean getPossibleScholarship(Integer term) {
        boolean mark = true;
        for (String sub : this.getDisciplines(term)) {
            if (this.getGrade(term, sub) <= 3) {
                mark = false;
            }
        }
        return mark;
    }

    /**
     * getter hash table.
     */

    public HashMap<Integer, HashMap<String, Integer>> getTable() {
        return this.discipline;
    }
}
