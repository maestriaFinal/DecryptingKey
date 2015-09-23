/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruta;

import ejemplo.*;
import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;

public class brute {

    private String hash;
    private String result;
    private ArrayList<Character> allowedChars = new ArrayList<Character>();
    private int maxLength = 10;
    private int currentLength = 0;
    private boolean found = false;
    private final long startTime = System.currentTimeMillis();
    private long endTime;

    public brute(String password) {
        this.hash = password;

        // a-z A-Z 0-9 punctuations!
        for (int i = 33; i <= 126; i++) {
            allowedChars.add((char) i);
            System.out.println("haciendo alloweChar " + i);
        }

        // extended ascii
        for (int i = 128; i <= 255; i++) {
            allowedChars.add((char) i);
            System.out.println("haciendo alloweChar " + i);
        }

    }

    public brute setLength(int length) {
        this.maxLength = length;

        return this;
    }

    public brute calc() {
        for (int i = 0; i <= this.maxLength; i++) {
            if (this.force("", 0, i)) {
                break;
            }
            System.out.println("haciendo alloweChar " + i);
            this.currentLength++;
        }

        return this;
    }

    private boolean force(String str, int position, int length) {
        for (char chr : allowedChars) {
            System.out.println("char chr : allowedChars ");
            this.showProgress(str + Character.toString(chr));

            if (position < length - 1) {
                force(str + Character.toString(chr), position + 1, length);
            }

            if (this.checkHash(str + Character.toString(chr))) {
                this.found = true;
                this.result = str + Character.toString(chr);
                System.out.println("char chr : allowedChars ");
            }
        }

        // si queremos conservar recursos D:
        /*try {
         Thread.sleep(100);
         } catch (InterruptedException e)
         {

         }*/
        return this.found;
    }

    private void showProgress(String str) {
        String find_msg = String.format("buscando en %s caracteres (%s)", this.currentLength, str);
        System.out.print("\r" + find_msg);
    }

    private boolean checkHash(String str) {
        if (DigestUtils.md5Hex(str).equals(this.hash)) {
            endTime = System.currentTimeMillis();
            return true;
        }

        return false;
    }

    public void show() {
        System.out.println(new Object() {
            @Override
            public String toString() {
                if (found) {
                    return String.format(
                            "\npassword encontrado!: '%s'\ntiempo de busqueda: %d ms\n",
                            result, (endTime - startTime));
                }

                return "\rnot found";
            }
        });
    }
}
