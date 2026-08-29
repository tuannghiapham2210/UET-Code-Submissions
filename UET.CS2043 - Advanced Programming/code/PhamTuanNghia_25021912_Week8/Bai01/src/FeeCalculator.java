//Problem A: uncommunicative naming and unused variable

//original code: 
// public double calculateFee(String t, int h, double r, boolean m) {
//     double f = h * r;
//     if (m) f = f * 0.9;
//     return f;
// }

//refactored: renamed variables to reveal intenetion and removed useless parameter t
public class FeeCalculator {
    public double calculateFee(int hours, double rate, boolean isMember){
        double fee = hours * rate;
        if (isMember) {
            fee = fee * 0.9; //members can get a 10% discount
        }

        return fee;
    }
}

