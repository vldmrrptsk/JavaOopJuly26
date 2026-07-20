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
        return from - to;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    private boolean isIntersectionRanges(Range range) {
        return this.from > range.to || this.to < range.from;
    }

    public Range getIntersection(Range range) {
        if (isIntersectionRanges(range)) {
            return null;
        }

        double rangeFrom = Math.max(this.from, range.from);
        double rangeTo = Math.min(this.to, range.to);

        return new Range(rangeFrom, rangeTo);
    }

    public Range[] getUnion(Range range) {
        if (isIntersectionRanges(range)) {
            return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
        }

        double rangeFrom = Math.min(this.from, range.from);
        double rangeTo = Math.max(this.to, range.to);

        return new Range[]{new Range(rangeFrom, rangeTo)};
    }

    public Range[] getDifference(Range range) {
        if (isIntersectionRanges(range)) {
            return null;
        }

        if (this.from < range.from) {
            if (this.to < range.to) {
                return new Range[]{new Range(this.from, range.from)};
            }

            return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
        }

        if (this.from > range.from) {
            if (this.to > range.to) {
                return new Range[]{new Range(range.from, this.from)};
            }
        }

        return new Range[]{new Range(range.from, this.from), new Range(this.to, range.to)};
    }

    @Override
    public String toString() {
        return "Интервал: [" +
                from + ", "
                + to +
                "]";
    }
}
