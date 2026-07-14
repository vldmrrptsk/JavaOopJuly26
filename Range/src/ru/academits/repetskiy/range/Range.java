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

    public double getInterval() {
        return Math.abs(from - to);
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public boolean compareRangeBoundaries(Range range) {
        return this.from > range.to || this.to < range.from;
    }

    public String[] getIntersection(Range range) {
        if (compareRangeBoundaries(range)) {
            return null;
        }

        double leftBoarderInterval = Math.max(this.from, range.from);
        double rightBoarderInterval = Math.min(this.to, range.to);

        return new String[]{leftBoarderInterval + "," + rightBoarderInterval};
    }

    public String[] getUnion(Range range) {
        if (compareRangeBoundaries(range)) {
            return new String[]{this.from + "," + this.to, range.from + "," + range.to};
        }

        double leftBoarderInterval = Math.min(this.from, range.from);
        double rightBoarderInterval = Math.max(this.to, range.to);

        return new String[]{leftBoarderInterval + "," + rightBoarderInterval};
    }

    public String[] getAsymmetricDifference(Range range) {
        if (compareRangeBoundaries(range)) {
            return null;
        } else if (this.from < range.from) {
            if (this.to < range.to) {
                return new String[]{this.from + " " + range.from};
            }

            return new String[]{this.from + "," + range.from, range.to + "," + this.to};
        } else if (this.from > range.from) {
            if (this.to > range.to) {
                return new String[]{range.from + " " + this.from};
            }
        }
        return new String[]{range.from + "," + this.from, this.to + "," + range.to};
    }
}
