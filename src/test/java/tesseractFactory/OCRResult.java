package tesseractFactory;

import io.cucumber.java.lt.Bet;

public class OCRResult {
    private String MinorResult = null;
    private String MajorResult = null;
    private String BetAmount = null;

    public OCRResult(String minorPar, String majorPar, String betAmountPar) {
        MinorResult = minorPar;
        MajorResult = majorPar;
        BetAmount = betAmountPar;
    }

    public void SetMinorResult(String minorParameter) {
        MinorResult = minorParameter;
    }

    public String GetMinorResult() {
        return MinorResult;
    }

    public void SetMajorResult(String majorParameter) {
        MajorResult = majorParameter;
    }

    public String GetMajorResult() {
        return MajorResult;
    }

    public void SetBetAmountResult(String betAmountParameter) {
        BetAmount = betAmountParameter;
    }

    public String GetBetAmountResult() {
        return BetAmount;
    }

    // Verifying the results based on the bet amount and the jackpot amounts.
    // This is where we determine pass and fails
    public boolean VerifyResults() {
        boolean result = false;

        switch (BetAmount) {
            case "$0.50":
                if (MinorResult.equals("$10.00") && MajorResult.equals("$40.00")) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "$1.00":
                if (MinorResult.equals("$20.00") && MajorResult.equals("$80.00")) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "$2.50":
                if (MinorResult.equals("$50.00") && MajorResult.equals("$200.00")) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "$5.00":
                if (MinorResult.equals("$100.00") && MajorResult.equals("$400.00")) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "$10.00":
                if (MinorResult.equals("$200.00") && MajorResult.equals("$800.00")) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "$25.00":
                if (MinorResult.equals("$500.00") && MajorResult.equals("$2,000.00")) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "$50.00":
                if (MinorResult.equals("$1,000.00") && MajorResult.equals("$4,000.00")) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "$100.00":
                if (MinorResult.equals("$2,000.00") && MajorResult.equals("$8,000.00")) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            default:
                System.out.println("cannot verify bet amount");
                result = false;
                break;

        }
        return result;
    }

}
