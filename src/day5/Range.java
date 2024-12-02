package day5;

public class Range {
    private Long start;
    private Long end;

    public Range(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
    public Long getStart() {
        return start;
    }

    public Long getEnd() {
        return end;
    }

    public Range intersect(Range o) {
        return new Range(Math.max(start, o.start), Math.min(this.end, o.end));
    }
}
