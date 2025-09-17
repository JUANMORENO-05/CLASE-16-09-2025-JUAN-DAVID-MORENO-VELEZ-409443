// Proyecto: Gestión Académica con Lista Enlazada Simple


class Student {
    String firstName, lastName, idNumber, program;
    int semester;
    Student next;

    public Student(String fn, String ln, String id, int sem, String prog) {
        firstName = fn; lastName = ln; idNumber = id; semester = sem; program = prog;
    }

    public String toString() {
        return firstName + " " + lastName + " (ID:" + idNumber + ") Sem:" + semester + " - " + program;
    }
}

class CourseClass {
    String id, name;
    int credits;
    Student head;

    public CourseClass(String id, String name, int credits) {
        if (credits <= 0) throw new IllegalArgumentException("Créditos inválidos");
        this.id = id; this.name = name; this.credits = credits;
    }

    // insertar en orden por apellido
    public boolean addStudent(Student s) {
        if (findStudent(s.idNumber) != null) return false; // duplicado
        if (head == null || s.lastName.compareToIgnoreCase(head.lastName) < 0) {
            s.next = head; head = s; return true;
        }
        Student cur = head;
        while (cur.next != null && cur.next.lastName.compareToIgnoreCase(s.lastName) <= 0)
            cur = cur.next;
        s.next = cur.next; cur.next = s;
        return true;
    }

    public Student findStudent(String id) {
        for (Student c = head; c != null; c = c.next)
            if (c.idNumber.equals(id)) return c;
        return null;
    }

    public boolean updateStudent(String id, String fn, String ln, Integer sem, String prog) {
        Student st = findStudent(id);
        if (st == null) return false;
        if (fn != null) st.firstName = fn;
        if (ln != null) { st.lastName = ln; removeStudent(id); addStudent(st); }
        if (sem != null) st.semester = sem;
        if (prog != null) st.program = prog;
        return true;
    }

    public boolean removeStudent(String id) {
        if (head == null) return false;
        if (head.idNumber.equals(id)) { head = head.next; return true; }
        Student cur = head;
        while (cur.next != null) {
            if (cur.next.idNumber.equals(id)) { cur.next = cur.next.next; return true; }
            cur = cur.next;
        }
        return false;
    }

    public String listStudents() {
        if (head == null) return "[Sin alumnos]";
        StringBuilder sb = new StringBuilder();
        for (Student c = head; c != null; c = c.next) sb.append(c).append("\n");
        return sb.toString();
    }
}

public class Taller1 {
    public static void main(String[] args) {
        CourseClass prog = new CourseClass("C001", "Programación I", 3);

        prog.addStudent(new Student("Ana","Gómez","1001",1,"Sistemas"));
        prog.addStudent(new Student("Juan","Álvarez","1002",2,"Sistemas"));
        prog.addStudent(new Student("Luis","Gómez","1003",1,"Sistemas"));

        System.out.println("Alumnos iniciales:\n" + prog.listStudents());

        System.out.println("Insertar duplicado 1002 -> " + prog.addStudent(new Student("Pedro","Ruiz","1002",3,"Sistemas")));

        prog.updateStudent("1003", null, "Arias", null, null);
        System.out.println("Después de actualizar 1003:\n" + prog.listStudents());

        prog.removeStudent("1001");
        System.out.println("Después de eliminar 1001:\n" + prog.listStudents());
    }
}
