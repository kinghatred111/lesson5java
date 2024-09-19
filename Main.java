import java.util.List;

class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Student {
    private String id;

    public Student(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

class StudyGroup {
    private Teacher teacher;
    private List<Student> students;

    public StudyGroup(Teacher teacher, List<Student> students) {
        this.teacher = teacher;
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }
}

class StudyGroupService {
    public StudyGroup createStudyGroup(Teacher teacher, List<Student> students) {
        return new StudyGroup(teacher, students);
    }
}

class Controller {
    private StudyGroupService studyGroupService;

    public Controller(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    public StudyGroup formStudyGroup(String teacherName, List<String> studentIds) {
        Teacher teacher = new Teacher(teacherName);
        List<Student> students = studentIds.stream().map(Student::new).toList();
        return studyGroupService.createStudyGroup(teacher, students);
    }
}

public class Main {
    public static void main(String[] args) {
        StudyGroupService studyGroupService = new StudyGroupService();
        Controller controller = new Controller(studyGroupService);

        List<String> studentIds = List.of("S1", "S2", "S3");
        StudyGroup studyGroup = controller.formStudyGroup("Преподаватель", studentIds);

        System.out.println("Teacher: " + studyGroup.getTeacher().getName());
        System.out.println("Students: ");
        for (Student student : studyGroup.getStudents()) {
            System.out.println(student.getId());
        }
    }
}
