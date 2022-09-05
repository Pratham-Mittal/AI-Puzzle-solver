package twenty48;


public class Arrays {
    private static int zero = 0;
    private int ze = 0;
    private int one  =1;
    public static int[][] dup(int[][] arr) {
        int[][] ret=new int[arr.length][arr[zero].length];
        for(int i=zero;i<arr.length;i++){
            for(int j=zero;j<arr[zero].length;j++){
                ret[i][j]=arr[i][j];
            }
        }
        return ret;
    }
    public static void killdup(int[][] arr, int[][] ret) {
        for(int i=zero;i<arr.length;i++){
            for(int j=zero;j<arr[zero].length;j++){
                ret[i][j]=arr[i][j];
            }
        }
    }
}
