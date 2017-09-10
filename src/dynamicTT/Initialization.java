package dynamicTT;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Consumer;

public class Initialization {

    //this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
    //currently hardcoded by taking one course with 6 subjects and 6 teachers
    private ArrayList<Subject> subjects = new ArrayList();
    private ArrayList<Subject> subjects1 = new ArrayList();
    private ArrayList<Subject> subjects2 = new ArrayList();
    private ArrayList<Subject> subjects3 = new ArrayList();
    private ArrayList<Subject> subjects4 = new ArrayList();
    private ArrayList<Subject> subjects5 = new ArrayList();
    private ArrayList<Professor> professors = new ArrayList();
    private ArrayList<TimeTable> timetables = new ArrayList();
    private ArrayList<Lecture> classes = new ArrayList<>();
    private ArrayList<Combination> combinations = new ArrayList<>();
    int i = 1;

    //reads input from a file.

    public void readInput() throws IOException, SQLException, ClassNotFoundException {
        ArrayList<ClassRoom> classroom = new ArrayList<>();
        DBConnection con = new DBConnection();

        ResultSet rst2 = con.getAllRooms();
        while (rst2.next()) {
            Object row[] = {rst2.getString(1), rst2.getString(2), rst2.getString(3), rst2.getString(4), rst2.getString(5), rst2.getString(6), rst2.getString(7)};
            //System.out.println("From db"+row[0].toString()+"  "+row[1].toString()+"  "+row[2].toString()+"  "+row[3].toString()+"  "+row[4].toString()+"  "+row[5].toString());
            ClassRoom cl = new ClassRoom(row[1].toString(), Integer.parseInt(row[3].toString()), Boolean.parseBoolean(row[4].toString()), row[6].toString());
          //  System.out.println("    "+cl.getRoomNo()+cl.getWeek()+"  "+cl.getSize()+"  "+cl.isLaboratory()+cl.getDepartment());
            classroom.add(cl);
        }
        ResultSet rst = con.getAllproff();
        while (rst.next()) {
            Object row[] = {rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9)};
            // System.out.println(rst.getString(1)+"#1#"+ rst.getString(2)+"#2#"+ rst.getString(3)+"#3#"+rst.getString(4)+"#4#"+ rst.getString(5)+"#5#"+ rst.getString(6)+ "#6#"+rst.getString(7)+"#7#"+rst.getString(8)+"#8#"+ rst.getString(9));
            //  System.out.println(row[8].toString()+"Seventh");
            Professor pr = new Professor(Integer.parseInt(row[0].toString()), row[2].toString(), row[8].toString());
            //System.out.println("     "+Integer.parseInt(row[0].toString())+ row[2].toString()+ row[8].toString());
            professors.add(pr);
        }
        createLectures(professors);
        ResultSet rst1 = con.getAllSub();
        while (rst1.next()) {

            Object row[] = {rst1.getString(1), rst1.getString(2), rst1.getString(3), rst1.getString(4), rst1.getString(5), rst1.getString(6)};
            // System.out.println(row[0].toString()+"  "+row[1].toString()+"  "+row[2].toString()+"  "+row[3].toString()+"  "+row[4].toString()+"  "+row[5].toString());
            String dept=DBConnection.getdept( row[5].toString());
            Subject s = new Subject(Integer.parseInt(row[0].toString()), row[2].toString(), Integer.parseInt(row[3].toString()), Boolean.parseBoolean(row[4].toString()),dept);
              // System.out.println("    "+Integer.parseInt(row[0].toString())+ row[2].toString()+ Integer.parseInt(row[3].toString())+ Boolean.parseBoolean(row[4].toString())+ dept);
              // System.out.println(s.getSubjectID()+s.getSubjectName()+s.getNumberOfLecturesPerWeek()+s.isIslab()+s.getDepartment());
            //  subjects.add(s);
            if (i < 10) {
                System.out.println("1.1");
                subjects1.add(s);
            } else if (i < 16) {
                  System.out.println("1.2");
                subjects2.add(s);
            } else if (i < 22) {
                  System.out.println("2.1");
                subjects3.add(s);

            } else if (i < 27) {
                  System.out.println("2.2");
                subjects4.add(s);

            } else {
                
                subjects5.add(s);
            }
            i++;
            System.out.println("I is : " + i);
        }
        
        

        Consumer<? super Lecture> a = null;
        //    System.out.println("classes"+classes.toString());
        TimeTable timetb1 = new TimeTable(classroom, classes);//, professors);
        

        //int courseid = 1;
        //String courseName="1st year 1st semester";
        System.out.println("reading input.......");


        System.out.println("new course creation.......");
        System.out.println("Sub1 :" + subjects1.size() + "Sub2 : " + subjects2.size() + "Sub3 : " + subjects3.size() + "Sub4 : " + subjects4.size());
        Course course1 = new Course(1, "1st year 1st semester", subjects1);
        course1.createCombination("Fundamentals of Computer/Fundamentals of Computer(Pr)/Principles of Programming/Principles of Programming(Pr)"
                + "/Basic Electronic & Design Logic/Basic Mathematics/General English/General Biology/Basic Science", 20);

        course1.createStudentGroups();
        ArrayList<StudentGroups> studentGroups = course1.getStudentGroups();
        timetb1.addStudentGroups(studentGroups);
		//combinations.addAll(course1.getCombinations());

        //timetb2.addStudentGroups(studentGroups);
        ///timetb3.addStudentGroups(studentGroups);
        subjects.clear();


        Course course2 = new Course(2, "1st year 2nd semester", subjects2);
        course2.createCombination("Design & Analysis of Algorithm/Software Engineering/Multimedia & Web Technology/Graphics & Image Processing(Pr)"
                + "/Graphics & Image Processing/Multimedia & Web Technology(Pr)", 20);

        course2.createStudentGroups();
        studentGroups = course2.getStudentGroups();
        timetb1.addStudentGroups(studentGroups);
        

        subjects.clear();


        Course course3 = new Course(3, "3rd year 1st semester", subjects3);
        course3.createCombination("Information System Security/Project Management/Professional Practice and Ethics/Principle of Accounting"
                + "/Marketing/Intelligent Systems(Pr)/Intelligent Systems", 20);

        course3.createStudentGroups();
        studentGroups = course3.getStudentGroups();
        timetb1.addStudentGroups(studentGroups);

        subjects.clear();


        Course course4 = new Course(4, "3rd year 2nd semester", subjects4);
        course4.createCombination("Human Computer Interaction/Operational Researcht/Embedded Systems/Embedded Systems(Pr)"
                + "/Entrepreneurship", 20);

        course4.createStudentGroups();
        studentGroups = course4.getStudentGroups();
        timetb1.addStudentGroups(studentGroups);

        System.out.println("Setting tt.......");

        System.out.println("adding tt.......");
        timetb1.initializeTimeTable();
        System.out.println("Initialization finish");
        //timetb2.initializeTimeTable();
        //timetb3.initializeTimeTable();
        timetables.add(timetb1);
        //timetable.add(timetb2);
        //timetable.add(timetb3);

        System.out.println("populating.......");

        //display();
        populateTimeTable(timetb1);
        GeneticAlgorithm ge = new GeneticAlgorithm();

        //ge.fitness(timetb1);
//		timetb1.createTimeTableGroups(combinations);
        ge.populationAccepter(timetables);
//		//ge.fitness(timetb2);

            //ge.fitness(timetb3);
        //populateTimeTable();
    }

    public void populateTimeTable(TimeTable timetb1) {
        int i = 0;
        System.out.println("populating started.......");
        while (i < 3) {
            TimeTable tempTimetable = timetb1;
            ArrayList<ClassRoom> allrooms = tempTimetable.getRoom();
            Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
            while (allroomsIterator.hasNext()) {
                ClassRoom room = allroomsIterator.next();
                ArrayList<Day> weekdays = room.getWeek().getWeekDays();
                Collections.shuffle(weekdays);
                if (!room.isLaboratory()) {
                    Iterator<Day> daysIterator = weekdays.iterator();
                    while (daysIterator.hasNext()) {
                        Day day = daysIterator.next();
                        Collections.shuffle(day.getTimeSlot());
                    }
                }
            }
            timetables.add(tempTimetable);
            i++;
        }
        System.out.println("populating done.......");
        System.out.println("display called.......");
        display();
    }

    private void createLectures(ArrayList<Professor> professors) {
        // TODO Auto-generated method stub

        java.util.Iterator<Professor> professorIterator = professors.iterator();
        while (professorIterator.hasNext()) {
            Professor professor = professorIterator.next();
            ArrayList<String> subjectsTaught = professor.getSubjectTaught();
            Iterator<String> subjectIterator = subjectsTaught.iterator();
            while (subjectIterator.hasNext()) {
                String subject = subjectIterator.next();
                // System.out.println("Prof : "+professor.getProfessorName()+" Sub "+subject);
                classes.add(new Lecture(professor, subject));
            }
        }
    }

   
    private void display() {
        // TODO Auto-generated method stub
        int i = 1;
        System.out.println("displaying all tt's.......");
        Iterator<TimeTable> timetableIterator = timetables.iterator();
        while (timetableIterator.hasNext()) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++\nTime Table no. " + i);
            TimeTable currentTimetable = timetableIterator.next();
            System.out.println("Score : " + currentTimetable.getFittness());
            ArrayList<ClassRoom> allrooms = currentTimetable.getRoom();
            Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
            while (allroomsIterator.hasNext()) {
                ClassRoom room = allroomsIterator.next();
                System.out.println("Room: " + room.getRoomNo());
                ArrayList<Day> weekdays = room.getWeek().getWeekDays();
                Iterator<Day> daysIterator = weekdays.iterator();
                while (daysIterator.hasNext()) {
                    Day day = daysIterator.next();
                    ArrayList<TimeSlot> timeslots = day.getTimeSlot();
                    Iterator<TimeSlot> timeslotIterator = timeslots.iterator();
                    //System.out.print(""+day.getName()+": ");
                    while (timeslotIterator.hasNext()) {
                        TimeSlot lecture = (TimeSlot) timeslotIterator.next();
                        if (lecture.getLecture() != null) {
                            //System.out.print(" (Subject: "+lecture.getLecture().getSubject()+" --> Professor: "+lecture.getLecture().getProfessor().getProfessorName()+" GrpName: "+lecture.getLecture().getStudentGroup().getName()+")");
                            System.out.print("(" + lecture.getLecture().getSubject() + "#" + lecture.getLecture().getProfessor().getProfessorName() + "#" + lecture.getLecture().getStudentGroup().getName().split("/")[0] + ")");
                        } else {
                            System.out.print("   free   ");
                        }
                    }
                    System.out.print("\n");
                }
                System.out.print("\n\n");
            }
            i++;
        }
    }
}
