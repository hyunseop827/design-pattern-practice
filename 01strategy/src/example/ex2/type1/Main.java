package example.ex2.type1;

import java.util.*;
import java.util.stream.Collectors;

enum GradeLetter {A, B, C, D, F}

final class Key {
    final String studentId;
    final String subjectId;

    Key(String studentId, String subjectId) {
        this.studentId = Objects.requireNonNull(studentId);
        this.subjectId = Objects.requireNonNull(subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return Objects.equals(studentId, key.studentId)
                && Objects.equals(subjectId, key.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subjectId);
    }
}

class GradeRecord {
    private static final Map<Key, GradeRecord> store = new HashMap<>();

    private final String studentId;
    private final String subjectId;
    private double mid;
    private double fin;
    private double assignment;

    private GradeRecord(String studentId, String subjectId,
                        double mid, double fin, double assignment) {
        this.studentId = Objects.requireNonNull(studentId);
        this.subjectId = Objects.requireNonNull(subjectId);
        setScores(mid, fin, assignment);
    }

    static GradeRecord create(String studentId, String subjectId,
                              double mid, double fin, double assignment) {
        return new GradeRecord(studentId, subjectId, mid, fin, assignment);
    }

    public static Optional<GradeRecord> load(String studentId, String subjectId) {
        return Optional.ofNullable(store.get(new Key(studentId, subjectId)));
    }

    public static List<GradeRecord> loadByStudent(String studentId) {
        return store.entrySet().stream()
                .filter(e -> e.getKey().studentId.equals(studentId))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public static List<GradeRecord> loadAll() {
        return new ArrayList<>(store.values());
    }

    private static void validate0to100(double v, String name) {
        if (v < 0 || v > 100) {
            throw new IllegalArgumentException(name + " must be between 0 and 100");
        }
    }

    public GradeRecord save() {
        store.put(new Key(studentId, subjectId), this);
        return this;
    }

    public double score() {
        return 0.35 * mid + 0.35 * fin + 0.30 * assignment;
    }

    public GradeLetter grade() {
        double s = score();
        if (s >= 80) return GradeLetter.A;
        if (s >= 70) return GradeLetter.B;
        if (s >= 60) return GradeLetter.C;
        if (s >= 50) return GradeLetter.D;
        return GradeLetter.F;
    }

    public void updateScores(double mid, double fin, double assignment) {
        setScores(mid, fin, assignment);
    }

    private void setScores(double mid, double fin, double assignment) {
        validate0to100(mid, "Midterm score");
        validate0to100(fin, "Final score");
        validate0to100(assignment, "Assignment score");
        this.mid = mid;
        this.fin = fin;
        this.assignment = assignment;
    }

    @Override
    public String toString() {
        return String.format(
                "student=%s, subject=%s, mid=%.1f, fin=%.1f, assignment=%.1f, score=%.1f, grade=%s",
                studentId, subjectId, mid, fin, assignment, score(), grade()
        );
    }
}

public class Main {
    public static void main(String[] args) {
        GradeRecord.create("S001", "CS101", 78, 82, 90).save();
        GradeRecord.create("S001", "CS102", 65, 60, 70).save();
        GradeRecord.create("S002", "CS101", 40, 55, 45).save();

        System.out.println("Single record load");
        GradeRecord.load("S001", "CS101").ifPresent(System.out::println);

        System.out.println("\nAll records for S001");
        for (GradeRecord r : GradeRecord.loadByStudent("S001")) {
            System.out.println(r);
        }

        System.out.println("\nAll records");
        for (GradeRecord r : GradeRecord.loadAll()) {
            System.out.println(r);
        }

        System.out.println("\nRe-evaluation after score update");
        GradeRecord.load("S002", "CS101").ifPresent(r -> {
            r.updateScores(55, 58, 70);
            System.out.println(r);
        });
    }
}
