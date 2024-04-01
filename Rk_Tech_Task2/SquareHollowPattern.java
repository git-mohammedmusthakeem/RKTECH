public class SquareHollowPattern {
    public static void main(String[] args) {
        int size = 5;
        for (int i = 0; i < size; i++) {
          
            for (int j = 0; j < size; j++) {
            
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        for (int i = 1; i <= size; i++) {
            
            for (int j = 1; j <= size; j++) {
                if (i == 1 || i == size || j == 1 || j == size) {
                    System.out.print(i+" "); 
                } else {
                    System.out.print("  "); 
                }
            }
            System.out.println(); 
        }

        for (int i = 1; i <= size; i++) {
                    for (int j = 1; j <= size; j++) {
                        if (i == 1 || i == size || j == 1 || j == size) {
                            System.out.print(j+" ");
                        } else {
                            System.out.print("  ");
                        }
                    }
                    System.out.println();
                }
            }
        }
        
