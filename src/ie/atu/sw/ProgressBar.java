package ie.atu.sw;

public class ProgressBar {
    public ProgressBar() {
    }

    // used to make sure that progress strings are only shown once
    private int progressMarker = 0;

    public void showProgress(double decimalPercentage) {

        if ((decimalPercentage * 100 < 10) && (progressMarker == 0)) {
            System.out.println(Colours.ANSI_RED + "Progress: 0%");
            this.progressMarker = progressMarker + 1;

        } else if (decimalPercentage * 100 >= 10 && progressMarker == 1) {
            System.out.println("Progress: 10%");
            this.progressMarker = progressMarker + 1;


        } else if (decimalPercentage * 100 >= 20 && progressMarker == 2) {
            System.out.println("Progress: 20%");
            this.progressMarker = progressMarker + 1;


        } else if (decimalPercentage * 100 >= 30 && progressMarker == 3) {
            System.out.println(Colours.ANSI_YELLOW + "Progress: 30%");
            this.progressMarker = progressMarker + 1;

        } else if (decimalPercentage * 100 >= 40 && progressMarker == 4) {
            System.out.println("Progress: 40%");
            this.progressMarker = progressMarker + 1;


        } else if (decimalPercentage * 100 >= 50 && progressMarker == 5) {
            System.out.println("Progress: 50%");
            this.progressMarker = progressMarker + 1;


        } else if (decimalPercentage * 100 >= 60 && progressMarker == 6) {
            System.out.println(Colours.ANSI_BLUE + "Progress: 60%");
            this.progressMarker = progressMarker + 1;


        } else if (decimalPercentage * 100 >= 70 && progressMarker == 7) {
            System.out.println("Progress: 70%");
            this.progressMarker = progressMarker + 1;


        } else if (decimalPercentage * 100 >= 80 && progressMarker == 8) {
            System.out.println(Colours.ANSI_CYAN + "Progress: 80%");
            this.progressMarker = progressMarker + 1;


        } else if (decimalPercentage * 100 >= 90 && progressMarker == 9) {
            System.out.println("Progress: 90%");
            this.progressMarker = progressMarker + 1;


        } else if (decimalPercentage * 100 >= 100 && progressMarker == 10) {
            System.out.println(Colours.ANSI_GREEN + "Progress: 100% \n" + Colours.ANSI_RESET);
            this.progressMarker = progressMarker + 1;

        }

    }
}
