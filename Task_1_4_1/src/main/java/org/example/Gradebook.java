package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Stack;

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

    HashMap<Integer, HashMap<String, Stack<Integer>>> discipline = new HashMap<>();

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

    public void addDiscipline(Integer term, String sub) {
        discipline.get(term).put(sub, new Stack<>());
    }

    /**
     * The method of removing discipline.
     */

    public void removeDiscipline(Integer term, String sub) {
        discipline.get(term).remove(sub);
    }

    /**
     * The method of adding an estimate.
     *
     */

    public void addGrade(Integer term, String sub, Integer grade) {
        discipline.get(term).get(sub).push(grade);
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
        List<String> ans = new ArrayList<>();
        for (Integer term : this.getSemesters()) {
            for (String sub : this.getDisciplines(term)) {
                if (!ans.contains(sub)) {
                    ans.add(sub);
                }
            }
        }
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
        return discipline.get(term).get(sub).peek();
    }

    /**
     * Find out the latest grade in the discipline.
     */

    public Integer getLastGrade(String sub) {
        Integer lastTerm = 0;
        ArrayList<Integer> terms = this.getSemesters();
        Collections.sort(terms);
        for (Integer term : terms) {
            List<String> subjs = this.getDisciplines(term);
            if (subjs.contains(sub)) {
                lastTerm = term;
            }
        }
        return discipline.get(lastTerm).get(sub).peek();
    }

    /**
     * Find out the average score in the discipline.
     */

    public Double getAverageGrade(String sub) {
        List<Integer> tempGrades = new ArrayList<>();
        float sum = 0;
        float c = 0;
        ArrayList<Integer> terms = this.getSemesters();
        Collections.sort(terms);
        for (Integer term : terms) {
            List<String> subjs = this.getDisciplines(term);
            if (subjs.contains(sub)) {
                Stack<Integer> temp = discipline.get(term).get(sub);
                while (!temp.empty()) {
                    Integer gr = temp.pop();
                    tempGrades.add(gr);
                    this.grades.add(gr);
                }
            }
        }
        OptionalDouble average = tempGrades.stream().mapToDouble(a -> a).average();
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
        float c = 0;
        float sum = 0;
        for (String sub : this.getAllDisciplines()) {
            this.getAverageGrade(sub);
        }
        OptionalDouble average = this.grades.stream().mapToDouble(a -> a).average();
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

    public HashMap<Integer, HashMap<String, Stack<Integer>>> getTable() {
        return this.discipline;
    }
}
