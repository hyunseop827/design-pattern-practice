package example.ex2.type3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

enum GradeLetter {A, B, C, D, F}

interface ScoringStrategy {
    double score(GradeRecord r);
}

interface GradingStrategy {
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
        return studentId.equals(key.studentId) && subjectId.equals(key.subjectId);
    }

    @Override
    public int hashCode() {
        int r = studentId.hashCode();
        r = 31 * r + subjectId.hashCode();
        return r;
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

final class WeightedAverageScoring implements ScoringStrategy {
    private final double wMid, wFin, wAsg;

    WeightedAverageScoring(double wMid, double wFin, double wAsg) {
        if (wMid < 0 || wFin < 0 || wAsg < 0) throw new IllegalArgumentException();
        if (Math.abs((wMid + wFin + wAsg) - 1.0) > 1e-9) throw new IllegalArgumentException();
        this.wMid = wMid;
        this.wFin = wFin;
        this.wAsg = wAsg;
    }

    public double score(GradeRecord r) {
        return wMid * r.mid() + wFin * r.fin() + wAsg * r.assignment();
    }
}

final class BestTwoAverageScoring implements ScoringStrategy {
    public double score(GradeRecord r) {
        double[] arr = {r.mid(), r.fin(), r.assignment()};
        Arrays.sort(arr);
        return (arr[1] + arr[2]) / 2.0;
    }
}

final class FixedCutGrading implements GradingStrategy {
    private final double aCut, bCut, cCut, dCut;

    FixedCutGrading(double aCut, double bCut, double cCut, double dCut) {
        this.aCut = aCut;
        this.bCut = bCut;
        this.cCut = cCut;
        this.dCut = dCut;
    }

    public GradeLetter toLetter(double s) {
        if (s >= aCut) return GradeLetter.A;
        if (s >= bCut) return GradeLetter.B;
        if (s >= cCut) return GradeLetter.C;
        if (s >= dCut) return GradeLetter.D;
        return GradeLetter.F;
    }
}

final class TextGradeFormatter {
    private final ScoringStrategy scoring;
    private final GradingStrategy grading;

    TextGradeFormatter(ScoringStrategy scoring, GradingStrategy grading) {
        this.scoring = Objects.requireNonNull(scoring);
        this.grading = Objects.requireNonNull(grading);
    }

    public String format(GradeRecord r) {
        double score = scoring.score(r);
        GradeLetter letter = grading.toLetter(score);
        return String.format(
                "student=%s, subject=%s, mid=%.1f, fin=%.1f, assignment=%.1f, score=%.1f, grade=%s",
                r.studentId(), r.subjectId(), r.mid(), r.fin(), r.assignment(), score, letter
        );
    }
}

final class GradeService {
    private final InMemoryGradeRepository repo;
    private final ScoringStrategy scoring;
    private final GradingStrategy grading;
    private final TextGradeFormatter formatter;

    GradeService(InMemoryGradeRepository repo, ScoringStrategy scoring, GradingStrategy grading) {
        this.repo = Objects.requireNonNull(repo);
        this.scoring = Objects.requireNonNull(scoring);
        this.grading = Objects.requireNonNull(grading);
        this.formatter = new TextGradeFormatter(scoring, grading);
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
        return repo.find(sid, sub).map(r -> grading.toLetter(scoring.score(r)));
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
        ScoringStrategy scoring = new WeightedAverageScoring(0.35, 0.35, 0.30);
        GradingStrategy grading = new FixedCutGrading(80, 70, 60, 50);

        InMemoryGradeRepository repo = new InMemoryGradeRepository();
        GradeService svc = new GradeService(repo, scoring, grading);

        svc.record("S-001", "CS101", 82, 78, 90);
        svc.record("S-001", "CS102", 65, 70, 60);
        svc.record("S-002", "CS101", 55, 58, 62);

        svc.printAll();

        svc.updateScores("S-001", "CS102", 75, 80, 70);
        svc.printOne("S-001", "CS102");

        System.out.println(svc.gradeOf("S-002", "CS101").orElse(null));

        ScoringStrategy altScoring = new BestTwoAverageScoring();
        GradeService svc2 = new GradeService(repo, altScoring, grading);
        System.out.println("--- with BestTwoAverageScoring ---");
        svc2.printAll();
    }
}

