package ru.academits.repetskiy.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    private boolean isDisjointWith(Range range) {
        return from >= range.to || to <= range.from;
    }

    private boolean isBoardersEqual(Range range){
        final double EPSILON = 1e-10;
        return Math.abs(from - range.from) < EPSILON && Math.abs(to - range.to) < EPSILON;
    }

    public Range getIntersection(Range range) {
        if (isDisjointWith(range)) {
            return null;
        }

        double rangeFrom = Math.max(from, range.from);
        double rangeTo = Math.min(to, range.to);

        return new Range(rangeFrom, rangeTo);
    }

    public Range[] getUnion(Range range) {
        if (isDisjointWith(range)) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        double rangeFrom = Math.min(from, range.from);
        double rangeTo = Math.max(to, range.to);

        return new Range[]{new Range(rangeFrom, rangeTo)};
    }

    public Range[] getDifference(Range range) {
        if (isDisjointWith(range) || isBoardersEqual(range)) {
            return new Range[]{};
        }

        if (from < range.from) {
            if (to <= range.to) {
                return new Range[]{new Range(from, range.from)};
            }

            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from >= range.from && to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{new Range(range.from, from), new Range(to, range.to)};
    }

    @Override
    public String toString() {
        return "Интервал: (" +
                from + ", "
                + to +
                ")";
    }
}
