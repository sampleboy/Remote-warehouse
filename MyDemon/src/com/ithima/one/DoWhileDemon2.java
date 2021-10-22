package com.ithima.one;

import org.omg.Messaging.SyncScopeHelper;

public class DoWhileDemon2 {
    public static void main(String[] args) {
       /* int x =3;
        while(x<3) {
            System.out.println("爱生活，爱java");
            x++;
        }
        System.out.println("-------------------------");
        int y =3;
        do {
            System.out.println("爱生活，爱java");
            y++;
        }while (y < 3);
        */
        for (int x = 1; x <= 10; x++) {
            System.out.println("爱生活，爱java");
        }
        System.out.println("---------------------------");
        int y = 1;
        while (y <= 10) {
            System.out.println("爱生活，爱java");
            y++;
        }
        System.out.println("y:" + y);
    }
}
