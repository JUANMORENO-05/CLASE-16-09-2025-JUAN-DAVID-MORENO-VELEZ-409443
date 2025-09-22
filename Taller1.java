// Proyecto: Gestión Académica con Lista Enlazada 

class Student {
    String firstName;
    String lastName;
    String idNumber;
    String program;
    int semester;
    Student next;

    public Student(String fn, String ln, String id, int sem, String prog) {
        this.firstName = fn;
        this.lastName = ln;
        this.idNumber = id;
        this.semester = sem;
        this.program = prog;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Student{firstName='" + firstName + "', " +
               "lastName='" + lastName + "', " +
               "idNumber='" + idNumber + "', " +
               "semester=" + semester + ", " +
               "program='" + program + "'}";
    }
}

class CourseClass {
    String id;
    String name;
    int credits;
    Student head;

    public CourseClass(String id, String name, int credits) {
        if (credits <= 0) throw new IllegalArgumentException("Créditos inválidos");
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.head = null;
    }

    public boolean addStudent(Student s) {
        if (findStudent(s.idNumber) != null) {
            System.out.println("Error: El estudiante con ID " + s.idNumber + " ya está inscrito.");
            return false;
        }

        if (head == null || compareStudents(s, head) < 0) {
            s.next = head;
            head = s;
        } else {
            Student cur = head;
            while (cur.next != null && compareStudents(s, cur.next) >= 0) {
                cur = cur.next;
            }
            s.next = cur.next;
            cur.next = s;
        }

        System.out.println("Estudiante " + s.firstName + " inscrito correctamente.");
        return true;
    }

    private int compareStudents(Student a, Student b) {
        int cmp = a.lastName.compareToIgnoreCase(b.lastName);
        if (cmp != 0) return cmp;
        return a.idNumber.compareTo(b.idNumber);
    }

    public Student findStudent(String id) {
        Student c = head;
        while (c != null) {
            if (c.idNumber.equals(id)) return c;
            c = c.next;
        }
        return null;
    }

    public boolean updateStudent(String id, String fn, String ln, Integer sem, String prog) {
        Student st = findStudent(id);
        if (st == null) {
            System.out.println("Error: Estudiante con ID " + id + " no encontrado.");
            return false;
        }

        if (fn != null) st.firstName = fn;
        if (sem != null) st.semester = sem;
        if (prog != null) st.program = prog;

        if (ln != null) {
            removeStudent(id);
            st.lastName = ln;
            addStudent(st);
        }

        System.out.println("Estudiante actualizado correctamente.");
        return true;
    }

    public boolean removeStudent(String id) {
        if (head == null) {
            System.out.println("Error: lista vacía.");
            return false;
        }

        if (head.idNumber.equals(id)) {
            head = head.next;
            System.out.println("Estudiante eliminado (era el primero).");
            return true;
        }

        Student cur = head;
        while (cur.next != null) {
            if (cur.next.idNumber.equals(id)) {
                cur.next = cur.next.next;
                System.out.println("Estudiante eliminado correctamente.");
                return true;
            }
            cur = cur.next;
        }
        System.out.println("Error: Estudiante con ID " + id + " no encontrado.");
        return false;
    }

    public void listStudents() {
        if (head == null) {
            System.out.println("[Sin alumnos]");
            return;
        }
        System.out.println("Estudiantes inscritos en " + name + ":");
        Student c = head;
        int i = 1;
        while (c != null) {
            System.out.println(i + ". " + c.toString());
            c = c.next;
            i++;
        }
    }
}

public class Taller1 {
    public static void main(String[] args) {
        CourseClass matematicas = new CourseClass("M101", "Matemáticas Discretas", 5);

        matematicas.addStudent(new Student("Carlos", "Martínez", "20230001", 2, "Ingeniería Industrial"));
        matematicas.addStudent(new Student("Lucía", "Torres", "20230002", 1, "Ingeniería Civil"));
        matematicas.addStudent(new Student("Andrés", "Martínez", "20230001", 3, "Ingeniería Mecánica")); // duplicado

        matematicas.listStudents();

        matematicas.updateStudent("20230002", "Lucía Fernanda", "Torres Rojas", 2, null);
        matematicas.listStudents();

        matematicas.removeStudent("20230001");
        matematicas.listStudents();

        matematicas.removeStudent("99999999"); // no existe
    }
}
