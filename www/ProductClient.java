import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;
public class ProductClient {

    public ProductClient (String [] args) {
        try{
            int[][] mat1;
            int[][] mat2;
            int lig,col,lig2,col2;
            Scanner sc=new Scanner(System.in);
            System.out.print("Please enter the number of rows for matrix mat1: ");
            lig=sc.nextInt();
            System.out.print("Please enter the number of columns for matrix mat2: ");
            col=sc.nextInt();
            System.out.print("Please enter the number of rows for matrix mat2: ");
            lig2=sc.nextInt();
            System.out.print("Please enter the number of columns for matrix mat2: ");
            col2=sc.nextInt();

            System.out.println("entrer les valeurs de la premiere matrice");
            mat1 = Matrix(lig,col);
            System.out.println("entrer les valeurs de la deuxieme matrice");
            mat2 = Matrix(lig2,col2);



            if(System.getSecurityManager() == null)
                System.setSecurityManager(new RMISecurityManager());
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            Factory fabrique = (Factory) reg.lookup("Factory");


            IProduct ProductObj1;
            ProductObj1= (IProduct)fabrique.newProduct();
            IProduct ProductObj2;
            ProductObj2= (IProduct)fabrique.newProduct();
            IProduct ProductObj3;
            ProductObj3= (IProduct)fabrique.newProduct();
            IProduct ProductObj4;
            ProductObj4= (IProduct)fabrique.newProduct();

            int[][] result1 = ProductObj1.ProductMatrix(mat1,mat2,0,lig/2, 0 , col2/2,col);
            int[][] result2 = ProductObj2.ProductMatrix(mat1,mat2,0,lig/2,col2/2,col2,col);
            int[][] result3 = ProductObj3.ProductMatrix(mat1,mat2,lig/2,lig,0,col2/2,col);
            int[][] result4 = ProductObj4.ProductMatrix(mat1,mat2,lig/2,lig,col2/2,col2,col);
            System.out.println("Le produit des deux matrices est :\n");
            Print2D(assembler(result1,result2,result3,result4,lig,col2),lig,col2);

        }
        catch (Exception e) {
            System.out.println ("Erreur d'acces a l'objet distant.");
            System.out.println (e.toString());
        }
    }
    public int[][] Matrix(int rows, int columns) {
        Scanner sc=new Scanner(System.in);
        int[][] m;
        m = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(" [" + (i+1) + "," + (j+1) + "]:") ;
                m[i][j] = sc.nextInt();
            }
        }
        return m;

    }


    public int[][] assembler( int[][] a ,int[][] b,int[][] c, int[][] d,int lig,int col) {
        int[][] result = new int[lig][col];

        for (int i = 0; i < lig; i++) {
            for (int j = 0; j < col; j++) {
                if (i < lig / 2) {
                    if (j < col / 2) {
                        result[i][j] = a[i][j];
                    } else {
                        result[i][j] = b[i][j];
                    }
                } else {
                    if (j < col / 2) {
                        result[i][j] = c[i][j];
                    } else {
                        result[i][j] = d[i][j];
                    }
                }
            }
        }
        return result;
    }
    public void Print2D(int[][] m, int lig,int col2) {
        for (int i=0;i<lig;i++) {
            for (int j=0;j<col2;j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}