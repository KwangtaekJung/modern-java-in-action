package ch2.sub2.applefilter;

public class AppleHeavyWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWight() > 150;
    }
}
