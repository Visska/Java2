package java_2_homework_2;


public class ArrayTest {

    private static final int MAX_LENGTH_ARRAY = 4;

    public static void main(String[] args) {
        String[][] myArray = {{"0", "2", "8", "5"}, {"3", "7", "o", "5"}, {"0", "1", "2", "3"}, {"3", "0", "0", "1"}};
        try {
            int sumArray = sumValueArray(myArray);
            System.out.printf("Summery array: %s %n", sumArray);
        } catch (MyArraySizeException e) {
            System.out.println(e.toString());
        } catch (MyArrayDataException e) {
            System.out.println("Invalid array element");
        }

    }


    private static void checkSizeArray(String[][] arrNumb) {

        if (arrNumb.length != MAX_LENGTH_ARRAY) {
            throw new MyArraySizeException("Incorrect array length.");
        }

        for (String[] row : arrNumb) {
            if (row.length != MAX_LENGTH_ARRAY) {
                throw new MyArraySizeException("Incorrect array length.");
            }
        }

    }


    private static int sumValueArray(String[][] arrNumb) {

        checkSizeArray(arrNumb);
        int sumArrayValue = 0;


        try {
            for (String[] strings : arrNumb) {
                for (String value : strings) {
                    sumArrayValue += Integer.parseInt(value);
                }
            }
        } catch (NumberFormatException e) {
            throw new MyArrayDataException("Invalid array element.");
        }

        return sumArrayValue;
    }


}
