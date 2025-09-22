// Clase Student (los datos del alumno)
class Student {
    String firstName;
    String lastName;
    String idNumber;
    String program;
    int semester;

    public Student(String fn, String ln, String id, int sem, String prog) {
        this.firstName = fn;
        this.lastName = ln;
        this.idNumber = id;
        this.semester = sem;
        this.program = prog;
    }

    @Override
    public String toString() {
        return firstName + " | " +
               lastName + " | " +
               idNumber + " | " +
               semester + " | " +
               program;
    }
}

// Nodo que envuelve al Student
class Node {
    Student data;
    Node next;

    Node(Student data) {
        this.data = data;
        this.next = null;
    }
}

// Lista enlazada de estudiantes
class LinkedList {
    private Node head;

    // Insertar al inicio
    public void insertAtHead(Student s) {
        Node newNode = new Node(s);
        newNode.next = head;
        head = newNode;
        System.out.println("Estudiante " + s.firstName + " añadido al inicio.");
    }

    // Buscar por ID
    public Student findStudent(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.idNumber.equals(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // Eliminar estudiante por ID
    public boolean removeStudent(String id) {
        if (head == null) return false;

        if (head.data.idNumber.equals(id)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.idNumber.equals(id)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Imprimir lista
    public void printList() {
        if (head == null) {
            System.out.println("[Sin alumnos]");
            return;
        }
        Node current = head;
        int i = 1;
        while (current != null) {
            System.out.println(i + ". " + current.data);
            current = current.next;
            i++;
        }
    }
}

// Clase principal
public class Lista_enlazada {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.insertAtHead(new Student("Carlos", "Martínez", "20230001", 2, "Ingeniería Industrial"));
        list.insertAtHead(new Student("Lucía", "Torres", "20230002", 1, "Ingeniería Civil"));
        list.insertAtHead(new Student("Andrés", "Gómez", "20230003", 3, "Ingeniería Mecánica"));

        System.out.println("\n--- Lista de estudiantes ---");
        list.printList();

        System.out.println("\n--- Buscar estudiante con ID 20230002 ---");
        Student s = list.findStudent("20230002");
        if (s != null) System.out.println("Encontrado: " + s);
        else System.out.println("No encontrado");

        System.out.println("\n--- Eliminar estudiante con ID 20230001 ---");
        boolean eliminado = list.removeStudent("20230001");
        System.out.println("Eliminado: " + eliminado);

        System.out.println("\n--- Lista final ---");
        list.printList();
    }
}
