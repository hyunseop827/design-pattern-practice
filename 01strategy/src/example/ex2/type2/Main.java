package example.ex2.type2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

enum GradeLetter {A, B, C, D, F}

interface ScoringPolicy {
    double score(double mid, double fin, double asg);
}

interface GradeScale {
    GradeLetter toLetter(double score);
}

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
        return Objects.equals(studentId, key.studentId) && Objects.equals(subjectId, key.subjectId);
    }

    @Override
    public int hashCode() {
        return 31 * studentId.hashCode() + subjectId.hashCode();
    }
}

final class DefaultScoring implements ScoringPolicy {
    private final double wMid, wFin, wAsg;

    DefaultScoring(double wMid, double wFin, double wAsg) {
        if (wMid < 0 || wFin < 0 || wAsg < 0) throw new IllegalArgumentException();
        if (Math.abs((wMid + wFin + wAsg) - 1.0) > 1e-9) throw new IllegalArgumentException();
        this.wMid = wMid;
        this.wFin = wFin;
        this.wAsg = wAsg;
    }

    public double score(double mid, double fin, double asg) {
        return wMid * mid + wFin * fin + wAsg * asg;
    }
}

final class DefaultScale implements GradeScale {
    public GradeLetter toLetter(double s) {
        if (s >= 80) return GradeLetter.A;
        if (s >= 70) return GradeLetter.B;
        if (s >= 60) return GradeLetter.C;
        if (s >= 50) return GradeLetter.D;
        return GradeLetter.F;
    }
}

final class GradeRecord {
    private final String studentId;
    private final String subjectId;
    private final double mid;
    private final double fin;
    private final double assignment;

    private GradeRecord(String sid, String sub, double mid, double fin, double asg) {
        this.studentId = Objects.requireNonNull(sid);
        this.subjectId = Objects.requireNonNull(sub);
        validate0to100(mid);
        validate0to100(fin);
        validate0to100(asg);
        this.mid = mid;
        this.fin = fin;
        this.assignment = asg;
    }

    static GradeRecord of(String sid, String sub, double mid, double fin, double asg) {
        return new GradeRecord(sid, sub, mid, fin, asg);
    }

    private static void validate0to100(double v) {
        if (v < 0 || v > 100) throw new IllegalArgumentException("0..100");
    }

    GradeRecord withScores(double mid, double fin, double asg) {
        return new GradeRecord(studentId, subjectId, mid, fin, asg);
    }

    double score(ScoringPolicy policy) {
        return policy.score(mid, fin, assignment);
    }

    GradeLetter grade(ScoringPolicy policy, GradeScale scale) {
        return scale.toLetter(score(policy));
    }

    String studentId() {
        return studentId;
    }

    String subjectId() {
        return subjectId;
    }

    double mid() {
        return mid;
    }

    double fin() {
        return fin;
    }

    double assignment() {
        return assignment;
    }
}

final class InMemoryGradeRepository {
    private final Map<Key, GradeRecord> store = new ConcurrentHashMap<>();

    public void save(GradeRecord record) {
        store.put(new Key(record.studentId(), record.subjectId()), record);
    }

    public Optional<GradeRecord> find(String studentId, String subjectId) {
        return Optional.ofNullable(store.get(new Key(studentId, subjectId)));
    }

    public List<GradeRecord> findByStudent(String studentId) {
        return store.entrySet().stream()
                .filter(e -> e.getKey().studentId.equals(studentId))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<GradeRecord> findAll() {
        return new ArrayList<>(store.values());
    }
}

final class TextGradeFormatter {
    private final ScoringPolicy scoring;
    private final GradeScale scale;

    TextGradeFormatter(ScoringPolicy scoring, GradeScale scale) {
        this.scoring = Objects.requireNonNull(scoring);
        this.scale = Objects.requireNonNull(scale);
    }

    public String format(GradeRecord r) {
        double score = r.score(scoring);
        GradeLetter letter = scale.toLetter(score);
        return String.format(
                "student=%s, subject=%s, mid=%.1f, fin=%.1f, assignment=%.1f, score=%.1f, grade=%s",
                r.studentId(), r.subjectId(), r.mid(), r.fin(), r.assignment(), score, letter
        );
    }
}

final class GradeService {
    private final InMemoryGradeRepository repo;
    private final TextGradeFormatter formatter;
    private final ScoringPolicy scoring;
    private final GradeScale scale;

    GradeService(InMemoryGradeRepository repo, TextGradeFormatter formatter,
                 ScoringPolicy scoring, GradeScale scale) {
        this.repo = Objects.requireNonNull(repo);
        this.formatter = Objects.requireNonNull(formatter);
        this.scoring = Objects.requireNonNull(scoring);
        this.scale = Objects.requireNonNull(scale);
    }

    void record(String sid, String sub, double mid, double fin, double asg) {
        repo.save(GradeRecord.of(sid, sub, mid, fin, asg));
    }

    void updateScores(String sid, String sub, double mid, double fin, double asg) {
        GradeRecord updated = repo.find(sid, sub)
                .map(r -> r.withScores(mid, fin, asg))
                .orElseThrow(() -> new NoSuchElementException("not found"));
        repo.save(updated);
    }

    Optional<GradeLetter> gradeOf(String sid, String sub) {
        return repo.find(sid, sub).map(r -> r.grade(scoring, scale));
    }

    void printOne(String sid, String sub) {
        repo.find(sid, sub).ifPresent(r -> System.out.println(formatter.format(r)));
    }

    void printByStudent(String sid) {
        repo.findByStudent(sid).forEach(r -> System.out.println(formatter.format(r)));
    }

    void printAll() {
        repo.findAll().forEach(r -> System.out.println(formatter.format(r)));
    }
}

public class Main {
    public static void main(String[] args) {
        ScoringPolicy scoring = new DefaultScoring(0.35, 0.35, 0.30);
        GradeScale scale = new DefaultScale();
        InMemoryGradeRepository repo = new InMemoryGradeRepository();
        TextGradeFormatter formatter = new TextGradeFormatter(scoring, scale);
        GradeService svc = new GradeService(repo, formatter, scoring, scale);

        svc.record("S-001", "CS101", 82, 78, 90);
        svc.record("S-001", "CS102", 65, 70, 60);
        svc.record("S-002", "CS101", 55, 58, 62);

        svc.printAll();
        svc.updateScores("S-001", "CS102", 75, 80, 70);
        svc.printOne("S-001", "CS102");
        System.out.println(svc.gradeOf("S-002", "CS101").orElse(null));
    }
}
