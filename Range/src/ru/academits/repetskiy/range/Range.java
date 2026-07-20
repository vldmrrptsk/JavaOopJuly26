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

    private boolean hasIntersection(Range range) {
        return from > range.to || to < range.from;
    }

    public Range getIntersection(Range range) {
        if (hasIntersection(range)) {
            return null;
        }

        double rangeFrom = Math.max(from, range.from);
        double rangeTo = Math.min(to, range.to);

        if (rangeFrom == rangeTo) {
            return null;
        }

        return new Range(rangeFrom, rangeTo);
    }

    public Range[] getUnion(Range range) {
        if (hasIntersection(range)) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        double rangeFrom = Math.min(this.from, range.from);
        double rangeTo = Math.max(this.to, range.to);

        return new Range[]{new Range(rangeFrom, rangeTo)};
    }

    public Range[] getDifference(Range range) {
        if (hasIntersection(range)) {
            return null;
        }

        if(from == range.to || range.from == to){
            return null;
        }

        if (from < range.from) {
            if (to < range.to) {
                return new Range[]{new Range(from, range.from)};
            }

            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from > range.from && to > range.to ) {
            return new Range[]{new Range(range.from, from)};
        }

        return new Range[]{new Range(range.from, from), new Range(to, range.to)};
    }

    @Override
    public String toString() {
        return "Интервал: [" +
                from + ", "
                + to +
                "]";
    }
}
